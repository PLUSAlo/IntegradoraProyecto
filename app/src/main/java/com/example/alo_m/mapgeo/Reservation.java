package com.example.alo_m.mapgeo;

/**
 * Created by alo_m on 20/04/2018.
 */

public class Reservation {

    String key;

    String nameUser;
    String date;
    String time;
    String price;

    public Reservation(){
    }

    public Reservation(String key, String nameUser, String date, String time, String price) {
        this.key = key;
        this.nameUser = nameUser;
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


    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String toString() {
        return "Reservation{" +
                "key='" + key + '\'' +
                ", nameUser='" + nameUser + '\'' +
                ", date=" + date + + '\'' +
                ", time=" + time + + '\'' +
                ", price=" + price + + '\'' +
                '}';
    }
}
