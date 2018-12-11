package com.example.theba.java_naja;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Chap1_3Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference current_user_db_score;
    private FirebaseUser user;
    private String user_id;
    private int mScore;
    private Button Nextpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap1_3);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        user_id = user.getUid();
        current_user_db_score = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("UserScore");

        TextView CopBODY = (TextView) findViewById(R.id.Body1);
        CopBODY.setTextIsSelectable(true);

        TextView CopBODY2 = (TextView) findViewById(R.id.Body2);
        CopBODY2.setTextIsSelectable(true);

        TextView CopHead = (TextView) findViewById(R.id.Header);
        CopHead.setTextIsSelectable(true);

        Nextpage = (Button) findViewById(R.id.Next_Button);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        current_user_db_score.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mScore = dataSnapshot.getValue(Integer.class);

                if(mScore>=3){

                    final Drawable top = getResources().getDrawable(R.drawable.book);
                    Nextpage.setCompoundDrawables(null,top,null,null);

                    Nextpage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Chap1_3Activity.this, MainActivity.class);
                            finish();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                    });
                } else {
                    Nextpage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Chap1_3Activity.this,QuizChap1_3Activity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ImageView SavePIC1 = (ImageView) findViewById(R.id.Pic1);
        SavePIC1.setOnClickListener(new Chap1_3Activity.DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }

            @Override
            public void onDoubleClick(View v) {

                ActivityCompat.requestPermissions(Chap1_3Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.p1_3);

                File path = Environment.getExternalStorageDirectory();

                File dir = new File(path + "/JAVA NaJa/saved/");
                dir.mkdirs();

                File file = new File(dir, "รูปที่ 1.3 Java.jpg");

                OutputStream out = null;

                try {
                    out = new FileOutputStream(file);
                    image.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
//                        Drawable myDrawable = getResources().getDrawable(R.drawable.picchapter1_1);
//
//                        bitmap = ((BitmapDrawablele)myDrawable).getBitmap();
//
//                        ImagePath = MediaStore.Images.Media.insertImage(
//                                getContentResolver(),
//                                bitmap,
//                                "demo_image",
//                                "demo_image"
//                        );
//
//                        URI = Uri.parse(ImagePath);
//
                Toast.makeText(Chap1_3Activity.this, "Image Saved Successfully \n\n" + dir, Toast.LENGTH_LONG).show();
            }
        });
    }

    public abstract class DoubleClickListener implements View.OnClickListener {

        private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

        long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                onDoubleClick(v);
            } else {
                onSingleClick(v);
            }
            lastClickTime = clickTime;
        }

        public abstract void onSingleClick(View v);

        public abstract void onDoubleClick(View v);
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
