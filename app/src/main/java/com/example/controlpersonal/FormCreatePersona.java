package com.example.controlpersonal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.controlpersonal.ClasesBd.Ficha;
import com.example.controlpersonal.ClasesBd.Rol;
import com.example.controlpersonal.ClasesBd.tipoIdent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class FormCreatePersona extends AppCompatActivity implements View.OnClickListener {

    //Spinners
    Spinner rolesSpinner;
    Spinner identSpinner;
    Spinner fichaSpinner,spinnerGenero;

    int tipoIdent;
    int rolId;
    int fichaId;
    String genero;

    List<Ficha> fichas = new ArrayList<>();
    List<com.example.controlpersonal.ClasesBd.tipoIdent> identificaciones = new ArrayList<>();
    List<Rol> roles = new ArrayList<>();

    ArrayAdapter<Rol> rolesAdapter;
    ArrayAdapter<tipoIdent> identAdapter;
    ArrayAdapter<Ficha> fichaAdapter;

    //Input para registrar
    EditText txtNombre, txtApellido,txtNumIdent,txtTelefono,txtCorreo,txtPassword;

    //Botones para la escucha de los eventos
    Button btnCrearP;

    String urlCrearP = "http://192.168.56.1/personalTurnos/usuarios/save.php";

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_create_persona);
        iniciarVariables();
        requestQueue = Volley.newRequestQueue(this);
        rolesSpinner = findViewById(R.id.rolesSpinner);
        identSpinner = findViewById(R.id.identSpinner);
        fichaSpinner = findViewById(R.id.fichaSpinner);
        spinnerGenero = findViewById(R.id.spinnerGenero);

        requestQueue = Volley.newRequestQueue(this);

        String urlRoles = "http://192.168.56.1/personalTurnos/roles/list.php";
        String urlTipoIdenti = "http://192.168.56.1/personalTurnos/tipoIdenti/list.php";
        String urlFicha = "http://192.168.56.1/personalTurnos/ficha/list.php";

        //Metodo para llenar el spinner de generos

        ArrayAdapter<CharSequence> adapterRoles = ArrayAdapter.createFromResource(this,R.array.generosResourses, android.R.layout.simple_spinner_item);
        spinnerGenero.setAdapter(adapterRoles);

        //Metodo para traer las fichas disponibles

        JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.POST, urlFicha, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Ficha fichaSele = new Ficha(1,"Seleccion una ficha...");
                fichas.add(fichaSele);
                try {
                    JSONArray jsonArray = response.getJSONArray("ficha");
                    //String prueba = response.getJSONArray("ficha").getJSONObject(0).toString();
                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.optInt("id_Ficha");
                        String codigo = jsonObject.optString("codigo");
                        fichas.add(new Ficha(id,codigo));
                        fichaAdapter = new ArrayAdapter<>(FormCreatePersona.this, android.R.layout.simple_dropdown_item_1line,fichas);
                        fichaSpinner.setAdapter(fichaAdapter);
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
        requestQueue.add(jsonObjectRequest3);

        //Metodo para traer los tipos de identificación
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST, urlTipoIdenti, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                tipoIdent identiSelec = new tipoIdent(1,"Seleccione tipo Identificacion...");
                identificaciones.add(identiSelec);
                try {
                    JSONArray jsonArray = response.getJSONArray("tipoIden");

                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.optInt("id");
                        String tipoIdent = jsonObject.optString("tipoIdenti");
                        identificaciones.add(new tipoIdent(id,tipoIdent));
                        identAdapter = new ArrayAdapter<>(FormCreatePersona.this, android.R.layout.simple_dropdown_item_1line,identificaciones);
                        identSpinner.setAdapter(identAdapter);
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
        requestQueue.add(jsonObjectRequest2);

        //Metodo para traer los roles
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlRoles, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Rol rolsele = new Rol(1,"Seleccione un rol...");
                roles.add(rolsele);
                try {
                    JSONArray jsonArray = response.getJSONArray("roles");
                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String nombre = jsonObject.optString("nombre");
                        int id = jsonObject.optInt("id");
                        roles.add(new Rol(id,nombre));
                        rolesAdapter = new ArrayAdapter<>(FormCreatePersona.this,android.R.layout.simple_dropdown_item_1line,roles);
                        rolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        rolesSpinner.setAdapter(rolesAdapter);
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
        requestQueue.add(jsonObjectRequest);
        //Finaliza la renderizacion de los SPINNERS

        //Llenando los valores cuando se selecciona los espiners
        rolesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rolId = roles.get(i).getRol();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        identSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoIdent = identificaciones.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        fichaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fichaId = fichas.get(i).getId_Ficha();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genero = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Evento clicl del boton crear persona
        btnCrearP.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString().trim();
                String apellido = txtApellido.getText().toString().trim();
                String numIdent = txtNumIdent.getText().toString().trim();
                String telefono = txtTelefono.getText().toString().trim();
                String correo = txtCorreo.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                crearPersona(tipoIdent,rolId,fichaId,nombre,apellido,numIdent,telefono,genero,correo,password);
            }
        });
    }

    private void crearPersona(final int tipoIdent, final int rolId, final int fichaId, final String nombre, final String apellido, final String numIdent, final String telefono, final String genero, final String correo, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlCrearP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(FormCreatePersona.this,"Usuario Creado Correctamente!!",Toast.LENGTH_LONG).show();
                vaciarCampos();
                Intent mostrarMenu = new Intent(getApplicationContext(),Menu.class);
                startActivity(mostrarMenu);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FormCreatePersona.this,"Ocurrio un error!!!",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("tipoIdent", String.valueOf(tipoIdent));
                params.put("rolId", String.valueOf(rolId));
                params.put("fichaId", String.valueOf(fichaId));
                params.put("nombre",nombre);
                params.put("apellido",apellido);
                params.put("numIdent",numIdent);
                params.put("telefono",telefono);
                params.put("genero",genero);
                params.put("correo",correo);
                params.put("password",password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void iniciarVariables(){
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtAprellido);
        txtNumIdent = findViewById(R.id.txtNumIdent);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPasswordLogin);
        //Btn
        btnCrearP = findViewById(R.id.btnActionCrearPersona);
    }

    private void vaciarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtNumIdent.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
    }

    @Override
    public void onClick(View view) {

    }

}