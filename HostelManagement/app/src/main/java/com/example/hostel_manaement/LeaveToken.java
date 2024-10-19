package com.example.hostel_manaement;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeaveToken extends AppCompatActivity {

    TextView timer1;
    int hour,min;
    String time;
    TextInputLayout inDate,outDate,reason;
    Button submit;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_token);

        Intent intent1= getIntent();
        String id = intent1.getStringExtra("id");

        timer1=findViewById(R.id.timer);
        timer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        LeaveToken.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour=hourOfDay;
                                min=minute;
                                Calendar calender=Calendar.getInstance();
                                calender.set(0,0,0,hour,min);
                                android.text.format.DateFormat df = new android.text.format.DateFormat();
                                //time=calender.getTime().toString();
                                SimpleDateFormat time_format = new SimpleDateFormat("HH:mm aa");
                                time = time_format.format(calender.getTime());
                                timer1.setText(df.format("hh:mm aa",calender));
                            }
                        },12,0,false
                );
                timePickerDialog.updateTime(hour,min);
                timePickerDialog.show();
            }
        });
        inDate=findViewById(R.id.dateIn);
        outDate=findViewById(R.id.dateOut);
        reason=findViewById(R.id.reason);
        submit=findViewById(R.id.apply);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Leave");

                String iDate=inDate.getEditText().getText().toString();
                String oDate=outDate.getEditText().getText().toString();
                String rsn=reason.getEditText().getText().toString();

                Token tkn=new Token(iDate,oDate,rsn,id,time);
                reference.child(id).setValue(tkn);
                Toast.makeText(LeaveToken.this, "Leave Token Applied", Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }

}