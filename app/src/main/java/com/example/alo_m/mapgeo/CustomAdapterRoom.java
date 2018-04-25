package com.example.alo_m.mapgeo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapterRoom extends BaseAdapter {

    TextView name, room, date, time, price;

    Context context;

    ArrayList<RoomReservation> data;

    LayoutInflater inflater;

    public CustomAdapterRoom(Context context, ArrayList<RoomReservation> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.from(context).inflate(R.layout.custom_list_room,viewGroup,false);

        room= (TextView) view.findViewById(R.id.read_room);
        name = (TextView) view.findViewById(R.id.read_name);
        date = (TextView) view.findViewById(R.id.read_date);
        time = (TextView) view.findViewById(R.id.read_time);
        price = (TextView) view.findViewById(R.id.read_price);

        room.setText(room.getText()+data.get(i).getRoom());
        name.setText(name.getText()+data.get(i).getName());
        date.setText(date.getText()+data.get(i).getDate());
        time.setText(time.getText()+data.get(i).getTime());
        price.setText(price.getText()+""+ data.get(i).getPrice());



        return view;
    }
}
