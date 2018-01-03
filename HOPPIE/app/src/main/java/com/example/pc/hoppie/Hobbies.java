package com.example.pc.hoppie;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Hobbies extends AppCompatActivity {

    RadioGroup gp;
    RadioButton rb;
    Button show,insert;
    String user_name,user_hobbies;
    String insertUrl="http://10.0.2.2/webapp/insert_hobbie.php";
    String showUrl="http://10.0.2.2/webapp/show_hobbies.php";
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies);
        Bundle b = getIntent().getExtras();
        user_name = b.getString("Username");
        Toast.makeText(Hobbies.this, user_name, Toast.LENGTH_SHORT).show();
        gp = (RadioGroup) findViewById(R.id.radioGroup);
        result=(TextView)findViewById(R.id.result);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"Apple Chancery.ttf");
        TextView myTextView = (TextView)findViewById(R.id.textView5);
        myTextView.setTypeface(myTypeface);
        TextView myTextView2 = (TextView)findViewById(R.id.textView3);
        myTextView2.setTypeface(myTypeface);

    }

    public void insertButton(View view){
        user_hobbies=rb.getText().toString();
        String method="insert";
        BackgroundTask3 backgroundTask=new BackgroundTask3(this);
        backgroundTask.execute(method,user_name,user_hobbies);
        //finish();
    }

    public void showButton(View view){
        Intent i = new Intent(getApplicationContext(), EventList.class);

        i.putExtra("choice",rb.getText().toString());
        startActivity(i);
    }



    public void rbClick(View view) {
        int radiobuttonid = gp.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
    }



    class   BackgroundTask3 extends AsyncTask<String,Void,String>{

        AlertDialog alertDialog;
        Context ctx;

        BackgroundTask3(Context ctx){

            this.ctx=ctx;
        }
        protected void onPreExecute(){
            alertDialog=new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login Information...");

        }

        @Override
        protected String doInBackground(String... params) {
            String insert_url="http://10.0.2.2/webapp/insert_hobbie.php";
            String method=params[0];
            if(method.equals("insert")){
                String user_name=params[1];
                String user_hobbies=params[2];

                try {
                    URL url=new URL(insert_url);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                    String data= URLEncoder.encode("user_name","UTF-8") +"="+ URLEncoder.encode(user_name,"UTF-8")+"&"+
                            URLEncoder.encode("user_hobbies","UTF-8") +"="+ URLEncoder.encode(user_hobbies,"UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS=httpURLConnection.getInputStream();
                    IS.close();
                    return "Insertion Success";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("Insertion Success")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            }
        }
    }
}
