package com.pcechz.nycschools;
import android.app.Activity;
import android.app.Application;

//import com.pcechz.nycschools.di.components.DaggerAppComponent;

import com.pcechz.nycschools.di.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerAppCompatActivity;

public class NycSchoolsApp extends Application implements HasActivityInjector {
    private static NycSchoolsApp sInstance;


    public static NycSchoolsApp getAppContext() {
        return sInstance;
    }



    private static synchronized void setInstance(NycSchoolsApp app) {
        sInstance = app;
    }
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
