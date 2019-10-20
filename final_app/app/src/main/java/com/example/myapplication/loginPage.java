package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginPage extends AppCompatActivity {

FirebaseDatabase database;
DatabaseReference users,details;
Button signIn,signUp;
EditText emailText,pwdText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //Firebase Database
        database=FirebaseDatabase.getInstance();
        users=database.getReference("Users");
        details=database.getReference("details");

        emailText=findViewById(R.id.set_email);
        pwdText=findViewById(R.id.password);
        signIn=findViewById(R.id.sign_in);
        signUp=findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent up=new Intent(loginPage.this,registrationPage.class);
                startActivity(up);

            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin(emailText.getText().toString(),
                        pwdText.getText().toString());
            }
        });











    }

    private void signin(final String name,final String pass) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(name).exists()){
                    if (!name.isEmpty()){
                        User login =dataSnapshot.child(name).getValue(User.class);
                        if (login.getPassword().equals(pass)){
                            Toast.makeText(loginPage.this,"Success login",Toast.LENGTH_SHORT).show();
                            Intent s= new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(s);
                        }
                        else{
                            Toast.makeText(loginPage.this,"password is wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(loginPage.this,"username is not registered",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
