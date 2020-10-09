package com.example.task1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayCountryActivity extends AppCompatActivity {

    private TextView nameTextView;
    private ImageView imageView;
    private TextView populationView;
    private TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_country);
        this.initViews();
        try {
            Country country = (Country) getIntent().getExtras().get(Country.MODEL_INTENT_KEY);
            this.onReceiveCountry(country);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            finish();
        }
    }

    public void onFinishReading(View v) {
        this.finish();
    }

    private void initViews() {
        this.nameTextView = findViewById(R.id.country_name);
        this.imageView = findViewById(R.id.country_image);
        this.populationView = findViewById(R.id.country_population);
        this.descriptionView = findViewById(R.id.country_description);
    }


    private void onReceiveCountry(Country c) {
        this.nameTextView.setText(c.name);
        this.setImageFromUrl(this.imageView, c.imagePath);
        this.populationView.setText(getString(R.string.population_text, c.population));
        this.descriptionView.setText(c.description);
    }

    private void setImageFromUrl(ImageView view, String url) {
        // @todo async task
    }

}