package com.justinjoseph.dynamiclistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class HolidaySongsAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Song> arrayList;
    private NetworkImageView album_image;
    private TextView song_name, album_name, artist_name, danceability, duration;

    public HolidaySongsAdapter(Context context, ArrayList<Song> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Song getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        album_image = convertView.findViewById(R.id.album_image);
        song_name = convertView.findViewById(R.id.song_name);
        album_name = convertView.findViewById(R.id.album_name);
        artist_name = convertView.findViewById(R.id.artist_name);
        danceability = convertView.findViewById(R.id.danceability);
        duration = convertView.findViewById(R.id.duration);

        RequestQueue queue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String,
                    Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);

            }
        });

        Integer duration_minutes = 0;
        Integer duration_seconds= 0;
        try {
            Integer i_duration = Math.floorDiv(Integer.parseInt(arrayList.get(position).getDuration_ms()), 1000);
            duration_minutes = Math.floorDiv(i_duration, 60);
            duration_seconds = i_duration % 60;
        } catch (Exception e) {
            Log.d(".HolidaySongsAdapter", "Duration conversion error");
        }


        album_image.setImageUrl(arrayList.get(position).getAlbum_image_url(), imageLoader);
        song_name.setText(arrayList.get(position).getSong_name());
        album_name.setText("Album: " + arrayList.get(position).getAlbum_name());
        artist_name.setText("Artist: " + arrayList.get(position).getArtist_name());
        danceability.setText("Danceability: " + arrayList.get(position).getDanceability());
        duration.setText(String.format("Duration: %d%s %d%s", duration_minutes, "m", duration_seconds, "s"));

        return convertView;
    }
}
