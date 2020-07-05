package com.example.gcuitfesst;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    CheckBox item1,item2,item3,item4,item5,item6,item7;
    String usern,check1,check2,check3,check4,check5,check6,check7,food1,food2,food3,food4,food5,food6,food7;
    TextView foodtime;
    Button submit;
    DatabaseReference ref,ref2;
    FirebaseAuth firebaseAuth;
    Intent intent2;
    Animation animation,animation2,animation3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth=FirebaseAuth.getInstance();
            intent2=getIntent();
            usern=intent2.getStringExtra(LoginPageScreen.extravlue);

        item1=(CheckBox)findViewById(R.id.item1);
        item2=(CheckBox)findViewById(R.id.item2);
        item3=(CheckBox)findViewById(R.id.item3);
        item4=(CheckBox)findViewById(R.id.item4);
        item5=(CheckBox)findViewById(R.id.item5);
        item6=(CheckBox)findViewById(R.id.item6);
        item7=(CheckBox)findViewById(R.id.item7);
        foodtime=(TextView) findViewById(R.id.foodtime);
        submit=(Button) findViewById(R.id.submit);
        //animation
        animation3=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_dow);
        foodtime.setAnimation(animation3);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_up);
        submit.setAnimation(animation);
        animation2=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_right);
        item1.setAnimation(animation2);
        item2.setAnimation(animation2);
        item3.setAnimation(animation2);
        item4.setAnimation(animation2);
        item5.setAnimation(animation2);
        item6.setAnimation(animation2);
        item7.setAnimation(animation2);
        ref= FirebaseDatabase.getInstance().getReference().child("FoodItems");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                food1=dataSnapshot.child("item1").getValue().toString();
                food2=dataSnapshot.child("item2").getValue().toString();
                food3=dataSnapshot.child("item3").getValue().toString();
                food4=dataSnapshot.child("item4").getValue().toString();
                food5=dataSnapshot.child("item5").getValue().toString();
                food6=dataSnapshot.child("item6").getValue().toString();
                food7=dataSnapshot.child("item7").getValue().toString();
                String time=dataSnapshot.child("Time").getValue().toString();
              item1.setText(food1);
              item2.setText(food2);
              item3.setText(food3);
              item4.setText(food4);
              item5.setText(food5);
              item6.setText(food6);
              item7.setText(food7);
              foodtime.setText(time);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


    }
    void ite1(View view)
    {
        item1=(CheckBox)findViewById(R.id.item1);
item1.setTextColor(Color.GREEN);
        Boolean selected = ((CheckBox) view).isChecked();
        if(selected)
        {
            check1=food1;
        }
        if(!selected)
        {
            item1.setTextColor(Color.RED);
        }

    }
    void ite2(View view)
    {
        item2=(CheckBox)findViewById(R.id.item2);
        item2.setTextColor(Color.GREEN);
        Boolean selected = ((CheckBox) view).isChecked();
        if(selected)
        {
            check2=food2;
        }
        if(!selected)
        {
            item2.setTextColor(Color.RED);
        }

    }void ite3(View view)
    {
        item3=(CheckBox)findViewById(R.id.item3);
        item3.setTextColor(Color.GREEN);
        Boolean selected = ((CheckBox) view).isChecked();
        if(selected)
        {
            check3=food3;
        }
        if(!selected)
        {
            item3.setTextColor(Color.RED);
        }

    }void ite4(View view)
    {
        item4=(CheckBox)findViewById(R.id.item4);
        item4.setTextColor(Color.GREEN);
        Boolean selected = ((CheckBox) view).isChecked();
        if(selected)
        {
            check4=food4;
        }
        if(!selected)
        {
            item4.setTextColor(Color.RED);
        }

    }void ite5(View view)
    {
        item5=(CheckBox)findViewById(R.id.item5);
        item5.setTextColor(Color.GREEN);
        Boolean selected = ((CheckBox) view).isChecked();
        if(selected)
        {
            check5=food5;
        }
        if(!selected)
        {
            item5.setTextColor(Color.RED);
        }

    }void ite6(View view)
    {
        item6=(CheckBox)findViewById(R.id.item6);
        item6.setTextColor(Color.GREEN);
        Boolean selected = ((CheckBox) view).isChecked();
        if(selected)
        {
            check6=food6;
        }
        if(!selected)
        {
            item6.setTextColor(Color.RED);
        }

    }void ite7(View view)
    {
        item7=(CheckBox)findViewById(R.id.item7);
        item7.setTextColor(Color.GREEN);
        Boolean selected = ((CheckBox) view).isChecked();
        if(selected)
        {
            check7=food7;
        }
        if(!selected)
        {
            item7.setTextColor(Color.RED);
        }

    }
    void foodbutton(View view)
    {
        FirebaseUser user=firebaseAuth.getCurrentUser();
        ref2= FirebaseDatabase.getInstance().getReference().child("FoodSelection");
        Foodselection foodselection = new Foodselection(usern,check1,check2,check3,check4,check5,check6,check7);
        ref2.child(usern).setValue(foodselection);
        Toast toast=Toast.makeText(Home.this,"Food Selection deposited",Toast.LENGTH_LONG);
        toast.show();
        startActivity(new Intent(getApplicationContext(),LoginPageScreen.class));
        overridePendingTransition(R.anim.slide_right,R.anim.slide_out);
        Toast toast2=Toast.makeText(Home.this,"Logged Out Successfully",Toast.LENGTH_LONG);
        toast2.show();



    }

}
