package com.justinjoseph.hustagram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PicturesAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<JSONObject> arrayList;
    private ImageView pictureView;
    private TextView datetimeView, commentView;

    public PicturesAdapter(Context context, ArrayList<JSONObject> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public JSONObject getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static Bitmap decodeToBase64(String image) {
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        byte[] decodedBytes = Base64.decode(image, Base64.DEFAULT);
        String decodedString = new String(decodedBytes);
//        Log.d("Decoded String", decodedString);

        Bitmap bitmap_image = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        return bitmap_image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        pictureView = convertView.findViewById(R.id.image_item_view);
        datetimeView = convertView.findViewById(R.id.datetime);
        commentView = convertView.findViewById(R.id.comment);

//        playlist_image.setImageUrl(arrayList.get(position).getAlbum_image_url(), imageLoader);
        try {
            String id = arrayList.get(position).getString("_id");
            String image = arrayList.get(position).getString("image");
            String datetime = arrayList.get(position).getString("datetime");
            String comment = arrayList.get(position).getString("comment");

            pictureView.setImageBitmap(decodeToBase64(image));
            datetimeView.setText("datetime: " + datetime);
            commentView.setText("comment: " + comment);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent updateCommentIntent = new Intent(context, UpdateCommentActivity.class);
                    updateCommentIntent.putExtra("id", id);
                    updateCommentIntent.putExtra("image", image);
                    updateCommentIntent.putExtra("comment", comment);
                    updateCommentIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(updateCommentIntent);
                }
            });
        } catch (JSONException e) {
            Log.d("./PicturesAdapter", "Pictures Adapter JSON Error");
        }


        return convertView;
    }
}
