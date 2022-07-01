package com.pcechz.nycschools.data.repo;

import com.pcechz.nycschools.data.db.entity.SatEntity;
import com.pcechz.nycschools.data.db.entity.SchoolEntity;
import com.pcechz.nycschools.data.repo.model.SchoolsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("resource/s3k6-pzi2.json?$limit=100")
    Call<List<SchoolEntity>> loadSchools();

    @GET("resource/f9bf-2cp4.json")
    Call<List<SatEntity>>loadSatScores(@Query("dbn") String dbn);
}
