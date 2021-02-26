package com.example.requester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.tabs.TabLayout;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Credentials;

public class InfoActivity extends AppCompatActivity {

    String condoNum;
    int tabPos = 0;
    TabLayout tabLayout;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    String token;

    ViewPager viewPager;
    TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());


        prefs = getApplicationContext().getSharedPreferences("PrefsFile", MODE_PRIVATE);
        editor = prefs.edit();

        tabLayout = findViewById(R.id.tabLayout);
        token = prefs.getString("Token", "");

        if (getIntent().getExtras() != null) {

            Intent intent = getIntent();
            condoNum = intent.getStringExtra("condoNum");

            System.out.println("CONDO NUM " + condoNum);
        }

        if (ContextCompat.checkSelfPermission(InfoActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(InfoActivity.this, new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE
            }, 100);
            AndroidNetworking.get("https://sudcopro.immo/api/part-owner/999/" + condoNum + "/list")
                    .addHeaders("Content-Type", "application/json")
                    .addHeaders("Authorization", "Bearer " + token)
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            parserJson(response);
                        }
                        @Override
                        public void onError(ANError error) {
                            // handle error
                            Toast.makeText(InfoActivity.this, "Username or password incorrects", Toast.LENGTH_SHORT).show();
                            System.out.println("ERROR");
                            System.out.println(error.getErrorBody());
                            System.out.println(error.getErrorCode());
                            System.out.println(error.getErrorDetail());
                            System.out.println(error.getResponse());
                        }
                    });

            ActivityCompat.requestPermissions(InfoActivity.this, new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE
            }, 100);
            AndroidNetworking.get("https://sudcopro.immo/api/provider/999/list")
                    .addHeaders("Content-Type", "application/json")
                    .addHeaders("Authorization", "Bearer " + token)
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response2) {
                            parserJson2(response2);
                        }
                        @Override
                        public void onError(ANError error) {
                            // handle error
                            Toast.makeText(InfoActivity.this, "Username or password incorrects", Toast.LENGTH_SHORT).show();
                            System.out.println("ERROR");
                            System.out.println(error.getErrorBody());
                            System.out.println(error.getErrorCode());
                            System.out.println(error.getErrorDetail());
                            System.out.println(error.getResponse());
                        }
                    });
        }

        viewPager = findViewById(R.id.viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#00004f"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.addTab(tabLayout.newTab().setText("Partowners"));
        tabLayout.addTab(tabLayout.newTab().setText("Providers"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new TabAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabPos = tab.getPosition();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

    private void parserJson2(JSONArray response2) {

            try {
                for (int i = 0; i < response2.length(); i++) {

                    Provider provider = new Provider(
                            response2.getJSONObject(i).getString("foref"),
                            response2.getJSONObject(i).getString("fonom"),
                            response2.getJSONObject(i).getString("foa1"),
                            response2.getJSONObject(i).getString("focp"),
                            response2.getJSONObject(i).getString("foville"),
                            response2.getJSONObject(i).getString("foemail")
                    );
                    DatabaseManager.getInstance(this).providerDao().insert(provider);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            lecterJSON2();

    }

    private void lecterJSON2() {
        for (Provider r : DatabaseManager.getInstance(this).providerDao().getAll());

    }

    private void parserJson(JSONArray response) {

        //if (DatabaseManager.getInstance(this).partownerDao().getAll().size() == 0){
            try {
                for (int i = 0; i < response.length(); i++) {

                    Partowner partowner = new Partowner(
                            response.getJSONObject(i).getString("petitre"),
                            response.getJSONObject(i).getString("penom"),
                            response.getJSONObject(i).getString("peprenom"),
                            response.getJSONObject(i).getString("pevoirienom"),
                            response.getJSONObject(i).getString("pead2"),
                            response.getJSONObject(i).getString("peville"),
                            response.getJSONObject(i).getString("peteldom"),
                            response.getJSONObject(i).getString("peemail")
                    );
                    DatabaseManager.getInstance(this).partownerDao().insert(partowner);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        //}
        lecterJSON();
    }

    private void lecterJSON() {
        for (Partowner r : DatabaseManager.getInstance(this).partownerDao().getAll());

    }
}

