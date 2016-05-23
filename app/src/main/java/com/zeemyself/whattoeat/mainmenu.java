package com.zeemyself.whattoeat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class mainmenu extends AppCompatActivity {
    ImageButton randomPicture;
    Spinner spinner;
    String place = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        spinner = (Spinner) findViewById(R.id.spinner);

//        Uri uri = Uri.parse("android.resource://com.zeemyself.whattoeat/drawable/spinrandom2");
//        InputStream stream = getContentResolver().openInputStream(uri);
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(uri)
//                .setAutoPlayAnimations(true)
//                .build();
        randomPicture = (ImageButton) findViewById(R.id.randomPicture);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(randomPicture);
        Glide.with(this).load(R.drawable.spinrandom2).into(imageViewTarget);

        randomPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                place = spinner.getSelectedItem().toString();
//                Android 6.0 Location required
                if ((ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    && (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

                        ActivityCompat.requestPermissions(mainmenu.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 55567);

                        return;

                }
                if(!place.equals("Choose Location")) {
                    Intent intent = new Intent(getApplicationContext(), resultactivity.class);
                    intent.putExtra("Place", place);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please select location",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean denied = false;
        if (requestCode == 55567){
            for(int i=0;i < permissions.length; i++){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED)
                    denied = true;
            }
        }
        if (denied){
            Toast.makeText(getApplicationContext(),"You didnt accept permission google map will not be shown",Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(getApplicationContext(),resultactivity.class);
            startActivity(intent);
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



    }
}
