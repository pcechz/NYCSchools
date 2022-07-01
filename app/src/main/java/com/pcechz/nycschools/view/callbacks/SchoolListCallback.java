package com.pcechz.nycschools.view.callbacks;

import com.pcechz.nycschools.data.db.entity.SchoolEntity;

public interface SchoolListCallback {
    void onSchoolClicked(SchoolEntity schoolEntity);
}
