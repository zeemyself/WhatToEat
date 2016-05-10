package com.zeemyself.whattoeat;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class resultactivity extends AppCompatActivity {

    ImageView imageResult;
//    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultactivity);

        imageResult = (ImageView) findViewById(R.id.imageResult);


        getPicture d = new getPicture();
        d.execute();
//        try {
////            ImageView i = (ImageView)findViewById(R.id.image);
//            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://i.imgur.com/eTuCPxM.jpg").getContent());
//            imageResult.setImageBitmap(bitmap);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }














//        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
//
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
//                PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
//                        PackageManager.PERMISSION_GRANTED) {
//            map.setMyLocationEnabled(true);
////            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
//        }

    }

    private class getPicture extends AsyncTask<Bitmap,Integer, Bitmap>{


        @Override
        protected Bitmap doInBackground(Bitmap... params) {
            Bitmap bitmap = null;
            try {
//            ImageView i = (ImageView)findViewById(R.id.image);
                bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://i.imgur.com/eTuCPxM.jpg").getContent());

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

}


