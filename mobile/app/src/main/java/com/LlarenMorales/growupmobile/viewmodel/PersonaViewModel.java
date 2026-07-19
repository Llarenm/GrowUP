package com.LlarenMorales.growupmobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.LlarenMorales.growupmobile.data.model.Persona;
import com.LlarenMorales.growupmobile.data.repository.PersonaRepository;
import com.LlarenMorales.growupmobile.data.repository.RepositoryCallback;

public class PersonaViewModel extends ViewModel {

    private final PersonaRepository repository = new PersonaRepository();
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();

    public LiveData<java.util.List<Persona>> getPersonas() {
        return repository.getPersonas();
    }
    public LiveData<String> getError() {
        return repository.getError();
    }
    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public void cargarPersonas() {
        repository.cargarPersonas();
    }

    public void guardarPersona(Persona persona) {
        if (!validar(persona)) return;

        if (persona.getId() == null) {
            repository.crearPersona(persona, new RepositoryCallback<Persona>() {
                @Override public void onSuccess(Persona result) {
                    mensaje.setValue("Persona creada correctamente");
                    cargarPersonas();
                }
                @Override public void onError(String message) {
                    mensaje.setValue(message);
                }
            });
        } else {
            repository.actualizarPersona(persona, new RepositoryCallback<Persona>() {
                @Override public void onSuccess(Persona result) {
                    mensaje.setValue("Persona actualizada correctamente");
                    cargarPersonas();
                }
                @Override public void onError(String message) {
                    mensaje.setValue(message);
                }
            });
        }
    }

    public void eliminarPersona(Integer id) {
        repository.eliminarPersona(id, new RepositoryCallback<Void>() {
            @Override public void onSuccess(Void result) {
                mensaje.setValue("Persona eliminada");
                cargarPersonas();
            }
            @Override public void onError(String message) {
                mensaje.setValue(message);
            }
        });
    }

    // Validaciones espejo de las @NotBlank/@Pattern del backend,
    // para dar feedback inmediato sin esperar la respuesta del servidor
    private boolean validar(Persona p) {
        if (p.getNombres() == null || !p.getNombres().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,50}$")) {
            mensaje.setValue("Nombre inválido: solo letras, 2-50 caracteres");
            return false;
        }
        if (p.getApellidos() == null || !p.getApellidos().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,50}$")) {
            mensaje.setValue("Apellido inválido: solo letras, 2-50 caracteres");
            return false;
        }
        if (p.getTipoIdentificacion() == null || !p.getTipoIdentificacion().matches("^(CC|CE|TI|PPT)$")) {
            mensaje.setValue("Tipo de documento debe ser CC, CE, TI o PPT");
            return false;
        }
        if (p.getNumeroIdentificacion() == null || !p.getNumeroIdentificacion().matches("^[a-zA-Z0-9]{8,15}$")) {
            mensaje.setValue("Número de documento inválido: 8-15 caracteres alfanuméricos");
            return false;
        }
        if (p.getFechaNacimiento() == null) {
            mensaje.setValue("Fecha de nacimiento obligatoria");
            return false;
        }
        return true;
    }

    private final MutableLiveData<Persona> personaCargada = new MutableLiveData<>();
    public LiveData<Persona> getPersonaCargada() { return personaCargada; }

    public void cargarPersonaPorId(Integer id) {
        repository.obtenerPersonaPorId(id, new RepositoryCallback<Persona>() {
            @Override public void onSuccess(Persona result) { personaCargada.setValue(result); }
            @Override public void onError(String message) { mensaje.setValue(message); }
        });
    }
}