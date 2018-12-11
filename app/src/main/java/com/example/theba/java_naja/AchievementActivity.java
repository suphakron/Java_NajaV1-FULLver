package com.example.theba.java_naja;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AchievementActivity extends AppCompatActivity {

    private TextView button_chap1, button_chap2, button_chap3, button_chap4,
                     button_chap5, button_chap6, button_chap7 , score_show1;
    private ImageView achievement1;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef ,current_user_db_score;
    private String user_id, Score;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#30000000")));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        getSupportActionBar().setTitle("ความสำเร็จ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        user_id = user.getUid();

        current_user_db_score = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("UserScore");

        button_chap1 = (TextView) findViewById(R.id.but_chap1);
        achievement1 = (ImageView) findViewById(R.id.achievement_check);
        score_show1 = (TextView) findViewById(R.id.Score_show);
        button_chap2 = (TextView) findViewById(R.id.but_chap2);
        button_chap3 = (TextView) findViewById(R.id.but_chap3);
        button_chap4 = (TextView) findViewById(R.id.but_chap4);
        button_chap5 = (TextView) findViewById(R.id.but_chap5);
        button_chap6 = (TextView) findViewById(R.id.but_chap6);
        button_chap7 = (TextView) findViewById(R.id.but_chap7);

        button_chap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap1Activity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        current_user_db_score.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer fname = dataSnapshot.getValue(Integer.class);
                if (fname >= 3){
                    achievement1.setImageResource(R.drawable.achievement_pass);
                    score_show1.setText("3/3");
                    score_show1.setTextColor(Color.parseColor("#c77b00"));
                } else if(fname > 0) {
                    achievement1.setImageResource(R.drawable.achievement_notpass);
                    score_show1.setText(fname + "/3");
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
