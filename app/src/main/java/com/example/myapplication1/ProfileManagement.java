package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication1.Database.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText userName,dob,password;
    String gender;

    RadioButton male, female;

        Button  update,add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        userName = findViewById(R.id.userNameP);
        dob = findViewById(R.id.dateOfBirthP);
        password = findViewById(R.id.passwordP);
        male = findViewById(R.id.MaleP);
        female = findViewById(R.id.FemaleP);
        update= findViewById(R.id.buttonUpdateP);
        add = findViewById(R.id.addP);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

       add.setOnClickListener(new View.OnClickListener() {


           @Override
           public void onClick(View view) {
               DBHandler dbHandler = new DBHandler(getApplicationContext());

               String user = userName.getText().toString();
               String pass = password.getText().toString();
               String Dob = dob.getText().toString();
               //String user = userName.getText().toString();

               if(male.isChecked()){
                   gender = "Male";
               }
               else{
                   gender = "Female";
               }
               long newRowId = dbHandler.addInfor(user,Dob,pass,gender);

               if(newRowId > 0) {


                   Toast.makeText(ProfileManagement.this, "User Added", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(getApplicationContext(),EditProfile.class);
                   startActivity(i);

               }
               else{
                   Toast.makeText(ProfileManagement.this, "Insertion failed!", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }

}
