package com.example.alvin.chainzmusic.views;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.alvin.chainzmusic.Constants;
import com.example.alvin.chainzmusic.R;
import com.example.alvin.chainzmusic.RequestHandler;
import com.example.alvin.chainzmusic.ShareprefManager;
import com.example.alvin.chainzmusic.views.LoginActivity;
import com.example.alvin.chainzmusic.views.profileActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eidtTextusername, editTextemail,eidtTextpassword;
    TextView registered,login;

    private Button signup;
    private ProgressDialog progressdialogue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        if (ShareprefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, profileActivity.class));
            return;
        }

        initialiseWidgets();


    }




    private void initialiseWidgets() {
        eidtTextusername =findViewById(R.id.username);
        eidtTextpassword=findViewById(R.id.password);
        editTextemail =findViewById(R.id.email);

        signup=findViewById(R.id.buttonSignup);
        signup.setOnClickListener(this);
        registered=findViewById(R.id.textViewRegistered);
        login=findViewById(R.id.textViewLogin);
        login.setOnClickListener(this);
        progressdialogue=new ProgressDialog(this);

    }



    private void registerUser() {
        final String username= eidtTextusername.getText().toString().trim();
        final String email= editTextemail.getText().toString().trim();
        final String password =eidtTextpassword.getText().toString().trim();
        progressdialogue.setMessage("regestering user...");
        progressdialogue.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressdialogue.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressdialogue.hide();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters=new HashMap<>();
                parameters.put("username",username);
                parameters.put("password",password);
                parameters.put("email",email);

                return parameters;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
    @Override
    public void onClick(View v) {
        if(v==signup){
            registerUser();


            }
       if (v==login){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
