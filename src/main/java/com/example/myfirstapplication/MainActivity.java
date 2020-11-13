package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickHandler(View view) {
        Toast.makeText(getApplicationContext(),"Hello Bindu",Toast.LENGTH_SHORT).show();
        EditText nameEditText;
        nameEditText=findViewById(R.id.editTextName);
        String input=nameEditText.getText().toString();
        TextView welcomeTextView;
        welcomeTextView=findViewById(R.id.textViewWelcome);
        welcomeTextView.setText(input);
    }
}