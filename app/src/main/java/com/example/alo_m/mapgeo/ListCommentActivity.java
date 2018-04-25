package com.example.alo_m.mapgeo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alo_m on 29/03/2018.
 */

public class ListCommentActivity extends AppCompatActivity implements View.OnClickListener {
  private Button btnList;

  private ListView lsvComments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comment);
        btnList = findViewById(R.id.btn_list);


        lsvComments = findViewById(R.id.lsv_comments);

        btnList.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_list:
                ListWSTask listWSTask = new ListWSTask();
                listWSTask.execute();
                break;
            default:
                break;
        }

    }

    private class ListWSTask extends AsyncTask<String, Integer, Boolean> {

        private String[] comments;

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean result = true;
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet("https://hotelposadabonita.herokuapp.com/comments.json");
            get.setHeader("content-type", "application/json");
            try {
                HttpResponse response = httpClient.execute(get);
                String strResponse = EntityUtils.toString(response.getEntity());
                JSONArray jsonResponse = new JSONArray(strResponse);
                comments = new String[jsonResponse.length()];
                for (int i=0; i<jsonResponse.length(); i++){
                    JSONObject jsonObject = jsonResponse.getJSONObject(i);
                    int idComment = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    String comment = jsonObject.getString("comment");
                    comments[i] = ""+ idComment+"- . -"+ name+"- . -"+comment;
                }
            }catch (Exception e){
                Log.e("Rest Service Error", "¡Error! ",e);
                return false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(ListCommentActivity.this, android.R.layout.simple_list_item_1, comments);
                lsvComments.setAdapter(arrayAdapter);
            }
        }
    }

}
