package com.example.asterisk.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login,signup;
    EditText name,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        name = findViewById(R.id.name);
        pass = findViewById(R.id.password);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String password = pass.getText().toString();
                SharedPreferences sp = getSharedPreferences("myfile",Context.MODE_PRIVATE);
                String myname = sp.getString("user1",null);
                String mypass = sp.getString("pass1",null);
                if(myname.equals(username) && mypass.equals(password)){
                    Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_LONG).show();
                    SharedPreferences sp1 = getSharedPreferences("yourfile",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp1.edit();
                    editor.commit();
                    Intent i = new Intent(MainActivity.this,DashboardActivity.class);
                    startActivity(i);
                    }
                    else {
                    Toast.makeText(MainActivity.this,"Login Failure",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("yourfile",Context.MODE_PRIVATE);
        boolean state = sp.getBoolean("state",false);
        if (state) {
            Intent i = new Intent(MainActivity.this,DashboardActivity.class);
            startActivity(i);
        }
    }
}
