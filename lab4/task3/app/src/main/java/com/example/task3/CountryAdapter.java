package com.example.task3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<Country> {

    private FragmentTransaction ft;

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
//                    todo work with this handler
                    openCountryFragment(currentCountry);
                }
            });
        } else {
            return null;
        }

        return listItemView;
    }


    private void openCountryFragment(Country c) {
        ((MainActivity)getContext()).onGoToCountry(c);
    }

    private FragmentManager getFragmentManager() {
        return ((Activity) getContext()).getFragmentManager();
    }

    private FragmentTransaction getFragmentTransaction() {
        return this.getFragmentManager().beginTransaction();
    }
}
