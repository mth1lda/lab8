package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
private Button catfacts;
private TextView fact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        "https://catfact.ninja/"
        catfacts = findViewById(R.id.catfacts);
        fact = findViewById(R.id.fact);

        catfacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpHandler httpHandler = new OkHttpHandler();
                httpHandler.execute();
            }
        });
    }
    public class OkHttpHandler extends AsyncTask<Void,Void,String> {


        @Override
        protected String doInBackground(Void... voids) {
                Request.Builder builder = new Request.Builder();

                Request request = builder
                        .url("https://catfact.ninja/fact")
                        .build();


                OkHttpClient client = new OkHttpClient()
                        .newBuilder()
                        .build();

                try {
                    Response response = client.newCall(request).execute();

                    JSONObject jsonObject = new JSONObject(
                            response.body().string()
                    );
//                    return jsonObject.getString("fact");
                    return jsonObject.getString("lenght") + jsonObject.getString("fact");


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            fact.setText(o.toString());
        }
    }
}