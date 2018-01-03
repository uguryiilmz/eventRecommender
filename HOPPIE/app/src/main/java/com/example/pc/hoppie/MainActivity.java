package com.example.pc.hoppie;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText ET_NAME, ET_PASS;
    String login_name,login_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET_NAME=(EditText) findViewById(R.id.userName);
        ET_PASS=(EditText) findViewById(R.id.userPass);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"Apple Chancery.ttf");
        TextView myTextView = (TextView)findViewById(R.id.textView);
        myTextView.setTypeface(myTypeface);
    }

    public void regButton(View view){
        startActivity(new Intent(this,Register.class));
    }

    public void logButton(View view){
        login_name=ET_NAME.getText().toString();
        login_pass=ET_PASS.getText().toString();
        String method="login";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,login_name,login_pass);
    }

    public void changeToJson(View view){
        startActivity(new Intent(this,JSON.class));
    }



}
