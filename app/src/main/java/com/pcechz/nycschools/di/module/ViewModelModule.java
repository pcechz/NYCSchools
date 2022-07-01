package com.pcechz.nycschools.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pcechz.nycschools.viewmodel.SchoolDetailsViewModel;
import com.pcechz.nycschools.viewmodel.SchoolListViewModel;
import com.pcechz.nycschools.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SchoolListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsSchoolListViewModel(SchoolListViewModel schoolListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SchoolDetailsViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsSchoolDetailsViewModel(SchoolDetailsViewModel schoolDetailsViewModel);


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}