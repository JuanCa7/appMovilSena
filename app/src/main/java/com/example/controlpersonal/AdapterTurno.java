package com.example.controlpersonal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.controlpersonal.ClasesBd.Turno;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterTurno extends RecyclerView.Adapter<AdapterTurno.HolderTurnos> {

    private Context miCtext;
    private List<Turno> turnoList;

    public AdapterTurno(Context miCtext,List<Turno> turnoList){
        this.miCtext = miCtext;
        this.turnoList = turnoList;
    }

    @NonNull
    @Override
    public HolderTurnos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(miCtext);
        View view = inflater.inflate(R.layout.card_turno,null);
        return new HolderTurnos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTurnos holder, int position) {
        Turno turno = turnoList.get(position);
        holder.txtPersona.setText(turno.getNombreP()+" "+turno.getApellidoP());
        holder.txtJornada.setText(turno.getNombreJ());
        holder.txtCargo.setText(turno.getNombreC());
        holder.txtFecha.setText(String.valueOf(turno.getFecha()));
        holder.txtEstado.setText("Estado: "+turno.getEstado());
    }

    @Override
    public int getItemCount() {
        return turnoList.size();
    }

    class HolderTurnos extends RecyclerView.ViewHolder{

        TextView txtPersona,txtFecha,txtJornada,txtCargo,txtEstado;
        public HolderTurnos(@NonNull View itemView) {
            super(itemView);
            txtPersona = itemView.findViewById(R.id.cardPersona);
            txtFecha = itemView.findViewById(R.id.cardFecha);
            txtJornada = itemView.findViewById(R.id.cardJornada);
            txtCargo = itemView.findViewById(R.id.cardCargo);
            txtEstado = itemView.findViewById(R.id.cardEstado);
        }
    }
}
