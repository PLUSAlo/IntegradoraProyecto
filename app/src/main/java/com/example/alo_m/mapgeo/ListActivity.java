package com.example.alo_m.mapgeo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class ListActivity extends AppCompatActivity {

    //Nombre de Restaurantes
    String[] names={"El Carruaje","El Delfín Pescados y Mariscos","Marcello Pizza", "Subway", "Taco Pizza","Toro Rojo",
            "Huaraches el Tacho","Pollo Sabroso","Las Tortugas","Nana Pancha","El 7 mares","Don Camarón","Pollo Feliz",
            "Restaurante Adelitas","De Quen Chon","Carnitas Vicente","Eduardo's Pizza","Taquería La Flamita",
            "Parripollo"};

    // Descripción de individul de los restaurantes
    String[] positions={"\n \t \tGuanajuato 5\n" +"Tenedor libre \n \t \t· Agradable \n\t \t· Informal",
            "\n \t \tCalle Veracruz 2" + "\n \t \t· Agradable \n \t \t· Informal",
            "\n \t \tAvenida A, Av. Sur 38" + "\n \t \t· Agradable \n \t \t· Informal",
            "\n \t \tGuanajuato No. 29"+"\nTelefono:\n \t \t01 418 182 0863",
            "\n \t \tCalle Guerrero 11-B, Col. Centro\n"+"Teléfono: \n \t \t01 418 182 0854",
            "\n \t \tAv. De los Heroes 104, Los Pinos\n"+"Teléfono: \n \t \t01 81 1642 4592",
            "\n \t \tRestaurante mexicano en Dolores Hidalgo\nCarretera Guanajuato-Dolores Hidalgo (0,99 km)"+"Teléfono: \n \t \t01 418 181 1503",
            "\n \t \tMichoacán 10, Centro\n"+"Teléfono: \n \t \t01 418 182 0053",
            "\n \t \tQuerétaro 8-C, Centro\n"+"Teléfono: \n \t \t01 418 182 2239",
            "\n \t \tCalle Guerrero 46, Centro\n"+"Teléfono: \n \t \t01 418 182 4382",
            "\n \t \tCalzada de los héroes # 64, Valle Verde\n"+"Teléfono: \n \t \t01 418 182 2605",
            "\n \t \tJosé Alfredo Jiménez 105\n"+"Teléfono: \n \t \t01 418 182 2666",
            "\n \t \tMariano Balleza 13D\n \t \t Mariano Balleza\n"+"Teléfono: \n \t \t01 418 182 3255",
            "\n \t \tMiguel Cervantes Saavedra 5\n \t \tRenacimiento"+"Teléfono: \n \t \t01 418 108 8586",
            "\n \t \tDolores Hidalgo - San Felipe 80\n \t \t Mariano Balleza",
            "\n \t \tMariano Balleza\n"+"Ubicado en Dolores Hidalgo Cuna de la Independencia Nacional",
            "\n \t \tCalle Veracruz 5, Centro\n"+"Teléfono: \n \t \t01 418 182 2124",
            "\n \t \tNorte 25, Centro\n"+"Ubicado en Dolores Hidalgo Cuna de la Independencia Nacional",
            "\n \t \tCalzada de los heroes 36 \"B\" colonia los pinos\n"+"Teléfono: \n \t \t01 418 182 1460"};



    //Imagenes para cada restaurante

    int[] images={R.drawable.carruaje,R.drawable.delfin,R.drawable.marcello_pizza,R.drawable.subway,
            R.drawable.taco_pizza,R.drawable.toro_rojo, R.drawable.huaraches_el_tacho,R.drawable.toro_rojo,
            R.drawable.tortugas,R.drawable.nana_pancha, R.drawable.el_siete_mares,R.drawable.don_camaron,
            R.drawable.pollo_feliz,R.drawable.adelitas,R.drawable.de_quen_chon,R.drawable.carnitas_vicente,
            R.drawable.pizza_eduardo, R.drawable.la_flamita,R.drawable.parripollo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //REFERENCE RECYCLER
        RecyclerView rv= (RecyclerView) findViewById(R.id.myRecycler);

        //SET PROPERTIES
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        MyAdapter adapter=new MyAdapter(this,names,positions,images);
        rv.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
