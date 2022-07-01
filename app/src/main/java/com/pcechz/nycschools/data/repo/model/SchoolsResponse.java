package com.pcechz.nycschools.data.repo.model;

import com.google.gson.annotations.SerializedName;
import com.pcechz.nycschools.data.db.entity.SatEntity;
import com.pcechz.nycschools.data.db.entity.SchoolEntity;

import java.util.List;

public class SchoolsResponse {

    private List<SchoolEntity> schools;
    private List<SatEntity> sats;


    /**
     * This method return the list of school entities
     * @return List of entities
     */
    public List<SchoolEntity> getSchools() {
        return schools;
    }

    /**
     * This method sets the school entities
     * @param schools - schoolslist
     */
    @SuppressWarnings("unused")
    public void setSchools(List<SchoolEntity> schools) {
        this.schools = schools;
    }

    /**
     * This method return the list of sat entities
     * @return List of entities
     */
    public List<SatEntity> getSats() {
        return sats;
    }

    /**
     * This method sets the sat entities
     * @param sats - satList
     */
    @SuppressWarnings("unused")
    public void setSats(List<SatEntity> sats) {
        this.sats = sats;
    }
}
