package com.javanajautc.theba.java_naja;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AchievementActivity extends AppCompatActivity {

    private TextView button_chap1, button_chap2, button_chap3, button_chap4, txt_date ,
                     button_chap5, button_chap6, button_chap7, button_chap8, button_cert, score_show1, score_show2,
                     score_show3, score_show4, score_show5, score_show6, score_show7, score_show8;
    private ImageView achievement1,achievement1_cup, achievement2, achievement2_cup, cert_cup,
                    achievement3, achievement3_cup, achievement4, achievement4_cup, achievement5, achievement5_cup,
                    achievement6, achievement6_cup, achievement7, achievement7_cup, achievement8, achievement8_cup;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef ,current_user_db_score, current_user_db, current_user_db_finish_date;
    private String user_id, Score;
    private FirebaseUser user;
    private Date date, month, year;
    String Months[] = {
            "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
            "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
            "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};

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
        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user_db_finish_date = current_user_db.child("finish_date");

        button_chap1 = (TextView) findViewById(R.id.but_chap1);
        button_chap2 = (TextView) findViewById(R.id.but_chap2);
        button_chap3 = (TextView) findViewById(R.id.but_chap3);
        button_chap4 = (TextView) findViewById(R.id.but_chap4);
        button_chap5 = (TextView) findViewById(R.id.but_chap5);
        button_chap6 = (TextView) findViewById(R.id.but_chap6);
        button_chap7 = (TextView) findViewById(R.id.but_chap7);
        button_chap8 = findViewById(R.id.but_chap8);
        button_cert = findViewById(R.id.but_certificate);
        button_cert.setVisibility(View.INVISIBLE);

        achievement1 = (ImageView) findViewById(R.id.achievement_check1);
        achievement2 = (ImageView) findViewById(R.id.achievement_check2);
        achievement3 = (ImageView) findViewById(R.id.achievement_check3);
        achievement4 = (ImageView) findViewById(R.id.achievement_check4);
        achievement5 = (ImageView) findViewById(R.id.achievement_check5);
        achievement6 = (ImageView) findViewById(R.id.achievement_check6);
        achievement7 = (ImageView) findViewById(R.id.achievement_check7);
        achievement8 = (ImageView) findViewById(R.id.achievement_check8);

        achievement1_cup = findViewById(R.id.achievement_cup1);
        achievement2_cup = findViewById(R.id.achievement_cup2);
        achievement3_cup = findViewById(R.id.achievement_cup3);
        achievement4_cup = findViewById(R.id.achievement_cup4);
        achievement5_cup = findViewById(R.id.achievement_cup5);
        achievement6_cup = findViewById(R.id.achievement_cup6);
        achievement7_cup = findViewById(R.id.achievement_cup7);
        achievement8_cup = findViewById(R.id.achievement_cup8);
        cert_cup = findViewById(R.id.achievement_certcup);

        score_show1 = (TextView) findViewById(R.id.Score_show1);
        score_show2 = (TextView) findViewById(R.id.Score_show2);
        score_show3 = (TextView) findViewById(R.id.Score_show3);
        score_show4 = (TextView) findViewById(R.id.Score_show4);
        score_show5 = (TextView) findViewById(R.id.Score_show5);
        score_show6 = (TextView) findViewById(R.id.Score_show6);
        score_show7 = (TextView) findViewById(R.id.Score_show7);
        score_show8 = (TextView) findViewById(R.id.Score_show8);

        button_chap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap1Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap2Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap3Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap4Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap5Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap6Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap7Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap8Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        current_user_db_score.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer score = dataSnapshot.getValue(Integer.class);
                if (score >=0 && score <3){
                    achievement1.setImageResource(R.drawable.achievement_notpass);
                    score_show1.setText(score + "/3");
                    score_show1.setTextColor(Color.parseColor("#c77b00")); //orange
                } else if (score >= 3 && score <14){
                    achievement1.setImageResource(R.drawable.achievement_pass);
                    achievement1_cup.setImageResource(R.drawable.achievement_cup);
                    score_show1.setText("3/3");
                    achievement2.setImageResource(R.drawable.achievement_notpass);
                    score_show2.setText(score-3 + "/11");
                    score_show2.setTextColor(Color.parseColor("#c77b00"));
                    score_show1.setTextColor(Color.parseColor("#8cc63e")); //green-app
                } else if(score >= 14 && score <28) {
                    achievement1.setImageResource(R.drawable.achievement_pass);
                    achievement1_cup.setImageResource(R.drawable.achievement_cup);
                    achievement2.setImageResource(R.drawable.achievement_pass);
                    achievement2_cup.setImageResource(R.drawable.achievement_cup);
                    score_show1.setText("3/3");
                    score_show1.setTextColor(Color.parseColor("#8cc63e"));
                    score_show2.setText("11/11");
                    score_show2.setTextColor(Color.parseColor("#8cc63e"));
                    achievement3.setImageResource(R.drawable.achievement_notpass);
                    score_show3.setText(score-14 + "/14");
                    score_show3.setTextColor(Color.parseColor("#c77b00"));
                } else if(score >= 28 && score <46) {
                    achievement1.setImageResource(R.drawable.achievement_pass);
                    achievement1_cup.setImageResource(R.drawable.achievement_cup);
                    achievement2.setImageResource(R.drawable.achievement_pass);
                    achievement2_cup.setImageResource(R.drawable.achievement_cup);
                    achievement3.setImageResource(R.drawable.achievement_pass);
                    achievement3_cup.setImageResource(R.drawable.achievement_cup);
                    score_show1.setText("3/3");
                    score_show1.setTextColor(Color.parseColor("#8cc63e"));
                    score_show2.setText("11/11");
                    score_show2.setTextColor(Color.parseColor("#8cc63e"));
                    score_show3.setText("14/14");
                    score_show3.setTextColor(Color.parseColor("#8cc63e"));
                    achievement4.setImageResource(R.drawable.achievement_notpass);
                    score_show4.setText(score-28 + "/18");
                    score_show4.setTextColor(Color.parseColor("#c77b00"));
                } else if(score >= 46 && score <59) {
                    achievement1.setImageResource(R.drawable.achievement_pass);
                    achievement1_cup.setImageResource(R.drawable.achievement_cup);
                    achievement2.setImageResource(R.drawable.achievement_pass);
                    achievement2_cup.setImageResource(R.drawable.achievement_cup);
                    achievement3.setImageResource(R.drawable.achievement_pass);
                    achievement3_cup.setImageResource(R.drawable.achievement_cup);
                    achievement4.setImageResource(R.drawable.achievement_pass);
                    achievement4_cup.setImageResource(R.drawable.achievement_cup);
                    score_show1.setText("3/3");
                    score_show1.setTextColor(Color.parseColor("#8cc63e"));
                    score_show2.setText("11/11");
                    score_show2.setTextColor(Color.parseColor("#8cc63e"));
                    score_show3.setText("14/14");
                    score_show3.setTextColor(Color.parseColor("#8cc63e"));
                    score_show4.setText("18/18");
                    score_show4.setTextColor(Color.parseColor("#8cc63e"));
                    achievement5.setImageResource(R.drawable.achievement_notpass);
                    score_show5.setText(score-46 + "/13");
                    score_show5.setTextColor(Color.parseColor("#c77b00"));
                } else if(score >= 59 && score <76) {
                    achievement1.setImageResource(R.drawable.achievement_pass);
                    achievement1_cup.setImageResource(R.drawable.achievement_cup);
                    achievement2.setImageResource(R.drawable.achievement_pass);
                    achievement2_cup.setImageResource(R.drawable.achievement_cup);
                    achievement3.setImageResource(R.drawable.achievement_pass);
                    achievement3_cup.setImageResource(R.drawable.achievement_cup);
                    achievement4.setImageResource(R.drawable.achievement_pass);
                    achievement4_cup.setImageResource(R.drawable.achievement_cup);
                    achievement5.setImageResource(R.drawable.achievement_pass);
                    achievement5_cup.setImageResource(R.drawable.achievement_cup);
                    score_show1.setText("3/3");
                    score_show1.setTextColor(Color.parseColor("#8cc63e"));
                    score_show2.setText("11/11");
                    score_show2.setTextColor(Color.parseColor("#8cc63e"));
                    score_show3.setText("14/14");
                    score_show3.setTextColor(Color.parseColor("#8cc63e"));
                    score_show4.setText("18/18");
                    score_show4.setTextColor(Color.parseColor("#8cc63e"));
                    score_show5.setText("13/13");
                    score_show5.setTextColor(Color.parseColor("#8cc63e"));
                    achievement6.setImageResource(R.drawable.achievement_notpass);
                    score_show6.setText(score-59 + "/17");
                    score_show6.setTextColor(Color.parseColor("#c77b00"));
                } else if(score >= 76 && score <89) {
                    achievement1.setImageResource(R.drawable.achievement_pass);
                    achievement1_cup.setImageResource(R.drawable.achievement_cup);
                    achievement2.setImageResource(R.drawable.achievement_pass);
                    achievement2_cup.setImageResource(R.drawable.achievement_cup);
                    achievement3.setImageResource(R.drawable.achievement_pass);
                    achievement3_cup.setImageResource(R.drawable.achievement_cup);
                    achievement4.setImageResource(R.drawable.achievement_pass);
                    achievement4_cup.setImageResource(R.drawable.achievement_cup);
                    achievement5.setImageResource(R.drawable.achievement_pass);
                    achievement5_cup.setImageResource(R.drawable.achievement_cup);
                    achievement6.setImageResource(R.drawable.achievement_pass);
                    achievement6_cup.setImageResource(R.drawable.achievement_cup);
                    score_show1.setText("3/3");
                    score_show1.setTextColor(Color.parseColor("#8cc63e"));
                    score_show2.setText("11/11");
                    score_show2.setTextColor(Color.parseColor("#8cc63e"));
                    score_show3.setText("14/14");
                    score_show3.setTextColor(Color.parseColor("#8cc63e"));
                    score_show4.setText("18/18");
                    score_show4.setTextColor(Color.parseColor("#8cc63e"));
                    score_show5.setText("13/13");
                    score_show5.setTextColor(Color.parseColor("#8cc63e"));
                    score_show6.setText("17/17");
                    score_show6.setTextColor(Color.parseColor("#8cc63e"));
                    achievement7.setImageResource(R.drawable.achievement_notpass);
                    score_show7.setText(score-76 + "/13");
                    score_show7.setTextColor(Color.parseColor("#c77b00"));
                } else if(score >= 89 && score <94) {
                    achievement1.setImageResource(R.drawable.achievement_pass);
                    achievement1_cup.setImageResource(R.drawable.achievement_cup);
                    achievement2.setImageResource(R.drawable.achievement_pass);
                    achievement2_cup.setImageResource(R.drawable.achievement_cup);
                    achievement3.setImageResource(R.drawable.achievement_pass);
                    achievement3_cup.setImageResource(R.drawable.achievement_cup);
                    achievement4.setImageResource(R.drawable.achievement_pass);
                    achievement4_cup.setImageResource(R.drawable.achievement_cup);
                    achievement5.setImageResource(R.drawable.achievement_pass);
                    achievement5_cup.setImageResource(R.drawable.achievement_cup);
                    achievement6.setImageResource(R.drawable.achievement_pass);
                    achievement6_cup.setImageResource(R.drawable.achievement_cup);
                    achievement7.setImageResource(R.drawable.achievement_pass);
                    achievement7_cup.setImageResource(R.drawable.achievement_cup);
                    score_show1.setText("3/3");
                    score_show1.setTextColor(Color.parseColor("#8cc63e"));
                    score_show2.setText("11/11");
                    score_show2.setTextColor(Color.parseColor("#8cc63e"));
                    score_show3.setText("14/14");
                    score_show3.setTextColor(Color.parseColor("#8cc63e"));
                    score_show4.setText("18/18");
                    score_show4.setTextColor(Color.parseColor("#8cc63e"));
                    score_show5.setText("13/13");
                    score_show5.setTextColor(Color.parseColor("#8cc63e"));
                    score_show6.setText("17/17");
                    score_show6.setTextColor(Color.parseColor("#8cc63e"));
                    score_show7.setText("13/13");
                    score_show7.setTextColor(Color.parseColor("#8cc63e"));
                    achievement8.setImageResource(R.drawable.achievement_notpass);
                    score_show8.setText(score-89 + "/5");
                    score_show8.setTextColor(Color.parseColor("#c77b00"));
                } else if(score>=94){
                    achievement1.setImageResource(R.drawable.achievement_pass);
                    achievement1_cup.setImageResource(R.drawable.achievement_cup);
                    achievement2.setImageResource(R.drawable.achievement_pass);
                    achievement2_cup.setImageResource(R.drawable.achievement_cup);
                    achievement3.setImageResource(R.drawable.achievement_pass);
                    achievement3_cup.setImageResource(R.drawable.achievement_cup);
                    achievement4.setImageResource(R.drawable.achievement_pass);
                    achievement4_cup.setImageResource(R.drawable.achievement_cup);
                    achievement5.setImageResource(R.drawable.achievement_pass);
                    achievement5_cup.setImageResource(R.drawable.achievement_cup);
                    achievement6.setImageResource(R.drawable.achievement_pass);
                    achievement6_cup.setImageResource(R.drawable.achievement_cup);
                    achievement7.setImageResource(R.drawable.achievement_pass);
                    achievement7_cup.setImageResource(R.drawable.achievement_cup);
                    achievement8.setImageResource(R.drawable.achievement_pass);
                    achievement8_cup.setImageResource(R.drawable.achievement_cup);
                    score_show1.setText("3/3");
                    score_show1.setTextColor(Color.parseColor("#8cc63e"));
                    score_show2.setText("11/11");
                    score_show2.setTextColor(Color.parseColor("#8cc63e"));
                    score_show3.setText("14/14");
                    score_show3.setTextColor(Color.parseColor("#8cc63e"));
                    score_show4.setText("18/18");
                    score_show4.setTextColor(Color.parseColor("#8cc63e"));
                    score_show5.setText("13/13");
                    score_show5.setTextColor(Color.parseColor("#8cc63e"));
                    score_show6.setText("17/17");
                    score_show6.setTextColor(Color.parseColor("#8cc63e"));
                    score_show7.setText("13/13");
                    score_show7.setTextColor(Color.parseColor("#8cc63e"));
                    score_show8.setText("5/5");
                    score_show8.setTextColor(Color.parseColor("#8cc63e"));

                    current_user_db_finish_date.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String str = dataSnapshot.getValue(String.class);
                                        if(str!=null){
                                            button_cert.setVisibility(View.VISIBLE);
                                            button_cert.setTextColor(Color.parseColor("#8cc63e"));
                                            cert_cup.setImageResource(R.drawable.icon_certificate);
                                        } else {
                                            button_cert.setVisibility(View.VISIBLE);
                                            button_cert.setTextColor(Color.parseColor("#c77b00"));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }
                                });

                }

                button_cert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(AchievementActivity.this,CertificateActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
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
