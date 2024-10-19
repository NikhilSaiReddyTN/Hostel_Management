package com.example.hostel_manaement;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.FirebaseDatabase;

public class Confirmation extends AppCompatActivity {

    TextView message;
    LottieAnimationView lottie;
    Button ok;
    String nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent intent1= getIntent();

        nav = intent1.getStringExtra("nav");

        message = findViewById(R.id.message);
        lottie =  findViewById(R.id.lottie);
        ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nav.equals("otp"))
                {
                    Intent intent=new Intent(Confirmation.this,Authentication1.class);
                    startActivity(intent);
                }else if(nav.equals("complaint"))
                {
                    Intent intent=new Intent(Confirmation.this,MainPage.class);
                    startActivity(intent);
                }


            }
        });
    }


}