package com.pcechz.nycschools.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pcechz.nycschools.data.db.entity.SchoolEntity;
import com.pcechz.nycschools.databinding.ItemSchoolListBinding;
import com.pcechz.nycschools.view.base.BaseAdapter;
import com.pcechz.nycschools.view.callbacks.SchoolListCallback;

import java.util.ArrayList;
import java.util.List;

public class SchoolListAdapter extends BaseAdapter<SchoolListAdapter.SchoolViewHolder, SchoolEntity>
        implements Filterable {

    private List<SchoolEntity> schoolEntities;

    private List<SchoolEntity> schoolEntitiesFiltered;

    private final SchoolListCallback schoolListCallback;

    public SchoolListAdapter(@NonNull SchoolListCallback schoolListCallback) {
        schoolEntities = new ArrayList<>();
        schoolEntitiesFiltered = new ArrayList<>();
        this.schoolListCallback = schoolListCallback;
    }

    @Override
    public void setData(List<SchoolEntity> entities) {
        this.schoolEntities = entities;
        this.schoolEntitiesFiltered = entities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return SchoolViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, schoolListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolViewHolder viewHolder, int i) {
        viewHolder.onBind(schoolEntitiesFiltered.get(i));
    }

    @Override
    public int getItemCount() {
        return schoolEntitiesFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    schoolEntitiesFiltered = schoolEntities;
                } else {
                    List<SchoolEntity> filteredList = new ArrayList<>();
                    for (SchoolEntity row : schoolEntities) {

                        // name match condition. this might differ depending on your requirement
                        if (row.getSchoolName().toLowerCase().contains(charString.toLowerCase())
                                || row.getSchoolEmail().toLowerCase().contains(charString.toLowerCase())
                                ) {
                            filteredList.add(row);
                        }
                    }

                    schoolEntitiesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = schoolEntitiesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                schoolEntitiesFiltered = (ArrayList<SchoolEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    static class SchoolViewHolder extends RecyclerView.ViewHolder {

        private static SchoolViewHolder create(LayoutInflater inflater, ViewGroup parent, SchoolListCallback callback) {
            ItemSchoolListBinding itemSchoolListBinding = ItemSchoolListBinding.inflate(inflater, parent, false);
            return new SchoolViewHolder(itemSchoolListBinding, callback);
        }

        final ItemSchoolListBinding binding;

        private SchoolViewHolder(ItemSchoolListBinding binding, SchoolListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onSchoolClicked(binding.getSchool()));
        }

        private void onBind(SchoolEntity schoolEntity) {
            binding.setSchool(schoolEntity);
            binding.executePendingBindings();
        }
    }
}