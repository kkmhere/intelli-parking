package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class registrationPage extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users,details;

    EditText nameText, emailText, carText, pwdText;
    Button btn;
    FirebaseAuth mAuth;

    public void login(View view){

        startActivity(new Intent(registrationPage.this, loginPage.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        database= FirebaseDatabase.getInstance();
        users=database.getReference("Users");
        details=database.getReference("details");





        mAuth = FirebaseAuth.getInstance();
        nameText =findViewById(R.id.Name);
        emailText = findViewById(R.id.Email);
        carText = findViewById(R.id.Carno);
        pwdText = findViewById(R.id.pass);
        btn =findViewById(R.id.register);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registrationPage.this, loginPage.class));
                final User user =new User(nameText.getText().toString(),
                        emailText.getText().toString(),
                        carText.getText().toString(),
                        pwdText.getText().toString());
                details.child("car no").setValue("HR 26 DA 2330");


                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getName()).exists())
                            Toast.makeText(registrationPage.this,"This name already exist",Toast.LENGTH_SHORT).show();
                        else {
                            users.child(user.getName()).setValue(user);
                            Toast.makeText(registrationPage.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                String email = emailText.getText().toString().trim();
                String pwd =pwdText.getText().toString().trim();


                mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(registrationPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(registrationPage.this, "You have been successfully registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(registrationPage.this, MainActivity.class));
                        } else {

                            Toast.makeText(registrationPage.this, "Error in registry", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}
