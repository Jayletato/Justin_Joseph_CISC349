package com.justinjoseph.simplelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> arrayList;
    private TextView name, phone, description;

    public UserAdapter(Context context, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public User getItem(int position) { return arrayList.get(position);}

    @Override
    public long getItemId(int position) {
        return position; //change this later, it's wrong
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false );
        name = convertView.findViewById(R.id.name);
        phone = convertView.findViewById(R.id.phone);
        description = convertView.findViewById(R.id.description);
        name.setText(arrayList.get(position).getName());
        phone.setText(arrayList.get(position).getPhone());
        description.setText(arrayList.get(position).getDescription());
        return convertView;
    }
}
