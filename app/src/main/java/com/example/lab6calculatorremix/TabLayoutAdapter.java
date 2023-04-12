package com.example.lab6calculatorremix;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabLayoutAdapter extends FragmentStateAdapter {

    public static final int NUM_TABS = 3;

    public TabLayoutAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment fragment;

        Bundle args = new Bundle();

        switch (position) {
            case 0:
                fragment = new TipCalculator();
                break;
            case 1:
                fragment = new TemperatureConverter();
                break;
            case 2:
                fragment = new DistanceConverter();
                break;
            default:
                throw new IllegalStateException();
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}