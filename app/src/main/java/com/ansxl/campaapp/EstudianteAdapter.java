package com.ansxl.campaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder>{

    private Context context;

    private ArrayList<Estudiante> estudiantes;

    public EstudianteAdapter(Context context, ArrayList<Estudiante> estudiantes){
        this.context = context;
        this.estudiantes = estudiantes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.estudiante_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Estudiante estudiante = estudiantes.get(position);
        String nombre = estudiante.getNombre();
        String matricula = String.valueOf(estudiante.getMatricula());
        String carrera = estudiante.getCarrera()==null? "":estudiante.getCarrera();
        String correo = estudiante.getCorreo()==null? "":estudiante.getCorreo();
        holder.nombre.setText(nombre);
        holder.matricula.setText(matricula);
        holder.carrera.setText(carrera);
        holder.correo.setText(correo);

    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre;
        public TextView matricula;
        public TextView correo;
        public TextView carrera;

        ViewHolder(View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            matricula = itemView.findViewById(R.id.matricula);
            correo = itemView.findViewById(R.id.correo);
            carrera = itemView.findViewById(R.id.carrera);
        }

    }
}
