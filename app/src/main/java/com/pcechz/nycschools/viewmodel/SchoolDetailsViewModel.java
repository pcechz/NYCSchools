package com.pcechz.nycschools.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pcechz.nycschools.data.db.entity.SatEntity;
import com.pcechz.nycschools.data.repo.Resource;
import com.pcechz.nycschools.data.repo.repository.SchoolRepository;
import com.pcechz.nycschools.utils.SingleLiveEvent;
import com.pcechz.nycschools.view.callbacks.SatResponseListener;

import java.util.List;

import javax.inject.Inject;

public class SchoolDetailsViewModel extends AndroidViewModel {
    SharedPreferences sharedpreferences = getApplication().getSharedPreferences("preference_key", Context.MODE_PRIVATE);

    private String dbn;

    private SchoolRepository schoolRepository;
    private final LiveData<Resource<List<SatEntity>>> sats;

    @Inject
    public SchoolDetailsViewModel(SchoolRepository schoolRepository, Application application) {
      super(application);
        String token = sharedpreferences.getString("token", "");
        sats = schoolRepository.loadSats(token);
    }

    public LiveData<Resource<List<SatEntity>>> getSats(String dbn) {
        this.dbn = dbn;
        return sats;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public void loadSchoolSats(){

        if(null != schoolRepository) {
            schoolRepository.loadSats(dbn);
        }
    }
}
