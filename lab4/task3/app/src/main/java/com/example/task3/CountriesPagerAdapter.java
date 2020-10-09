package com.example.task3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CountriesPagerAdapter extends FragmentPagerAdapter {

    private Country[] countries;
    CountriesPagerAdapter(@NonNull FragmentManager fm, Country[] countries) {
        super(fm);
        this.countries = countries;
    }
    @Override
    public int getCount() {
        return this.countries.length + 1;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position <= 0 || position > (this.countries.length + 1)) {
            return CountryButtonsFragment.newInstance(this.countries);
        } else {
            return CountryDetailsFragment.newInstance(this.countries[position - 1]);
        }
    }
}
