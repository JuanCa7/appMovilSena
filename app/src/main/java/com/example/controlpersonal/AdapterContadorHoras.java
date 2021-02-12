package com.example.controlpersonal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.controlpersonal.ClasesBd.ContadorHoras;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterContadorHoras extends RecyclerView.Adapter<AdapterContadorHoras.HolderContadorHoras> {

    private Context miCtext;
    private List<ContadorHoras> contadorHorasList;

    public AdapterContadorHoras(Context miCtext,List<ContadorHoras> contadorHorasList){
        this.miCtext = miCtext;
        this.contadorHorasList = contadorHorasList;
    }

    @NonNull
    @Override
    public HolderContadorHoras onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(miCtext);
        View view = inflater.inflate(R.layout.cardcontadorhoras,null);
        return new HolderContadorHoras(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContadorHoras.HolderContadorHoras holder, int position) {
        ContadorHoras contadorHoras = contadorHorasList.get(position);
        holder.txtPersona.setText(contadorHoras.getNombreP()+" "+contadorHoras.getApellidoP());
        holder.txtPrograma.setText(contadorHoras.getPrograma());
        holder.txtCodigo.setText(contadorHoras.getCodigo());
        holder.txtHoras.setText(String.valueOf(contadorHoras.getTiempoTrans())+"Horas");
    }

    @Override
    public int getItemCount() {
        return contadorHorasList.size();
    }

    public class HolderContadorHoras extends RecyclerView.ViewHolder{
        TextView txtPersona,txtPrograma,txtCodigo,txtHoras;
        public HolderContadorHoras(@NonNull View itemView) {
            super(itemView);
            txtPersona = itemView.findViewById(R.id.cardPersonaHoras);
            txtPrograma = itemView.findViewById(R.id.cardPrograma);
            txtCodigo = itemView.findViewById(R.id.cardCodigo);
            txtHoras = itemView.findViewById(R.id.cardHoras);
        }
    }
}
