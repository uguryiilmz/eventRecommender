package com.example.pc.hoppie;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends Activity {

    EditText ET_NAME,ET_USER_NAME,ET_USER_PASS,ET_LASTNAME,ET_STREET,ET_ZİPCODE;
    String name,last_name,user_name,user_pass,street_name,zip_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ET_NAME=(EditText)findViewById(R.id.name);
        ET_USER_NAME=(EditText)findViewById(R.id.user_Name);
        ET_USER_PASS=(EditText)findViewById(R.id.password);
        ET_LASTNAME=(EditText)findViewById(R.id.lastName);
        ET_STREET=(EditText) findViewById(R.id.street);
        ET_ZİPCODE=(EditText) findViewById(R.id.zipCode);

    }

    public void reg2Button(View view){
        name=ET_NAME.getText().toString();
        last_name=ET_LASTNAME.getText().toString();
        user_name=ET_USER_NAME.getText().toString();
        user_pass=ET_USER_PASS.getText().toString();
        street_name=ET_STREET.getText().toString();
        zip_code=ET_ZİPCODE.getText().toString();
        String method="register";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,name,last_name,user_name,user_pass,street_name,zip_code);
        finish();
    }


}
