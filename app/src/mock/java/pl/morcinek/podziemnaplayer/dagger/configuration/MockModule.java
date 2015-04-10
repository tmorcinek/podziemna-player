package pl.morcinek.podziemnaplayer.dagger.configuration;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.morcinek.podziemnaplayer.data.network.ApiService;
import pl.morcinek.podziemnaplayer.data.network.MockApiService;
import pl.morcinek.podziemnaplayer.general.dagger.configuration.ApplicationModule;

@Module(
        includes = {
                ApplicationModule.class,
        },
        library = true,
        overrides = true
)
public final class MockModule {

    @Provides
    @Singleton
    ApiService provideApiService() {
        return new MockApiService();
    }
}
