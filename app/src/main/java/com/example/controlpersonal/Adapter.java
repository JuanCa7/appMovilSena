package com.example.controlpersonal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.controlpersonal.ClasesBd.Persona;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewholder> {

    private Context miCtext;
    private List<Persona> personaList;

    public Adapter(Context miCtext,List<Persona> personaList){
        this.miCtext = miCtext;
        this.personaList = personaList;
    }
    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(miCtext);
        View view = inflater.inflate(R.layout.card_persona,null);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        Persona persona = personaList.get(position);
        holder.nombre.setText(persona.getNombre()+" "+persona.getApellido());
        holder.celular.setText(String.valueOf(persona.getCelular()));
        holder.estado.setText(persona.getEstado());
    }

    @Override
    public int getItemCount() {
        return personaList.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        TextView nombre,celular,estado;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.cardNombre);
            celular = itemView.findViewById(R.id.cardCelular);
            estado = itemView.findViewById(R.id.cardEstado);

        }
    }
}
