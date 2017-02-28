package com.example.hendriebeats.birdappexample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import static com.example.hendriebeats.birdappexample.R.id.txtDescription;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final dbHelper db = new dbHelper(this);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addBird(new Bird(1, "Mocking Jay"));
        db.addBird(new Bird(2, "Blue Welsh"));
        db.addBird(new Bird(3, "Red Robin"));
        db.addBird(new Bird(4, "Collard Stork"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Bird> contacts = db.getAllBirds();

        for (Bird cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Description: " + cn.getDescription();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        Button button= (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText nameView = (EditText)findViewById(R.id.txtName);
                String nameValue = nameView.getText().toString();

                EditText descriptionView = (EditText)findViewById(R.id.txtDescription);
                String descriptionValue = descriptionView.getText().toString();

                nameView.setText("");
                descriptionView.setText("");
                //git testing line

                db.addBird(new Bird(5, nameValue, descriptionValue));

            }
        });

        Button viewBirds = (Button) findViewById(R.id.viewBirds);
        viewBirds.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewBirds.class);
                startActivity(i);
            }
        });

    }
}
