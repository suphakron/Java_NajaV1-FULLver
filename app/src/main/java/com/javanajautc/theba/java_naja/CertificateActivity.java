package com.javanajautc.theba.java_naja;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CertificateActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser user;
    TextView username,txt_date;
    DatabaseReference current_user_db, current_user_db_fName, current_user_db_lName ,current_user_db_finish_date;
    private String Fname, Lname, user_id, imgLocation;
    DatabaseReference myRef;
    private Date date, month, year;
    String Months[] = {
            "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
            "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
            "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();

        user_id = user.getUid();
        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user_db_finish_date = current_user_db.child("finish_date");

        username = findViewById(R.id.Cert_username);
        txt_date = findViewById(R.id.text_date);

        current_user_db_fName = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("FName");
        current_user_db_fName.addValueEventListener(new ValueEventListener() {
            //final ProgressDialog progressDialog = ProgressDialog.show(CertificateActivity .this, "Please wait...", "Loading...", true, true);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Fname = dataSnapshot.getValue(String.class);
                //progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        current_user_db_lName = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("LName");
        current_user_db_lName.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ProgressDialog progressDialog = ProgressDialog.show(CertificateActivity .this, "Please wait...", "Loading...", true, true);
                Lname = dataSnapshot.getValue(String.class);
                username.setText(Fname + " " + Lname);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        current_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("finish_date")) {
                    ValueEventListener valueEventListener = current_user_db_finish_date.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String str = dataSnapshot.getValue(String.class);
                            txt_date.setText(str);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                } else {
                    date = new Date();
                    //String now_date = DateFormat.getDateInstance(DateFormat.LONG).format(date);
                    String now_date = new SimpleDateFormat("dd").format(date);

                    month = new Date();
                    String now_month = new SimpleDateFormat("MM").format(month);
                    now_month = now_month.replace("0","");
                    int count_month = Integer.parseInt(now_month);
                    count_month = count_month - 1;

                    year = new Date();
                    String now_year = new SimpleDateFormat("yyyy").format(year);
                    int year = Integer.parseInt(now_year);
                    year = year + 543;
                    now_year = String.valueOf(year);

                    //Toast.makeText(CertificateActivity.this,""+now_date+" "+now_year,Toast.LENGTH_LONG).show();
                    //Toast.makeText(CertificateActivity.this,""+count_month,Toast.LENGTH_LONG).show();
                    String str = String.format("%s %s พ.ศ.%s", now_date, Months[count_month], now_year);
                    txt_date.setText(str);

                    Map newpost = new HashMap();
                    newpost.put("finish_date", str);
                    current_user_db.updateChildren(newpost);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}