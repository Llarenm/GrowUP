package com.LlarenMorales.growupmobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.LlarenMorales.growupmobile.data.model.Cuenta;
import com.LlarenMorales.growupmobile.data.model.LoginDTO;
import com.LlarenMorales.growupmobile.data.model.RegistroDTO;
import com.LlarenMorales.growupmobile.data.repository.CuentaRepository;
import com.LlarenMorales.growupmobile.data.repository.RepositoryCallback;

public class CuentaViewModel extends ViewModel {

    private final CuentaRepository repository = new CuentaRepository();
    private final MutableLiveData<Cuenta> cuentaActual = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public LiveData<Cuenta> getCuentaActual() { return cuentaActual; }
    public LiveData<String> getError() { return error; }

    public void login(String email, String contrasena) {
        repository.login(new LoginDTO(email, contrasena), new RepositoryCallback<Cuenta>() {
            @Override public void onSuccess(Cuenta result) { cuentaActual.setValue(result); }
            @Override public void onError(String message) { error.setValue(message); }
        });
    }

    public void registrar(RegistroDTO dto) {
        repository.registrarUsuario(dto, new RepositoryCallback<Cuenta>() {
            @Override public void onSuccess(Cuenta result) { cuentaActual.setValue(result); }
            @Override public void onError(String message) { error.setValue(message); }
        });
    }
}