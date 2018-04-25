package com.example.alo_m.mapgeo;

/**
 * Created by Android Studio on 18/04/2018.
 */

public class Offer {
    String id;
    String name;
    String description;
    String price;
    String imageUri;
    String key;

    public Offer(String id, String name, String description, String price, String imageUri) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUri = imageUri;
    }
    public Offer(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", imageUri='" + imageUri + '\'' +
                ", key= '"+key+"'}'";
    }
}
