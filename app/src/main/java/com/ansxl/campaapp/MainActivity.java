package com.ansxl.campaapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<Estudiante> estudiantes;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        estudiantes = new ArrayList<>();


        recyclerView = findViewById(R.id.estudiantes_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        cargarEstudiantes();


    }

    private void cargarEstudiantes() {
        EstudianteService estudianteService = ServiceGenerator.createService(EstudianteService.class);
        Call<List<Estudiante>> estudiantesrecibidos = estudianteService.getEstudiantes();
        if(estudiantesrecibidos!=null){
            estudiantesrecibidos.enqueue(new Callback<List<Estudiante>>() {
                @Override
                public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {
                    if(response.code()!=200){
                        Toast.makeText(getApplicationContext(), "404", Toast.LENGTH_LONG).show();
                        Log.e("this", response.code()+"");
                    }else{
                        if(response.body()!=null){
                            estudiantes= (ArrayList<Estudiante>) response.body();
                            EstudianteAdapter estudianteAdapter = new EstudianteAdapter(getApplicationContext(), estudiantes);
                            recyclerView.setAdapter(estudianteAdapter);
                        }else{
                            estudiantes = new ArrayList<>();
                            Log.e("this", "empty");
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Estudiante>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "404", Toast.LENGTH_LONG).show();
                    Log.e("this", "404");
                }
            });
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
