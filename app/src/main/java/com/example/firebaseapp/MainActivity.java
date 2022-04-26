package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText userNameInput, userAgeInput, userHeightInput;
    private TextView resultTextView;
    private FirebaseDatabase database;
    private DatabaseReference messageRef;
    private int licznik = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameInput = (EditText)findViewById(R.id.editTextUserName);
        userAgeInput = (EditText)findViewById(R.id.editTextUserAge);
        userHeightInput = (EditText)findViewById(R.id.editTextUserHeight);
        resultTextView = (TextView)findViewById(R.id.textViewUsers);

        database = FirebaseDatabase.getInstance("https://fir-app-f558a-default-rtdb.firebaseio.com/");
        messageRef = database.getReference("message");

        // Read from the database
//        messageRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("tag", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.d("tag", "Failed to read value.", error.toException());
//            }
//        });
    }

    public void addUserButtonOnClick(View view){
        Log.i("info","dodano");
        //myRef.setValue("Hello, World!");
        messageRef.child("Wiadomosc_"+licznik).setValue("Treść wiadomosci: "+licznik);
        licznik++;
    }

    public void displayUsersButtonOnClick(View view){
        Log.i("info","wyswietlono");

        Task<DataSnapshot> task = messageRef.get();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataSnapshot ds = task.getResult();
        Object value = ds.getValue();
        resultTextView.setText(value.toString());
    }




}