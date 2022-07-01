package com.pcechz.nycschools.di.components;


import android.app.Application;

import com.pcechz.nycschools.NycSchoolsApp;
import com.pcechz.nycschools.di.builder.ActivityBuilderModule;
import com.pcechz.nycschools.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(NycSchoolsApp nycSchoolsApp);
}

