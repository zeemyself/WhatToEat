package com.zeemyself.whattoeat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class activity extends ActionBarActivity {
    public String[] food = {"แกงป่า","ส้มตำ","หมูกระทะ","ข้าวต้มปลา","ปลาเผา","ยู้ฮู","พูลสิน","ข้าวต้ม ๓ บาท"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        wheel();

    }
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    public void wheel(){
        final TextView text = (TextView) findViewById(R.id.foodtext);
        final ImageButton wheel = (ImageButton) findViewById(R.id.wheel);
        wheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String foodtext = food[randInt(0,food.length-1)];
                AlertDialog.Builder pop = new AlertDialog.Builder(activity.this);
                pop.setTitle("WE got....");
                pop.setMessage(foodtext);
                pop.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text.setText(foodtext);
                    }
                });

                pop.show();
                wheel.setClickable(false);
                Toast.makeText(getApplicationContext(), foodtext, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
