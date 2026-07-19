package com.LlarenMorales.growupmobile.ui.persona;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.LlarenMorales.growupmobile.R;
import com.LlarenMorales.growupmobile.data.model.Persona;
import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {

    public interface OnPersonaActionListener {
        void onEditar(Persona persona);
        void onEliminar(Persona persona);
    }

    private List<Persona> personas = new ArrayList<>();
    private final OnPersonaActionListener listener;

    public PersonaAdapter(OnPersonaActionListener listener) {
        this.listener = listener;
    }

    public void setPersonas(List<Persona> nuevas) {
        this.personas = nuevas != null ? nuevas : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona p = personas.get(position);
        holder.tvNombreCompleto.setText(p.getNombres() + " " + p.getApellidos());
        holder.tvDocumento.setText(p.getTipoIdentificacion() + ": " + p.getNumeroIdentificacion());

        boolean esCoordinador = com.LlarenMorales.growupmobile.util.SesionUsuario.getInstance().esCoordinador();
        holder.btnEditar.setVisibility(esCoordinador ? View.VISIBLE : View.GONE);
        holder.btnEliminar.setVisibility(esCoordinador ? View.VISIBLE : View.GONE);

        holder.btnEditar.setOnClickListener(v -> listener.onEditar(p));
        holder.btnEliminar.setOnClickListener(v -> listener.onEliminar(p));
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreCompleto, tvDocumento;
        Button btnEditar, btnEliminar;

        PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreCompleto = itemView.findViewById(R.id.tvNombreCompleto);
            tvDocumento = itemView.findViewById(R.id.tvDocumento);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}