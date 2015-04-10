package pl.morcinek.podziemnaplayer.general.dagger.configuration;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.morcinek.podziemnaplayer.data.ResponseProcessor;
import pl.morcinek.podziemnaplayer.data.network.ApiService;
import pl.morcinek.podziemnaplayer.data.network.ApiServiceImpl;
import pl.morcinek.podziemnaplayer.data.network.NetworkRequester;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
@Module(library = true)
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ResponseProcessor provideResponseProcessor() {
        return new ResponseProcessor();
    }

    @Provides
    @Singleton
    ApiService provideApiService(ResponseProcessor responseProcessor) {
        return new ApiServiceImpl(responseProcessor);
    }

    @Provides
    @Singleton
    NetworkRequester provideNetworkRequester(ApiService apiService) {
        return new NetworkRequester(apiService);
    }
}
