package com.mycompany.universidad.modelo.PlanDeEstudio;

import com.mycompany.universidad.modelo.*;

public class PlanA implements PlanDeEstudio {
    private String nombrePlan;

    public PlanA(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    @Override
    public String getNombrePlan() {
        return nombrePlan;
    }

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {
        // En Plan A, el alumno puede cursar si aprobó la cursada de todas las correlativas
        for (Materia correlativa : materia.getCorrelativas()) {
            if (!alumno.aproboCursada(correlativa)) {
                return false; // Si no aprobó alguna correlativa, no puede cursar
            }
        }
        return true; // Si aprobó todas las cursadas de las correlativas, puede cursar
    }

    @Override
    public boolean puedeRendir(Alumno alumno, Materia materia) {
        return alumno.getRegularizadas().contains(materia);
    }
    
    @Override
    public String toString() {
        return "Plan A - Aprobó las cursadas de las correlativas.";
    }

}








