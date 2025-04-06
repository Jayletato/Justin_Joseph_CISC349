package com.justinjoseph.hustagram;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_comment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        String id = getIntent().getStringExtra("id");
        String image = getIntent().getStringExtra("image");
        String comment = getIntent().getStringExtra("comment");

        ImageView imageView = findViewById(R.id.comment_image);
        TextView commentView = findViewById(R.id.current_comment);
        EditText newCommentView = findViewById(R.id.new_comment);
        Button finished = findViewById(R.id.finished_button);

        imageView.setImageBitmap(PicturesAdapter.decodeToBase64(image));
        commentView.setText("comment: " + comment);
        finished.setOnClickListener(v -> {
            JSONObject json = new JSONObject();
            try {
                json.put("_id", id);
                json.put("comment", newCommentView.getText().toString());
                Log.d("./UpdateCommentActivity", id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String image_url = getString(R.string.url_string) +"/updatecomment";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, image_url, json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("JsonOjbect Request Success", "Response: " + response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("JsonObjectRequest Error", Log.getStackTraceString(error));
                }
            });

            queue.add(jsonObjectRequest);
            finish();
        });
    }
}