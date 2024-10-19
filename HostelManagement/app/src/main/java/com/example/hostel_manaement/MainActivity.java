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

public class MainActivity extends AppCompatActivity {

    Button callSignUp,login_btn;
    TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        login_btn=findViewById(R.id.login_button);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                login(v);
            }
        });

        callSignUp=findViewById(R.id.signup_btn);
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });


    }

    public void login(View v)
    {
        if(!checkUsername() | !checkPassword())
        {

        }
        else{
            isUser();
        }
    }

    private void isUser()
    {
        String user=username.getEditText().getText().toString();
        String pwd=password.getEditText().getText().toString();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Student");
        Query checkUser = reference.orderByChild("id").equalTo(user);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passDB=snapshot.child(user).child("pass").getValue(String.class);

                    if(passDB.equals(pwd))
                    {
                        password.setError(null);
                        password.setErrorEnabled(false);
                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                        if(user.equals("19XJ1A0000"))
                        {
                            Intent intent=new Intent(MainActivity.this,Authentication1.class);
                            startActivity(intent);
                        }else
                        {
                            String namefromDB = snapshot.child(user).child("name").getValue(String.class);
                            String idfromDB = snapshot.child(user).child("id").getValue(String.class);
                            String phfromDB = snapshot.child(user).child("phone").getValue(String.class);
                            String hostelfromDB = snapshot.child(user).child("hostel").getValue(String.class);
                            String roomfromDB = snapshot.child(user).child("room").getValue(String.class);


                            Intent intent=new Intent(MainActivity.this,MainPage.class);

                            intent.putExtra("name",namefromDB);
                            intent.putExtra("id",idfromDB);
                            intent.putExtra("phone",phfromDB);
                            intent.putExtra("hostel",hostelfromDB);
                            intent.putExtra("room",roomfromDB);

                            startActivity(intent);
                        }
                    }else
                    {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else
                {
                    username.setError("No such user Exists");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private Boolean checkUsername()
    {
        String noSpace="(?=\\s+$)";
        String val=username.getEditText().getText().toString();
        if(val.isEmpty())
        {
            username.setError("Field cannot be empty");
            return false;
        }/*else if(!val.matches(noSpace))
        {
            username.setError("No White Spaces Allowed");
            return false;
        }*/else
        {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean checkPassword()
    {
        String val=password.getEditText().getText().toString();
        if(val.isEmpty())
        {
            password.setError("Field cannot be empty");
            return false;
        }else if(val.length()>10)
        {
            password.setError("Password must be between 1-10 characters");
            return false;
        }
        else
        {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}