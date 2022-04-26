package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    private DatabaseReference usersRef;
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
        usersRef = database.getReference("users");

//        // Read from the database
//        messageRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //String value = dataSnapshot.getValue().toString();
//                String result = "Wiadomosci:\n";
//                //Log.i("tag", "Value is: " + value);
//                for (DataSnapshot dsp : dataSnapshot.getChildren()){
//                    result += dsp.getValue().toString() + "\n";
//                }
//                resultTextView.setText(result);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String value = dataSnapshot.getValue().toString();
                String result = "Użytkownicy:\n";
                //Log.i("tag", "Value is: " + value);
                for (DataSnapshot dsp : dataSnapshot.getChildren()){
                    //result += dsp.getValue().toString() + "\n";
                    User userData = dsp.getValue(User.class);
                    result += dsp.getKey() + " - " + "Wiek: " + userData.getAge() + " Wzrost: " + userData.getHeight() + "\n";
                    //result += dsp.getKey() + ": " + dsp.getValue() + "\n";
                }
                resultTextView.setText(result);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void addUserButtonOnClick(View view){
        Log.i("info","dodano");
        //myRef.setValue("Hello, World!");
        messageRef.child("Wiadomosc_"+licznik).setValue("Treść wiadomosci: "+licznik);
        licznik++;

        int age = -1;
        int height = -1;
        String name = "";
        name = userNameInput.getText().toString();
        age = Integer.parseInt(userAgeInput.getText().toString());
        height = Integer.parseInt(userHeightInput.getText().toString());

        if(name != "" && age != -1 && height != -1){

            usersRef.child(name).child("age").setValue(age);
            usersRef.child(name).child("height").setValue(height);
        }


    }

//    public void displayUsersButtonOnClick(View view){
//        Log.i("info","wyswietlono");
//
//        Task<DataSnapshot> task = messageRef.get();
//
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        DataSnapshot ds = task.getResult();
//
//        Object value = ds.getValue();
//        resultTextView.setText(value.toString());
//    }




}