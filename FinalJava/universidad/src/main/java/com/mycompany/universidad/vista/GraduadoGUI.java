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
import java.util.List;
import com.mycompany.universidad.modelo.*;

public class GraduadoGUI extends JFrame {
    private Facultad facultad;
    private JComboBox<String> comboAlumnos, comboCarreras;
    private JLabel lblResultado;
    private JButton btnVerificar, btnCancelar;

    public GraduadoGUI(Facultad facultad) {
        this.facultad = facultad;

        setTitle("Verificación de Graduación");
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
        panel.add(crearLabel("Seleccionar Alumno:"), gbc);

        gbc.gridx = 1;
        comboAlumnos = new JComboBox<>(facultad.getEstudiantes().stream()
            .map(a -> a.getNombre() + " " + a.getApellido() + " - DNI: " + a.getDni())
            .toArray(String[]::new));
        comboAlumnos.setPreferredSize(new Dimension(250, 30));
        panel.add(comboAlumnos, gbc);
        comboAlumnos.addActionListener(e -> actualizarCarreras());

        // Selección de Carrera
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(crearLabel("Seleccionar Carrera:"), gbc);

        gbc.gridx = 1;
        comboCarreras = new JComboBox<>();
        comboCarreras.setPreferredSize(new Dimension(250, 30));
        panel.add(comboCarreras, gbc);

        // Estado de Graduación
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(crearLabel("Estado:"), gbc);

        gbc.gridx = 1;
        lblResultado = new JLabel("Pendiente...");
        lblResultado.setForeground(Color.RED);
        panel.add(lblResultado, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 3;
        btnVerificar = crearBoton("Verificar");
        panel.add(btnVerificar, gbc);

        gbc.gridx = 1;
        btnCancelar = crearBoton("Cancelar");
        panel.add(btnCancelar, gbc);

        add(panel);

        // Eventos de botones
        btnVerificar.addActionListener(e -> verificarGraduacion());
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

    private void verificarGraduacion() {
        String alumnoSeleccionado = (String) comboAlumnos.getSelectedItem();
        String carreraSeleccionada = (String) comboCarreras.getSelectedItem();

        if (alumnoSeleccionado == null || carreraSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno y una carrera.");
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

        if (alumno != null && carrera != null) {
            boolean graduado = verificarSiAlumnoEsGraduado(alumno, carrera);

            if (graduado) {
                lblResultado.setText("Graduado ✅");
                lblResultado.setForeground(new Color(0, 128, 0)); // Verde
                JOptionPane.showMessageDialog(null, "El alumno ha finalizado la carrera con éxito.");
            } else {
                lblResultado.setText("No ha finalizado ❌");
                lblResultado.setForeground(Color.RED);
                JOptionPane.showMessageDialog(null, "El alumno aún no cumple con los requisitos de graduación.");
            }
        }
    }

    private boolean verificarSiAlumnoEsGraduado(Alumno alumno, Carrera carrera) {
        List<Materia> materiasFinalizadas = alumno.getFinalizadas();

        System.out.println("✅ Verificando graduación para " + alumno.getNombre() + " " + alumno.getApellido());
        System.out.println("📌 Materias finalizadas: " + materiasFinalizadas.stream().map(Materia::getNombre).toList());

        boolean aproboTodasObligatorias = carrera.getMateriasObligatorias().stream()
            .allMatch(materiasFinalizadas::contains);

        long optativasAprobadas = carrera.getMateriasOptativas().stream()
            .filter(materiasFinalizadas::contains)
            .count();

        System.out.println("✔ Aprobó todas las obligatorias? " + aproboTodasObligatorias);
        System.out.println("✔ Optativas aprobadas: " + optativasAprobadas + " / " + carrera.getCantidadOptativasNecesarias());

        return aproboTodasObligatorias && optativasAprobadas >= carrera.getCantidadOptativasNecesarias();
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
