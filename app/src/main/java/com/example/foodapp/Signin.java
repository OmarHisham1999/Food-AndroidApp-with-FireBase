package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.Common.Common;
import com.example.foodapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signin extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(Signin.this);
                mDialog.setMessage("Please Wait...");
                mDialog.show();



                if(edtPhone.getText().toString().trim().length() == 0)
                {
                    edtPhone.setError("Phone is required");
                    edtPhone.requestFocus();
                    mDialog.dismiss();
                    return;
                }

                if(edtPassword.getText().toString().trim().length() == 0)
                {
                    edtPassword.setError("Password is required");
                    edtPassword.requestFocus();
                    mDialog.dismiss();
                    return;
                }


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        if(snapshot.child(edtPhone.getText().toString()).exists()){
                            //get User Info
                            mDialog.dismiss();

                            User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Toast.makeText(Signin.this, "SignIn Successfully ", Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(Signin.this,Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();

                            } else
                                Toast.makeText(Signin.this, "SignIn Failed ", Toast.LENGTH_SHORT).show();

                        }
                       else
                        {
                            Toast.makeText(Signin.this, "User Doesn't Exist ", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                    }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
