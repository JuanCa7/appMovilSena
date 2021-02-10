package com.example.controlpersonal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.controlpersonal.ClasesBd.Cargo;
import com.example.controlpersonal.ClasesBd.Jornada;
import com.example.controlpersonal.ClasesBd.Persona;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class CrearTurno extends AppCompatActivity {
    //Spinners
    Spinner personaSpinner;
    Spinner jornadaSpinner;
    Spinner cargoSpinner;

    //Lista de personas
    List<Persona> listPersonas = new ArrayList<>();
    List<Jornada> listJornada = new ArrayList<>();
    List<Cargo> listCargo = new ArrayList<>();

    //Adapter
    ArrayAdapter<Persona> personasAdapter;
    ArrayAdapter<Jornada> jornadasAdapter;
    ArrayAdapter<Cargo> cargoAdapter;

    //Boton para crear un turno en la base de datos
    Button btnAsignarTurno, btnfecha;

    //Fecha para asignar un turno
    String fecha;

    //txt Descripcion
    EditText txtxDescripcion;
    RequestQueue requestQueueTurnos;

    //Rutas para consumir la api
    String rutaPersona = "http://192.168.56.1/personalTurnos/usuarios/list.php";
    String rutaJornadas = "http://192.168.56.1/personalTurnos/jornada/list.php";
    String rutaCargos = "http://192.168.56.1/personalTurnos/cargo/list.php";
    String rutaAsignarTurnos = "http://192.168.56.1/personalTurnos/turnos/save.php";

    //ids persona,jornada,cargo
    int personaSelect,jornadaSelect,cargoSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_turno);

        fecha="2021-08-21";
        //Inicializar Spinners
        personaSpinner = findViewById(R.id.personaSpinner);
        jornadaSpinner = findViewById(R.id.jornadaSpinner);
        cargoSpinner = findViewById(R.id.cargoSpinner);

        //Inicializamos el request quee
        requestQueueTurnos = Volley.newRequestQueue(this);

        //Inicializar boton crear turno
        btnAsignarTurno = findViewById(R.id.btnCrearTurno);
        btnfecha = findViewById(R.id.btnFechaTurno);

        txtxDescripcion = findViewById(R.id.txtDescripcionTurno);

        //Metodo para llenar Spinner de cargos
        JsonObjectRequest llenarSpinnerCargos = new JsonObjectRequest(Request.Method.POST, rutaCargos, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Cargo car = new Cargo(1,"Seleccione un cargo...");
                listCargo.add(car);
                try {
                    JSONArray jsonArray = response.getJSONArray("cargos");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idCargo = jsonObject.optInt("idCargo");
                        String nombre = jsonObject.optString("nombre");
                        listCargo.add(new Cargo(idCargo,nombre));
                        cargoAdapter = new ArrayAdapter<>(CrearTurno.this, android.R.layout.simple_dropdown_item_1line,listCargo);
                        cargoSpinner.setAdapter(cargoAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueueTurnos.add(llenarSpinnerCargos);

        //Metodo para llenar Spinner de jornadas
        JsonObjectRequest llenarSpinnerJornadas = new JsonObjectRequest(Request.Method.POST, rutaJornadas, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Jornada jor = new Jornada(1,"Seleccione una jornada...");
                listJornada.add(jor);
                try {
                    JSONArray jsonArray = response.getJSONArray("jornadas");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idJornada = jsonObject.optInt("idJornada");
                        String nombre = jsonObject.optString("nombre");
                        listJornada.add(new Jornada(idJornada,nombre));
                        jornadasAdapter = new ArrayAdapter<>(CrearTurno.this, android.R.layout.simple_dropdown_item_1line,listJornada);
                        jornadaSpinner.setAdapter(jornadasAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueueTurnos.add(llenarSpinnerJornadas);

        //Metodo para llenar Spinner de personas aprendices
        JsonObjectRequest llenarSpinnerPersonas = new JsonObjectRequest(Request.Method.POST, rutaPersona, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Persona pe = new Persona(1, "Seleccione un","aprendiz...");
                listPersonas.add(pe);
                try {
                    JSONArray jsonArray = response.getJSONArray("personas");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id_Persona = jsonObject.optInt("id_Persona");
                        String nombre = jsonObject.optString("nombre");
                        String apellido = jsonObject.optString("apellido");
                        Integer celular = jsonObject.optInt("celular");
                        String numIdentificacion = jsonObject.optString("numIdentificacion");
                        String usuario = jsonObject.optString("usuario");
                        listPersonas.add(new Persona(id_Persona, nombre,apellido,celular,numIdentificacion,usuario));
                        personasAdapter = new ArrayAdapter<>(CrearTurno.this, android.R.layout.simple_dropdown_item_1line, listPersonas);
                        personaSpinner.setAdapter(personasAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueueTurnos.add(llenarSpinnerPersonas);

        //llenar ids Seleccionados
        personaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                personaSelect = listPersonas.get(i).getId_Persona();
                //Toast.makeText(CrearTurno.this,"id Persona Selected: "+personaSelect,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        jornadaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jornadaSelect = listJornada.get(i).getIdJornada();
                //Toast.makeText(CrearTurno.this,"id jornada Selected: "+jornadaSelect,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        cargoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cargoSelect = listCargo.get(i).getIdCargo();
                //Toast.makeText(CrearTurno.this,"id cargo Selected: "+cargoSelect,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Evento click para asignar un turno
        btnAsignarTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descrip = txtxDescripcion.getText().toString();
                asignarTurno(descrip);
            }
        });

        btnfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                Toast.makeText(CrearTurno.this,"Seleccionar fecha activa",Toast.LENGTH_SHORT).show();
                DatePickerDialog dpd = new DatePickerDialog(CrearTurno.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        fecha = year+"-"+monthOfYear+"-"+dayOfMonth;
                        Toast.makeText(CrearTurno.this,"Fecha Seleccionada: "+fecha,Toast.LENGTH_SHORT).show();
                    }
                },dia,mes,year);
                dpd.show();
            }
        });
    }

    private void asignarTurno(final String descrip) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, rutaAsignarTurnos, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(CrearTurno.this,"Turno asignado correctamente!!!",Toast.LENGTH_LONG).show();
                Intent mostrarMenu = new Intent(getApplicationContext(),Menu.class);
                startActivity(mostrarMenu);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CrearTurno.this,"Ocurrio un error!!!",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("persona_fk",String.valueOf(personaSelect));
                params.put("jornada_fk",String.valueOf(jornadaSelect));
                params.put("cargo_fk",String.valueOf(cargoSelect));
                params.put("fecha",fecha);
                params.put("descripcion",descrip);
                return params;
            }
        };
        requestQueueTurnos.add(stringRequest);
    }

}