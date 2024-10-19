package com.example.hostel_manaement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    Button token_btn,comp_btn,info_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_page);
        Intent intent1= getIntent();

        String namefromDB = intent1.getStringExtra("name");
        String idfromDB = intent1.getStringExtra("id");
        String phfromDB = intent1.getStringExtra("phone");
        String hostelfromDB = intent1.getStringExtra("hostel");
        String roomfromDB = intent1.getStringExtra("room");
        token_btn=findViewById(R.id.leaveTkn);
        token_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPage.this,LeaveToken.class);
                intent.putExtra("id",idfromDB);
                startActivity(intent);
            }
        });

        comp_btn=findViewById(R.id.complaint);
        comp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPage.this,Complaint.class);
                intent.putExtra("id",idfromDB);
                intent.putExtra("hostel",hostelfromDB);
                startActivity(intent);
            }
        });

        info_btn=findViewById(R.id.user_info);
        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPage.this,UserProfile.class);

                intent.putExtra("name",namefromDB);
                intent.putExtra("id",idfromDB);
                intent.putExtra("phone",phfromDB);
                intent.putExtra("hostel",hostelfromDB);
                intent.putExtra("room",roomfromDB);

                startActivity(intent);
            }
        });
    }
}