package com.juliodev.taxihaiti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.juliodev.taxihaiti.model.ChatMessage;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        Button Send = (Button) findViewById(R.id.btSend);
        final EditText tvMessage = (EditText) findViewById(R.id.tvMessage);
        ListView lvMessage = (ListView) findViewById(R.id.lvMessage);

        Firebase.setAndroidContext(this);
        // setup code
        final Firebase ref = new Firebase("https://taxihaiti-b8535.firebaseio.com");

        /*
            Firebase myFirebaseRef = new Firebase("https://taxihaiti-b8535.firebaseio.com");
            myFirebaseRef.child("message").setValue("Va te faire foutre.");
            myFirebaseRef.child("name").setValue("Julio");*/


        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessage chat = new ChatMessage(tvMessage.getText().toString(), "Julio");
                ref.push().setValue(chat);
                tvMessage.setText("");
            }
        });
    }
}
