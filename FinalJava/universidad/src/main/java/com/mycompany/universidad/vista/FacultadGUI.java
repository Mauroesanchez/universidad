/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.universidad.vista;

/**
 *
 * @author Mauro
 */

import javax.swing.*;
import java.awt.*;
import com.mycompany.universidad.modelo.*;
import com.mycompany.universidad.modelo.PlanDeEstudio.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

    public class FacultadGUI extends JFrame {
        private Facultad facultad; 

        public FacultadGUI() {
        
        this.facultad = new Facultad("Universidad UNTDF");

        
        cargarDatosIniciales();

        setTitle("Gestión de Facultad");
        setSize(600, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panel de opciones
        JPanel panelOpciones = new JPanel(new GridLayout(7, 1, 10, 10));
        panelOpciones.setBackground(new Color(50, 50, 100));
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botones del panel
        JButton btnPlanEstudio = crearBoton("Plan de Estudio");
        JButton btnAlumnos = crearBoton("Alumnos");
        JButton btnMaterias = crearBoton("Materias");
        JButton btnGraduado = crearBoton("Graduado");
        JButton btnCarreras = crearBoton("Carreras");
        JButton btnInscripcionMateria = crearBoton("Inscribir Alumno en Materia");
        JButton btnSalir = crearBoton("Salir");

        // Agregar botones al panel
        panelOpciones.add(btnPlanEstudio);
        panelOpciones.add(btnAlumnos);
        panelOpciones.add(btnMaterias);
        panelOpciones.add(btnGraduado);
        panelOpciones.add(btnCarreras);
        panelOpciones.add(btnInscripcionMateria);
        panelOpciones.add(btnSalir);

        // Panel de bienvenida
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setBackground(new Color(50, 50, 100));
        JLabel lblBienvenida = new JLabel("BIENVENIDO - UNTDF");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        lblBienvenida.setForeground(Color.WHITE);
        panelBienvenida.add(lblBienvenida);

        // Agregar componentes al frame
        add(panelBienvenida, BorderLayout.NORTH);
        add(panelOpciones, BorderLayout.CENTER);

        // Eventos de los botones
        btnPlanEstudio.addActionListener(e -> new PlanDeEstudioGUI());
        btnAlumnos.addActionListener(e -> new AlumnosGUI(facultad));
        btnMaterias.addActionListener(e -> new MateriasGUI(facultad));
        btnGraduado.addActionListener(e -> new GraduadoGUI(facultad));
        btnCarreras.addActionListener(e -> new CarrerasGUI(facultad));
        btnInscripcionMateria.addActionListener(e -> new InscripcionMateriaGUI(facultad));
        btnSalir.addActionListener(e -> System.exit(0));

        
        revalidate();
        repaint();

        setVisible(true);
    }


    
    private void cargarDatosIniciales() {
        System.out.println("⚡ Cargando datos iniciales...");

        // Crear carrera con **0 optativas necesarias**
        Carrera sistemas = new Carrera("Ingeniería en Sistemas", 0, new PlanA("Plan A"));

        // Crear materias obligatorias
        Materia info = new Materia("Elementos de Informática", true, false, 1);
        Materia prog = new Materia("Expresión de Problemas y Algoritmos", true, false, 1);
        Materia algebra = new Materia("Álgebra", true, false, 1);
        Materia algo1 = new Materia("Algorítmica y Programación I", true, false, 2);
        Materia logica = new Materia("Elementos de Lógica y Matemática Discreta", true, false, 2);
        Materia analisis = new Materia("Análisis Matemático", true, false, 2);
        Materia sistOrg = new Materia("Sistemas y Organizaciones", true, false, 3);
        Materia arqComp = new Materia("Arquitectura de Computadoras", true, false, 3);
        Materia algo2 = new Materia("Algorítmica y Programación II", true, false, 3);
        Materia estadistica = new Materia("Estadística", true, false, 3);
        Materia basesDatos = new Materia("Bases de Datos I", true, false, 4);
        Materia poo = new Materia("Programación y Diseño Orientado a Objetos", true, false, 4);
        Materia ingSoftware1 = new Materia("Ingeniería de Software I", true, false, 4);
        Materia labProg = new Materia("Laboratorio de Programación y Lenguajes", true, false, 5);
        Materia ingSoftware2 = new Materia("Ingeniería de Software II", true, false, 5);
        Materia concurrencia = new Materia("Introducción a la Concurrencia", true, false, 5);
        Materia sistOp = new Materia("Sistemas Operativos", true, false, 6);
        Materia labSoft = new Materia("Laboratorio de Software", true, false, 6);

        // Agregar correlativas
        algo1.setCorrelativas(List.of(prog));
        algo2.setCorrelativas(List.of(algo1, logica));
        arqComp.setCorrelativas(List.of(info));
        estadistica.setCorrelativas(List.of(algebra, analisis));
        basesDatos.setCorrelativas(List.of(algo2));
        poo.setCorrelativas(List.of(algo1));
        ingSoftware1.setCorrelativas(List.of(algo1));
        labProg.setCorrelativas(List.of(algo2));
        ingSoftware2.setCorrelativas(List.of(ingSoftware1, estadistica));
        concurrencia.setCorrelativas(List.of(arqComp, algo2));
        sistOp.setCorrelativas(List.of(concurrencia));
        labSoft.setCorrelativas(List.of(basesDatos, poo, ingSoftware1));

        // Agregar materias a la carrera
        sistemas.agregarMateriaObligatoria(info);
        sistemas.agregarMateriaObligatoria(prog);
        sistemas.agregarMateriaObligatoria(algebra);
        sistemas.agregarMateriaObligatoria(algo1);
        sistemas.agregarMateriaObligatoria(logica);
        sistemas.agregarMateriaObligatoria(analisis);
        sistemas.agregarMateriaObligatoria(sistOrg);
        sistemas.agregarMateriaObligatoria(arqComp);
        sistemas.agregarMateriaObligatoria(algo2);
        sistemas.agregarMateriaObligatoria(estadistica);
        sistemas.agregarMateriaObligatoria(basesDatos);
        sistemas.agregarMateriaObligatoria(poo);
        sistemas.agregarMateriaObligatoria(ingSoftware1);
        sistemas.agregarMateriaObligatoria(labProg);
        sistemas.agregarMateriaObligatoria(ingSoftware2);
        sistemas.agregarMateriaObligatoria(concurrencia);
        sistemas.agregarMateriaObligatoria(sistOp);
        sistemas.agregarMateriaObligatoria(labSoft);

        // Agregar carrera a la facultad
        facultad.agregarCarrera(sistemas);

        // Crear alumnos
        Alumno alumno1 = new Alumno(12345678, "Juan", "Pérez");
        Alumno alumno2 = new Alumno(87654321, "María", "Gómez");
        Alumno alumno3 = new Alumno(11223344, "Carlos", "López");
        Alumno alumno4 = new Alumno(55667788, "Ana", "Fernández");
        Alumno alumno5 = new Alumno(99887766, "Pedro", "Ramírez"); // Alumno que se debe graduar

        // Agregar alumnos a la facultad
        facultad.agregarEstudiante(alumno1);
        facultad.agregarEstudiante(alumno2);
        facultad.agregarEstudiante(alumno3);
        facultad.agregarEstudiante(alumno4);
        facultad.agregarEstudiante(alumno5);

        // Inscribir alumnos en la carrera
        facultad.inscribirAlumnoEnCarrera(12345678, "Ingeniería en Sistemas");
        facultad.inscribirAlumnoEnCarrera(87654321, "Ingeniería en Sistemas");
        facultad.inscribirAlumnoEnCarrera(11223344, "Ingeniería en Sistemas");
        facultad.inscribirAlumnoEnCarrera(55667788, "Ingeniería en Sistemas");
        facultad.inscribirAlumnoEnCarrera(99887766, "Ingeniería en Sistemas"); // Pedro

        for (Materia materia : sistemas.getMateriasObligatorias()) {
            alumno5.inscribirseAMateria(materia); 
            alumno5.aprobarParcial(materia);      
            alumno5.aprobarFinal(materia);        
        }

        System.out.println("✅ Datos iniciales cargados correctamente.");
    }




    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(100, 100, 200));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        return boton;
    }

    

    












    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
