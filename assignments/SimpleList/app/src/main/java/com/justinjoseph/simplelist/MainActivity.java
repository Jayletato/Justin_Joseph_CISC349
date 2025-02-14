package com.justinjoseph.simplelist;

import android.content.Intent;
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

        //The image is currently hardcoded in the layout element so they all have the same picture
        ArrayList<User> arrayOfUsers = new ArrayList<>();
        arrayOfUsers.add(new User("Eve", "777-777-7777","Description"));
        arrayOfUsers.add(new User("John", "777-717-7777","meow"));
        arrayOfUsers.add(new User("Bingus", "127-777-7777","Currently on parole >:3"));
        arrayOfUsers.add(new User("Michael", "777-777-7777","I am hungry for thermal paste!1!"));
        arrayOfUsers.add(new User("Adam", "727-777-7777","Description"));
        arrayOfUsers.add(new User("Mary", "777-777-7764","Description"));
        arrayOfUsers.add(new User("Olivia", "777-377-7477","nyaaa :3"));


        // Create the adapter to convert the array to views
        UserAdapter adapter = new UserAdapter(this, arrayOfUsers);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_list, listItem);
        final LayoutInflater factory = getLayoutInflater();
        final View myView = factory.inflate(R.layout.my_list, null);


        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 User current_user = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), current_user.getName(), Toast.LENGTH_SHORT).show();

                // Start ProfileActivity
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                String message = "Hello from MainActivity";
                i.putExtra("Username", current_user.getName());
                i.putExtra("Phone", current_user.getPhone());
                i.putExtra("Description", current_user.getDescription());
                startActivity(i);
            }
        });

    }
}