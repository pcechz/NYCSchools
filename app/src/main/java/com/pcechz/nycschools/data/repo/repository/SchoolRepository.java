package com.pcechz.nycschools.data.repo.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.pcechz.nycschools.data.db.dao.SchoolsDao;
import com.pcechz.nycschools.data.db.entity.SatEntity;
import com.pcechz.nycschools.data.db.entity.SchoolEntity;
import com.pcechz.nycschools.data.repo.ApiService;
import com.pcechz.nycschools.data.repo.NetworkBoundSatResource;
import com.pcechz.nycschools.data.repo.NetworkBoundSchoolResource;
import com.pcechz.nycschools.data.repo.Resource;
import com.pcechz.nycschools.data.repo.model.SchoolsResponse;
import com.pcechz.nycschools.view.callbacks.SatResponseListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class SchoolRepository {

    private final SchoolsDao schoolsDao;
    private final ApiService apiService;

    @Inject
    SchoolRepository(SchoolsDao dao, ApiService service) {
        this.schoolsDao = dao;
        this.apiService = service;
    }

    /**
     * This method fetches schools from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     * @return List of schools
     */
    public LiveData<Resource<List<SchoolEntity>>> loadSchools() {
        return new NetworkBoundSchoolResource<List<SchoolEntity>, SchoolsResponse>() {

            @Override
            protected void saveCallResult(List<SchoolEntity> item) {
                if(null != item)
                    Log.d("numm", String.valueOf(item.size()));
                    schoolsDao.saveSchools(item);
            }



            @NonNull
            @Override
            protected LiveData<List<SchoolEntity>> loadFromDb() {
                return schoolsDao.loadSchools();
            }

            @NonNull
            @Override
            protected Call<List<SchoolEntity>> createCall() {
                return apiService.loadSchools();
            }
        }.getAsLiveData();
    }

    /**
     * This method fetches sats from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     * @return Sats Data
     */


    public LiveData<Resource<List<SatEntity>>> loadSats(String dbn) {
        return new NetworkBoundSatResource<List<SatEntity>, SchoolsResponse>() {

            @Override
            protected void saveCallResult(List<SatEntity> item) {
                if(null != item)
                schoolsDao.saveSats(item);
            }



            @NonNull
            @Override
            protected LiveData<List<SatEntity>> loadFromDb() {
                return schoolsDao.loadSats(dbn);
            }

            @NonNull
            @Override
            protected Call<List<SatEntity>> createCall() {
             //   Log.d("TAG createCall", dbn);
                return apiService.loadSatScores(dbn);
            }
        }.getAsLiveData();
    }



}
