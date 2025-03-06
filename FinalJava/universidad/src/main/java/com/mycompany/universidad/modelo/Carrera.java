package com.mycompany.universidad.modelo;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.universidad.modelo.PlanDeEstudio.PlanDeEstudio;



public class Carrera {
    private String nombre;
    private List<Materia> materiasObligatorias = new ArrayList<>();
    private List<Materia> materiasOptativas = new ArrayList<>();
    private int cantidadOptativasNecesarias;
    private PlanDeEstudio planDeEstudio;

    public Carrera(String nombre, int cantidadOptativasNecesarias, PlanDeEstudio planDeEstudio) {
        this.nombre = nombre;
        this.cantidadOptativasNecesarias = cantidadOptativasNecesarias;
        this.planDeEstudio = planDeEstudio;
    }


    public String getNombre() {
        return nombre;
    }

    public List<Materia> getMateriasObligatorias() {
        return materiasObligatorias;
    }

    public List<Materia> getMateriasOptativas() {
        return materiasOptativas;
    }

    public int getCantidadOptativasNecesarias() {
        return cantidadOptativasNecesarias;
    }

    public PlanDeEstudio getPlanDeEstudio() {
        return planDeEstudio;
    }

    public void agregarMateriaObligatoria(Materia materia) {
        if (!materiasObligatorias.contains(materia)) {
            materiasObligatorias.add(materia);
        }
    }

    public void agregarMateriaOptativa(Materia materia) {
        if (!materiasOptativas.contains(materia)) {
            materiasOptativas.add(materia);
        }
    }

    public boolean esObligatoria(Materia materia) {
        return materiasObligatorias.contains(materia);
    }

    public boolean esOptativa(Materia materia) {
        return materiasOptativas.contains(materia);
    }

   

    public boolean puedeInscribirseEnMateria(Alumno alumno, Materia materia) {
        if (!materiasObligatorias.contains(materia) && !materiasOptativas.contains(materia)) {
            return false;
        }
        return planDeEstudio.puedeCursar(alumno, materia);
    }
}

