package com.mycompany.universidad.modelo.PlanDeEstudio;

import com.mycompany.universidad.modelo.*;

public class PlanB implements PlanDeEstudio {
    private String nombrePlan;

    public PlanB(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    @Override
    public String getNombrePlan() {
        return nombrePlan;
    }

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {
        for (Materia correlativa : materia.getCorrelativas()) {
            if (!alumno.aproboFinal(correlativa)) {
                return false; // Si no aprobó el final de alguna correlativa, no puede cursar.
            }
        }
        return true; // Si aprobó todos los finales, puede cursar.
    }

    @Override
    public boolean puedeRendir(Alumno alumno, Materia materia) {
        return alumno.getRegularizadas().contains(materia);
    }
    
    @Override
    public String toString() {
        return "Plan B - Aprobó los finales de las correlativas.";
    }

}

