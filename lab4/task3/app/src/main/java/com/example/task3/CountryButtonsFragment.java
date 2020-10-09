package com.example.task3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryButtonsFragment extends Fragment {


    private static final String COUNTRIES = "countries";

    private ListView countriesContainer;

    private Country[] countries;

    public CountryButtonsFragment() {
        // Required empty public constructor
    }

    public static CountryButtonsFragment newInstance(Country[] countries) {
        CountryButtonsFragment fragment = new CountryButtonsFragment();
        Bundle args = new Bundle();
        args.putSerializable(COUNTRIES, countries);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countries = (Country[]) getArguments().getSerializable(COUNTRIES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_country_buttons, container, false);
        this.countriesContainer = parent.findViewById(R.id.countries_container);
        appendButtons(countriesContainer, countries);
        return parent;
    }




    private void appendButtons(ListView container, final Country[] countries) {
        final ArrayList<Country> countriesList = new ArrayList<Country>(Arrays.asList(countries));
        CountryAdapter countryAdapter = new CountryAdapter(getActivity(), countriesList);
        container.setAdapter(countryAdapter);
    }
}