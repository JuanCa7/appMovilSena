package com.example.controlpersonal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btnCrearPersona,btnCrearTurno,btnCerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnCrearPersona = (Button) findViewById(R.id.crearPersona);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCrearTurno = findViewById(R.id.btnActionCrearTurno);

        btnCrearPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarFormulario = new Intent(getApplicationContext(),FormCreatePersona.class);
                startActivity(mostrarFormulario);
            }
        });

        btnCerrarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("usuarioSesion", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent mostrarLogin = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mostrarLogin);
                finish();
            }
        });
        btnCrearTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent asignarTurno = new Intent(getApplicationContext(), CrearTurno.class);
                startActivity(asignarTurno);
            }
        });
    }
}