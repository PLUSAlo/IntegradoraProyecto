package com.example.alo_m.mapgeo;

public class Data {

    String key;

    String name;
    String email;
    int phone;

    public Data(String key, String name, String nameUser, String date, String time, String price){

    }


    public Data(String key, String name, String email, int phone) {
        this.key = key;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Data(){

    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Data{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }
}
