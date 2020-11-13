package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.widget.CursorAdapter;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.myactivity.database.DbAccessObj;
import com.example.myactivity.database.FeedReaderContract.FeedEntry;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;
    public static final String MYPREFS = "myprefs";
    public static final String NAMEKEY = "namekey";
    public static final String PWDKEY = "pwdkey";

    DbAccessObj dbAccessObj;
    EditText nameEditText,pwdEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
        nameEditText =  findViewById(R.id.editTextName);
        pwdEditText = findViewById(R.id.editTextPwd);

        dbAccessObj = new DbAccessObj(this);
        dbAccessObj.openDb();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
        ListView dbListView = findViewById(R.id.dblistview);
        //Uri uriSms = Uri.parse("content://sms/inbox");
        Uri callOgUri = CallLog.Calls.CONTENT_URI;
        Cursor dataCursor =  getContentResolver().query( callOgUri,null,null,null,null);
        // Cursor dataCursor = dbAccessObj.getRows();
        //put the data into adapter
        CursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.row_listview,
                dataCursor,
                new String[]{CallLog.Calls.NUMBER,CallLog.Calls.CACHED_NAME},
                        //{"body","address"},
                //FeedEntry.COLUMN_NAME_TITLE,FeedEntry.COLUMN_NAME_SUBTITLE},
                //"title","subtitle"},
                new int[] {R.id.textviewRow,R.id.textViewsubtitle});
        //set the adapter onto the listview
        dbListView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onpause");
        saveData();
    }

    /**
     * this method will save data from edittexts into a sharedprefs
     */
    private void saveData() {
        Log.i(TAG,"saveData");

        //get the data from the edittext
        String name = nameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        //create a file names myprefs
        SharedPreferences preferences = getSharedPreferences(MYPREFS,MODE_PRIVATE);
        //open the file
        SharedPreferences.Editor editor = preferences.edit();
        //write to the file
        editor.putString(NAMEKEY,name);
        editor.putString(PWDKEY,pwd);
        //save the file
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onresume");
        restoreData();
    }

    private void restoreData(){
        Log.i(TAG,"restoreData");

        //open the file
        SharedPreferences preferences = getSharedPreferences(MYPREFS,MODE_PRIVATE);
        //read the file
        String name = preferences.getString(NAMEKEY,"");
        String pwd = preferences.getString(PWDKEY,"");
        //set the data in edittexts
        nameEditText.setText(name);
        pwdEditText.setText(pwd);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onstop");

    }

    public void clickHandler(View view) {
        Log.e(TAG,"clickHandler");

        switch (view.getId()){
            case R.id.buttonlogin:
                // startHome();
                getCredentials();
                break;
            case R.id.buttoncancel:
                Intent dialIntent =new Intent(Intent.ACTION_VIEW,  Uri.parse("tel:12345678" ));
                //"//http://www.google.com"));
                //new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1234567"));
                startActivity(dialIntent);
                break;
        }
    }

    private void getCredentials() {
        dbAccessObj.query(nameEditText.getText().toString());
    }

    private void startHome() {
        Intent hIntent = new Intent(MainActivity.this, HomeActivity.class);
        hIntent.putExtra("mykey","abdul");
        int c = add(10,20);
        startActivity(hIntent);
    }

    private int add(int a, int b) {
        return a+b;
    }

    public void handleDb(View view) {
        switch (view.getId()){
            case R.id.buttonput:
                String title = nameEditText.getText().toString();
                String subtitle = pwdEditText.getText().toString();

                dbAccessObj.createRow(title,subtitle);

                break;
            case R.id.buttonget:
                //get the data from db
                String data =  dbAccessObj.readRow();
                //set the data onto textview
                TextView dbTextView = findViewById(R.id.textViewdb);
                dbTextView.setText(data);
                break;
        }
    }
}