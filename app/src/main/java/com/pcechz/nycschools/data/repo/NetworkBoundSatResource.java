package com.pcechz.nycschools.data.repo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.MalformedJsonException;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.pcechz.nycschools.NycSchoolsApp;
import com.pcechz.nycschools.R;
import com.pcechz.nycschools.data.db.entity.SatEntity;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class NetworkBoundSatResource<T, V> {

    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    @MainThread
    protected NetworkBoundSatResource() {
        result.setValue(Resource.loading(null));

        // Always load the data from DB intially so that we have
        LiveData<T> dbSource = loadFromDb();

        // Fetch the data from network and add it to the resource
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch()) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> {
                    if(null != newData)
                        result.setValue(Resource.success(newData)) ;
                });
            }
        });
    }

    /**
     * This method fetches the data from remoted service and save it to local db
     * @param dbSource - Database source
     */
    private void fetchFromNetwork(final LiveData<T> dbSource) {
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
        createCall().enqueue(new Callback<List<SatEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<SatEntity>> call, @NonNull Response<List<SatEntity>> response) {
                result.removeSource(dbSource);
                saveResultAndReInit(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<SatEntity>> call, @NonNull Throwable t) {
                result.removeSource(dbSource);
                result.addSource(dbSource, newData -> result.setValue(Resource.error(getCustomErrorMessage(t), newData)));
            }
        });
    }

    private String getCustomErrorMessage(Throwable error){

        if (error instanceof SocketTimeoutException) {
            return NycSchoolsApp.getAppContext().getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return  NycSchoolsApp.getAppContext().getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
            return  NycSchoolsApp.getAppContext().getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return NycSchoolsApp.getAppContext().getString(R.string.unknownError) + error.toString();
        }

    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    private void saveResultAndReInit(List<SatEntity> response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                result.addSource(loadFromDb(), newData -> {
                    if (null != newData)
                        result.setValue(Resource.success(newData));
                });
            }
        }.execute();
    }

    @WorkerThread
    protected abstract void saveCallResult(List<SatEntity> item);

    @MainThread
    private boolean shouldFetch() {
        return true;
    }

    @NonNull
    @MainThread
    protected abstract LiveData<T> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Call<List<SatEntity>> createCall();

    public final LiveData<Resource<T>> getAsLiveData() {
        return result;
    }
}