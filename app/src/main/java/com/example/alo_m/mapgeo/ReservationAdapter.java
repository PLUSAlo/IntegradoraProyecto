package com.example.alo_m.mapgeo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alo_m on 20/04/2018.
 */

public class ReservationAdapter extends BaseAdapter {

    TextView nameUser, date, time, price;

    Context context;

    ArrayList<Reservation> reservations;

    LayoutInflater inflater;

    public ReservationAdapter(Context context, ArrayList<Reservation> reservations){
        this.context = context;
        this.reservations = reservations;
    }
    @Override
    public int getCount() {
        return reservations.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.from(context).inflate(R.layout.reservation_list,viewGroup,false);

        nameUser= (TextView) view.findViewById(R.id.cliente);
        date = (TextView) view.findViewById(R.id.date);
        time = (TextView) view.findViewById(R.id.time) ;
        price = (TextView) view.findViewById(R.id.price);


        nameUser.setText(nameUser.getText()+reservations.get(i).getNameUser());
        date.setText(date.getText()+ reservations.get(i).getDate());
        time.setText(time.getText()+reservations.get(i).getTime());
        price.setText(price.getText()+reservations.get(i).getPrice());
        return view;
    }
}
