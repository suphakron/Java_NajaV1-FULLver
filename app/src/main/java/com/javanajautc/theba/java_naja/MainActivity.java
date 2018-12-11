package com.example.theba.java_naja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.session.MediaSession;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.TokenData;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.security.Provider;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View headerView;
    NavigationView navigationView;
    FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef ,current_user_db_score, current_user_db_fname, current_user_db_lname, current_user_db, current_user_db_pic;
    private String user_id, Score;
    private FirebaseUser user;
    private String email, imgurl;
    private TextView navUsername, score, Fname, Lname;
    private ImageView icon_cert;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private int mScore,count = 0;
    private double score_percent;
    private CircleImageView img_profile;
    private TextView tv;
    private UserInfo Uinfo;
    private StorageReference mStorageRef,filepath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("หน้าหลัก");
        toolbar.setBackgroundColor(Color.parseColor("#8cc63e"));

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        user_id = user.getUid();
        mStorageRef = FirebaseStorage.getInstance().getReference();


        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user_db_fname = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("FName");
        current_user_db_lname = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("LName");
        current_user_db_score = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("UserScore");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        final TextView navUsername = (TextView) headerView.findViewById(R.id.textEmail);
        //email = email.concat(" ").concat(user.getEmail());
        img_profile = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.imageProfile);
        Fname = (TextView) headerView.findViewById(R.id.textFName);
        Lname = (TextView) headerView.findViewById(R.id.textLName);
        icon_cert = headerView.findViewById(R.id.icon_cert);
        icon_cert.setVisibility(View.INVISIBLE);

        score = (TextView) navigationView.getHeaderView(0).findViewById(R.id.Score_show);
//        Userinfomation uInfo = new Userinfomation();
//        String Score = uInfo.getScore();
//        score.setText(Score);

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
                    ValueEventListener valueEventListener = current_user_db_score.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mScore = dataSnapshot.getValue(Integer.class);
                            Score = String.valueOf(mScore);
                            score.setText(Score);

                            score_percent = mScore * 100;
                            score_percent = score_percent / 45;
                            //Score = Double.toString(Math.floor(score_percent));
                            Score = Integer.toString((int)score_percent);

                            String Score_str = Score.replace(".","");


                            mScore = Integer.parseInt(Score_str);
                            //Toast.makeText(MainActivity.this,"" + mScore,Toast.LENGTH_SHORT).show(); Check integer percent
                            if(mScore>=100){
                                icon_cert.setVisibility(View.VISIBLE);
                            } else {
                                icon_cert.setVisibility(View.INVISIBLE);
                            }
                            mProgress.setProgress(mScore);
                            //tv.setText(String.format("%.2f %%", score_percent));
                            tv.setText(mScore + " %");
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

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // update pic profile GOOGLE **TOO very Important**
//                List<? extends UserInfo> infos = user.getProviderData();
//                for (UserInfo ui : infos) {
//                    if (ui.getProviderId().equals(GoogleAuthProvider.PROVIDER_ID)) {
//                        String email = user.getEmail();
//                        navUsername.setText(email);
//                        // GOOGLE PIC PROFILE
//                        String imgurl = mAuth.getCurrentUser().getPhotoUrl().toString();
//                        Picasso.get().load(imgurl).into(img_profile);
//                    } else {
        current_user_db_pic = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("image-src");
        current_user_db_pic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String path = dataSnapshot.getValue(String.class);

                if(path != null && img_profile != null) {
                    filepath = mStorageRef.child("Profile").child(path);
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).into(img_profile);
                        }
                    });
                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                finish();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        icon_cert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CertificateActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });

        //if login with facebook then get TOKEN and show Name
        if(AccessToken.getCurrentAccessToken()!=null) {
            String email = user.getDisplayName();
            navUsername.setText(email);

            //AccessToken userToken = AccessToken.getCurrentAccessToken();
            String userID = Profile.getCurrentProfile().getId();
            String url_pic = "http://graph.facebook.com/"+userID+"/picture?type=large";
//            Bitmap Bitmap = getFacebookProfilePicture(userID);
//            img_profile.setImageBitmap(Bitmap);

            Picasso.get().load(url_pic).into(img_profile);

            //Drawable image_test = getResources().getDrawable(R.drawable.left_arrow);
            //img_profile.setImageDrawable(image_test);


            //Image image_pro = user.getPhotoUrl("https://graph.facebook.com/" + userID + "/picture?type=large");
        } else {
            String email = user.getEmail();
            navUsername.setText(email);
        }


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Button button_chap1 = (Button) findViewById(R.id.img_Button1);

        button_chap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter1 = new Intent(MainActivity.this,Chap1Activity.class);
                startActivity(Chapter1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Button button_chap2 = (Button) findViewById(R.id.img_Button2);

        button_chap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter2 = new Intent(MainActivity.this,Chap2Activity.class);
                startActivity(Chapter2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button button_chap3 = (Button) findViewById(R.id.img_Button3);

        button_chap3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter3 = new Intent(MainActivity.this,Chap3Activity.class);
                startActivity(Chapter3);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button button_chap4 = (Button) findViewById(R.id.img_Button4);

        button_chap4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter4 = new Intent(MainActivity.this,Chap4Activity.class);
                startActivity(Chapter4);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button button_chap5 = (Button) findViewById(R.id.img_Button5);

        button_chap5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter5 = new Intent(MainActivity.this,Chap5Activity.class);
                startActivity(Chapter5);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        Button button_chap6 = (Button) findViewById(R.id.img_Button6);

        button_chap6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter6 = new Intent(MainActivity.this,Chap6Activity.class);
                startActivity(Chapter6);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button button_chap7 = (Button) findViewById(R.id.img_Button7);

        button_chap7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter7 = new Intent(MainActivity.this,Chap7Activity.class);
                startActivity(Chapter7);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        Button button_chap8 = (Button) findViewById(R.id.img_Button8);

        button_chap8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter8 = new Intent(MainActivity.this,Chap8Activity.class);
                startActivity(Chapter8);
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

//    public static Bitmap getFacebookProfilePicture(String userID) throws SocketException, SocketTimeoutException, MalformedURLException, IOException, Exception
//    {
//        String imageURL;
//
//        Bitmap bitmap = null;
//        imageURL = "http://graph.facebook.com/"+userID+"/picture?type=large";
//        InputStream in = (InputStream) new URL(imageURL).getContent();
//        bitmap = BitmapFactory.decodeStream(in);
//
//        return bitmap;
//    }

    public static Bitmap getFacebookProfilePicture(String userID) throws IOException {
        URL imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
        Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

        return bitmap;
    }

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
            // Handle the camera action
        } else if (id == R.id.nav_Editprofile) {
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
            finish();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (id == R.id.nav_Achievement) {

            Intent intent = new Intent(MainActivity.this, AchievementActivity.class);
            finish();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        } else if (id == R.id.nav_Setting) {

            Map newpost = new HashMap();
            newpost.put("UserScore",0);
            current_user_db.updateChildren(newpost);
            Toast.makeText(MainActivity.this,"Score Reset : 0",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_help) {

            Map newpost = new HashMap();
            newpost.put("UserScore",45);
            current_user_db.updateChildren(newpost);
            Toast.makeText(MainActivity.this,"Score Update : 45",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout) {
            mAuth.signOut();
            LoginManager.getInstance().logOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
