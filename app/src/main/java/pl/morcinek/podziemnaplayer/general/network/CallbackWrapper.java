package pl.morcinek.podziemnaplayer.general.network;


import pl.morcinek.podziemnaplayer.general.network.response.NetworkResponseListener;
import pl.morcinek.podziemnaplayer.general.network.response.ProgressController;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class CallbackWrapper<T> implements Callback<T> {

    private NetworkResponseListener<T> responseListener;

    private ProgressController[] progressControllers;

    public CallbackWrapper(NetworkResponseListener<T> responseListener, ProgressController... progressControllers) {
        this.responseListener = responseListener;
        this.progressControllers = progressControllers;
        preExecute();
    }

    public void cancel() {
        responseListener = null;
        postExecuteWithSuccess(false);
        progressControllers = new ProgressController[0];
    }

    private void preExecute() {
        for (ProgressController progressController : progressControllers) {
            progressController.preExecute();
        }
    }

    private void postExecuteWithSuccess(boolean success) {
        for (ProgressController progressController : progressControllers) {
            progressController.postExecuteWithSuccess(success);
        }
    }

    @Override
    public void success(T object) {
        if (responseListener != null) {
            postExecuteWithSuccess(true);
            responseListener.success(object);
        }
    }

    @Override
    public void failure(Exception error) {
        if (responseListener != null) {
            postExecuteWithSuccess(false);
            responseListener.failure(error);
        }
    }
}
