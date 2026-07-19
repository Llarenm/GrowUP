package com.LlarenMorales.growupmobile.data.repository;

import com.LlarenMorales.growupmobile.data.model.Persona;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.LlarenMorales.growupmobile.data.remote.ApiService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class PersonaRepositoryTest {

    private MockWebServer server;
    private ApiService apiService;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void listarPersonas_respuestaExitosa_devuelveLista() throws Exception {
        String json = "[{\"id\":1,\"nombres\":\"Juan\",\"apellidos\":\"Perez\"," +
                "\"tipoIdentificacion\":\"CC\",\"numeroIdentificacion\":\"123456\"," +
                "\"fechaNacimiento\":\"2000-01-01\"}]";
        server.enqueue(new MockResponse().setBody(json).setResponseCode(200));

        CountDownLatch latch = new CountDownLatch(1);
        final List<Persona>[] resultado = new List[1];

        apiService.listarPersonas().enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                resultado[0] = response.body();
                latch.countDown();
            }
            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();
        assertNotNull(resultado[0]);
        assertEquals(1, resultado[0].size());
        assertEquals("Juan", resultado[0].get(0).getNombres());
    }

    @Test
    public void listarPersonas_error500_devuelveRespuestaNoExitosa() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(500));

        CountDownLatch latch = new CountDownLatch(1);
        final boolean[] fueExitosa = {true};

        apiService.listarPersonas().enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                fueExitosa[0] = response.isSuccessful();
                latch.countDown();
            }
            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                latch.countDown();
            }
        });

        latch.await();
        assertFalse(fueExitosa[0]);
    }
}