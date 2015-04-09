package pl.morcinek.podziemnaplayer.general.handlers;

import android.app.Activity;
import android.view.ViewGroup;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;

import pl.morcinek.podziemnaplayer.R;
import pl.morcinek.podziemnaplayer.general.network.error.ErrorHandler;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class RetryErrorHandler implements ErrorHandler {

    private final Activity activity;

    private ActionClickListener actionClickListener;

    private ViewGroup viewGroup;

    public RetryErrorHandler(Activity activity) {
        this.activity = activity;
    }

    public void registerAction(final Runnable retryAction) {
        actionClickListener = new ActionClickListener() {
            @Override
            public void onActionClicked(Snackbar snackbar) {
                retryAction.run();
            }
        };
    }

    public void registerViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public void handleError(Object networkError) {
        SnackbarManager.show(
                Snackbar.with(activity)
                        .text(getLocalizedMessage(networkError))
                        .actionLabel(R.string.retry)
                        .actionListener(actionClickListener)
                , viewGroup);
    }

    private String getLocalizedMessage(Object networkError) {
        return networkError.toString();
    }
}
