package pl.morcinek.podziemnaplayer.general.progress;

import android.view.View;

import pl.morcinek.podziemnaplayer.R;
import pl.morcinek.podziemnaplayer.general.network.response.ProgressController;


/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class ItemProgressBarController implements ProgressController {

    private final View progressBar;
    private final View indicator;

    public ItemProgressBarController(View view) {
        this.progressBar = view.findViewById(R.id.progress_bar);
        this.indicator = view.findViewById(R.id.download_button);
    }

    @Override
    public void preExecute() {
        progressBar.setVisibility(View.VISIBLE);
        indicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void postExecuteWithSuccess(boolean success) {
        progressBar.setVisibility(View.GONE);
        indicator.setVisibility(View.VISIBLE);
    }
}
