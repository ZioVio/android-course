package com.example.task1;

import java.io.Serializable;

public class Country implements Serializable {
    public String name;
    public String imagePath;
    public int population;
    public String description;

    public static String MODEL_INTENT_KEY = "Country";

    public Country(String name,
                   String imagePath,
                   int population,
                   String description) {
        this.name = name;
        this.imagePath = imagePath;
        this.population = population;
        this.description = description;
    }
}
