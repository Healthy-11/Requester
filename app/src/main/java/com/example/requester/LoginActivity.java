package com.example.requester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.renderscript.Element;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import okhttp3.Credentials;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE
            }, 100);
        }
        prefs = getApplicationContext().getSharedPreferences("PrefsFile", MODE_PRIVATE);
        editor = prefs.edit();

        System.out.println("Coucou");
        

        Button login_btn = (Button) findViewById(R.id.buttonLogin);
        EditText edit_username = (EditText) findViewById(R.id.editTextTextUsername);
        EditText edit_password = (EditText) findViewById(R.id.editTextTextPassword);

        edit_username.setText(prefs.getString("Username", ""));
        edit_password.setText(prefs.getString("Password", ""));

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();

                if (!edit_password.getText().toString().isEmpty() && !edit_username.getText().toString().isEmpty()) {


                    JSONObject mahObject = new JSONObject();
                    try {
                        mahObject.put("username", username);
                        mahObject.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (mahObject != null) {
                        AndroidNetworking.post("https://sudcopro.immo/api/login_check")
                                .addHeaders("Content-Type", "application/json")
                                .addJSONObjectBody(mahObject)
                                .setPriority(Priority.LOW)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        String token = null;
                                        try {
                                            token = response.getString("token");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        editor.putString("Token", token);
                                        editor.putString("Username", username);
                                        editor.putString("Password", password);
                                        editor.apply();

                                        AndroidNetworking.get("https://sudcopro.immo/api/sync/condos/999")
                                                .addHeaders("Content-Type", "application/json")
                                                .addHeaders("Authorization", "Bearer " + token)
                                                .setPriority(Priority.LOW)
                                                .build()
                                                .getAsJSONArray(new JSONArrayRequestListener() {
                                                    @Override
                                                    public void onResponse(JSONArray response) {
                                                        parserJson(response);



                                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                        Toast.makeText(LoginActivity.this, "Login success,\ntoken accepted", Toast.LENGTH_SHORT).show();
                                                        startActivity(intent);
                                                    }
                                                    @Override
                                                    public void onError(ANError error) {
                                                        // handle error
                                                        Toast.makeText(LoginActivity.this, "Incorrect token", Toast.LENGTH_SHORT).show();
                                                        System.out.println("ERROR");
                                                        System.out.println(error.getErrorBody());
                                                        System.out.println(error.getErrorCode());
                                                        System.out.println(error.getErrorDetail());
                                                        System.out.println(error.getResponse());
                                                    }
                                                });
                                    }

                                    @Override
                                    public void onError(ANError error) {
                                        Toast.makeText(LoginActivity.this, "Username or password incorrects", Toast.LENGTH_SHORT).show();
                                        System.out.println("ERROR");
                                        System.out.println(error.getErrorBody());
                                        System.out.println(error.getErrorCode());
                                        System.out.println(error.getErrorDetail());
                                        System.out.println(error.getResponse());
                                    }
                                });
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Please fill informations", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void parserJson(JSONArray response) {

        if (DatabaseManager.getInstance(this).condoDao().getAll().size() == 0){
        try {
            for (int i = 0; i < response.length(); i++) {

                Condo condo = new Condo(
                        response.getJSONObject(i).getString("id"),
                        response.getJSONObject(i).getString("societe"),
                        response.getJSONObject(i).getString("cccleunik"),
                        response.getJSONObject(i).getString("cccopro"),
                        response.getJSONObject(i).getString("ccnom"),
                        response.getJSONObject(i).getString("cccp"),
                        response.getJSONObject(i).getString("ccville")
                );
                DatabaseManager.getInstance(this).condoDao().insert(condo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        lecterJSON();
    }}

    private void lecterJSON() {
        for (Condo r : DatabaseManager.getInstance(this).condoDao().getAll()) ;

    }
}