package com.javanajautc.theba.java_naja;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chap7Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef ,current_user_db_score, current_user_db_fname, current_user_db_lname, current_user_db;
    private String user_id, Score;
    private String email;
    private TextView score, Fname, Lname;
    private int mScore;
    private Button button_chap7_1, button_chap7_2, button_chap7_3, button_chap7_4, button_chap7_5, button_chap7_6, button_chap7_7;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap7);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#8cc63e"));

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        user_id = user.getUid();
        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user_db_fname = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("FName");
        current_user_db_lname = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("LName");
        current_user_db_score = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("UserScore");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textEmail);
        navUsername.setText(email);
        CircleImageView img_profile = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.imageProfile);
        score = (TextView) navigationView.getHeaderView(0).findViewById(R.id.Score_show);
        Fname = (TextView) headerView.findViewById(R.id.textFName);
        Lname = (TextView) headerView.findViewById(R.id.textLName);

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.custom_progressbar_drawable);
        final ProgressBar mProgress = (ProgressBar) headerView.findViewById(R.id.circularProgressbar);
        tv = (TextView) headerView.findViewById(R.id.tv);
        //mProgress.setProgress(0);   Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

        current_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("UserScore")) {
                    current_user_db_score.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mScore = dataSnapshot.getValue(Integer.class);
                            Score = String.valueOf(mScore);
                            score.setText(Score);

                            mProgress.setProgress(mScore);
                            tv.setText(Score + " %");

                            if (mScore == 83){
                                button_chap7_1.getBackground().setColorFilter(Color.parseColor("#bebcbc"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_2.setEnabled(false);
                                button_chap7_2.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_3.setEnabled(false);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_4.setEnabled(false);
                                button_chap7_4.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_5.setEnabled(false);
                                button_chap7_5.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_6.setEnabled(false);
                                button_chap7_6.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_6.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_7.setEnabled(false);
                                button_chap7_7.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setTextColor(Color.parseColor("#80000000"));
                            } else if (mScore == 84){
                                button_chap7_1.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_2.setEnabled(true);
                                button_chap7_2.getBackground().setColorFilter(Color.parseColor("#bebcbc"), PorterDuff.Mode.MULTIPLY);
                                //#20000000
                                button_chap7_3.setEnabled(false);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_3.setEnabled(false);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_4.setEnabled(false);
                                button_chap7_4.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_5.setEnabled(false);
                                button_chap7_5.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_6.setEnabled(false);
                                button_chap7_6.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_6.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_7.setEnabled(false);
                                button_chap7_7.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setTextColor(Color.parseColor("#80000000"));
                            } else if (mScore == 85){
                                button_chap7_1.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);

                                button_chap7_2.setEnabled(true);
                                button_chap7_2.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setEnabled(true);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#bebcbc"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setEnabled(false);
                                button_chap7_4.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_5.setEnabled(false);
                                button_chap7_5.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_6.setEnabled(false);
                                button_chap7_6.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_6.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_7.setEnabled(false);
                                button_chap7_7.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setTextColor(Color.parseColor("#80000000"));
                            } else if (mScore == 86){
                                button_chap7_1.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);

                                button_chap7_2.setEnabled(true);
                                button_chap7_2.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setEnabled(true);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setEnabled(true);
                                button_chap7_4.getBackground().setColorFilter(Color.parseColor("#bebcbc"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setEnabled(false);
                                button_chap7_5.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_6.setEnabled(false);
                                button_chap7_6.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_6.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_7.setEnabled(false);
                                button_chap7_7.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setTextColor(Color.parseColor("#80000000"));
                            }else if (mScore == 87){
                                button_chap7_1.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);

                                button_chap7_2.setEnabled(true);
                                button_chap7_2.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setEnabled(true);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setEnabled(true);
                                button_chap7_4.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setEnabled(true);
                                button_chap7_5.getBackground().setColorFilter(Color.parseColor("#bebcbc"), PorterDuff.Mode.MULTIPLY);

                                button_chap7_6.setEnabled(false);
                                button_chap7_6.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_6.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_7.setEnabled(false);
                                button_chap7_7.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setTextColor(Color.parseColor("#80000000"));
                            }else if (mScore == 88){
                                button_chap7_1.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);

                                button_chap7_2.setEnabled(true);
                                button_chap7_2.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setEnabled(true);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setEnabled(true);
                                button_chap7_4.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setEnabled(true);
                                button_chap7_5.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_6.setEnabled(true);
                                button_chap7_6.getBackground().setColorFilter(Color.parseColor("#bebcbc"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setEnabled(false);
                                button_chap7_7.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setTextColor(Color.parseColor("#80000000"));
                            } else if (mScore == 89){
                                button_chap7_1.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);

                                button_chap7_2.setEnabled(true);
                                button_chap7_2.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setEnabled(true);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setEnabled(true);
                                button_chap7_4.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setEnabled(true);
                                button_chap7_5.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_6.setEnabled(true);
                                button_chap7_6.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setEnabled(true);
                                button_chap7_7.getBackground().setColorFilter(Color.parseColor("#bebcbc"), PorterDuff.Mode.MULTIPLY);

                            } else if (mScore >= 96){
                                button_chap7_1.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);

                                button_chap7_2.setEnabled(true);
                                button_chap7_2.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setEnabled(true);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setEnabled(true);
                                button_chap7_4.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setEnabled(true);
                                button_chap7_5.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_6.setEnabled(true);
                                button_chap7_6.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setEnabled(true);
                                button_chap7_7.getBackground().setColorFilter(Color.parseColor("#aee663"), PorterDuff.Mode.MULTIPLY);

                            }else {
                                button_chap7_1.setEnabled(false);
                                button_chap7_1.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_1.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_2.setEnabled(false);
                                button_chap7_2.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_2.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_3.setEnabled(false);
                                button_chap7_3.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_3.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_4.setEnabled(false);
                                button_chap7_4.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_4.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_5.setEnabled(false);
                                button_chap7_5.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_5.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_6.setEnabled(false);
                                button_chap7_6.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_6.setTextColor(Color.parseColor("#80000000"));
                                button_chap7_7.setEnabled(false);
                                button_chap7_7.getBackground().setColorFilter(Color.parseColor("#e3e3e3"), PorterDuff.Mode.MULTIPLY);
                                button_chap7_7.setTextColor(Color.parseColor("#80000000"));
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                } else {
                    Map newpost = new HashMap();
                    newpost.put("UserScore",0);
                    current_user_db.updateChildren(newpost);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        current_user_db_fname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fname = dataSnapshot.getValue(String.class);
                Fname.setText(fname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        current_user_db_lname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lname = dataSnapshot.getValue(String.class);
                Lname.setText(lname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(AccessToken.getCurrentAccessToken()!=null) {
            email = user.getDisplayName();
            navUsername.setText(email);
            //AccessToken userToken = AccessToken.getCurrentAccessToken();
            String userID = Profile.getCurrentProfile().getId();
            String url_pic = "http://graph.facebook.com/"+userID+"/picture?type=large";
//            Bitmap Bitmap = getFacebookProfilePicture(userID);
//            img_profile.setImageBitmap(Bitmap);
            Picasso.get().load(url_pic).into(img_profile);
        } else {
            email = user.getEmail();
            navUsername.setText(email);
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){

                    startActivity(new Intent(Chap7Activity.this, LoginActivity.class));
                }
            }
        };

        button_chap7_1 = (Button) findViewById(R.id.img_Button1);

        button_chap7_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter7_1 = new Intent(Chap7Activity.this,Chap7_1Activity.class);
                startActivity(Chapter7_1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap7_2 = (Button) findViewById(R.id.img_Button2);

        button_chap7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter7_2 = new Intent(Chap7Activity.this,Chap7_2Activity.class);
                startActivity(Chapter7_2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap7_3 = (Button) findViewById(R.id.img_Button3);
        button_chap7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter7_3 = new Intent(Chap7Activity.this,Chap7_3Activity.class);
                startActivity(Chapter7_3);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap7_4 = (Button) findViewById(R.id.img_Button4);

        button_chap7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter7_4 = new Intent(Chap7Activity.this,Chap7_4Activity.class);
                startActivity(Chapter7_4);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        button_chap7_5 = (Button) findViewById(R.id.img_Button5);

        button_chap7_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter7_5 = new Intent(Chap7Activity.this,Chap7_5Activity.class);
                startActivity(Chapter7_5);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap7_6 = (Button) findViewById(R.id.img_Button6);
        button_chap7_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter7_6 = new Intent(Chap7Activity.this,Chap7_6Activity.class);
                startActivity(Chapter7_6);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        button_chap7_7 = (Button) findViewById(R.id.img_Button7);

        button_chap7_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter7_7 = new Intent(Chap7Activity.this,Chap7_7Activity.class);
                startActivity(Chapter7_7);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_HOME) {
            Intent intent = new Intent(Chap7Activity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Handle the camera action
        } else if (id == R.id.nav_Editprofile) {
            startActivity(new Intent(Chap7Activity.this,EditProfileActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (id == R.id.nav_Achievement) {

            startActivity(new Intent(Chap7Activity.this,AchievementActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        } else if (id == R.id.nav_Setting) {

            Map newpost = new HashMap();
            newpost.put("UserScore",0);
            current_user_db.updateChildren(newpost);

        } else if (id == R.id.nav_help) {

            Map newpost = new HashMap();
            newpost.put("UserScore",3);
            current_user_db.updateChildren(newpost);

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout) {
            mAuth.signOut();
            LoginManager.getInstance().logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent Touchevent){
//        switch (Touchevent.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                x1 = Touchevent.getX();
//                y1 = Touchevent.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = Touchevent.getX();
//                y2 = Touchevent.getY();
//                if (x1 < x2) {
//                    Intent i = new Intent(Chap1Activity.this, MainActivity.class);
//                    startActivity(i);
//                }
//                break;
//        }
//        return false;
//    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
