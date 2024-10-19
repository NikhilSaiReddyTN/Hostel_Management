package com.example.hostel_manaement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    String[] hostels= {"Phase-1","Phase-2","Dorms"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    Button regBtn;
    TextInputLayout regName,regId,regPhno,regHost,regRoom,regPar,regPass;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regBtn=findViewById(R.id.register);
        regName=findViewById(R.id.name);
        regId=findViewById(R.id.username);
        regPhno=findViewById(R.id.phone);
        regHost=findViewById(R.id.hostel);
        regRoom=findViewById(R.id.room);
        regPar=findViewById(R.id.parent);
        regPass=findViewById(R.id.password);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Student");

                if(!validateName() | !validateID() | !validatePhone() | !validatePass())
                {

                }else
                {
                    String name=regName.getEditText().getText().toString();
                    String id=regId.getEditText().getText().toString();
                    String phone=regPhno.getEditText().getText().toString();
                    String hostel=regHost.getEditText().getText().toString();
                    String room=regRoom.getEditText().getText().toString();
                    String parent=regPar.getEditText().getText().toString();
                    String pass=regPass.getEditText().getText().toString();

                    Student student=new Student(name,id,phone,hostel,room,parent,pass);
                    reference.child(id).setValue(student);

                    Intent intent=new Intent(SignUp.this,MainActivity.class);
                    startActivity(intent);
                }


            }
        });

        autoCompleteTxt=findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(this,R.layout.dropdown_item,hostels);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item= parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: ", Toast.LENGTH_SHORT).show();
            }
        }

        );
    }

    private Boolean validateName(){
        String val=regName.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regName.setError("Field cannot be empty");
            return false;
        }else
        {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateID(){
        String noSpace= "(?=\\s+$)";
        String val=regId.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regId.setError("Field cannot be empty");
            return false;
        }else if(val.contains(" "))
        {
            regId.setError("No White Spaces Allowed");
            return false;
        }else
        {
            regId.setError(null);
            regId.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhone(){
        //String validPhone= "(0|91)?[6-9][0-9]{9}";
        String val=regPhno.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regPhno.setError("Field cannot be empty");
            return false;
        }/*else if(!val.matches(validPhone))
        {
            regPhno.setError("Invalid Phone Number");
            return false;
        }*/else
        {
            regPhno.setError(null);
            return true;
        }
    }



    private Boolean validatePass(){
        String val=regPass.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regPass.setError("Field cannot be empty");
            return false;
        }else if(val.length()>10)
        {
            regPass.setError("Password must be between 1-10 characters");
            return false;
        }
        else
        {
            regPass.setError(null);
            regPass.setErrorEnabled(false);
            return true;
        }
    }


}