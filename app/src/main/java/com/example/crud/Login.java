package com.example.crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    TextView tv_registrar;
    EditText eusername,epassword;
    Button btn_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eusername = findViewById(R.id.TV_usu);
        epassword = findViewById(R.id.TV_pass);
        btn_log = findViewById(R.id.btn_ingresar);

        tv_registrar = findViewById(R.id.tv_registrar);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentReg = new Intent(Login.this,Registro.class);
                Login.this.startActivity(intentReg);
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (eusername.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Debe ingresar usernombre", Toast.LENGTH_SHORT).show();
                }else{
                    if(epassword.getText().toString().isEmpty())
                        Toast.makeText(Login.this, "Debe ingresar password", Toast.LENGTH_SHORT).show();
                }


                final String username = eusername.getText().toString();
                final String password = epassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String name = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");

                                Intent intent = new Intent(Login.this, Usuario.class);
                                intent.putExtra("name",name);
                                intent.putExtra("username",username);
                                intent.putExtra("age",age);
                                intent.putExtra("password",password);

                                Login.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("No existe el usuario")
                                        .setNegativeButton("Intentar de nuevo",null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);

            }
        });
    }
}
