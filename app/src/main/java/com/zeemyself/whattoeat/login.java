package com.zeemyself.whattoeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private ImageButton guess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        guess = (ImageButton) findViewById(R.id.guesslogin);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Yeahh", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),mainmenu.class));
            }
        });




    }
}
