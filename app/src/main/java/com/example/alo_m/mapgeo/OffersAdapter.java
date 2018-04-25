package com.example.alo_m.mapgeo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Android Studio on 18/04/2018.
 */

public class OffersAdapter extends ArrayAdapter<Offer> {
    Activity activity;
    int resource;
    List<Offer> list;

    public OffersAdapter(Activity activity, int resource, List<Offer> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View view = layoutInflater.inflate(resource, null);

        ImageView imageView = view.findViewById(R.id.imv_get);
        TextView name = view.findViewById(R.id.edt_name_offers);
        TextView description = view.findViewById(R.id.edt_description);
        TextView price = view.findViewById(R.id.edt_price);

        name.setText(list.get(position).getName());
        description.setText(list.get(position).getDescription());
        price.setText(list.get(position).getPrice());
        Glide.with(activity).load(list.get(position).getImageUri()).into(imageView);
        return view;
    }

}
