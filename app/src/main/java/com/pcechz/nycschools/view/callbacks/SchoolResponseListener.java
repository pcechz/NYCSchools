package com.pcechz.nycschools.view.callbacks;

import com.pcechz.nycschools.data.db.entity.SchoolEntity;

public interface SchoolResponseListener {

    void onSuccess(SchoolEntity data);
    void onFailure(String message);
}
