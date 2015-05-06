package pl.morcinek.podziemnaplayer.general.dagger.configuration;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.morcinek.podziemnaplayer.data.ResponseProcessor;
import pl.morcinek.podziemnaplayer.data.network.ApiService;
import pl.morcinek.podziemnaplayer.data.network.ApiServiceImpl;
import pl.morcinek.podziemnaplayer.data.network.NetworkRequester;
import pl.morcinek.podziemnaplayer.files.DownloadHandler;

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

    @Provides
    @Singleton
    DownloadManager provideDownloadManager(Context context) {
        return (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    @Provides
    @Singleton
    DownloadHandler provideDownloadHandler(Context context, DownloadManager downloadManager) {
        return new DownloadHandler(context, downloadManager);
    }
}
