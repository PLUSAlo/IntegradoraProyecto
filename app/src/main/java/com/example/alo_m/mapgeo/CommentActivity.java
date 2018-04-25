package com.example.alo_m.mapgeo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSave;


    private EditText edtName;
    private EditText edtComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        btnSave = findViewById(R.id.btn_save);

        edtName = findViewById(R.id.edt_name);
        edtComment = findViewById(R.id.edt_comment);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                InsertWSTask insertWSTask = new InsertWSTask();
                insertWSTask.execute(edtName.getText().toString(), edtComment.getText().toString());
                break;
            default:
                break;
        }

    }

    private class InsertWSTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost("https://hotelposadabonita.herokuapp.com/comments.json");
            post.setHeader("content-type", "application/json");

            try {

                JSONObject data = new JSONObject();
                System.out.println(params);
                data.put("name", params[0]);
                data.put("comment", params[1]);
                StringEntity entity = new StringEntity(data.toString());
                post.setEntity(entity);

                HttpResponse response = httpClient.execute(post);
                String strResponse = EntityUtils.toString(response.getEntity());

            }catch (Exception e){
                Log.e("Rest Service Error", "¡Error! ",e);
                return false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            String message = result?"Successful":"Failed";

            Toast.makeText(CommentActivity.this, "Insert "+message, Toast.LENGTH_SHORT).show();

        }
    }
}
