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

public class CarrerasGUI extends JFrame {
    private Facultad facultad;  
    private JTextField txtNombreCarrera;
    private JComboBox<String> comboAlumnos, comboCarreras, comboPlanDeEstudio; // Nuevo JComboBox para el plan
    private JButton btnAgregarCarrera, btnInscribirAlumno, btnCancelar;
    private DefaultComboBoxModel<String> modeloCarreras;

    public CarrerasGUI(Facultad facultad) { 
        this.facultad = facultad;  
        inicializarGUI(); 
        cargarDatos(); 
    }

    private void inicializarGUI() {
        setTitle("Gestión de Carreras");
        setSize(600, 550); // Ajuste de altura por el nuevo campo
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(50, 50, 100));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(crearLabel("Nombre de la Carrera:"), gbc);

        gbc.gridx = 1;
        txtNombreCarrera = new JTextField(20);
        panel.add(txtNombreCarrera, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(crearLabel("Plan de Estudio:"), gbc);

        gbc.gridx = 1;
        String[] planes = {"Plan A", "Plan B", "Plan C", "Plan D", "Plan E"}; 
        comboPlanDeEstudio = new JComboBox<>(planes);
        comboPlanDeEstudio.setPreferredSize(new Dimension(200, 25));
        panel.add(comboPlanDeEstudio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        btnAgregarCarrera = crearBoton("Agregar Carrera");
        panel.add(btnAgregarCarrera, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(crearLabel("Seleccionar Alumno:"), gbc);

        gbc.gridx = 1;
        comboAlumnos = new JComboBox<>();
        panel.add(comboAlumnos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(crearLabel("Seleccionar Carrera:"), gbc);

        gbc.gridx = 1;
        modeloCarreras = new DefaultComboBoxModel<>();
        comboCarreras = new JComboBox<>(modeloCarreras);
        panel.add(comboCarreras, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        btnInscribirAlumno = crearBoton("Inscribir Alumno");
        panel.add(btnInscribirAlumno, gbc);

        gbc.gridx = 1;
        btnCancelar = crearBoton("Cancelar");
        panel.add(btnCancelar, gbc);

        add(panel);

        btnAgregarCarrera.addActionListener(e -> agregarCarrera());
        btnInscribirAlumno.addActionListener(e -> inscribirAlumno());
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        return label;
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(100, 100, 200));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(200, 30));
        return boton;
    }

    private void agregarCarrera() {
        String nombreCarrera = txtNombreCarrera.getText().trim();
        if (nombreCarrera.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de la carrera no puede estar vacío.");
            return;
        }

        try {
            int cantidadOptativasNecesarias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de optativas necesarias:"));

            // Obtener el plan seleccionado
            String planSeleccionado = (String) comboPlanDeEstudio.getSelectedItem();
            PlanDeEstudio plan;
            switch (planSeleccionado) {
                case "Plan A": plan = new PlanA("Plan A"); break;
                case "Plan B": plan = new PlanB("Plan B"); break;
                case "Plan C": plan = new PlanC("Plan C"); break;
                case "Plan D": plan = new PlanD("Plan D"); break;
                case "Plan E": plan = new PlanE("Plan E"); break;
                default: throw new IllegalStateException("Plan de estudio no válido");
            }

            Carrera nuevaCarrera = new Carrera(nombreCarrera, cantidadOptativasNecesarias, plan);
            facultad.agregarCarrera(nuevaCarrera);

            modeloCarreras.addElement(nombreCarrera);
            txtNombreCarrera.setText("");
            JOptionPane.showMessageDialog(null, 
                "Carrera agregada correctamente:\n" +
                "Nombre: " + nombreCarrera + "\n" +
                "Optativas necesarias: " + cantidadOptativasNecesarias + "\n" +
                "Plan de Estudio: " + planSeleccionado
            );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos válidos.");
        }
    }

    private void inscribirAlumno() {
        String alumnoSeleccionado = (String) comboAlumnos.getSelectedItem();
        String carreraSeleccionada = (String) comboCarreras.getSelectedItem();

        if (alumnoSeleccionado != null && carreraSeleccionada != null) {
            int dni = Integer.parseInt(alumnoSeleccionado.split(" - DNI: ")[1]); 
            boolean exito = facultad.inscribirAlumnoEnCarrera(dni, carreraSeleccionada);

            if (exito) {
                JOptionPane.showMessageDialog(null, "Alumno inscrito en " + carreraSeleccionada + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo inscribir al alumno.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno y una carrera.");
        }
    }

    private void cargarDatos() {
        comboAlumnos.removeAllItems();
        comboCarreras.removeAllItems();

        for (Alumno alumno : facultad.getEstudiantes()) {
            comboAlumnos.addItem(alumno.getNombre() + " " + alumno.getApellido() + " - DNI: " + alumno.getDni());
        }

        for (Carrera carrera : facultad.getCarreras()) {
            comboCarreras.addItem(carrera.getNombre());
        }
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
