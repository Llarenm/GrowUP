package com.LlarenMorales.growupmobile.ui.persona;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import com.LlarenMorales.growupmobile.R;
import com.LlarenMorales.growupmobile.data.model.Persona;
import com.LlarenMorales.growupmobile.viewmodel.PersonaViewModel;
import java.util.Calendar;


public class PersonaFormFragment extends Fragment {

    private static final String[] TIPOS_DOC = {"CC", "CE", "TI", "PPT"};

    private PersonaViewModel viewModel;
    private EditText etNombres, etApellidos, etNumeroIdentificacion, etFechaNacimiento;
    private Spinner spTipoIdentificacion;
    private TextView tvErrorForm;
    private Integer personaId = null; // null = modo crear

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_persona_form, container, false);

        etNombres = root.findViewById(R.id.etNombres);
        etApellidos = root.findViewById(R.id.etApellidos);
        spTipoIdentificacion = root.findViewById(R.id.spTipoIdentificacion);
        etNumeroIdentificacion = root.findViewById(R.id.etNumeroIdentificacion);
        etFechaNacimiento = root.findViewById(R.id.etFechaNacimiento);
        tvErrorForm = root.findViewById(R.id.tvErrorForm);
        Button btnGuardar = root.findViewById(R.id.btnGuardar);

        ArrayAdapter<String> spAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, TIPOS_DOC);
        spTipoIdentificacion.setAdapter(spAdapter);

        etFechaNacimiento.setOnClickListener(v -> mostrarDatePicker());

        viewModel = new ViewModelProvider(this).get(PersonaViewModel.class);
        viewModel.getMensaje().observe(getViewLifecycleOwner(), msg -> {
            if (msg == null) return;
            tvErrorForm.setText(msg);
            if (msg.contains("correctamente")) {
                Navigation.findNavController(root).popBackStack();
            }
        });

        // Modo editar: leer argumento de navegación
        if (getArguments() != null) {
            int id = getArguments().getInt("personaId", -1);
            if (id != -1) {
                personaId = id;
                viewModel.getPersonaCargada().observe(getViewLifecycleOwner(), this::llenarFormulario);
                viewModel.cargarPersonaPorId(personaId);
            }
        }

        btnGuardar.setOnClickListener(v -> guardar());
        return root;
    }

    private void mostrarDatePicker() {
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(getContext(), (view, year, month, day) -> {
            String fecha = String.format("%04d-%02d-%02d", year, month + 1, day);
            etFechaNacimiento.setText(fecha);
        }, cal.get(Calendar.YEAR) - 18, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void llenarFormulario(Persona p) {
        if (p == null) return;
        etNombres.setText(p.getNombres());
        etApellidos.setText(p.getApellidos());
        etNumeroIdentificacion.setText(p.getNumeroIdentificacion());
        etFechaNacimiento.setText(p.getFechaNacimiento());
        int index = java.util.Arrays.asList(TIPOS_DOC).indexOf(p.getTipoIdentificacion());
        if (index >= 0) spTipoIdentificacion.setSelection(index);
    }

    private void guardar() {
        Persona p = new Persona();
        p.setId(personaId);
        p.setNombres(etNombres.getText().toString().trim());
        p.setApellidos(etApellidos.getText().toString().trim());
        p.setTipoIdentificacion((String) spTipoIdentificacion.getSelectedItem());
        p.setNumeroIdentificacion(etNumeroIdentificacion.getText().toString().trim());
        p.setFechaNacimiento(etFechaNacimiento.getText().toString().trim());
        viewModel.guardarPersona(p);
    }
}