package com.example.hostel_manaement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Authentication1 extends AppCompatActivity {
    Button verify_btn;
    TextInputLayout id_inp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication1);
        verify_btn = findViewById(R.id.verify);
        id_inp = findViewById(R.id.sid);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }


    public void login(View v) {
        if(!checkUsername()) {

        } else {
            isUser();
        }
    }

    private void isUser() {
        String user = id_inp.getEditText().getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student");
        Query checkUser = reference.orderByChild("id").equalTo(user);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    id_inp.setError(null);
                    id_inp.setErrorEnabled(false);
                    Intent intent = new Intent(Authentication1.this, Authentication2.class);
                    intent.putExtra("id1", user);
                    startActivity(intent);
                } else {
                    id_inp.setError("No such user Exists");
                    id_inp.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private Boolean checkUsername() {
        String noSpace = "(?=\\s+$)";
        String val = id_inp.getEditText().getText().toString();
        if (val.isEmpty()) {
            id_inp.setError("Field cannot be empty");
            return false;
        } else {
            id_inp.setError(null);
            id_inp.setErrorEnabled(false);
            return true;
        }
    }
}