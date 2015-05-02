package pl.morcinek.podziemnaplayer.data.network;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import pl.morcinek.podziemnaplayer.BuildConfig;
import pl.morcinek.podziemnaplayer.data.Resource;
import pl.morcinek.podziemnaplayer.data.ResponseProcessor;
import pl.morcinek.podziemnaplayer.general.network.Callback;

/**
 * Created by Tomasz Morcinek.
 * Copyright (c) 2015 SportingBet. All rights reserved.
 */
public class ApiServiceImpl implements ApiService {

    private ResponseProcessor responseProcessor;
    private OkHttpClient httpClient;

    public ApiServiceImpl(ResponseProcessor responseProcessor) {
        this.responseProcessor = responseProcessor;
        this.httpClient = new OkHttpClient();
    }

    @Override
    public void getResources(final Callback<List<Resource>> callback) {
        runInBackground(new Runnable() {
            public void run() {
                try {
                    Response response = httpClient.newCall(createRequest()).execute();
                    if (response.isSuccessful()) {
                        callback.success(processResponse(response));
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    callback.failure(e);
                }
            }
        });
    }

    private void runInBackground(Runnable runnable) {
        new Thread(runnable).start();
    }

    private List<Resource> processResponse(Response response) throws IOException {
        return responseProcessor.retrieveDataFromStream(response.body().byteStream());
    }

    private Request createRequest() {
        return new Request.Builder().url(BuildConfig.API_ENDPOINT).build();
    }
}
