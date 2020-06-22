package com.robotemplates.testguide;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MarkerClusterItem implements ClusterItem {

    private LatLng latLng;
    private String mtitle;


    public MarkerClusterItem(double lat, double lng, String mtitle) {
        this.mtitle = mtitle;
        latLng = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return mtitle;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
