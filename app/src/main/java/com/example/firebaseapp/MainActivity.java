package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText userNameInput, userAgeInput, userHeightInput;
    private TextView resultTextView;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameInput = (EditText)findViewById(R.id.editTextUserName);
        userAgeInput = (EditText)findViewById(R.id.editTextUserAge);
        userHeightInput = (EditText)findViewById(R.id.editTextUserHeight);
        resultTextView = (TextView)findViewById(R.id.textViewUsers);

        database = FirebaseDatabase.getInstance("https://console.firebase.google.com/project/fir-app-f558a/database/fir-app-f558a-default-rtdb/data/~2F");
        myRef = database.getReference("message");
    }

    public void addUserButtonOnClick(View view){
        Log.i("info","dodano");
        myRef.setValue("Hello, World!");
    }

    public void displayUsersButtonOnClick(View view){
        Log.i("info","wyswietlono");
    }


}