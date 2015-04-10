package pl.morcinek.podziemnaplayer.general.dagger.configuration;

import dagger.Module;
import dagger.Provides;
import pl.morcinek.podziemnaplayer.general.dagger.components.DaggerActivity;
import pl.morcinek.podziemnaplayer.general.handlers.RetryErrorHandler;
import pl.morcinek.podziemnaplayer.home.HomeActivity;
import pl.morcinek.podziemnaplayer.home.HomeContentController;
import pl.morcinek.podziemnaplayer.home.ListFragment;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
@Module(
        injects = {
                HomeActivity.class,
                ListFragment.class,
        },
        addsTo = ApplicationModule.class,
        library = true
)
public class ActivityModule {

    private final DaggerActivity daggerActivity;

    public ActivityModule(DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    @Provides
    RetryErrorHandler provideRetryLayoutErrorHandler() {
        return new RetryErrorHandler(daggerActivity);
    }

    @Provides
    HomeContentController provideActivityContentController() {
        return new HomeContentController(daggerActivity);
    }
}
