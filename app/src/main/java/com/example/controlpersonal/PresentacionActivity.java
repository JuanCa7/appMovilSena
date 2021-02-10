package com.example.controlpersonal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class PresentacionActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);
        progressBar = findViewById(R.id.progressBarInicio);
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("usuarioSesion",Context.MODE_PRIVATE);
                boolean sesion = preferences.getBoolean("sesion",false);
                if (sesion){
                    Intent mostrarMenu = new Intent(getApplicationContext(),Menu.class);
                    startActivity(mostrarMenu);
                    finish();
                }else{
                    Intent mostrarLogin = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(mostrarLogin);
                }
            }
        },2000);
    }
}