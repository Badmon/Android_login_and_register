package com.example.crud;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL="http://192.168.1.7:8080/Register.php";

    private Map<String,String> params;
    public RegisterRequest(String name, String username, int age, String password,Response.Listener<String> Listener){
        super(Method.POST, REGISTER_REQUEST_URL, Listener,null);
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

//android:usesCleartextTraffic="true" CUANDO TENEMOS PROBLEMA CON EL HTTP
//AGREGAR AL MANIFEST