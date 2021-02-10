package com.example.controlpersonal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText usuario, password;
    Button btnIniciarSesion;
    String usuarioS,passwordS;

    String urlValidar = "http://192.168.56.1/personalTurnos/validar_usuario.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.edtUsuario);
        password = findViewById(R.id.edtPassword);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
        recuperarPreferencias();

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuarioS = usuario.getText().toString();
                passwordS = password.getText().toString();
                if(!usuarioS.isEmpty() && !passwordS.isEmpty()){
                    validarUsuario();
                }else{
                    Toast.makeText(MainActivity.this, "NO SE PERMITEN CAMPOS VACIOS!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    private void validarUsuario(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlValidar, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    guardarPreferencias();
                    Intent mostrarMenu = new Intent(getApplicationContext(),Menu.class);
                    startActivity(mostrarMenu);
                    finish();
                }else {
                    Toast.makeText(MainActivity.this, "USUARIO Y CONTRASEÑA INCORRECTAS", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("usuario",usuarioS);
                parametros.put("password",passwordS);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("usuarioSesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario",usuarioS);
        editor.putString("password",passwordS);
        editor.putBoolean("sesion",true);
        editor.commit();
    }
    public void recuperarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("usuarioSesion", Context.MODE_PRIVATE);
        usuario.setText(preferences.getString("usuario","Camilo@gmail.com"));
        password.setText(preferences.getString("password","12233"));
    }
}