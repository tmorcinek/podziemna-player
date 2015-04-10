package pl.morcinek.podziemnaplayer.general.controllers;

import android.support.v4.widget.SwipeRefreshLayout;

import pl.morcinek.podziemnaplayer.general.network.response.ProgressController;

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
public class RefreshProgressController implements ProgressController {

    private SwipeRefreshLayout swipeRefreshLayout;

    public RefreshProgressController(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    public void preExecute() {
        updateSwipeRefreshLayout(true);
    }

    @Override
    public void postExecuteWithSuccess(boolean success) {
        updateSwipeRefreshLayout(false);
    }

    private void updateSwipeRefreshLayout(final boolean started) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(started);
            }
        });
    }
}
