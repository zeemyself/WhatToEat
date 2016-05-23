package com.zeemyself.whattoeat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Random;
import java.util.jar.Manifest;

public class resultactivity extends AppCompatActivity {

    Random rand;
    RatingBar bar;
    ImageView imageResult;
    GoogleMap map;
    TextView foodname,price,name;
    String place;
    String location[] = {"google.navigation:q=บาร์วิศวะ (IUP)","google.navigation:q=บาร์วิทยาศาสตร์","google.navigation:q=โรงอาหารกลาง 1"};
    int index;
    int draw[] = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h};
    String random = new Random().nextInt(7)+ "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultactivity);

        imageResult = (ImageView) findViewById(R.id.imageResult);
//        int temp = Integer.parseInt(random);
//        imageResult.setImageResource(getResources().getIdentifier(random,"drawable", getPackageName()));
//        imageResult.setImageResource(draw[Integer.parseInt(random)]);
        imageResult.setBackgroundResource(draw[Integer.parseInt(random)]);
        foodname = (TextView) findViewById(R.id.foodname);
        price = (TextView) findViewById(R.id.price);
        name = (TextView) findViewById(R.id.name);
        place = getIntent().getStringExtra("Place");
        connectDB();
        getIndex();
//        image = getImageUrl();
//        Log.d("image",image);
//        Toast.makeText(getApplicationContext(),place,Toast.LENGTH_LONG).show();
//        getPicture d = new getPicture(image);
//        d.execute();
//        new getPicture((ImageView) findViewById(R.id.imageResult))
//                .execute(image);
//        GoogleMap
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},55567);
        }
        map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(13.846461, 100.569726)));
        map.animateCamera(CameraUpdateFactory.zoomTo(17));
        map.addMarker(new MarkerOptions()
                            .position(new LatLng(13.846461, 100.569726))
                            .title("IUP"));
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Uri gmmIntentUri = Uri.parse(location[index]);

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });
    }
    public void getIndex(){
        if(place.equals("IUP"))
            index = 0;
        else if(place.equals("บาร์วิทยา"))
            index = 1;
        else
            index = 2;
    }
    private class getPicture extends AsyncTask<Bitmap,Integer, Bitmap>{
//        String image = getImageUrl();
        String url = "";
        public getPicture(String url){
            this.url = url;
        }
        @Override
        protected Bitmap doInBackground(Bitmap... params) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
                Log.d("IMAGEZAAAA",url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageResult.setImageBitmap(bitmap);
        }
    }

    public void connectDB(){
        rand = new Random();

//        Log.d("BUG",place+","+random);
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://whattoeat-zee.firebaseio.com/");
        myFirebaseRef.child("IUP").child(random).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
                bar = (RatingBar) findViewById(R.id.ratingBar);
                foodname = (TextView) findViewById(R.id.foodname);
                price = (TextView) findViewById(R.id.price);
                name.setText(dataSnapshot.child("name").getValue() + "");
                price.setText("ราคา : "+dataSnapshot.child("price").getValue() + " บาท");
//                Toast.makeText(getApplicationContext(),dataSnapshot.getValue()+"",Toast.LENGTH_LONG).show();
//                AlertDialog.Builder test = new AlertDialog.Builder(resultactivity.this);
//                        test.setTitle("Fire Base");
//                        test.setMessage(dataSnapshot.getValue()+"\n"+
//                                    dataSnapshot.child("name").getValue()+"\n"+
//                                    dataSnapshot.child("price").getValue()).create().show();

                String rate = dataSnapshot.child("rating").getValue()+"";
                bar.setRating(Float.parseFloat(rate));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public String getImageUrl(){
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://whattoeat-zee.firebaseio.com/");
        final String[] imageurl = {""};
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               imageurl[0] = dataSnapshot.child("IUP").child(random).child("image").getValue()+"";
//                Toast.makeText(getApplicationContext(),imageurl[0],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return imageurl[0];
    }




}


