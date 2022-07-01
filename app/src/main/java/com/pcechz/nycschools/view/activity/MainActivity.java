package com.pcechz.nycschools.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.MenuItem;

import com.pcechz.nycschools.R;
import com.pcechz.nycschools.databinding.ActivityMainBinding;
import com.pcechz.nycschools.utils.FragmentUtils;
import com.pcechz.nycschools.view.base.BaseActivity;
import com.pcechz.nycschools.view.fragments.SchoolListFragment;

import static com.pcechz.nycschools.utils.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        FragmentUtils.replaceFragment(this, SchoolListFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}