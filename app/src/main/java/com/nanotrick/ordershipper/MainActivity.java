package com.nanotrick.ordershipper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.nanotrick.ordershipper.Common.Common;
import com.nanotrick.ordershipper.Model.Shipper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;


public class MainActivity extends AppCompatActivity {
    private FButton btnSignIn;
    private MaterialEditText edtPhone, edtPassword;

    private FirebaseDatabase database;
    private DatabaseReference referenceShippers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (FButton) findViewById(R.id.btnSignIn);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);

        //Firebase
        database = FirebaseDatabase.getInstance();
        referenceShippers = database.getReference(Common.SHIPPER_TABLE);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(edtPhone.getText().toString(), edtPassword.getText().toString());
            }
        });

    }

    private void login(String phone, final String password) {
        referenceShippers.child(phone)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Shipper shipper = dataSnapshot.getValue(Shipper.class);
                            if (shipper.getPassword().equals(password)) {
                                //Login succeed
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                Common.currentShipper = shipper;
                                finish();

                            }else
                            {
                                Toast.makeText(MainActivity.this, "Your shipper's phone not exists !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Password incoorect !", Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }
}
