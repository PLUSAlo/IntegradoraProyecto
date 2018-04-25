package com.example.alo_m.mapgeo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapterService extends BaseAdapter {

    TextView name, service, date, time, price;

    Context context;

    ArrayList<ServiceReservation> data;

    LayoutInflater inflater;

    public CustomAdapterService(Context context, ArrayList<ServiceReservation> data) {
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

        view = inflater.from(context).inflate(R.layout.custom_list_service,viewGroup,false);

        service = (TextView) view.findViewById(R.id.read_service);
        name = (TextView) view.findViewById(R.id.read_name);
        date = (TextView) view.findViewById(R.id.read_date);
        time = (TextView) view.findViewById(R.id.read_time);
        price = (TextView) view.findViewById(R.id.read_price);

        service.setText(service.getText()+data.get(i).getService());
        name.setText(name.getText()+data.get(i).getName());
        date.setText(date.getText()+data.get(i).getDate());
        time.setText(time.getText()+data.get(i).getTime());
        price.setText(price.getText()+""+ data.get(i).getPrice());



        return view;
    }
}
