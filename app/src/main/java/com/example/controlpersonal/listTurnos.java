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
import com.example.controlpersonal.ClasesBd.Persona;
import com.example.controlpersonal.ClasesBd.Turno;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class listTurnos extends AppCompatActivity {

    private RecyclerView recyclerTurnos ;

    private static String urlTurnos = "http://192.168.56.1/personalTurnos/turnos/list.php";

    List<Turno> turnoList;

    RequestQueue requestQueueTurnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_turnos);
        //Inicializamos el recicler view
        recyclerTurnos = findViewById(R.id.reciclerViewTurnos);
        recyclerTurnos.setHasFixedSize(true);
        recyclerTurnos.setLayoutManager(new LinearLayoutManager(this));
        
        turnoList = new ArrayList<>();
        
        requestQueueTurnos = Volley.newRequestQueue(this);

        //Metodo para llenar la lista de turnos
        listarTurnos();
    }

    private void listarTurnos() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlTurnos, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject turnos = array.getJSONObject(i);
                        turnoList.add(new Turno(
                                turnos.getInt("idTurno"),
                                turnos.getInt("persona_fk"),
                                turnos.getString("nombreP"),
                                turnos.getString("apellidoP"),
                                turnos.getInt("jornada_fk"),
                                turnos.getString("nombreJ"),
                                turnos.getInt("cargo_fk"),
                                turnos.getString("nombreC"),
                                turnos.getString("fecha"),
                                turnos.getString("descripcion"),
                                turnos.getString("estado")
                        ));
                    }
                    AdapterTurno adapterTurno = new AdapterTurno(listTurnos.this,turnoList);
                    recyclerTurnos.setAdapter(adapterTurno);
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