package com.mycompany.universidad.modelo;

import java.util.ArrayList;
import java.util.List;

public class Facultad {
    private String nombre;
    private List<Carrera> carreras = new ArrayList<>();
    private List<Alumno> estudiantes = new ArrayList<>();

    public Facultad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public List<Alumno> getEstudiantes() {
        return estudiantes;
    }

    public void agregarCarrera(Carrera carrera) {
        if (!carreras.contains(carrera)) {
            carreras.add(carrera);
        }
    }

    public void eliminarCarrera(Carrera carrera) {
        carreras.remove(carrera);
    }

    public void agregarEstudiante(Alumno estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    public void eliminarEstudiante(Alumno estudiante) {
        estudiantes.remove(estudiante);
    }

    public Alumno buscarEstudiantePorDNI(int dni) {
        return estudiantes.stream()
                .filter(e -> e.getDni() == dni)
                .findFirst()
                .orElse(null);
    }

    public Carrera buscarCarreraPorNombre(String nombreCarrera) {
        return carreras.stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombreCarrera))
                .findFirst()
                .orElse(null);
    }

    public boolean puedeInscribirse(Alumno alumno, Materia materia, Carrera carrera) {
        return carrera.puedeInscribirseEnMateria(alumno, materia);
    }

    public boolean inscribirAlumnoEnCarrera(int dni, String nombreCarrera) {
    Alumno alumno = buscarEstudiantePorDNI(dni);
    Carrera carrera = buscarCarreraPorNombre(nombreCarrera);

    if (alumno != null && carrera != null) {
        alumno.inscribirseACarrera(carrera);
        return true; // Inscripción exitosa
    }
    return false; // No se pudo inscribir (alumno o carrera no encontrados)
}


   
}
