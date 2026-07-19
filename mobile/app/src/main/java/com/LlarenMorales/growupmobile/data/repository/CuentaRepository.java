package com.LlarenMorales.growupmobile.data.repository;

import com.LlarenMorales.growupmobile.data.model.Cuenta;
import com.LlarenMorales.growupmobile.data.model.LoginDTO;
import com.LlarenMorales.growupmobile.data.model.RegistroDTO;
import com.LlarenMorales.growupmobile.data.remote.ApiService;
import com.LlarenMorales.growupmobile.data.remote.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuentaRepository {

    private final ApiService apiService;

    public CuentaRepository() {
        apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    public void login(LoginDTO loginDTO, RepositoryCallback<Cuenta> callback) {
        apiService.login(loginDTO).enqueue(new Callback<Cuenta>() {
            @Override
            public void onResponse(Call<Cuenta> call, Response<Cuenta> response) {
                if (response.isSuccessful()) callback.onSuccess(response.body());
                else callback.onError("Credenciales incorrectas");
            }
            @Override
            public void onFailure(Call<Cuenta> call, Throwable t) {
                callback.onError("Fallo de red: " + t.getMessage());
            }
        });
    }

    public void registrarUsuario(RegistroDTO dto, RepositoryCallback<Cuenta> callback) {
        apiService.registrarUsuario(dto).enqueue(new Callback<Cuenta>() {
            @Override
            public void onResponse(Call<Cuenta> call, Response<Cuenta> response) {
                if (response.isSuccessful()) callback.onSuccess(response.body());
                else callback.onError("Error " + response.code() + " al registrar");
            }
            @Override
            public void onFailure(Call<Cuenta> call, Throwable t) {
                callback.onError("Fallo de red: " + t.getMessage());
            }
        });
    }
}