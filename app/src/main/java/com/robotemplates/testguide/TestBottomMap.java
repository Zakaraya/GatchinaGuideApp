package com.robotemplates.testguide;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.maps.android.clustering.ClusterManager;
import com.robotemplates.testguide.fragmentMap.AllMapFragment;
import com.robotemplates.testguide.fragmentMap.ModernMapFragment;
import com.robotemplates.testguide.fragmentMap.OldMapFragment;
import com.robotemplates.testguide.fragmentMap.SovetMapFragment;

public class TestBottomMap extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    public GoogleMap mMap;
    AllMapFragment allMapFragment=new AllMapFragment();


    private UiSettings mUiSettings;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private boolean mPermissionDenied = false;

    private DatabaseReference ref;

    private FusedLocationProviderClient fusedLocationClient;

    private ClusterManager<MarkerClusterItem> clusterManager;
    MarkerClusterItem markerClusterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_map_bottom);
        loadFragment(new OldMapFragment());

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myToolbar.setTitleTextColor(Color.WHITE);


    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_frame_back, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.container_frame_back, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.menu_map:
                fragment = new AllMapFragment();
                break;

            case R.id.menu_history:
                fragment = new OldMapFragment();
                break;

            case R.id.menu_settings:
                fragment = new SovetMapFragment();
                break;
            case R.id.menu_modern:
                fragment = new ModernMapFragment();
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
            case R.id.menu_layers_normal:
               // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return false;
            case R.id.menu_layers_satellite:
              // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return false;
            case R.id.menu_layers_hybrid:
              //  mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return false;
            case R.id.menu_layers_terrain:
             //   mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return false;
           default:
                return super.onOptionsItemSelected(item);
       }
        //   return super.onOptionsItemSelected(item);
    }

}
