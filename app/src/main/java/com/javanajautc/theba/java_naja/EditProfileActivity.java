package com.javanajautc.theba.java_naja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference current_user_db, current_user_db_fName, current_user_db_lName, current_user_db_pic;
    DatabaseReference myRef;

    private EditText Email, FName, LName, Password, ConfirmPassword;
    private String userEmail, userFname, userLname, userPwd, userConPwd, user_id, imgLocation;
    private Button Submit;
    private TextView editPicture;
    private StorageReference filepath;
    private Uri uri,resultUri;
    private final int PICK_IMAGE_REQUEST = 71;
    private CircleImageView imgProfile;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setTitle("แก้ไขข้อมูลส่วนตัว");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();

        user_id = user.getUid();
        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        Email = (EditText) findViewById(R.id.emailuser);
        FName = (EditText) findViewById(R.id.fName);
        LName = (EditText) findViewById(R.id.lName);
        //Password = (EditText) findViewById(R.id.pwduser);
        //ConfirmPassword = (EditText) findViewById(R.id.pwduser2);
        Submit = (Button) findViewById(R.id.accept);
        editPicture = findViewById(R.id.edit_picprofile);
        imgProfile = findViewById(R.id.imageProfile);

        Email.setText(user.getEmail());
        Email.setEnabled(false);

        current_user_db_pic = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("image-src");
        current_user_db_pic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ProgressDialog progressDialog = ProgressDialog.show(EditProfileActivity .this, "Please wait...", "Loading...", true, false);
                String path = dataSnapshot.getValue(String.class);

                if(path != null) {
                    filepath = mStorageRef.child("Profile").child(path);
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).into(imgProfile);
                            progressDialog.dismiss();
                        }
                    });
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        current_user_db_fName = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("FName");
        current_user_db_fName.addValueEventListener(new ValueEventListener() {
            final ProgressDialog progressDialog = ProgressDialog.show(EditProfileActivity .this, "Please wait...", "Loading...", true, true);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Fname = dataSnapshot.getValue(String.class);
                FName.setText(Fname);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        current_user_db_lName = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("LName");
        current_user_db_lName.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //final ProgressDialog progressDialog = ProgressDialog.show(EditProfileActivity .this, "Please wait...", "Loading...", true, true);
                String Lname = dataSnapshot.getValue(String.class);
                LName.setText(Lname);
                //progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        editPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseImage();

            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userEmail = Email.getText().toString();
                userFname = FName.getText().toString();
                userLname = LName.getText().toString();
                //userPwd = Password.getText().toString();
                //userConPwd = ConfirmPassword.getText().toString();

                //if(userPwd.equals(userConPwd)) {
                    //setting rule can update data
                    //***adding info user***

                    Map newPost = new HashMap();
                    newPost.put("Email", userEmail);
                    newPost.put("FName", userFname);
                    newPost.put("LName", userLname);
                    newPost.put("image-src", imgLocation);
                    //newPost.put("Password", userPwd);
                    current_user_db.updateChildren(newPost);

                filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                        if(taskSnapshot!=null){
                            Toast.makeText(EditProfileActivity.this,"Picture Added",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditProfileActivity.this,"Picture Can't Added",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                    Intent intent = new Intent(EditProfileActivity.this,LoginActivity.class);
                    Toast.makeText(EditProfileActivity.this,"ทำการบันทึกประวัติของคุณแล้ว",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                /*} else {
                    Toast.makeText(EditProfileActivity.this,"รหัสผ่านไม่ตรงกัน กรุณาตรวจสอบ",Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null) {
            Uri uri = data.getData();
            //imgProfile.setImageURI(uri);

            CropImage.activity(uri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if(resultCode == RESULT_OK){
                resultUri = result.getUri();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),resultUri);
                    imgProfile.setImageBitmap(bitmap);

                    filepath = mStorageRef.child("Profile").child(user_id + ".jpg");
                    imgLocation = user_id + ".jpg";

                    /*filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                            if(taskSnapshot!=null){
                                Toast.makeText(EditProfileActivity.this,"Picture Added",Toast.LENGTH_SHORT).show();
                                Uri uploadurl = taskSnapshot.getUploadSessionUri();
                                current_user_db.child("image").setValue(uploadurl)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){

                                                    startActivity(new Intent(EditProfileActivity.this, EditProfileActivity.class));

                                                    Toast.makeText(EditProfileActivity.this,"Image upload Done!",Toast.LENGTH_SHORT).show();
                                                } else {
                                                    String msg = task.getException().getMessage();
                                                    Toast.makeText(EditProfileActivity.this,"Error : " + msg,Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }

                        }
                    });*/

                } catch (IOException e){
                    e.printStackTrace();
                }



                /*filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> Task) {
                        if(Task.isSuccessful()){
                            Toast.makeText(EditProfileActivity.this,"Picture Added",Toast.LENGTH_SHORT).show();
                            Uri downloadurl =  Task.getResult().getUploadSessionUri();

                            current_user_db.child("image").setValue(downloadurl)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){

                                                startActivity(new Intent(EditProfileActivity.this, EditProfileActivity.class));

                                                Toast.makeText(EditProfileActivity.this,"Image upload Done!",Toast.LENGTH_SHORT).show();
                                            } else {
                                                String msg = task.getException().getMessage();
                                                Toast.makeText(EditProfileActivity.this,"Error : " + msg,Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                    }
                });*/
            } else {
                Toast.makeText(EditProfileActivity.this,"Error : Image can't cropped. try again",Toast.LENGTH_SHORT).show();
            }

        }

//            filepath = mStorageRef.child("Profile").child(uri.getLastPathSegment());
//            imgLocation = uri.getLastPathSegment();
//            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Uri downloadurl =  taskSnapshot.getUploadSessionUri();
//                    current_user_db.child("image").setValue(downloadurl);
//                }
//            });

//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                imgProfile.setImageBitmap(bitmap);
//
//                filepath = mStorageRef.child("Profile").child(uri.getLastPathSegment());
//                imgLocation = uri.getLastPathSegment();
//                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
//                            Uri uploadurl = taskSnapshot.getUploadSessionUri();
//                            current_user_db.child("image").setValue(uploadurl);
//                            //current_user_db.child("image").setValue(uploadurl);
//                        }
//                    });
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        }
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
