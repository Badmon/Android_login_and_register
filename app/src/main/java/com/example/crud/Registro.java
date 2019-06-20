package com.example.crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText enombre,eusuario,epassword,eage;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        enombre = findViewById(R.id.EditT_nombre);
        eusuario = findViewById(R.id.EditT_usu);
        epassword = findViewById(R.id.EditT_password);
        eage = findViewById(R.id.EdiT_age);

        btn_registrar = findViewById(R.id.btn_registrar);
        btn_registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        final String name=enombre.getText().toString();
        final String username=eusuario.getText().toString();
        final String password=epassword.getText().toString();
        final int age=Integer.parseInt(eage.getText().toString());


        Response.Listener<String> respoListener =   new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                            Intent intent = new Intent(Registro.this,Login.class);
                            Registro.this.startActivity(intent);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setMessage("Error registro")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(name,username,age,password,respoListener);
            RequestQueue queue = Volley.newRequestQueue(Registro.this);
            queue.add(registerRequest);

    }
}
