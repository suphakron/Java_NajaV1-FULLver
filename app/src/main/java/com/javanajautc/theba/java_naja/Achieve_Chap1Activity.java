package com.javanajautc.theba.java_naja;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Achieve_Chap1Activity extends AppCompatActivity {

    private ImageView star,star2,star3;
    private TextView txt_total;
    FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef ,current_user_db_score;
    private String user_id, Score;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve__chap1);

        ActionBar mBar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#30000000")));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView title = (TextView) findViewById(R.id.action_bar_title);
        title.setText("บทที่ 1");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        user_id = user.getUid();

        current_user_db_score = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("UserScore");

        star = findViewById(R.id.star);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        txt_total = findViewById(R.id.score_total);

        current_user_db_score.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer score = dataSnapshot.getValue(Integer.class);
//                if (fname.equals(1)){
//                    star.setEnabled(true);
//                } else if (fname.equals(2)){
//                    star2.setEnabled(true);
//                } else if (fname.equals(3)){
//                    star3.setEnabled(true);
//                }
                 if (score == 1) {
                    star.setImageResource(R.drawable.star);
                    txt_total.setText("1/3");
                } else if (score == 2) {
                    star.setImageResource(R.drawable.star);
                    star2.setImageResource(R.drawable.star);
                    txt_total.setText("2/3");
                } else if (score >= 3) {
                    star.setImageResource(R.drawable.star);
                    star2.setImageResource(R.drawable.star);
                    star3.setImageResource(R.drawable.star);
                    txt_total.setText("3/3");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //do whatever
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
