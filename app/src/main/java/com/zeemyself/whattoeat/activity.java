package com.zeemyself.whattoeat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


//public class activity extends ActionBarActivity {
//    public String[] food = {"แกงป่า","ส้มตำ","หมูกระทะ","ข้าวต้มปลา","ปลาเผา","ยู้ฮู","พูลสิน","ข้าวต้ม ๓ บาท"};
//    public ImageView foodpic;
//    public int randomnumber;
//    public  AlertDialog.Builder pop;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_activity);
//        wheel();
//
//    }
//    public static int randInt(int min, int max) {
//
//        // NOTE: Usually this should be a field rather than a method
//        // variable so that it is not re-seeded every call.
//        Random rand = new Random();
//
//        // nextInt is normally exclusive of the top value,
//        // so add 1 to make it inclusive
//        int randomNum = rand.nextInt((max - min) + 1) + min;
//
//        return randomNum;
//    }
//    public void wheel(){
//        final TextView text = (TextView) findViewById(R.id.foodtext);
//        final ImageButton wheel = (ImageButton) findViewById(R.id.wheel);
//        wheel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                randomnumber = randInt(0,food.length-1);
//                final String foodtext = food[randomnumber];
//                pop = new AlertDialog.Builder(activity.this);
//                pop.setTitle("WE got....");
//                pop.setMessage(foodtext);
//                setPic();
//                pop.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        text.setText(foodtext);
//                        wheel.setClickable(false);
//                    }
//                });
//                pop.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                pop.show();
//
//                Toast.makeText(getApplicationContext(), foodtext, Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//    public void setPic(){
//        ImageView p = new ImageView(getApplicationContext());
//            if (randomnumber ==0)
//            p.setImageDrawable(getResources().getDrawable(R.drawable.kaengpa));
//            else if(randomnumber == 6)
//            p.setImageDrawable(getResources().getDrawable(R.drawable.poonsin));
//
//        pop.setView(p);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_activity, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
public class activity extends ActionBarActivity {
    public String[] food = {"แกงป่า","ส้มตำ","หมูกระทะ","ข้าวต้มปลา","ปลาเผา","ยู้ฮู","พูลสิน","ข้าวต้ม ๓ บาท","ผัดไท"};
    public ImageButton rice;
    public int randomnumber;
    public  AlertDialog.Builder pop;
    public ImageButton random;
    public TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
            rice = (ImageButton) findViewById(R.id.rice);
                rice.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "445", Toast.LENGTH_LONG).show();
                        setContentView(R.layout.random);
                        random = (ImageButton) findViewById(R.id.random);
                        text = (TextView) findViewById(R.id.welcometext);
                        random.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                text.setText(food[food.length-1]);
                            }
                        });
                    }
                });

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
