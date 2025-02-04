package com.justinjoseph.simplelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    String[] listItem;

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
//
//
//        final LayoutInflater factory = getLayoutInflater();
//        final View myView = factory.inflate(R.layout.my_list, null);
//        textView = (TextView) myView.findViewById(R.id.textView);
//
//        listView = findViewById(R.id.listView);
//        listItem = getResources().getStringArray(R.array.array_technology);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_list, listItem);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String value = adapter.getItem(position);
//                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT). show();
//            }
//        });

        ArrayList<User> arrayOfUsers = new ArrayList<>();
        arrayOfUsers.add(new User("Eve", "777-777-7777"));
        arrayOfUsers.add(new User("John", "777-777-7777"));
        arrayOfUsers.add(new User("Mark", "777-777-7777"));
        arrayOfUsers.add(new User("Michael", "777-777-7777"));
        arrayOfUsers.add(new User("Adam", "777-777-7777"));
        arrayOfUsers.add(new User("Mary", "777-777-7777"));
        arrayOfUsers.add(new User("Olivia", "777-777-7777"));


        // Create the adapter to convert the array to views
        UserAdapter adapter = new UserAdapter(this, arrayOfUsers);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_list, listItem);
        final LayoutInflater factory = getLayoutInflater();
        final View myView = factory.inflate(R.layout.my_list, null);


        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            String value = adapter.getItem(position);
//            Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT). show();
//        }

    }
}