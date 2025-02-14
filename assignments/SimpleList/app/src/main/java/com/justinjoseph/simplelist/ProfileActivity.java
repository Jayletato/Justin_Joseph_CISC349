package com.justinjoseph.simplelist;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
   private TextView username;
   private TextView phone_number;
   private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = (TextView) findViewById(R.id.profile_name);
        phone_number = (TextView) findViewById(R.id.profile_pnumber);
        description = (TextView) findViewById(R.id.profile_description);


        Log.d("ProfileActivity",String.format("username = %s", getIntent().getStringExtra("Username")));
        Log.d("ProfileActivity",String.format("phone_number = %s", getIntent().getStringExtra("Phone")));
        Log.d("ProfileActivity",String.format("description = %s", getIntent().getStringExtra("Description")));
        username.setText(getIntent().getStringExtra("Username"));
        phone_number.setText(getIntent().getStringExtra("Phone"));
        description.setText(getIntent().getStringExtra("Description"));
    }
}