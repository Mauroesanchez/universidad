package com.mycompany.universidad.modelo.PlanDeEstudio;

import com.mycompany.universidad.modelo.*;

public class PlanD implements PlanDeEstudio {
    private String nombrePlan;

    public PlanD(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    @Override
    public String getNombrePlan() {
        return nombrePlan;
    }

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {
        // Verificar que aprobó la cursada de todas las correlativas
        for (Materia correlativa : materia.getCorrelativas()) {
            if (!alumno.aproboCursada(correlativa)) {
                return false;
            }
        }

        // Verificar que aprobó los finales de todas las materias de los últimos 3 cuatrimestres
        int cuatrimestreMateria = materia.getCuatrimestre();
        for (Materia materiaFinalizada : alumno.getFinalizadas()) {
            if (materiaFinalizada.getCuatrimestre() >= cuatrimestreMateria - 3 
                    && materiaFinalizada.getCuatrimestre() < cuatrimestreMateria) {
                if (!alumno.getFinalizadas().contains(materiaFinalizada)) {
                    return false;
                }
            }
        }
        
        return true; // Si pasó ambas condiciones, puede cursar
    }

    @Override
    public boolean puedeRendir(Alumno alumno, Materia materia) {
        return alumno.getRegularizadas().contains(materia);
    }
    
    @Override
    public String toString() {
        return "Plan D - Aprobó las cursadas de las correlativas y los finales de todas las materias de 3 cuatrimestres previos.";
    }

}


