package com.example.alo_m.mapgeo;

public class RoomReservation {

    String key;
    String room;//RoomReservation
    String name;//Client
    String date;//Date
    String time;//Time
    int price;//Price


    public RoomReservation() {
    }

    public RoomReservation(String key, String room, String name, String date, String time, int price) {
        this.key = key;
        this.room = room;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
        return "RoomReservation{" +
                "key='" + key + '\'' +
                ", serviceReservation='" + room + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                '}';
    }
}

