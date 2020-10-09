package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Country[] countries = getCountries();
        this.appendButtons(countries);
    }

    private Country[] getCountries() {
        return new Country[] {
                new Country("Switzerland", "https://en.wikipedia.org/wiki/File:Flag_of_Switzerland.svg", 8570000, "Switzerland, officially the Swiss Confederation, is a country situated at the confluence of Western, Central, and Southern Europe.[12][note 4] It is a federal republic composed of 26 cantons, with federal authorities based in Bern.[1][2][note 1] Switzerland is a landlocked country bordered by Italy to the south, France to the west, Germany to the north, and Austria and Liechtenstein to the east. It is geographically divided among the Swiss Plateau, the Alps, and the Jura, spanning a total area of 41,285 km2 (15,940 sq mi), and land area of 39,997 km2 (15,443 sq mi). While the Alps occupy the greater part of the territory, the Swiss population of approximately 8.5 million is concentrated mostly on the plateau, where the largest cities and economic centres are located, among them Zürich, Geneva and Basel. These cities are home to several offices of international organisations such as the headquarters of FIFA, the UN's second-largest Office, and the main building of the Bank for International Settlements. The main international airports of Switzerland are also located in these cities."),
                new Country("Poland", "https://en.wikipedia.org/wiki/File:Flag_of_Poland.svg", 37970000 , "Poland (Polish: Polska [ˈpɔlska] (About this soundlisten)), officially the Republic of Poland (Polish: Rzeczpospolita Polska[c] [ʐɛt͡ʂpɔˈspɔlita ˈpɔlska] (About this soundlisten)), is a country located in Central Europe.[12] It is divided into 16 administrative subdivisions, covering an area of 312,696 square kilometres (120,733 sq mi), and has a largely temperate seasonal climate.[7] With a population of nearly 38.5 million people, Poland is the fifth most populous member state of the European Union.[7] Poland's capital and largest metropolis is Warsaw. Other major cities include Kraków, Łódź, Wrocław, Poznań, Gdańsk, and Szczecin.\n" + "\n" + "Poland is bordered by the Baltic Sea, Lithuania, and Russia's Kaliningrad Oblast to the north, Belarus and Ukraine to the east, Slovakia and the Czech Republic to the south, and Germany to the west. "),
                new Country("UK", "https://en.wikipedia.org/wiki/File:Flag_of_the_United_Kingdom.svg", 66650000 , "The United Kingdom of Great Britain and Northern Ireland, commonly known as the United Kingdom (UK or U.K.)[14] or Britain,[note 10] is a sovereign country located off the north\u00ADwestern coast of the European mainland. The United Kingdom includes the island of Great Britain, the north-eastern part of the island of Ireland, and many smaller islands.[15] Northern Ireland shares a land border with the Republic of Ireland. Otherwise, the United Kingdom is surrounded by the Atlantic Ocean, with the North Sea to the east, the English Channel to the south and the Celtic Sea to the southwest, giving it the 12th-longest coastline in the world. The Irish Sea separates Great Britain and Ireland. The total area of the United Kingdom is 94,000 square miles (240,000 km2). "),
        };
    }


    private void appendButtons(final Country[] countries) {
        ListView container = findViewById(R.id.countries_container);
        final ArrayList<Country> countriesList = new ArrayList<Country>(Arrays.asList(countries));
        CountryAdapter countryAdapter = new CountryAdapter(this, countriesList);
        container.setAdapter(countryAdapter);
    }
}