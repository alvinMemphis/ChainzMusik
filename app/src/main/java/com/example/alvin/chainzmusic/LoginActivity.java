package com.example.alvin.chainzmusic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextusername, editTextpassword;
    TextView textLocationnot;
    private Button login;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeVariables();
        Intent intent =getIntent();
        if (intent.getStringExtra("message")!=""){
            String message=intent.getStringExtra("message");
            textLocationnot.setVisibility(View.VISIBLE);
        }

        if (ShareprefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,profileActivity.class));
            return;
        }


    }

    private void initializeVariables() {
        editTextusername=findViewById(R.id.editTextUsername);
        editTextpassword =findViewById(R.id.editTextPassword);
        textLocationnot=findViewById(R.id.textViewnot);
        textLocationnot.setVisibility(View.GONE);
        login=findViewById(R.id.buttonLogin);
        login.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
    }
    private void userLogin(){
        final String username=editTextpassword.getText().toString().trim();
        final String password=editTextpassword.getText().toString().trim();
        progressDialog.setMessage("loging in...");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                Constants.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            if(!jsonObject.getBoolean("error")){
                                ShareprefManager.getInstance(getApplicationContext()).userLogin(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("username"),
                                        jsonObject.getString("email")
                                );
                                startActivity(new Intent(getApplicationContext(),profileActivity.class));
                                finish();

                            }
                            else {
                               Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("username",username);
                map.put("password",password);
                return map;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onClick(View v) {
        if (v==login){
            userLogin();
        }
    }
}
