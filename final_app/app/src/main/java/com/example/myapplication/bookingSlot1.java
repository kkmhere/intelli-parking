package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bookingSlot1 extends AppCompatActivity {
    int payStatus=0;
    FirebaseDatabase database;
    DatabaseReference details;
    public void rbClick(View view){

        int a = 50;
        Toast.makeText(this, "You have to pay Rs." + a +  "for the slot!!", Toast.LENGTH_SHORT).show();

    }

    public void rbClick2(View view){
        int b = 100;
        Toast.makeText(this, "You have to pay Rs." + b + "for the slot!!", Toast.LENGTH_SHORT).show();

    }

    public void rbClick3(View view){

        int c= 150;
        Toast.makeText(this, "You have to pay Rs." + c + "for the slot!!", Toast.LENGTH_SHORT).show();

    }

    public void rbClick4(View view){

        int d =200;
        Toast.makeText(this, "You have to pay Rs." + d + "for the slot!!", Toast.LENGTH_SHORT).show();

    }

    public void rbClick5(View view){

        int e = 250;
        Toast.makeText(this, "You have to pay Rs." + e + "for the slot!!", Toast.LENGTH_SHORT).show();

    }

    public void rbClick6(View view){

        int f = 300;
        Toast.makeText(this, "You have to pay Rs." + f + "for the slot!!", Toast.LENGTH_SHORT).show();

    }


    public void success(View view){
        //details=database.getReference("details");
        //payStatus=1;
        //details.child("pStatus").setValue(payStatus);
       startActivity(new Intent(bookingSlot1.this, Confirmation_1.class));
    }

   public void cancel(View view){

        startActivity(new Intent(bookingSlot1.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_slot1);



    }

}
