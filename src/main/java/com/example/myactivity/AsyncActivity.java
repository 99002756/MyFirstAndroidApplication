package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncActivity extends AppCompatActivity {
    private static final String TAG = AsyncActivity.class.getSimpleName();
    ProgressBar progressBar;
    EditText eBookInput;
    TextView mTitleText,mAuthorText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        progressBar = findViewById(R.id.progressBar);
        eBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);
    }

    public void handleClick(View view) {
        Log.i(TAG,"handleClick");
        //download something from internet
        String queryString = eBookInput.getText().toString();
        new FetchBook(mTitleText, mAuthorText).execute(queryString);
        //DownloadTask downloadTask = new DownloadTask(progressBar);
        //downloadTask.execute("https://urlForimagetobedownloaded");
        //show the download progress on progress bar

    }

    public void serviceHandler(View view) {
        Intent serviceIntent = new Intent(AsyncActivity.this,MusicService.class);
        switch (view.getId()){
            case R.id.buttonstart:
                //start a service/music
                startService(serviceIntent);
                break;
            case R.id.buttonstop:
                //stop a service/music
                stopService(serviceIntent);
                break;
        }
    }
}