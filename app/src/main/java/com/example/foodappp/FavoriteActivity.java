package com.example.foodappp;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodappp.Fragment.FeaturedFragment;
import com.example.foodappp.activities.Profile;
import com.example.foodappp.adapters.HomeSliderAdapter;
import com.example.foodappp.adapters.ViewPagerAdapter;
import com.example.foodappp.ui.gallery.DailyMealFragment;
import com.example.foodappp.ui.gallery.NewFoodFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class FavoriteActivity extends AppCompatActivity {

    //Slider
    ViewPager viewPager1;
    LinearLayout sliderDotspanel;
    Timer timer;
    private int dotscount;
    int page_position = 0;
    private ImageView[] dots;
    private Integer[] images = {R.drawable.burger1, R.drawable.burger2, R.drawable.burger4, R.drawable.icecream2, R.drawable.icecream3};


    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        ///Slider
        timer = new Timer();
        viewPager1 = findViewById(R.id.viewPager);

        sliderDotspanel = findViewById(R.id.SliderDots);

        HomeSliderAdapter viewPagerAdapter1 = new HomeSliderAdapter(getApplicationContext(), images);

        viewPager1.setAdapter(viewPagerAdapter1);

        dotscount = viewPagerAdapter1.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(getApplicationContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }


        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        scheduleSlider();


        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter =  new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new DailyMealFragment(), "Featured");
        viewPagerAdapter.addFragment(new FeaturedFragment(), "Popular");
        viewPagerAdapter.addFragment(new NewFoodFragment(), "New");
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
    public void scheduleSlider() {

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == dotscount) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                viewPager1.setCurrentItem(page_position, true);
            }
        };

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 4000);
    }


    @Override
    public void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    public void onPause() {
        timer.cancel();
        super.onPause();
    }




}