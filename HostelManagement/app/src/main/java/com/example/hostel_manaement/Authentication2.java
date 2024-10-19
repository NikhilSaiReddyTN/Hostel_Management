package com.example.hostel_manaement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Authentication2 extends AppCompatActivity {

    Button submit_btn;
    TextInputLayout otp_code;
    ProgressBar progressBar;
    String phone,verificationCodeBySystem;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication2);

        progressBar=findViewById(R.id.progress_bar);
        otp_code=findViewById(R.id.otp);
        submit_btn=findViewById(R.id.button2);
        mAuth=FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);
        String id_inp=getIntent().getStringExtra("id1");

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Student");
        Query checkUser = reference.orderByChild("id").equalTo(id_inp);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    String ph=snapshot.child(id_inp).child("parent").getValue(String.class);
                    phone=("+91").concat(ph);
                    sendVerificationCodeToUser(phone);
                    //Toast.makeText(Authentication2.this, phone, Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(Authentication2.this, "Number not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=otp_code.getEditText().getText().toString();

                if(code.length()!=6)
                {
                    otp_code.setError("Wrong OTP");
                    otp_code.requestFocus();
                    return;
                }else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    verifyCode(code);
                }
            }
        });
    }

    private void sendVerificationCodeToUser(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyCode(String codeByUser)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationCodeBySystem,codeByUser);
        signInByCredential(credential);
    }

    private void signInByCredential(PhoneAuthCredential credential)
    {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
        .addOnCompleteListener(Authentication2.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Authentication2.this, "Successful Verification", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Authentication2.this, Confirmation.class);
                    intent.putExtra("nav","otp");
                    startActivity(intent);
                }else
                {
                    Toast.makeText(Authentication2.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s,token);
            Toast.makeText(Authentication2.this, "Code sent", Toast.LENGTH_LONG).show();
            verificationCodeBySystem=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            String code= credential.getSmsCode();
            if(code!=null)
            {
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
            //signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Authentication2.this, "Verification Failed", Toast.LENGTH_LONG).show();
        }

    };

}