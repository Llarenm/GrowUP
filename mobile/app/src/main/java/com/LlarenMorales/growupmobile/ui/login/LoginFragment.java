package com.LlarenMorales.growupmobile.ui.login;

import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.LlarenMorales.growupmobile.R;
import com.LlarenMorales.growupmobile.util.SesionUsuario;
import com.LlarenMorales.growupmobile.viewmodel.CuentaViewModel;

public class LoginFragment extends Fragment {

    private CuentaViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        EditText etEmail = root.findViewById(R.id.etEmail);
        EditText etContrasena = root.findViewById(R.id.etContrasena);
        Button btnLogin = root.findViewById(R.id.btnIniciarSesion);
        TextView tvError = root.findViewById(R.id.tvError);

        viewModel = new ViewModelProvider(this).get(CuentaViewModel.class);

        viewModel.getCuentaActual().observe(getViewLifecycleOwner(), cuenta -> {
            if (cuenta != null) {
                SesionUsuario.getInstance().iniciarSesion(cuenta); // ← agregar esta línea
                Navigation.findNavController(root).navigate(R.id.action_login_to_menu);
            }
        });
        viewModel.getError().observe(getViewLifecycleOwner(), tvError::setText);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String contrasena = etContrasena.getText().toString().trim();
            if (email.isEmpty() || contrasena.isEmpty()) {
                tvError.setText("Completa email y contraseña");
                return;
            }
            viewModel.login(email, contrasena);
        });

        return root;
    }
}