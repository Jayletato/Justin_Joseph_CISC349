package com.justinjoseph.dynamiclistview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    String albumImage, songName, albumName, artistName, danceability, durationMs, playlistImage;
    private NetworkImageView image;
    private final ArrayList<Song> arrayOfSongs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    String json_url = "https://nua.insufficient-light.com/data/holiday_songs_spotify.json";
    RequestQueue queue = Volley.newRequestQueue(this);
    /// Create JSONArrayRequest  from url
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject JsonObjectFromArray = response.getJSONObject(i);
                    albumImage = JsonObjectFromArray.getString("album_img");
                    songName = JsonObjectFromArray.getString("track_name");
                    albumName = JsonObjectFromArray.getString("album_name");
                    artistName = JsonObjectFromArray.getString("artist_name");
                    danceability = JsonObjectFromArray.getString("danceability");
                    durationMs = JsonObjectFromArray.getString("duration_ms");
                    // Using "album_img" instead of "playlist_img" since the playlist link in this JSON File is invalid
                    playlistImage = JsonObjectFromArray.getString("album_img");
                    arrayOfSongs.add(new Song(albumImage, songName, albumName, artistName, danceability, durationMs, playlistImage));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            HolidaySongsAdapter adapter = new HolidaySongsAdapter(getApplicationContext(), arrayOfSongs);

            final LayoutInflater factory = getLayoutInflater();
            final View myView = factory.inflate(R.layout.my_list,null);

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Song current_song = adapter.getItem(position);

                    //Pass album name and playlist image to next activity
                    Intent i = new Intent(MainActivity.this, CloseUpActivity.class);
                    i.putExtra("Album Name", current_song.getAlbum_name());
                    i.putExtra("Playlist Image URL", current_song.getPlaylist_image_url());
                    startActivity(i);


                }
            });

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(".MainActivity.OnCreate.jsonArrayRequest", Objects.requireNonNull(error.getMessage()));
        }
    });
    queue.add(jsonArrayRequest);

    }
}
