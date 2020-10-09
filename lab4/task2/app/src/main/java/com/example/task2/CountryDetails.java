package com.example.task2;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetails extends Fragment {

    private static final String ARG_PARAM = Country.MODEL_INTENT_KEY;

    private Country country;

    private TextView nameTextView;
    private ImageView imageView;
    private TextView populationView;
    private TextView descriptionView;

    public CountryDetails() {
    }

    public static CountryDetails newInstance(Country country) {
        CountryDetails fragment = new CountryDetails();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            country = (Country) getArguments().getSerializable(ARG_PARAM);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_details, container, false);
        this.initViews(view);
        this.setUpEventListeners(view);
        this.onReceiveCountry(this.country);
        return view;
    }

    private void initViews(View v) {
        this.nameTextView = v.findViewById(R.id.country_name);
        this.imageView = v.findViewById(R.id.country_image);
        this.populationView = v.findViewById(R.id.country_population);
        this.descriptionView = v.findViewById(R.id.country_description);
    }


    private void onReceiveCountry(Country c) {
        this.nameTextView.setText(c.name);
        this.setImageFromUrl(this.imageView, c.imagePath);
        this.populationView.setText(getString(R.string.population_text, c.population));
        this.descriptionView.setText(c.description);
    }

    private void setUpEventListeners(View root) {
        Button finishReadingButton = root.findViewById(R.id.finish_reading_button);
        if (finishReadingButton != null) {
            finishReadingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager().beginTransaction().remove(CountryDetails.this).commit();
                }
            });
        }
    }

    private void setImageFromUrl(ImageView view, String url) {
        // @todo async task
    }
}