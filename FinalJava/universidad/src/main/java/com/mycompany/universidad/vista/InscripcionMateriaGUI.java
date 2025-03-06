/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.universidad.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import com.mycompany.universidad.modelo.*;
import com.mycompany.universidad.modelo.PlanDeEstudio.*;

public class InscripcionMateriaGUI extends JFrame {
    private Facultad facultad;
    private JComboBox<String> comboAlumnos, comboCarreras, comboMaterias;
    private JButton btnInscribir, btnCancelar;

    public InscripcionMateriaGUI(Facultad facultad) {
        this.facultad = facultad;
        inicializarGUI();
    }

    private void inicializarGUI() {
        setTitle("Inscripción de Alumno en Materia");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color fondoColor = new Color(50, 50, 100);
        Color botonColor = new Color(100, 100, 200);
        Color textoColor = Color.WHITE;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(fondoColor);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Selección de Alumno
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(crearLabel("Seleccionar Alumno:"), gbc);

        gbc.gridx = 1;
        comboAlumnos = new JComboBox<>(facultad.getEstudiantes().stream()
            .map(a -> a.getNombre() + " " + a.getApellido() + " - DNI: " + a.getDni())
            .toArray(String[]::new));
        comboAlumnos.setPreferredSize(new Dimension(250, 30)); // Aumenta el ancho del combobox
        panel.add(comboAlumnos, gbc);
        comboAlumnos.addActionListener(e -> actualizarCarreras());

        // Selección de Carrera
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(crearLabel("Seleccionar Carrera:"), gbc);

        gbc.gridx = 1;
        comboCarreras = new JComboBox<>();
        comboCarreras.setPreferredSize(new Dimension(250, 30)); // Aumenta el ancho del combobox
        panel.add(comboCarreras, gbc);
        comboCarreras.addActionListener(e -> actualizarMaterias());

        // Selección de Materia
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(crearLabel("Seleccionar Materia:"), gbc);

        gbc.gridx = 1;
        comboMaterias = new JComboBox<>();
        comboMaterias.setPreferredSize(new Dimension(250, 30)); // Aumenta el ancho del combobox
        panel.add(comboMaterias, gbc);



        // Botón de Inscribir
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnInscribir = crearBoton("Inscribir Alumno");
        panel.add(btnInscribir, gbc);
        btnInscribir.addActionListener(e -> inscribirAlumno());

        // Botón de Cancelar
        gbc.gridy = 4;
        btnCancelar = crearBoton("Cancelar");
        panel.add(btnCancelar, gbc);
        btnCancelar.addActionListener(e -> dispose());

        add(panel);
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

    private void actualizarCarreras() {
        comboCarreras.removeAllItems();
        String alumnoSeleccionado = (String) comboAlumnos.getSelectedItem();
        if (alumnoSeleccionado == null) return;

        int dni = Integer.parseInt(alumnoSeleccionado.split(" - DNI: ")[1]);
        Alumno alumno = facultad.getEstudiantes().stream()
            .filter(a -> a.getDni() == dni)
            .findFirst()
            .orElse(null);

        if (alumno != null) {
            alumno.getCarreras().forEach(c -> comboCarreras.addItem(c.getNombre()));
        }
    }

    private void actualizarMaterias() {
        comboMaterias.removeAllItems(); // ✅ Borra los elementos anteriores

        String carreraSeleccionada = (String) comboCarreras.getSelectedItem();
        if (carreraSeleccionada == null || carreraSeleccionada.isEmpty()) {
            return;
        }

        Carrera carrera = facultad.getCarreras().stream()
            .filter(c -> c.getNombre().equals(carreraSeleccionada))
            .findFirst()
            .orElse(null);

        if (carrera == null) {
            return;
        }

        // ✅ Obtener todas las materias de la carrera
        boolean tieneMaterias = false;

        for (Materia materia : carrera.getMateriasObligatorias()) {
            comboMaterias.addItem(materia.getNombre());
            tieneMaterias = true;
        }

        for (Materia materia : carrera.getMateriasOptativas()) {
            comboMaterias.addItem(materia.getNombre());
            tieneMaterias = true;
        }

        // ✅ Si no hay materias, mostrar mensaje
        if (!tieneMaterias) {
            comboMaterias.addItem("No hay materias disponibles");
        }

        // ✅ 🔥 REFRESCAR EL `JComboBox`
        comboMaterias.repaint();
        comboMaterias.revalidate();
    }





    private void inscribirAlumno() {
        String alumnoSeleccionado = (String) comboAlumnos.getSelectedItem();
        String carreraSeleccionada = (String) comboCarreras.getSelectedItem();
        String materiaSeleccionada = (String) comboMaterias.getSelectedItem();

        if (alumnoSeleccionado == null || carreraSeleccionada == null || materiaSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno, una carrera y una materia.");
            return;
        }

        int dni = Integer.parseInt(alumnoSeleccionado.split(" - DNI: ")[1]);
        Alumno alumno = facultad.getEstudiantes().stream()
            .filter(a -> a.getDni() == dni)
            .findFirst()
            .orElse(null);

        Carrera carrera = facultad.getCarreras().stream()
            .filter(c -> c.getNombre().equals(carreraSeleccionada))
            .findFirst()
            .orElse(null);

        Materia materia = carrera.getMateriasObligatorias().stream()
            .filter(m -> m.getNombre().equals(materiaSeleccionada))
            .findFirst()
            .orElse(null);

        if (materia == null) {
            materia = carrera.getMateriasOptativas().stream()
                .filter(m -> m.getNombre().equals(materiaSeleccionada))
                .findFirst()
                .orElse(null);
        }

        if (alumno != null && materia != null) {
            // ✅ Obtener el Plan de Estudio desde la Carrera
            PlanDeEstudio plan = carrera.getPlanDeEstudio();

            if (plan.puedeCursar(alumno, materia)) {
                alumno.inscribirseAMateria(materia);
                JOptionPane.showMessageDialog(null, 
                    "Inscripción exitosa:\n" +
                    "Alumno: " + alumno.getNombre() + " " + alumno.getApellido() + "\n" +
                    "DNI: " + dni + "\n" +
                    "Carrera: " + carreraSeleccionada + "\n" +
                    "Materia: " + materiaSeleccionada
                );
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No se puede inscribir a la materia.\n" +
                    "No cumple con los requisitos del plan de estudio.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
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
