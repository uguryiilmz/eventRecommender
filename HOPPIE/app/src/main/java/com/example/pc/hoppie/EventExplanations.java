package com.example.pc.hoppie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;



public class EventExplanations extends AppCompatActivity {

    ImageLoader imageLoader = ImageLoader.getInstance();
    String location;
    String name;
    String time;


    private EventListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_explanations);

        name = getIntent().getStringExtra("user_explanation");
        TextView des = (TextView) findViewById(R.id.description);
        des.setText(name);

        location = getIntent().getStringExtra("location");
        TextView loc=(TextView) findViewById(R.id.location);
        loc.setText(location);
        time = getIntent().getStringExtra("time");
        TextView desc = (TextView) findViewById(R.id.when);
        desc.setText(time);
    }
}
