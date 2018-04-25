package com.example.alo_m.mapgeo;

/**
 * Created by alo_m on 22/04/2018.
 */

public class PackageReservation {
    String key;
    String packages;
    String name;//Client
    String date;//Date
    String time;//Time
    int price;//Price

    public PackageReservation() {
    }

    public PackageReservation(String key, String packages, String name, String date, String time, int price) {
        this.key = key;
        this.packages = packages;
        this.name = name;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PackageReservation{" +
                "key='" + key + '\'' +
                ", service='" + packages + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                '}';
    }
}
