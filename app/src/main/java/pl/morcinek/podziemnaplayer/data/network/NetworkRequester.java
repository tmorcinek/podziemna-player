package pl.morcinek.podziemnaplayer.data.network;

import java.util.List;

import pl.morcinek.podziemnaplayer.data.Resource;
import pl.morcinek.podziemnaplayer.general.network.CallbackWrapper;
import pl.morcinek.podziemnaplayer.general.network.response.NetworkResponseListener;
import pl.morcinek.podziemnaplayer.general.network.response.ProgressController;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class NetworkRequester {

    private final ApiService apiService;

    private NetworkResponseListener<List<Resource>> responseListener;

    private ProgressController[] progressControllers;
    private CallbackWrapper<List<Resource>> callbackWrapper;

    public NetworkRequester(ApiService apiService) {
        this.apiService = apiService;
    }

    public void initialize(NetworkResponseListener<List<Resource>> responseListener, ProgressController... progressControllers) {
        this.responseListener = responseListener;
        this.progressControllers = progressControllers;
    }

    public void requestResources() {
        prepareRequest();
        apiService.getResources(callbackWrapper);
    }

    private void prepareRequest() {
        callbackWrapper = new CallbackWrapper<>(this.responseListener, progressControllers);
    }

    public void cancelRequest() {
        callbackWrapper.cancel();
    }
}
