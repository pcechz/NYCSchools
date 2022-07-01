package com.pcechz.nycschools.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pcechz.nycschools.data.db.entity.SchoolEntity;
import com.pcechz.nycschools.data.repo.Resource;
import com.pcechz.nycschools.data.repo.repository.SchoolRepository;

import java.util.List;

import javax.inject.Inject;

public class SchoolListViewModel extends ViewModel {
    private final LiveData<Resource<List<SchoolEntity>>> schools;

    @Inject
    public SchoolListViewModel(SchoolRepository schoolRepository) {
        schools = schoolRepository.loadSchools();
    }

    public LiveData<Resource<List<SchoolEntity>>> getSchools() {
        return schools;
    }
}
