package com.pcechz.nycschools.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pcechz.nycschools.data.db.entity.SatEntity;
import com.pcechz.nycschools.data.db.entity.SchoolEntity;

import java.util.List;

@Dao
public interface SchoolsDao {
    @Query("SELECT * FROM schools")
    LiveData<List<SchoolEntity>> loadSchools();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveSchools(List<SchoolEntity> schoolEntities);

    @Query("SELECT * FROM sats WHERE dbn = :dbn")
    LiveData<List<SatEntity>> loadSats(String dbn);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveSats(List<SatEntity> satEntities);

}