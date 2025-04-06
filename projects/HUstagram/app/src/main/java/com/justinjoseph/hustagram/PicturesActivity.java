package com.justinjoseph.hustagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class PicturesActivity extends AppCompatActivity {
    ArrayList<JSONObject> pictures;
    RequestQueue queue;
    String url;
    ListView picturesScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pictures);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        queue = Volley.newRequestQueue(this);
        queue.start();
        picturesScrollView = findViewById(R.id.pictures_scrollview);

        url = getString(R.string.url_string);
        pictures = new ArrayList<JSONObject>();

        // getPictures is in a function to synchronize the async queue
        boolean getPicturesFinished = getPictures(pictures);
        if (getPicturesFinished){
            Log.d("./PicturesActivity.java", "Pictures received");
        }

//        Log.d("array", pictures.toString());
//        for (int i = 0; i < pictures.toArray().length; i++){
//            Log.d("pictures", pictures.get(i).toString());
//        }
    }

    // Insert the pictures from the database into the ArrayList.
    private boolean getPictures(ArrayList<JSONObject> arrayList){
        String get_images_url = url+"/getimages";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, get_images_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        arrayList.add(response.getJSONObject(i));
                        Log.d("onResponse", (response.getJSONObject(i).toString()));
                    }

                    Log.d("JsonObject Request Success", "Response: " + response.toString());

                    Log.d("array", pictures.toString());
                    //TODO: Create views for all images in the arraylist
                    PicturesAdapter adapter = new PicturesAdapter(getBaseContext(), pictures);

                    ListView picturesListView = findViewById(R.id.pictures_scrollview);
                    picturesListView.setAdapter(adapter);
                } catch (Exception e) {
                    Log.d("JsonObject Functional Error", "Response: " + response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JsonObjectRequest Error", Log.getStackTraceString(error));
                Toast.makeText(getApplicationContext(), "Picture retrieval failed!", Toast.LENGTH_SHORT);
                finish();
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        queue.add(jsonArrayRequest);


        return true;
    }

    public static Bitmap decodeToBase64(String image) {
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        byte[] decodedBytes = Base64.decode(image, Base64.DEFAULT);
        String decodedString = new String(decodedBytes);
        Log.d("Decoded String", decodedString);

        Bitmap bitmap_image = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        return bitmap_image;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("./PicturesActivity", "onResume called");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("./PicturesActivity", "onRestart called");
        recreate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("./PicturesActivity", "onStop called");
        queue.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("./PicturesActivity", "onPause called");
        queue.stop();
    }
}