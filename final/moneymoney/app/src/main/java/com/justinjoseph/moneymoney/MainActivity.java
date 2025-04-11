package com.justinjoseph.moneymoney;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.justinjoseph.moneymoney.databinding.ActivityMainBinding;
import com.justinjoseph.moneymoney.main_fragments.DailyFragment;
import com.justinjoseph.moneymoney.main_fragments.HomeFragment;
import com.justinjoseph.moneymoney.main_fragments.WeeklyFragment;

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
        View view = binding.getRoot();
        setContentView(view);
        replaceFragment(new HomeFragment());

        Toolbar titleToolbar = view.findViewById(R.id.title_bar);
        titleToolbar.setTitle("Home");
//        setSupportActionBar(titleToolbar);

        // Sets binding to fragment from the navigation menu
        binding.bottomNavigationView.setOnItemSelectedListener(item -> { // I couldn't get this in a switch statement
            if (item.getItemId() == R.id.home_tab) {
                replaceFragment(new HomeFragment());
                titleToolbar.setTitle("Home");
            }
            else if (item.getItemId() == R.id.daily_tab) {
                replaceFragment(new DailyFragment());
                titleToolbar.setTitle("Daily");
            } else if (item.getItemId() == R.id.weekly_tab) {
                replaceFragment(new WeeklyFragment());
                titleToolbar.setTitle("Weekly");
            } else if (item.getItemId() == R.id.monthly_tab) {
                replaceFragment(new WeeklyFragment());
                titleToolbar.setTitle("Monthly");
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