package com.justinjoseph.dynamiclistview;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Song {
    private final String album_image_url;
    private final String song_name;
    private final String album_name;
    private final String artist_name;
    private final String danceability;
    private final String duration_ms;
    private final String playlist_image_url;

    public Song(String album_image_url,String song_name, String album_name, String artist_name, String danceability, String duration_ms, String playlist_image_url) {
        this.album_image_url = album_image_url;
        this.song_name = song_name;
        this.album_name = album_name;
        this.artist_name = artist_name;
        this.danceability = danceability;
        this.duration_ms = duration_ms;
        this.playlist_image_url = playlist_image_url;
    }

    public String getSong_name(){
        return song_name;
    }
    public String getAlbum_image_url() {
        return album_image_url;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public String getDanceability() {
        return danceability;
    }

    public String getDuration_ms() {
        return duration_ms;
    }

    public String getPlaylist_image_url() { return playlist_image_url; }
}
