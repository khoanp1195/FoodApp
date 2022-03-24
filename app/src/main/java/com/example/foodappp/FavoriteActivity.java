package com.example.foodappp;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodappp.Fragment.Food1Fragment;
import com.example.foodappp.Fragment.Food2Fragment;
import com.example.foodappp.activities.Profile;
import com.example.foodappp.adapters.ViewPagerAdapter;
import com.example.foodappp.ui.gallery.DailyMealFragment;
import com.example.foodappp.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.example.foodappp.databinding.ActivityFavoriteBinding;
import com.google.android.material.tabs.TabLayout;

public class FavoriteActivity extends AppCompatActivity {



    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter =  new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new DailyMealFragment(), "Home");
        viewPagerAdapter.addFragment(new Food1Fragment(), "Food1");
        viewPagerAdapter.addFragment(new Food2Fragment(), "Food2");
        viewPager.setAdapter(viewPagerAdapter);






        /*------------------------------------------Bottom Navigation View----------------------------------------------------------------------*/
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.favorite);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    case R.id.favorite:
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(getApplication(), SettingFragment.class));

                        /*
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.feedback:
                        startActivity(new Intent(getApplicationContext(), ReportProblemActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                         */
                }
                return false;
            }
        });
        /*------------------------------------------Bottom Navigation View----------------------------------------------------------------------*/





    }


}