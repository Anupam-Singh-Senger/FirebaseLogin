package com.example.firebaseloginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    ProgressBar p1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        p1=(ProgressBar)findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Second.class);
                startActivity(i);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1.setVisibility(ProgressBar.VISIBLE);
                String s1=e1.getText().toString().trim();
                String s2=e2.getText().toString().trim();
                e2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                if(s1.isEmpty())
                {
                    p1.setVisibility(ProgressBar.INVISIBLE);
                    e1.setError("enter your email");
                    return;
                }
                else
                {
                    if(s2.isEmpty())
                    {
                        p1.setVisibility(ProgressBar.INVISIBLE);
                        e2.setError("enter your password");
                        return;
                    }
                }
                firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "login done", Toast.LENGTH_SHORT).show();
                            p1.setVisibility(ProgressBar.INVISIBLE);
                            Intent j= new Intent(MainActivity.this,Third.class);
                            startActivity(j);
                            finish();
                        }
                        else
                        {
                            p1.setVisibility(ProgressBar.INVISIBLE);
                            Toast.makeText(MainActivity.this, "login not done", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}