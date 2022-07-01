package com.pcechz.nycschools.di.builder;

import com.pcechz.nycschools.view.fragments.SchoolDetailFragment;
import com.pcechz.nycschools.view.fragments.SchoolListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract SchoolListFragment contributeSchoolListFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract SchoolDetailFragment contributeSchoolDetailFragment();

}