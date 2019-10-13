package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication1.Database.DBHandler;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText userName, dob,password;
    String gender;

    RadioButton male, female;

    Button delete,edit,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        userName = findViewById(R.id.usernameE);
        dob = findViewById(R.id.DateOfBirthE);
        password = findViewById(R.id.passwordE);
        male = findViewById(R.id.maleE);
        female = findViewById(R.id.femaleE);
        search= findViewById(R.id.buttonSearchE);
        edit = findViewById(R.id.buttonEditE);
        delete = findViewById(R.id.buttonDeleteE);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                List users = dbHandler.readAllInfo(userName.getText().toString());

                if(users.isEmpty()){
                    Toast.makeText(EditProfile.this, "No user", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "User found", Toast.LENGTH_SHORT).show();
                    userName.setText(users.get(0).toString());
                    dob.setText(users.get(1).toString());
                    password.setText(users.get(2).toString());
                   // gender.setText(users.get(4).toString());

                    if(users.get(3).toString().equals("Male")){
                        male.setChecked(true);
                    }
                    else{
                        female.setChecked(true);
                    }
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String dateofbirth= dob.getText().toString();

                if(male.isChecked()){
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }

                Boolean status = dbHandler.updateInfo(user,dateofbirth,pass,gender);

               if( status){
                   Toast.makeText(EditProfile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(EditProfile.this, "Update failed!", Toast.LENGTH_SHORT).show();
               }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                dbHandler.deleteInfo(userName.getText().toString());

                Toast.makeText(EditProfile.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();

                userName.setText(null);
                password.setText(null);
                dob.setText(null);
                male.setChecked(false);
                female.setChecked(false);
            }
        });

    }
}
