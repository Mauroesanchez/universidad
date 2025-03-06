package com.mycompany.universidad.modelo;

import java.util.ArrayList;
import java.util.List;

public class Materia {
    private String nombre;
    private boolean esObligatoria;
    private boolean tienePromocion;
    private List<Materia> correlativas;
    private int cuatrimestre;

    public Materia(String nombre, boolean esObligatoria, boolean tienePromocion, int cuatrimestre) {
        this.nombre = nombre;
        this.esObligatoria = esObligatoria;
        this.tienePromocion = tienePromocion;
        this.correlativas = new ArrayList<>();
        this.cuatrimestre = cuatrimestre;  
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean esObligatoria() {
        return esObligatoria;
    }

    public void setEsObligatoria(boolean esObligatoria) {
        this.esObligatoria = esObligatoria;
    }

    public boolean tienePromocion() {
        return tienePromocion;
    }

    public void setTienePromocion(boolean tienePromocion) {
        this.tienePromocion = tienePromocion;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public List<Materia> getCorrelativas() {
        return correlativas;
    }

    // Métodos para manejar correlatividades
    public void setCorrelativas(List<Materia> correlativas) {
        this.correlativas = correlativas;
    }


    public void eliminarCorrelativa(Materia materia) {
        correlativas.remove(materia);
    }

    public boolean esCorrelativaDe(Materia otraMateria) {
        return correlativas.contains(otraMateria);
    }

    @Override
    public String toString() {
        return nombre + " (Obligatoria: " + esObligatoria + ", Promoción: " + tienePromocion + ", Cuatrimestre: " + cuatrimestre + ")";
    }
}

