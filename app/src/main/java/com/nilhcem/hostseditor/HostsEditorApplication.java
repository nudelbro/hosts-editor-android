package com.nilhcem.hostseditor;

import android.app.Application;
import android.content.Context;

import com.nilhcem.hostseditor.core.log.ReleaseTree;

import dagger.ObjectGraph;
import timber.log.Timber;

/**
 * Creates and provides access to Dagger's {@link ObjectGraph} instance.
 */
public class HostsEditorApplication extends Application {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
        buildObjectGraph();
    }

    public void inject(Object target) {
        mObjectGraph.inject(target);
    }

    public <T> T get(Class<T> type) {
        return mObjectGraph.get(type);
    }

    public static HostsEditorApplication get(Context context) {
        return (HostsEditorApplication) context.getApplicationContext();
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }
    }

    private void buildObjectGraph() {
        mObjectGraph = ObjectGraph.create(new HostsEditorModule());
    }
}
