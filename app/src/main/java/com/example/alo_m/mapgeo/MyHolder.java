package com.example.alo_m.mapgeo;

/**
 * Created by Elena Alvarado on 13/03/2018.
 */


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Hp on 3/15/2016.
 */
public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //OUR VIEWS
    ImageView img;
    TextView nameTxt;
    TextView posTxt;
    private ItemClickListener itemClickListener;

    //our contructor
    public MyHolder(View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        posTxt= (TextView) itemView.findViewById(R.id.posTxt);
        img= (ImageView) itemView.findViewById(R.id.playerImage);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;

    }
}
