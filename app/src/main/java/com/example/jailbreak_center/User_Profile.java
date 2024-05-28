package com.example.jailbreak_center;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class User_Profile extends AppCompatActivity {

    private TextView txt_username;
    private ImageView img_user;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_username = (TextView) findViewById(R.id.txt_username);
        img_user = (ImageView) findViewById(R.id.image_user);
        btn_logout = (Button) findViewById(R.id.btn_log_out);

        Connect connect = new Connect(User_Profile.this);
        User_class userData = connect.session_getData();

        txt_username.setText(userData.getUsername());
        img_user.setImageBitmap(userData.getImage());

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(User_Profile.this,"The Session has been Terminated",Toast.LENGTH_SHORT).show();
                connect.close_session();
                Tools_Navigation.update_screens(User_Profile.this,MenuSelection.class);
                finish();
            }
        });
    }
}