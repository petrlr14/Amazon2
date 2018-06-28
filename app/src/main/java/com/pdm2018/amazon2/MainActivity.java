package com.pdm2018.amazon2;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pdm2018.amazon2.API.Amazon2API;
import com.pdm2018.amazon2.API.RetrofitSingleton;
import com.pdm2018.amazon2.API.deserializer.TokenDeserializer;
import com.pdm2018.amazon2.models.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mResponseTv;
    private Amazon2API mAPIService;
    public String token;
    private static Context sContext;
    private TokenDeserializer deserializer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText titleEt = (EditText) findViewById(R.id.et_title);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        mAPIService = RetrofitSingleton.startAPI();

        sContext=getApplicationContext();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();
                sendPost();
                /*if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    //sendPost(title, body);
                }else{
                    Snackbar.make(findViewById(R.id.activity_post),"Username or Password empty, try again", Snackbar.LENGTH_LONG).show();
                }*/
            }
        });
    }

    public void sendPost() {
        mAPIService.login().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                //Snackbar.make(findViewById(R.id.activity_post),"Response:"+response.toString(), Snackbar.LENGTH_LONG).show();
                //Log.d("Response",response.body().getName());

                if(response.isSuccessful()) {
                    Snackbar.make(findViewById(R.id.activity_post),"Response: "+response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

    public static Context getmainContext(){
        return sContext;
    }

}
