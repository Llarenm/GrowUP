package com.LlarenMorales.growupmobile.data.remote;

import java.util.List;
import com.LlarenMorales.growupmobile.data.model.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    // --- Cuenta: solo crear + login ---
    @POST("cuenta")
    Call<Cuenta> registrarCuenta(@Body Cuenta cuenta);

    @POST("cuenta/login")
    Call<Cuenta> login(@Body LoginDTO loginDTO);

    // --- Registro: solo crear (persona + cuenta combinados) ---
    @POST("registro")
    Call<Cuenta> registrarUsuario(@Body RegistroDTO registroDTO);

    // --- Persona: CRUD completo ---
    @POST("personas")
    Call<Persona> crearPersona(@Body Persona persona);

    @GET("personas")
    Call<List<Persona>> listarPersonas();

    @GET("personas/{id}")
    Call<Persona> obtenerPersonaPorId(@Path("id") Integer id);

    @GET("personas/documento/{numeroIdentificacion}")
    Call<Persona> buscarPorDocumento(@Path("numeroIdentificacion") String numeroIdentificacion);

    @PUT("personas")
    Call<Persona> actualizarPersona(@Body Persona persona);

    @DELETE("personas/{id}")
    Call<Void> eliminarPersona(@Path("id") Integer id);
}