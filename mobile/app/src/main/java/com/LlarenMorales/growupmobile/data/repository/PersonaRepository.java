package com.LlarenMorales.growupmobile.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.LlarenMorales.growupmobile.data.model.Persona;
import com.LlarenMorales.growupmobile.data.remote.ApiService;
import com.LlarenMorales.growupmobile.data.remote.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonaRepository {

    private final ApiService apiService;
    private final MutableLiveData<List<Persona>> personas = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public PersonaRepository() {
        apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    public LiveData<List<Persona>> getPersonas() { return personas; }
    public LiveData<String> getError() { return error; }

    public void cargarPersonas() {
        apiService.listarPersonas().enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                if (response.isSuccessful()) {
                    personas.setValue(response.body());
                } else {
                    error.setValue("Error " + response.code() + " al listar personas");
                }
            }
            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                error.setValue("Fallo de red: " + t.getMessage());
            }
        });
    }

    public void crearPersona(Persona persona, RepositoryCallback<Persona> callback) {
        apiService.crearPersona(persona).enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (response.isSuccessful()) callback.onSuccess(response.body());
                else callback.onError("Error " + response.code() + " al crear persona");
            }
            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                callback.onError("Fallo de red: " + t.getMessage());
            }
        });
    }

    public void actualizarPersona(Persona persona, RepositoryCallback<Persona> callback) {
        apiService.actualizarPersona(persona).enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (response.isSuccessful()) callback.onSuccess(response.body());
                else callback.onError("Error " + response.code() + " al actualizar");
            }
            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                callback.onError("Fallo de red: " + t.getMessage());
            }
        });
    }

    public void eliminarPersona(Integer id, RepositoryCallback<Void> callback) {
        apiService.eliminarPersona(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) callback.onSuccess(null);
                else callback.onError("Error " + response.code() + " al eliminar");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Fallo de red: " + t.getMessage());
            }
        });
    }

    public void obtenerPersonaPorId(Integer id, RepositoryCallback<Persona> callback) {
        apiService.obtenerPersonaPorId(id).enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (response.isSuccessful()) callback.onSuccess(response.body());
                else callback.onError("Error " + response.code() + " al obtener persona");
            }
            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                callback.onError("Fallo de red: " + t.getMessage());
            }
        });
    }
}