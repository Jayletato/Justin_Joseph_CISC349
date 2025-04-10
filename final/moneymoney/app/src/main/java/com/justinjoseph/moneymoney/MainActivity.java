package com.justinjoseph.moneymoney;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.justinjoseph.moneymoney.databinding.ActivityMainBinding;
import com.justinjoseph.moneymoney.fragments.DailyFragment;
import com.justinjoseph.moneymoney.fragments.HomeFragment;
import com.justinjoseph.moneymoney.fragments.WeeklyFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

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

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        // Sets binding to fragment from the navigation menu
        binding.bottomNavigationView.setOnItemSelectedListener(item -> { // I couldn't get this in a switch statement
            if (item.getItemId() == R.id.home_tab) {
                replaceFragment(new HomeFragment());
            }
            else if (item.getItemId() == R.id.daily_tab) {
                replaceFragment(new DailyFragment());
            } else if (item.getItemId() == R.id.weekly_tab) {
                replaceFragment(new WeeklyFragment());
            } else if (item.getItemId() == R.id.monthly_tab) {
                replaceFragment(new WeeklyFragment());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}