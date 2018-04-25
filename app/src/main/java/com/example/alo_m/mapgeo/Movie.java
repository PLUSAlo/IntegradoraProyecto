package com.example.alo_m.mapgeo;

/**
 * Created by Elena Alvarado on 29/03/2018.
 */

public class Movie {
    private String Name, ImageURL;

    public Movie(String name, String imageURL) {
        Name = name;
        ImageURL = imageURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
