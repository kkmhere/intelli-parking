package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Led;
import com.example.myapplication.Model.User;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.Inet4Address;


public class MainActivity extends AppCompatActivity {

    TextView status,statustwo;
    DatabaseReference ref,refOne;
    FirebaseDatabase database;
    DatabaseReference led,slot,slotTwo;
    int payStatus=0,payStatusTwo=0;

    Button bookingSlotOne,bookingSlottwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookingSlotOne=findViewById(R.id.bookSlotOne);
        bookingSlottwo=findViewById(R.id.bookSlotTwo);

        status=findViewById(R.id.status);
        statustwo=findViewById(R.id.two);

        //led data
        database= FirebaseDatabase.getInstance();
        led=database.getReference("Led");
        slot=database.getReference("details");
        //slotTwo=database.getReference("SlotTwo");



        ref= FirebaseDatabase.getInstance().getReference("val").child("status_slot_1");
        refOne=FirebaseDatabase.getInstance().getReference("val").child("status_slot_2");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue().toString();

                int stausOne=Integer.parseInt(s);
               if(stausOne==0)
               {
                   status.setText("Not Available");
                   bookingSlotOne.setVisibility(View.INVISIBLE);
               }
               else{
                   status.setText("Available");
                   bookingSlotOne.setVisibility(View.VISIBLE);
               }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        bookingSlotOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payStatus=1;
                led.child("ledStatus").setValue(payStatus);
                slot.child("slot").setValue(payStatus);
                Intent intent=new Intent(MainActivity.this,bookingSlot1.class);
                startActivity(intent);


            }
        });
        bookingSlottwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payStatusTwo=1;
                Intent intent=new Intent(MainActivity.this,bookingSlot2.class);
                startActivity(intent);
                led.child("ledStatus2").setValue(payStatusTwo);
                slot.child("slot").setValue(2);

            }
        });
        refOne.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue().toString();
                int StatusTwo=Integer.parseInt(s);
                if (StatusTwo==0){
                    statustwo.setText("Not Available");
                    bookingSlottwo.setVisibility(View.INVISIBLE);
                }
                else
                {
                    statustwo.setText("Available");
                    bookingSlottwo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
