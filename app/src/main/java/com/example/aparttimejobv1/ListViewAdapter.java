package com.example.aparttimejobv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<JobInfo> {
    int res;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<JobInfo> objects) {
        super(context, resource, objects);
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if( convertView == null ){
            convertView = inflater.inflate(res,parent,false);
        }

        TextView comNameTV = convertView.findViewById(R.id.companyName);
        TextView locationTV = convertView.findViewById(R.id.location);
        TextView titleTV = convertView.findViewById(R.id.companyName);
        TextView desTV = convertView.findViewById(R.id.companyName);

        JobInfo jobInfo = getItem(position);
        comNameTV.setText(jobInfo.getCompanyName());
        locationTV.setText(jobInfo.getLocationComapny());
        titleTV.setText(jobInfo.getJobTitleReqiure());
        comNameTV.setText(jobInfo.getJobDescription());

        return convertView;
    }
}
