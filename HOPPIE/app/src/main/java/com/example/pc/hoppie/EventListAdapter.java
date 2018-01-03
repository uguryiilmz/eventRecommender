package com.example.pc.hoppie;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pc on 16.5.2017.
 */

public class EventListAdapter extends BaseAdapter {
    private final ArrayList<JSONObject> arrayList;
    private final EventList activity;

    public EventListAdapter(ArrayList<JSONObject> arraylist, EventList activity){
        this.arrayList=arraylist;
        this.activity=activity;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(activity).inflate(R.layout.model_event_list,parent,false);
        TextView name= (TextView) convertView.findViewById(R.id.name);
        TextView title=(TextView) convertView.findViewById(R.id.tittle);

        final JSONObject item= (JSONObject) arrayList.get(position);
        try {
            name.setText(item.getString("user_explanation"));
            title.setText(item.getString("tittle"));



            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,EventExplanations.class);
                    try{
                        intent.putExtra("user_explanation",item.getString("user_explanation"));
                        intent.putExtra("location",item.getString("location"));
                        intent.putExtra("time",item.getString("time"));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    activity.startActivity(intent);
                }
            });
        } catch (JSONException e){
            e.printStackTrace();;
        }
        return convertView;
    }
}


