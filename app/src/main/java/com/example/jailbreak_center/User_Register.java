package com.example.jailbreak_center;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class User_Register extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;
    private EditText email, username, password;
    private ImageView user_picture;
    private Button btn_register, btn_user_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btn_register = (Button) findViewById(R.id.register);
        btn_user_picture = (Button) findViewById(R.id.btn_userphoto);
        user_picture = (ImageView) findViewById(R.id.user_picture);

        btn_user_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityIfNeeded(Intent.createChooser(intent,"Pick an Image"),GALLERY_REQUEST_CODE);
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email, txt_username, txt_password;
                txt_email = email.getText().toString().trim();
                txt_password = password.getText().toString().trim();
                txt_username = username.getText().toString().trim();
                if(!txt_email.isEmpty() && !txt_password.isEmpty() && !txt_username.isEmpty() && user_picture.getDrawable() != null){
                    Bitmap bitmap = ((BitmapDrawable) user_picture.getDrawable()).getBitmap();
                    bitmap = Bitmap.createScaledBitmap(bitmap,200,200,false);
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
                    byte[] img = byteArray.toByteArray();
                    Database db = new Database(getApplicationContext(), null, null, 1);
                    if(db.Check_user(txt_email,"email",db.getWritableDatabase())){
                        Toast.makeText(User_Register.this,"This email is already on the DB",Toast.LENGTH_SHORT).show();
                    }
                    else if(db.Check_user(txt_username,"username",db.getWritableDatabase())){
                        Toast.makeText(User_Register.this,"This username is already on the DB",Toast.LENGTH_SHORT).show();
                    }else {
                        String res = db.Register(txt_email, txt_password, txt_username, img);
                        Toast.makeText(User_Register.this, res, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                else{
                    Toast.makeText(User_Register.this,"Missing fields to be filled in",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri ImageData = data.getData();
            if(ImageData != null) {
                user_picture.setImageURI(ImageData);
            }
        }
    }
}