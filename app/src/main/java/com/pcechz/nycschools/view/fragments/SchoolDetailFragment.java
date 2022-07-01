package com.pcechz.nycschools.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.pcechz.nycschools.R;
import com.pcechz.nycschools.common.Constants;
import com.pcechz.nycschools.data.repo.Status;
import com.pcechz.nycschools.databinding.FragmentSchoolDetailsBinding;
import com.pcechz.nycschools.view.base.BaseFragment;
import com.pcechz.nycschools.viewmodel.SchoolDetailsViewModel;

public class SchoolDetailFragment extends BaseFragment<SchoolDetailsViewModel, FragmentSchoolDetailsBinding> {
    @Override
    protected Class<SchoolDetailsViewModel> getViewModel() {
        return SchoolDetailsViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_school_details;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (null != args){
            dataBinding.loadingProgress.setVisibility(View.GONE);
            dataBinding.textTitle.setText(args.getString("schoolName"));
            dataBinding.cityTxt.setText(args.getString("city"));
            dataBinding.textContent.setText(args.getString("content"));
            viewModel.setDbn(args.getString("dbn"));
            viewModel.getSats(args.getString("dbn")).observe(getViewLifecycleOwner(), listResource -> {


                if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                }
                if(null != listResource && null != listResource.data && listResource.data.size() != 0 ){
                    dataBinding.testTakersNum.setText(null != listResource.data.get(0).getNumOfSatTestTakers() ? listResource.data.get(0).getNumOfSatTestTakers() : "");
                    dataBinding.mathNum.setText(null != listResource.data.get(0).getMathAvgScore() ? listResource.data.get(0).getMathAvgScore() : "");
                    dataBinding.readingNum.setText(null != listResource.data.get(0).getCriticalReadingAvgScore() ? listResource.data.get(0).getCriticalReadingAvgScore() : "");
                    dataBinding.writingNum.setText(null != listResource.data.get(0).getWritingAvgScore() ? listResource.data.get(0).getWritingAvgScore() : "");



                }

            });;

        }
//        if(null != args) {
//            viewModel.setUrl(args.getString(Constants.BUNDLE_KEY_ARTICLE_URL));
//            viewModel.loadSchoolDetails();
//        }
//        viewModel.getSchoolEntityMutableLiveData().observe(getViewLifecycleOwner(), schoolEntity -> {
//            if(null != schoolEntity && null != args) {
//                dataBinding.textTitle.setText(schoolEntity.getSchoolName());
//                dataBinding.textContent.setText(schoolEntity.getOverviewParagraph());
//                //dataBinding.cityTxt.setText(args.getString(Constants.BUNDLE_KEY_ARTICLE_PUBLISHED_DATE));
//                dataBinding.loadingProgress.setVisibility(View.GONE);
//            }
//        });
//
//        viewModel.getErrorMessageRecieved().observe(this, message ->{
//            dataBinding.loadingProgress.setVisibility(View.GONE);
//            dataBinding.textContent.setText(getActivity().getString(R.string.networkError));
//        });
    }
}
