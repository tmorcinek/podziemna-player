package pl.morcinek.podziemnaplayer.general.dagger.configuration;

import dagger.Module;
import dagger.Provides;
import pl.morcinek.podziemnaplayer.general.controllers.RefreshProgressController;
import pl.morcinek.podziemnaplayer.general.dagger.components.DaggerActivity;
import pl.morcinek.podziemnaplayer.general.handlers.RetryErrorHandler;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
@Module(
        injects = {
        },
        addsTo = ApplicationModule.class,
        library = true
)
public class ActivityModule {

    private final DaggerActivity showcaseActivity;

    public ActivityModule(DaggerActivity showcaseActivity) {
        this.showcaseActivity = showcaseActivity;
    }

    @Provides
    RetryErrorHandler provideRetryLayoutErrorHandler() {
        return new RetryErrorHandler(showcaseActivity);
    }

    @Provides
    RefreshProgressController provideRefreshProgressController() {
        return new RefreshProgressController(showcaseActivity);
    }
}
