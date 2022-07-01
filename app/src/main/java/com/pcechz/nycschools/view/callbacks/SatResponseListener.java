package com.pcechz.nycschools.view.callbacks;

import com.pcechz.nycschools.data.db.entity.SatEntity;

public interface SatResponseListener {

    void onSuccess(SatEntity data);
    void onFailure(String message);
}
