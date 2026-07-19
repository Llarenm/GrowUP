package com.LlarenMorales.growupmobile.ui.menu;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.LlarenMorales.growupmobile.R;
import com.LlarenMorales.growupmobile.util.SesionUsuario;

public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        View cardGestionPersonas = root.findViewById(R.id.cardGestionPersonas);
        boolean esCoordinador = SesionUsuario.getInstance().esCoordinador();
        cardGestionPersonas.setVisibility(esCoordinador ? View.VISIBLE : View.GONE);

        root.findViewById(R.id.btnGestionPersonas).setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_menu_to_personaList)
        );
        return root;
    }
}