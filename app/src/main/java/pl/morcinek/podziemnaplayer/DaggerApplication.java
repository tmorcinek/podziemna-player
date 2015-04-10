package pl.morcinek.podziemnaplayer;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class DaggerApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(Modules.create(this));
    }

    public ObjectGraph getApplicationGraph() {
        return objectGraph;
    }
}
