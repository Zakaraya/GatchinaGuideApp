package com.robotemplates.testguide.ui.All;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robotemplates.testguide.R;
import com.squareup.picasso.Picasso;

public class HomeViewModel extends RecyclerView.ViewHolder {

    public HomeViewModel(@NonNull final View itemView) {
        super(itemView);
        mView=itemView;
    }

    View mView;

    public void setDetails(Context context, String title, String description, String image){
        TextView post_title=mView.findViewById(R.id.post_title);
        TextView post_desc=mView.findViewById(R.id.post_desc);
        ImageView post_image=mView.findViewById(R.id.post_imeg);
//        TextView test_km = mView.findViewById(R.id.post_km);
//        LatLng myLocation = new LatLng(60.00001, 40.01244);
//        LatLng poiLocation = new LatLng(55.00001, 30.01244);
//        String distance = LocationUtility.getDistanceString(LocationUtility.getDistance(myLocation, poiLocation), LocationUtility.isMetricSystem());


        post_title.setText(title);
        post_desc.setText(description);
        Picasso.get().load(image).into(post_image);
    }
}



