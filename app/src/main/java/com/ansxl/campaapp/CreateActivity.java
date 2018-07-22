package com.ansxl.campaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        final EditText nombre = findViewById(R.id.NombreN);
        final EditText carrera = findViewById(R.id.CarreraN);
        final EditText correo = findViewById(R.id.CorreoN);
        Button b = findViewById(R.id.Add);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Estudiante e = new Estudiante(nombre.getText().toString(), carrera.getText().toString(), correo.getText().toString());
                EstudianteService estudianteService = ServiceGenerator.createService(EstudianteService.class);
                Call<Estudiante> est = estudianteService.insertEstudiante(e);
                if(est!=null){
                    est.enqueue(new Callback<Estudiante>() {
                        @Override
                        public void onResponse(Call<Estudiante> call, Response<Estudiante> response) {
                            if(response.code()==404){Toast.makeText(getApplicationContext(), "404", Toast.LENGTH_LONG).show();}

                            else{
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Estudiante> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }
}
