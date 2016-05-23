package com.zeemyself.whattoeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class login extends AppCompatActivity {
    private ImageButton guess;
    private LoginButton login_button;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());



        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
//        login_button = (LoginButton) findViewById(R.id.login_button);
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.d("FB","success");
                        Profile user = Profile.getCurrentProfile();
                        String name = user.getFirstName();
                        Intent intent = new Intent(getApplicationContext(),mainmenu.class);
                        intent.putExtra("name",name);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.d("FB","cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.d("FB",exception.toString());
                    }
                });



        guess = (ImageButton) findViewById(R.id.guesslogin);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Yeahh", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),mainmenu.class));
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
