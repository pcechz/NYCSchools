package com.pcechz.nycschools.view.fragments;


import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pcechz.nycschools.R;
import com.pcechz.nycschools.common.Constants;
import com.pcechz.nycschools.data.db.entity.SchoolEntity;
import com.pcechz.nycschools.data.repo.Status;
import com.pcechz.nycschools.databinding.FragmentListSchoolsBinding;
import com.pcechz.nycschools.utils.FragmentUtils;
import com.pcechz.nycschools.view.adapter.SchoolListAdapter;
import com.pcechz.nycschools.view.base.BaseFragment;
import com.pcechz.nycschools.view.callbacks.SchoolListCallback;
import com.pcechz.nycschools.viewmodel.SchoolListViewModel;

public class SchoolListFragment extends BaseFragment<SchoolListViewModel, FragmentListSchoolsBinding> implements SchoolListCallback {

    public static SchoolListFragment newInstance() {
        Bundle args = new Bundle();
        SchoolListFragment fragment = new SchoolListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<SchoolListViewModel> getViewModel() {
        return SchoolListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list_schools;
    }

    @Override
    public void onSchoolClicked(SchoolEntity schoolEntity) {
        if(null != getActivity()){
            Bundle args = new Bundle();
            args.putString("dbn", schoolEntity.getDbn());
            args.putString("schoolName", schoolEntity.getSchoolName());
            args.putString("schoolNumber", schoolEntity.getPhoneNumber());
            args.putString("schoolEmail", schoolEntity.getSchoolEmail());
            args.putString("schoolWebsite", schoolEntity.getWebsite());
            args.putString("city", schoolEntity.getCity());
            args.putString("stateCode", schoolEntity.getStateCode());
            args.putString("content", schoolEntity.getOverviewParagraph());

            SharedPreferences sharedPref = getActivity().getSharedPreferences("preference_key",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("token", schoolEntity.getDbn());
            editor.apply();
            SchoolDetailFragment detailFragment = new SchoolDetailFragment();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment, R.id.fragContainer, true, FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new SchoolListAdapter(this));
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        viewModel.getSchools()
                .observe(getViewLifecycleOwner(), listResource -> {


                    if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                        dataBinding.loginProgress.setVisibility(View.GONE);
                    }
                    dataBinding.setResource(listResource);


                    // If the cached data is already showing then no need to show the error
                    if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                        dataBinding.loginProgress.setVisibility(View.GONE);
                        dataBinding.errorText.setVisibility(View.GONE);
                    }
                });

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        if(null == getActivity())
//            return;
//
//        SearchView searchView;
//        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        // Associate searchable configuration with the SearchView
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) menu.findItem(R.id.action_search)
//                .getActionView();
//
//        searchView.setSearchableInfo(searchManager
//                .getSearchableInfo(getActivity().getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        // listening to search query text change
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // filter recycler view when query submitted
//                if(null != dataBinding.recyclerView.getAdapter())
//                    ((SchoolListAdapter)dataBinding.recyclerView.getAdapter()).getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                // filter recycler view when text is changed
//                if(null != dataBinding.recyclerView.getAdapter())
//                    ((SchoolListAdapter)dataBinding.recyclerView.getAdapter()).getFilter().filter(query);
//                return false;
//            }
//        });
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_search) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}