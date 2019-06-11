package com.example.crud;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL="http://197.168.11.23:8080/LoginCRUD.php";

    private Map<String,String> params;
    public LoginRequest(String name, String username, int age, String password,Response.Listener<String> Listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, Listener,null);

        params=new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("age",age+"");
        params.put("password",password);

    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
