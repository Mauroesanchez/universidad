package com.mycompany.universidad.modelo.PlanDeEstudio;


import com.mycompany.universidad.modelo.*;

public interface PlanDeEstudio {
    String getNombrePlan(); // Métodos en interfaces son públicos y abstractos por defecto

    boolean puedeCursar(Alumno alumno, Materia materia);
    boolean puedeRendir(Alumno alumno, Materia materia);
}
