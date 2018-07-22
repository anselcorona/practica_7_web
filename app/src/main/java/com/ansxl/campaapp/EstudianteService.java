package com.ansxl.campaapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EstudianteService {

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "Content-Type: application/json"
    })
    @GET("estudiantes/")
    Call<List<Estudiante>> getEstudiantes();
    @GET("estudiantes/{matricula}")
    Call<Estudiante> getEstudianteByMatricula(@Path("matricula") String matricula);
    @POST("estudiantes/")
    Call<Estudiante> insertEstudiante(@Body Estudiante estudiante);


}
