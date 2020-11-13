package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.GridLayoutManager;
import android.os.Bundle;

public class RecyclerActivity extends AppCompatActivity {
    String[] languagesData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        //int nof=6;
        languagesData = new String[]{"english","tamil","hindi","kanada","telugu","malayalam"};
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
      //  MyAdapter adapter = new MyAdapter(this,languagesData);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager((new GridLayoutManager(this,nof)));
      //  recyclerView.setAdapter(adapter);
    }
}