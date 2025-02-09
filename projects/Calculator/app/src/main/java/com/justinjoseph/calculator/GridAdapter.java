package com.justinjoseph.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<NumberModel> {

    private final ArrayList<NumberModel> list;
    public GridAdapter(Context context, ArrayList<NumberModel> list) {
        super(context, 0, list);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NumberModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        NumberModel model = getItem(position);

        TextView textView = itemView.findViewById(R.id.grid_button);

        if (model != null) {
            textView.setText(model.getNumber());
        }
        return itemView;
    }

}

//package com.justinjoseph.calculator;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//
//public class GridAdapter extends BaseAdapter {
//
//    private final Context context;
//    private final String[] buttons;
//    public GridAdapter(Context context, String[] buttons) {
//        this.context = context;
//        this.buttons = buttons;
//    }
//
//    LayoutInflater inflater;
//
//    @Override
//    public int getCount() {
//        return buttons.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return buttons[position];
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (inflater == null) {
//            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.grid_item,null);
//        }
//
//        Button grid_button = convertView.findViewById(R.id.grid_button);
//        grid_button.setText(buttons[position]);
//
//        return convertView;
//    }
//}
