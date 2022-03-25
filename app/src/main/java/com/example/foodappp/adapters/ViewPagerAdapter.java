package com.example.foodappp.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private final ArrayList<String> fragmentTitle = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public  void  addFragment (Fragment fragment, String title)
    {
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }
}



//    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
//        super(fragmentManager, lifecycle);
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        switch (position)
//        {
//            case 0:
//                return new HomeFragment();
//            case 1:
//                return  new Food1Fragment();
//            case 3:
//                return  new Food2Fragment();
//            default:
//                return new HomeFragment();
//
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 3;
//    }
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        String title = "";
//
//        switch (position)
//        {
//            case 0:
//                title = "Home";
//            case 1:
//                title = "Food1";
//            case 2:
//                title = "Food2";
//
//        }
//
//        return super.getItemId(position);
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//}
