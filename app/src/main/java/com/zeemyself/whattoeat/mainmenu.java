package com.zeemyself.whattoeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class mainmenu extends AppCompatActivity {
    ImageButton randomPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);


        randomPicture = (ImageButton) findViewById(R.id.randomPicture);
        randomPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),resultactivity.class);
                startActivity(intent);
            }
        });

    }
}
