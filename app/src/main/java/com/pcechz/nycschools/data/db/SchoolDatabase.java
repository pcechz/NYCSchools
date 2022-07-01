package com.pcechz.nycschools.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pcechz.nycschools.data.db.dao.SchoolsDao;
import com.pcechz.nycschools.data.db.entity.SatEntity;
import com.pcechz.nycschools.data.db.entity.SchoolEntity;

@Database(entities = {SchoolEntity.class, SatEntity.class}, version = 2)
public abstract class SchoolDatabase extends RoomDatabase {
    public abstract SchoolsDao schoolsDao();
}