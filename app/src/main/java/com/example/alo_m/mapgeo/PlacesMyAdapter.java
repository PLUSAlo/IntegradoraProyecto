package com.example.alo_m.mapgeo;

/**
 * Created by Elena Alvarado on 13/03/2018.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hp on 3/15/2016.
 */
public class PlacesMyAdapter extends RecyclerView.Adapter<PlacesMyHolder>{

    //FIELDS TO STORE PASSED IN VALUES
    Context c;
    String[] players;
    String[] positions;
    int[] images;

    public PlacesMyAdapter(Context ctx, String[] players, String[] positions, int[] images)
    {
        //ASSIGN THEM
        this.c=ctx;
        this.players=players;
        this.positions=positions;
        this.images=images;

    }
    @Override
    public PlacesMyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //INFLATE A VIEW FROM XML
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.places_model,null);

        //HOLDER
        PlacesMyHolder holder=new PlacesMyHolder(v);

        return holder;
    }


    //DATA IS BEING BOUND TO VIEWS
    @Override
    public void onBindViewHolder(PlacesMyHolder holder, final int position) {

        //BIND DATA
        //holder.nameTxt.setText(players[position]);
        //holder.posTxt.setText(positions[position]);
        holder.img.setImageResource(images[position]);

        //WHEN ITEM IS CLICKED
        holder.setItemClickListener(new PlacesItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                //INTENT OBJ
                Intent i=new Intent(c,PlacesDetailActivity.class);

                //ADD DATA TO OUR INTENT
                i.putExtra("Name",players[position]);
                i.putExtra("Position",positions[position]);
                i.putExtra("Image",images[position]);

                //START DETAIL ACTIVITY
                c.startActivity(i);

            }
        });

    }

    //TOTAL PLAYERS
    @Override
    public int getItemCount() {
        return players.length;
    }
}
