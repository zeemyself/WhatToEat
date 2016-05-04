package com.zeemyself.whattoeat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class activity extends ActionBarActivity {
    public String[] food = {"ผัดไท","ก๋วยเตี๋ยวเป็ด","ข้าวมันไก่","ข้าวหมกไก่","ส้มตำ","ข้าวแกง","ฟู้ดคอด","สุกี้","ก๋วยจับ","ยำ","ข้าวหมูกรอบ","อาหารตามสั่ง","ก๋วยเตี๋ยวเรือ"};
    private ImageButton rice,noodle;
    public  AlertDialog.Builder pop;
    private ImageButton random;
    private TextView text,times;
    private int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        noodle = (ImageButton) findViewById(R.id.noodle);
        rice = (ImageButton) findViewById(R.id.rice);
        random = (ImageButton) findViewById(R.id.random);
        text = (TextView) findViewById(R.id.welcometext);
        times = (TextView) findViewById(R.id.times);



        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                noodle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),login.class);
                        startActivity(intent);
//                        setContentView(R.layout.random);
//                        random = (ImageButton) findViewById(R.id.random);
//                        times = (TextView) findViewById(R.id.times);
//                        random.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if(isConnected) {
//                                    new SendfeedbackJob().execute();
//                                    click++;
//                                    times.setText(String.valueOf(click));
//                                    times.setVisibility(View.VISIBLE);
//                                }
//                                else{
//                                  Toast.makeText(getApplicationContext(), "Please connect Internet", Toast.LENGTH_LONG).show();
//                               }
////                              job = new SendfeedbackJob();
//                                //random.setClickable(false);
//                            }
//                        });
                    }
                });

                rice.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "445", Toast.LENGTH_LONG).show();
                        setContentView(R.layout.random);
//                        random = (ImageButton) findViewById(R.id.random);
//                        text = (TextView) findViewById(R.id.welcometext);
//                        random.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                text.setText(food[randInt(0,food.length-1)]);
//                            }
//                        });
                    }
                });


    }
    //Batmaster Code

    private ArrayList<HashMap<String, String>> requestServer() {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://www.zeemyself.com/connectzeedb.php");

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

//            int currentId = PreferencesHandle.getCurrentId(getApplicationContext());
            String sql = String.format("SELECT * FROM Food",1);
            nameValuePairs.add(new BasicNameValuePair("select", sql));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                InputStream is = entity.getContent();
                StringBuffer sb = new StringBuffer();
                String line = null;

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                if ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();

                JSONArray jArrays = new JSONArray(sb.toString());
                for (int i = 0; i < jArrays.length(); i++) {
                    HashMap<String, String> user = new HashMap<String, String>();
                    JSONObject jo = jArrays.getJSONObject(i);
                    user.put("FoodName", jo.get("FoodName").toString());
                    user.put("Air", jo.get("Air").toString());

                    list.add(user);


                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private class SendfeedbackJob extends AsyncTask<String, Void, String> {

        private ArrayList<HashMap<String, String>> list;

        @Override
        protected String doInBackground(String[] params) {
            list = requestServer();
            return "some message";

        }

        @Override
        protected void onPostExecute(String message) {
            int rand = randInt(0,list.size()-1);
            text = (TextView) findViewById(R.id.welcometext);
            text.setText(list.get(rand).get("FoodName"));
        }
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
