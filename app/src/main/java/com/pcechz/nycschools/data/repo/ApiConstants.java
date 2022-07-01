package com.pcechz.nycschools.data.repo;

public class ApiConstants {
    public static final String BASE_URL = "https://data.cityofnewyork.us/";
    public static final long CONNECT_TIMEOUT = 30000;
    public static final long READ_TIMEOUT = 30000;
    public static final long WRITE_TIMEOUT = 30000;
    public static final String API_KEY = "Put your api key here";

    private ApiConstants(){
        // Private constructor to hide the implicit one
    }

}