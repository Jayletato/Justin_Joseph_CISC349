package com.justinjoseph.logintwo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPassword;
    private EditText editTextUsername;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextPassword = findViewById(R.id.password_input);
        editTextUsername = findViewById(R.id.username_input);
        buttonLogin = findViewById(R.id.login_button);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.24:5000/json-example";

        buttonLogin.setOnClickListener(v -> {
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("username", editTextUsername.getText().toString());
                jsonBody.put("password",editTextPassword.getText().toString());
            } catch (JSONException e){
                throw new RuntimeException(e);
            }

            JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
                }
            });
            queue.add(loginRequest);

        });

        queue.start();

    }
}