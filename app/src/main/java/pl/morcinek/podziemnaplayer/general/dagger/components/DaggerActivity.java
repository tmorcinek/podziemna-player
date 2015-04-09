package pl.morcinek.podziemnaplayer.general.dagger.components;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import pl.morcinek.podziemnaplayer.DaggerApplication;
import pl.morcinek.podziemnaplayer.general.dagger.configuration.ActivityModule;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class DaggerActivity extends ActionBarActivity {

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityGraph = getShowcaseApplication().getApplicationGraph().plus(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        activityGraph = null;
        super.onDestroy();
    }

    private DaggerApplication getShowcaseApplication() {
        return (DaggerApplication) getApplication();
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ActivityModule(this));
    }

    public void inject(Object object) {
        activityGraph.inject(object);
    }
}
