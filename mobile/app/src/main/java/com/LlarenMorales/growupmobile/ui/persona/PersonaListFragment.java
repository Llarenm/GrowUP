package com.LlarenMorales.growupmobile.ui.persona;

import android.os.Bundle;
import android.widget.Button;
import android.view.*;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.LlarenMorales.growupmobile.R;
import com.LlarenMorales.growupmobile.viewmodel.PersonaViewModel;

public class PersonaListFragment extends Fragment implements PersonaAdapter.OnPersonaActionListener {

    private PersonaViewModel viewModel;
    private PersonaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_persona_list, container, false);

        RecyclerView rv = root.findViewById(R.id.rvPersonas);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PersonaAdapter(this);
        rv.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PersonaViewModel.class);
        viewModel.getPersonas().observe(getViewLifecycleOwner(), adapter::setPersonas);
        viewModel.getError().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null) Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        });

        Button btnNuevaPersona = root.findViewById(R.id.btnNuevaPersona);
        boolean esCoordinador = com.LlarenMorales.growupmobile.util.SesionUsuario.getInstance().esCoordinador();
        btnNuevaPersona.setVisibility(esCoordinador ? View.VISIBLE : View.GONE);

        btnNuevaPersona.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_personaList_to_personaForm)
        );

        viewModel.cargarPersonas();
        return root;
    }

    @Override
    public void onEditar(com.LlarenMorales.growupmobile.data.model.Persona persona) {
        Bundle args = new Bundle();
        args.putInt("personaId", persona.getId());
        Navigation.findNavController(getView()).navigate(R.id.action_personaList_to_personaForm, args);
    }

    @Override
    public void onEliminar(com.LlarenMorales.growupmobile.data.model.Persona persona) {
        viewModel.eliminarPersona(persona.getId());
    }
}