package pl.morcinek.podziemnaplayer;

import android.app.Application;

import pl.morcinek.podziemnaplayer.dagger.configuration.MockModule;

import pl.morcinek.podziemnaplayer.general.dagger.configuration.ApplicationModule;


public final class Modules {

    public static Object[] create(Application application) {
        return new Object[]{
                new ApplicationModule(application),
                new MockModule()
        };
    }
}
