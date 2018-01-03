package com.example.pc.hoppie;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Pc on 3.5.2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    Context ctx;

    BackgroundTask(Context ctx){
        this.ctx=ctx;
    }
    protected void onPreExecute(){
        alertDialog=new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information...");
    }
    protected String doInBackground(String... params) {
        String reg_url="http://10.0.2.2/webapp/register.php";
        String log_url="http://10.0.2.2/webapp/login.php";
        String method=params[0];
        if(method.equals("register")){
            String name=params[1];
            String lastName=params[2];
            String user_name=params[3];
            String user_pass=params[4];
            String Street=params[5];
            String zipCode=params[6];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("user","UTF-8") +"="+ URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("lastName","UTF-8") +"="+ URLEncoder.encode(lastName,"UTF-8")+"&"+
                        URLEncoder.encode("user_name","UTF-8") +"="+ URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8") +"="+ URLEncoder.encode(user_pass,"UTF-8")+"&"+
                        URLEncoder.encode("Street","UTF-8") +"="+ URLEncoder.encode(Street,"UTF-8")+"&"+
                        URLEncoder.encode("zipCode","UTF-8") + "="+ URLEncoder.encode(zipCode,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "Registration Success";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("login")){
            String login_name=params[1];
            String login_pass=params[2];
            try {
                URL url=new URL(log_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data=URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    response=response+line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    protected void onProgressUpdate(Void...values){
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(String  result ){
        if(result.equals("Registration Success")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if(result.equals("Login failed...Try Again...")){
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        else{
            alertDialog.setMessage(result);
            alertDialog.show();
            String user_name=result;
            Intent myIntent=new Intent(ctx,Hobbies.class);
            Bundle b =new Bundle();
            b.putString("Username",user_name);
            myIntent.putExtras(b);
            ctx.startActivity(myIntent);

        }
    }
}
