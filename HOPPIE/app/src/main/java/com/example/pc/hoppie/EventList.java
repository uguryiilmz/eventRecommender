package com.example.pc.hoppie;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.jar.JarException;

import cz.msebera.android.httpclient.Header;

import static android.R.attr.value;

public class EventList extends AppCompatActivity {

    private ListView lv;
    private EventListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("choice");
        Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
        //The key argument here must match that used in the other activity


        ImageLoaderConfiguration configuration= new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(configuration);

        ActionBar supportbar = getSupportActionBar();
        supportbar.setHomeButtonEnabled(true);
        supportbar.setDisplayHomeAsUpEnabled(true);

        lv= (ListView) findViewById(R.id.listView);
        final ArrayList<JSONObject> arrdata= new ArrayList<JSONObject>();
        adapter= new EventListAdapter(arrdata,this);
        lv.setAdapter(adapter);


        if(value.equals("Business")) {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            client.get("http://10.0.2.2/webapp/json_get_data.php", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray data = response.getJSONArray("server_response");
                        for (int i = 0; i < data.length(); i++) {
                            String user_explanation = data.getJSONObject(i).getString("user_explanation");
                            String tittle = data.getJSONObject(i).getString("tittle");
                            String location = data.getJSONObject(i).getString("location");
                            String time = data.getJSONObject(i).getString("time");
                            JSONObject item = new JSONObject();
                            item.put("user_explanation", user_explanation);
                            //item.put("image_path",photo);
                            item.put("tittle", tittle);
                            item.put("location", location);
                            item.put("time", time);

                            arrdata.add(item);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        else if(value.equals("Sport")){
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            client.get("http://10.0.2.2/webapp/json_get_data2.php", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray data = response.getJSONArray("server_response");
                        for (int i = 0; i < data.length(); i++) {
                            String user_explanation = data.getJSONObject(i).getString("user_explanation");
                            String tittle = data.getJSONObject(i).getString("tittle");
                            String location = data.getJSONObject(i).getString("location");
                            String time = data.getJSONObject(i).getString("time");
                            JSONObject item = new JSONObject();
                            item.put("user_explanation", user_explanation);
                            item.put("tittle", tittle);
                            item.put("location", location);
                            item.put("time", time);

                            arrdata.add(item);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        else if(value.equals("Art")){
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            client.get("http://10.0.2.2/webapp/json_get_data3.php", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray data = response.getJSONArray("server_response");
                        for (int i = 0; i < data.length(); i++) {
                            String user_explanation = data.getJSONObject(i).getString("user_explanation");
                            String tittle = data.getJSONObject(i).getString("tittle");
                            String location = data.getJSONObject(i).getString("location");
                            String time = data.getJSONObject(i).getString("time");
                            JSONObject item = new JSONObject();
                            item.put("user_explanation", user_explanation);
                            item.put("tittle", tittle);
                            item.put("location", location);
                            item.put("time", time);

                            arrdata.add(item);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }
}

