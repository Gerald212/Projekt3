package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addUserButtonOnClick(View view){
        Log.i("info","dodano");
    }

    public void displayUsersButtonOnClick(View view){
        Log.i("info","wyswietlono");
    }


}