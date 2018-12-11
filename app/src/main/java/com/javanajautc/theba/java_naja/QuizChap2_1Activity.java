package com.javanajautc.theba.java_naja;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class QuizChap2_1Activity extends AppCompatActivity {

    private QuizChapter2 mQuiz = new QuizChapter2();
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef, current_user_db_score;
    private FirebaseUser user;

    private TextView mScoreView;
    private TextView mQuizView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnwser, user_id, user_Score;
    private int mScore;
    private int mQuizNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_chap2_1);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        user = mAuth.getCurrentUser();
        user_id = user.getUid();
        current_user_db_score = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("UserScore");


        mScoreView = (TextView) findViewById(R.id.score);
        mQuizView = (TextView) findViewById(R.id.Quiz);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);

        current_user_db_score.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mScore = dataSnapshot.getValue(Integer.class);
                updateQuiz();
                //Toast.makeText(QuizChap2_1Activity.this,""+mScore,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice1.getText() == mAnwser){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuiz();

                    Toast.makeText(QuizChap2_1Activity.this,"คำตอบถูกต้อง",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizChap2_1Activity.this,"คำตอบไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                    finish();
                    updateQuiz();
                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice2.getText() == mAnwser){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuiz();

                    Toast.makeText(QuizChap2_1Activity.this,"คำตอบถูกต้อง",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizChap2_1Activity.this,"คำตอบไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                    finish();
                    updateQuiz();
                }
            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice3.getText() == mAnwser){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuiz();

                    Toast.makeText(QuizChap2_1Activity.this,"คำตอบถูกต้อง",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizChap2_1Activity.this,"คำตอบไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                    finish();
                    updateQuiz();
                }
            }
        });

        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonChoice4.getText() == mAnwser){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuiz();

                    Toast.makeText(QuizChap2_1Activity.this,"คำตอบถูกต้อง",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizChap2_1Activity.this,"คำตอบไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                    finish();
                    updateQuiz();
                }
            }
        });
    }

    private void updateQuiz(){

        if (mScore >= 4){

            user_Score = String.valueOf(mScore);

            current_user_db_score = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
            Map newpost = new HashMap();
            newpost.put("UserScore",mScore);
            current_user_db_score.updateChildren(newpost);

            Intent intent = new Intent(QuizChap2_1Activity.this, Chap2_2Activity.class);
            finish();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        } else {

            user_Score = Integer.toString(mScore);

            current_user_db_score = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
            Map newpost = new HashMap();
            newpost.put("UserScore",mScore);
            current_user_db_score.updateChildren(newpost);
        }

        mQuizView.setText(mQuiz.getQuestion(mQuizNumber));
        mButtonChoice1.setText(mQuiz.getChoice1(mQuizNumber));
        mButtonChoice2.setText(mQuiz.getChoice2(mQuizNumber));
        mButtonChoice3.setText(mQuiz.getChoice3(mQuizNumber));
        mButtonChoice4.setText(mQuiz.getChoice4(mQuizNumber));

        mAnwser = mQuiz.getCorrectAnswer(mQuizNumber);

        if (mQuizNumber >= 1){
            mQuizNumber = 0;
        } else {
            mQuizNumber = 0;
        }
    }

    private void updateScore(int score){
        mScoreView.setText("" + mScore);
    }

}