package com.example.gcuitfesst;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_page extends AppCompatActivity {
    Button registerbutton;

  DatabaseReference databaseReference;
  FirebaseDatabase firebaseDatabase;
  FirebaseAuth firebaseAuth;
    String gender= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        databaseReference =FirebaseDatabase.getInstance().getReference("Student");
        firebaseAuth = FirebaseAuth.getInstance();

            }
public void rebutton(View view){
    final EditText register_name =(EditText) findViewById(R.id.register_name);
    final EditText register_gcuid =(EditText) findViewById(R.id.register_gcuid);
    final EditText register_gcuuser =(EditText) findViewById(R.id.register_gcuuser);
    final EditText register_gcupass =(EditText) findViewById(R.id.register_gcupass);

    final RadioButton male_radio =(RadioButton) findViewById(R.id.male_radio);
    final RadioButton female_radio =(RadioButton) findViewById(R.id.female_radio);
    final String name = register_name.getText().toString().trim();
    final String gcuid = register_gcuid.getText().toString().trim();
    final String gcuuser = register_gcuuser.getText().toString().trim();
    final String gcupass = register_gcupass.getText().toString().trim();

    if(male_radio.isChecked()){
        gender="male";
    }
    if(female_radio.isChecked()){
        gender="female";
    }

    if(TextUtils.isEmpty(name))
    {
        Toast toast=Toast.makeText(Registration_page.this,"Please enter name",Toast.LENGTH_LONG);
        toast.show();
        return;

    }
    if(TextUtils.isEmpty(gcuid))
    {
        Toast toast =Toast.makeText(Registration_page.this,"Please enter GCU ID",Toast.LENGTH_LONG);
        toast.show();
        return;

    }
    if(TextUtils.isEmpty(gcuuser))
    {
       Toast toast= Toast.makeText(Registration_page.this,"Please enter GCU USERNAME",Toast.LENGTH_LONG);
        toast.show();
       return;

    }
    if(TextUtils.isEmpty(gcupass))
    {
        Toast toast= Toast.makeText(Registration_page.this,"Please enter GCU PASSWORD",Toast.LENGTH_LONG);
        toast.show();
        return;

    }
    if(TextUtils.isEmpty(gender))
    {
        Toast toast=Toast.makeText(Registration_page.this,"Please select a Gender",Toast.LENGTH_LONG);
        toast.show();
        return;

    }
    if(gcupass.length()<6)
    {
        Toast toast=Toast.makeText(Registration_page.this,"Suggest:Your password should greater than 6 letters",Toast.LENGTH_LONG);
        toast.show();
        return;
    }

    firebaseAuth.createUserWithEmailAndPassword(gcuuser, gcupass)
            .addOnCompleteListener(Registration_page.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Student information = new Student(
                                name,
                                gcuid,
                                gcuuser,
                                gcupass,
                                gender
                        );
                        FirebaseDatabase.getInstance().getReference("Student")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast toast =Toast.makeText(Registration_page.this,"Registration Successfull",Toast.LENGTH_LONG);
                                toast.show();
                                startActivity(new Intent(getApplicationContext(),LoginPageScreen.class));
                                overridePendingTransition(R.anim.slide_right,R.anim.slide_out);
                            }
                        });

                    } else {
                        Toast toast = Toast.makeText(Registration_page.this,"Registration Unsuccessful",Toast.LENGTH_LONG);
                        toast.show();

                    }


                }
            });
}
        }







