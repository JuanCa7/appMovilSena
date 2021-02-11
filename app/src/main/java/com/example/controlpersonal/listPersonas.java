package com.example.controlpersonal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.controlpersonal.ClasesBd.Persona;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class listPersonas extends AppCompatActivity {

    private RecyclerView recyclerPersona;

    private static String urlPersona = "http://192.168.56.1/personalTurnos/usuarios/listpersonas.php";

    List<Persona> personaList;

    RequestQueue requestQueueTurnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_personas);

        //Inicializamos el recicler view
        recyclerPersona = findViewById(R.id.reciclerViewPersona);
        recyclerPersona.setHasFixedSize(true);
        recyclerPersona.setLayoutManager(new LinearLayoutManager(this));
        personaList = new ArrayList<>();

        //Inicializamos el request quee
        requestQueueTurnos = Volley.newRequestQueue(this);

        //Metodo para llenar la lista de personas
        mostrar();
    }

    private void mostrar() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlPersona, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject productos = array.getJSONObject(i);
                        personaList.add(new Persona(
                                productos.getInt("id_Persona"),
                                productos.getString("nombre"),
                                productos.getString("apellido"),
                                productos.getInt("celular"),
                                productos.getString("numIdentificacion"),
                                productos.getString("usuario"),
                                productos.getString("estado")
                        ));
                    }
                    Adapter adapter = new Adapter(listPersonas.this, personaList);
                    recyclerPersona.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueueTurnos.add(stringRequest);
    }

}