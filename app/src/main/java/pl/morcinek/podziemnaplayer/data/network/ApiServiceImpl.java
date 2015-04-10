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
        httpClient.newCall(createRequest()).enqueue(new com.squareup.okhttp.Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                callback.failure(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                List<Resource> resources = responseProcessor.retrieveDataFromStream(response.body().byteStream());
                callback.success(resources);
            }
        });
    }

    private Request createRequest() {
        return new Request.Builder().url(BuildConfig.API_ENDPOINT).build();
    }
}
