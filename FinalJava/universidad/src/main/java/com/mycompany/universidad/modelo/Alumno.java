package com.mycompany.universidad.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alumno {
    private int dni;
    private String nombre;
    private String apellido;
    private List<Carrera> carreras = new ArrayList<>();
    private List<Materia> cursando = new ArrayList<>();
    private List<Materia> finalizadas = new ArrayList<>();
    private List<Materia> regularizadas = new ArrayList<>();
    private List<Materia> desaprobadas = new ArrayList<>();
    private Map<Carrera, Boolean> carrerasFinalizadas = new HashMap<>();

    public Alumno(int dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public List<Materia> getCursando() {
        return cursando;
    }
    
    public boolean aproboCursada(Materia materia) {
        return regularizadas.contains(materia);
    }


    public List<Materia> getFinalizadas() {
        return finalizadas;
    }

    public List<Materia> getRegularizadas() {
        return regularizadas;
    }

    public boolean aproboFinal(Materia materia) {
        return finalizadas.contains(materia);
    }
    
    
    public List<Materia> getDesaprobadas() {
        return desaprobadas;
    }

    public void inscribirseACarrera(Carrera carrera) {
        if (!carreras.contains(carrera)) {
            carreras.add(carrera);
        }
    }

    public void inscribirseAMateria(Materia materia) {
        if (!cursando.contains(materia)) {
            cursando.add(materia);
        }
    }

    public void aprobarParcial(Materia materia) {
        if (cursando.contains(materia) && !regularizadas.contains(materia)) {
            regularizadas.add(materia);
        }
    }

    public void aprobarFinal(Materia materia) {
        if (regularizadas.contains(materia) && !finalizadas.contains(materia)) {
            finalizadas.add(materia);
            cursando.remove(materia);
            regularizadas.remove(materia);
        }
    }

    public void desaprobarMateria(Materia materia) {
        if (cursando.contains(materia) && !desaprobadas.contains(materia)) {
            desaprobadas.add(materia);
        }
    }

    public boolean haRegularizado(Materia materia) {
        return regularizadas.contains(materia);
    }

    public boolean haAprobadoFinal(Materia materia) {
        return finalizadas.contains(materia);
    }

    public boolean aproboFinalesUltimosCuatrimestres(int cantidad) {
        int contador = 0;
        for (Materia materia : finalizadas) {
            if (materia.getCuatrimestre() >= (materia.getCuatrimestre() - cantidad)) {
                contador++;
            }
        }
        return contador >= cantidad;
    }

    
}

