package com.pcechz.nycschools.data.db.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "sats")
public class SatEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("dbn")
    private String dbn;

    @SerializedName("school_name")
    private String schoolName;

    public String getNumOfSatTestTakers() {
        return numOfSatTestTakers;
    }

    public void setNumOfSatTestTakers(String numOfSatTestTakers) {
        this.numOfSatTestTakers = numOfSatTestTakers;
    }

    public String getCriticalReadingAvgScore() {
        return criticalReadingAvgScore;
    }

    public void setCriticalReadingAvgScore(String criticalReadingAvgScore) {
        this.criticalReadingAvgScore = criticalReadingAvgScore;
    }

    public String getMathAvgScore() {
        return mathAvgScore;
    }

    public void setMathAvgScore(String mathAvgScore) {
        this.mathAvgScore = mathAvgScore;
    }

    public String getWritingAvgScore() {
        return writingAvgScore;
    }

    public void setWritingAvgScore(String writingAvgScore) {
        this.writingAvgScore = writingAvgScore;
    }

    @SerializedName("num_of_sat_test_takers")
    private String numOfSatTestTakers;

    @SerializedName("sat_critical_reading_avg_score")
    private String criticalReadingAvgScore;

    @SerializedName("sat_math_avg_score")
    private String mathAvgScore;

    @SerializedName("sat_writing_avg_score")
    private String writingAvgScore;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }





}
