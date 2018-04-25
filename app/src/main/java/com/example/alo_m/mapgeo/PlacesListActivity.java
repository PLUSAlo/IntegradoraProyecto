package com.example.alo_m.mapgeo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class PlacesListActivity extends AppCompatActivity {

    //Nombre de Lugares Turisticos
    String[] names={
            "Templo de la Tercera Orden",
            "Parroquia Ntra. Señora de los Dolores" ,
            "Museo José Alfredo",
            "Museo Desendientes de Hidalgo",
            "Museo de la Independencia",
            "Museo Bicentenario",
            "Casa de Hidalgo",
            "Museo del Vino",
            "Monumento a los Héroes",
            "Glorieta de José Alfredo",
            "Mausoleo José Alfredo",
            "Parador José Alfredo",
            "Parroquia de la Asunción",
            "Monumento a la Bandera",
            "Iglesia el Calvario",
            "Jardin Principal",
            "Kiosko"};

    // Descripción de individul de los restaurantes
    String[] positions={
            "\n \t \tPuebla 7, Centro\n" +"Dolores Hidalgo, Gto. \n \t \t· 37800",
            "\n \t \tPlaza Principal s/n, Centro" + "\n \t \t·Dolores Hidalgo, Gto \n \t \t· 37800",
            "\n \t \tGuanajueto 13, Centro" + "\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tHidalgo 2, Centro"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tZacatecas 6, Centro\n"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",//
            "\n \t \tPlaza Principal s/n, Centro\n"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tMorelos 1, Centro\n"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tHidalgo 14-16, Centro\n"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tCalzada de los Héroes 119, Centro\n \t \t"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tHidalgo 14-16, Centro\n"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tDolores Hidalgo-Guanajueto s/n, Centro\n"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tDolores Hidalgo-Guanajuato Km. 4, Francisco I. Madero\n"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tPuebla 32, Centro\n"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tCalle Quintana Roo 4, Centro\n \t \t"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tGuanajuato 35B, Centro\n"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tJardín Grande Hidalgo 25, Centro\n \t \t"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800",
            "\n \t \tJardín Principal s/n, Centro\n \t \t"+"\n \t \t·Dolores Hidalgo, Gto\n \t \t· 37800"};



    //Imagenes para cada restaurante

    int[] images={
            R.drawable.firstplacestwo,//5
            R.drawable.secondplace,//9
            R.drawable.thirdplace,//15
            R.drawable.fourplace,//7
            R.drawable.fiveplace,//6
            R.drawable.sixplace,//12
            R.drawable.sevenplace,//10
            R.drawable.eightplace,//2
            R.drawable.nineplace,//8
            R.drawable.tenplace,//14
            R.drawable.elevenplace,//3
            R.drawable.twelveplace,//17
            R.drawable.thirteenplace,//16
            R.drawable.fifteenplace,//4
            R.drawable.sixteenplace,//13
            R.drawable.seventeenplace,//11
            R.drawable.eighteenplace};//1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //REFERENCE RECYCLER
        RecyclerView rv= (RecyclerView) findViewById(R.id.myRecycler);

        //SET PROPERTIES
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        PlacesMyAdapter adapter=new PlacesMyAdapter(this,names,positions,images);
        rv.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.places_menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
