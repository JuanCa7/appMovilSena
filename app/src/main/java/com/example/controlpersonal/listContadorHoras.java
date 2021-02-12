package com.example.controlpersonal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.controlpersonal.ClasesBd.ContadorHoras;
import com.example.controlpersonal.ClasesBd.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class listContadorHoras extends AppCompatActivity {

    private RecyclerView recyclerContadorHoras;

    private static String urlContadorHoras = "http://192.168.56.1/personalTurnos/contadorhoras/list.php";

    List<ContadorHoras> contadorHorasList;

    RequestQueue requestQueueContadorHoras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contador_horas);
        //Inicializamos el recicler view
        recyclerContadorHoras = findViewById(R.id.reciclerViewContadorHoras);
        recyclerContadorHoras.setHasFixedSize(true);
        recyclerContadorHoras.setLayoutManager(new LinearLayoutManager(this));
        contadorHorasList = new ArrayList<>();
        requestQueueContadorHoras = Volley.newRequestQueue(this);

        //metodo para listar contador de horas
        mostrarListaHoras();
    }

    private void mostrarListaHoras() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlContadorHoras, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ch = array.getJSONObject(i);
                        contadorHorasList.add(new ContadorHoras(
                                ch.getInt("idContadorH"),
                                ch.getInt("Persona_id"),
                                ch.getInt("tiempoTrans"),
                                ch.getString("nombreP"),
                                ch.getString("apellidoP"),
                                ch.getString("programa"),
                                ch.getString("codigo")
                        ));
                    }
                    AdapterContadorHoras adapterContadorHoras = new AdapterContadorHoras(listContadorHoras.this,contadorHorasList);
                    recyclerContadorHoras.setAdapter(adapterContadorHoras);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueueContadorHoras.add(stringRequest);
    }
}