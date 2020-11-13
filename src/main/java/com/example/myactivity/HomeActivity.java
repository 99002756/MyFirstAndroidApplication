package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {

    String[] languages;
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.w(TAG, "onCreate");
        languages = new String[]{"english", "hindi", "urdu", "kannada"};
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!= null) {
            String data = extras.getString("mykey");
            TextView resultTextView = findViewById(R.id.buttonlogin);
            resultTextView.setText(data);
        }

        ListView countriesListView = findViewById(R.id.countriesListview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                //android.R.layout.simple_list_item_1, //layout file of each row in the listview
                R.layout.row_listview,
                // android.R.layout.simple_list_item_1, //layout file of each row in the listview
                languages);
        countriesListView.setAdapter(adapter);
    }



}