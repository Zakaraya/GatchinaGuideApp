package com.robotemplates.testguide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    ImageView image_detail;
    ImageView test;
    TextView title, desc, address, link, mobPhone;

    private String PostKey;
    private DatabaseReference clickPostRef;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        PostKey=getIntent().getExtras().get("PostKey").toString();
        clickPostRef= FirebaseDatabase.getInstance().getReference().child("All").child(PostKey);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onBackPressed();// возврат на предыдущий activity
            }
        });

        image_detail=findViewById(R.id.post_image_click);
        title=findViewById(R.id.fragment_poi_detail_info_intro);
        desc=findViewById(R.id.fragment_poi_detail_description_text);
        address=findViewById(R.id.fragment_poi_detail_info_address);
        link=findViewById(R.id.fragment_poi_detail_info_link);
        mobPhone=findViewById(R.id.fragment_poi_detail_info_phone);
        test=findViewById(R.id.fragment_poi_detail_map_image);

//        test.setText("https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap\n" +
//                "&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318\n" +
//                "&markers=color:red%7Clabel:C%7C40.718217,-73.998284\n" +
//                "&key=YOUR_API_KEY");

        clickPostRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String image=dataSnapshot.child("image").getValue().toString();
                Picasso.get().load(image).into(image_detail);
          //      String lat=dataSnapshot.child("lat").getValue().toString();
             //   String lng=dataSnapshot.child("lng").getValue().toString();


//                String url = "https://maps.googleapis.com/maps/api/staticmap?center=Gatchina&zoom=13&size=320x320&maptype=roadmap" +
//                        "&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318" +
//                        "&markers=color:red%7Clabel:C%7C40.718217,-73.998284" +
//                        "&key=AIzaSyDmh_HWDZem1zBIZPl7puMXP-52zJRLV5A";
                final double lat1 = (double) dataSnapshot.child("lat").getValue();
                final double lng1 = (double) dataSnapshot.child("lng").getValue();
                String url = "https://maps.googleapis.com/maps/api/staticmap?zoom=14&size=400x320&maptype=roadmap" +
                        "&markers=color:red%7Clabel:S%7C" +lat1 + "," + lng1 +
                        "&key=AIzaSyDmh_HWDZem1zBIZPl7puMXP-52zJRLV5A";



                Picasso.get().load(url).into(test, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), "Error to load Maps: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




                String titleDetail=dataSnapshot.child("title").getValue().toString();

                title.setText(titleDetail);

                String descDetail=dataSnapshot.child("fullDesc").getValue().toString();
                desc.setText(descDetail);


               final double lat = (double) dataSnapshot.child("lat").getValue();
              final double lng = (double) dataSnapshot.child("lng").getValue();

                String addressDetail=dataSnapshot.child("address").getValue().toString();
                address.setText(addressDetail);
                address.setVisibility(View.VISIBLE);
                address.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+lat+","+lng);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");

                        if (mapIntent.resolveActivity(getPackageManager()) != null) {  // Проверка установленыли google Карты приложение... Если будетаь лагать - убрать
                            startActivity(mapIntent);
                        }
                      //  startNavigateActivity(lat, lng);
                    }
                });

                String linkDetail=dataSnapshot.child("link").getValue().toString();
                link.setText(linkDetail);

                String mobPhoneDetail=dataSnapshot.child("phone").getValue().toString();
                mobPhone.setText(mobPhoneDetail);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onClick(View v) {
        link=findViewById(R.id.fragment_poi_detail_info_link);
        Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www."+link.getText().toString()));
        startActivity(browserIntent);
    }

    private void startNavigateActivity(double lat, double lon)
    {
        try
        {
            String uri = String.format("http://maps.google.com/maps?daddr=%s,%s", Double.toString(lat), Double.toString(lon));
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }
        catch(android.content.ActivityNotFoundException e)
        {
            // can't start activity
        }
    }

}
