package com.test.demafayz.testapplication.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by demafayz on 26.08.16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(configuration);
    }
}
