package com.example.hostel_manaement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Complaint extends AppCompatActivity {

    String[] urgency= {"High","Medium","Low"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    TextInputLayout location,date,description;
    String ulevel;
    Button submit;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        Intent intent1= getIntent();

        String id = intent1.getStringExtra("id");
        String hostelfromDB = intent1.getStringExtra("hostel");
        autoCompleteTxt=findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(this,R.layout.dropdown_item,urgency);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ulevel= parent.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT).show();
            }
        }
        );
        location=findViewById(R.id.location);
        description=findViewById(R.id.desc);
        date=findViewById(R.id.date);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference("Complaint");

                String loc=location.getEditText().getText().toString();
                String dt=date.getEditText().getText().toString();
                String dscrp=description.getEditText().getText().toString();

                Comp comp=new Comp(id,hostelfromDB,loc,dscrp,dt,ulevel);
                reference.child(id).setValue(comp);
                Toast.makeText(Complaint.this, "Complaint Registered", Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }
}