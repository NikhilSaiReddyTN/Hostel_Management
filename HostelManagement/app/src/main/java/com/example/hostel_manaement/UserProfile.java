package com.example.hostel_manaement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    EditText uname,uid,uphone;
    TextView hostelid,roomid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        hostelid = findViewById(R.id.room);
        roomid = findViewById(R.id.hostel);
        uname = findViewById(R.id.nameIn);
        uid = findViewById(R.id.idIn);
        uphone = findViewById(R.id.phoneIn);

        showUsers();


    }

    private void showUsers() {

        Intent intent1= getIntent();

        String namefromDB = intent1.getStringExtra("name");
        String idfromDB = intent1.getStringExtra("id");
        String phfromDB = intent1.getStringExtra("phone");
        String hostelfromDB = intent1.getStringExtra("hostel");
        String roomfromDB = intent1.getStringExtra("room");

        hostelid.setText(hostelfromDB);
        roomid.setText(roomfromDB);
        uname.setText(namefromDB);
        uid.setText(idfromDB);
        uphone.setText(phfromDB);


    }
}