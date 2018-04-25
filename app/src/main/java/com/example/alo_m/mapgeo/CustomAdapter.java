package com.example.alo_m.mapgeo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    TextView name, email, phone;

    Context context;

    ArrayList<Data> data;

    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Data> data) {
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

        view = inflater.from(context).inflate(R.layout.custom_list,viewGroup,false);

        name = (TextView) view.findViewById(R.id.read_name);
        email = (TextView) view.findViewById(R.id.read_email);
        phone = (TextView) view.findViewById(R.id.read_phone);


        name.setText(name.getText()+data.get(i).getName());
        email.setText(email.getText()+data.get(i).getEmail());
        phone.setText(phone.getText()+""+ data.get(i).getPhone());



        return view;
    }
}
