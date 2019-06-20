package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Usuario extends AppCompatActivity {

    TextView textView_name,textView_username,textView_age,textView_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        textView_name = findViewById(R.id.TextV_nombre);
        textView_username = findViewById(R.id.TextV_usuario);
        textView_age = findViewById(R.id.TextV_edad);
        textView_password = findViewById(R.id.TextV_password);

        Intent intent = getIntent();
        String name=intent.getStringExtra("name");
        String username=intent.getStringExtra("username");
        int age=intent.getIntExtra("age",-1);
        String password=intent.getStringExtra("password");

        textView_name.setText(name);
        textView_username.setText(username);
        textView_age.setText(age+"");
        textView_password.setText(password);


    }

}
