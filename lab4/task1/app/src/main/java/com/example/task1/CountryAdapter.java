package com.example.task1;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<Country> {

    public CountryAdapter(Activity ctx, ArrayList<Country> countries) {
        super(ctx, 0, countries);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.country_button, parent, false);
        }

        final Country currentCountry = getItem(position);
        Button btn = listItemView.findViewById(R.id.country_button);
        if (btn != null && currentCountry != null) {
            btn.setText(currentCountry.name);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCountruActivity(currentCountry);
                }
            });
        } else {
            return null;
        }

        return listItemView;
    }

    private void openCountruActivity(Country c) {
        Intent in = new Intent();
        in.setClass(getContext(), DisplayCountryActivity.class);
        in.putExtra(Country.MODEL_INTENT_KEY, c);
        getContext().startActivity(in);
    }
}
