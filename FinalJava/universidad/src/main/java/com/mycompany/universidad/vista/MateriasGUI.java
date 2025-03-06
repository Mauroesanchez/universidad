/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.universidad.vista;

/**
 *
 * @author Mauro
 */

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;
import java.awt.*;
import com.mycompany.universidad.modelo.*;

public class MateriasGUI extends JFrame {
    private JTextField txtNombreMateria, txtCuatrimestre;
    private JCheckBox chkEsObligatoria, chkTienePromocion;
    private JPanel panelCorrelativas;  // Panel para checkboxes de correlativas
    private JButton btnGuardarMateria, btnCancelar;
    private JComboBox<String> comboCarreras;
    private Facultad facultad;
    private List<JCheckBox> checkBoxesCorrelativas;  // Lista de checkboxes

    public MateriasGUI(Facultad facultad) {
    this.facultad = facultad;

    setTitle("Gestión de Materias");
    setSize(600, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);

    Color fondoColor = new Color(50, 50, 100);
    Color botonColor = new Color(100, 100, 200);
    Color textoColor = Color.WHITE;

    getContentPane().setBackground(fondoColor);
    setLayout(new BorderLayout());

    JPanel panelMateria = new JPanel(new GridBagLayout());
    panelMateria.setBorder(BorderFactory.createTitledBorder(null, "Crear Materia", 
        javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), textoColor));
    panelMateria.setBackground(fondoColor);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;

    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setForeground(textoColor);
    gbc.gridx = 0;
    gbc.gridy = 0;
    panelMateria.add(lblNombre, gbc);

    txtNombreMateria = new JTextField();
    txtNombreMateria.setPreferredSize(new Dimension(200, 25));
    gbc.gridx = 1;
    panelMateria.add(txtNombreMateria, gbc);

    JLabel lblCarrera = new JLabel("Carrera:");
    lblCarrera.setForeground(textoColor);
    gbc.gridx = 0;
    gbc.gridy = 1;
    panelMateria.add(lblCarrera, gbc);

    // Crear lista de carreras con opción inicial
    List<String> carrerasDisponibles = new ArrayList<>();
    carrerasDisponibles.add("Selecciona una carrera");
    carrerasDisponibles.addAll(facultad.getCarreras().stream()
                                       .map(Carrera::getNombre)
                                       .toList());

    comboCarreras = new JComboBox<>(carrerasDisponibles.toArray(new String[0]));
    comboCarreras.setPreferredSize(new Dimension(200, 25));
    gbc.gridx = 1;
    panelMateria.add(comboCarreras, gbc);

    comboCarreras.addActionListener(e -> actualizarListaCorrelativas());

    JLabel lblCuatrimestre = new JLabel("Cuatrimestre:");
    lblCuatrimestre.setForeground(textoColor);
    gbc.gridx = 0;
    gbc.gridy = 2;
    panelMateria.add(lblCuatrimestre, gbc);

    txtCuatrimestre = new JTextField();
    txtCuatrimestre.setPreferredSize(new Dimension(200, 25));
    gbc.gridx = 1;
    panelMateria.add(txtCuatrimestre, gbc);

    JLabel lblObligatoria = new JLabel("Obligatoria:");
    lblObligatoria.setForeground(textoColor);
    gbc.gridx = 0;
    gbc.gridy = 3;
    panelMateria.add(lblObligatoria, gbc);

    chkEsObligatoria = new JCheckBox();
    chkEsObligatoria.setBackground(fondoColor);
    gbc.gridx = 1;
    panelMateria.add(chkEsObligatoria, gbc);

    JLabel lblPromocion = new JLabel("Tiene Promoción:");
    lblPromocion.setForeground(textoColor);
    gbc.gridx = 0;
    gbc.gridy = 4;
    panelMateria.add(lblPromocion, gbc);

    chkTienePromocion = new JCheckBox();
    chkTienePromocion.setBackground(fondoColor);
    gbc.gridx = 1;
    panelMateria.add(chkTienePromocion, gbc);

    JLabel lblCorrelativas = new JLabel("Correlativas:");
    lblCorrelativas.setForeground(textoColor);
    gbc.gridx = 0;
    gbc.gridy = 5;
    panelMateria.add(lblCorrelativas, gbc);

    panelCorrelativas = new JPanel();
    panelCorrelativas.setLayout(new GridLayout(0, 1));  // Permite múltiples checkboxes
    panelCorrelativas.setBackground(fondoColor);
    JScrollPane scrollCorrelativas = new JScrollPane(panelCorrelativas);
    scrollCorrelativas.setPreferredSize(new Dimension(250, 100));
    gbc.gridx = 1;
    panelMateria.add(scrollCorrelativas, gbc);

    btnGuardarMateria = new JButton("Guardar Materia");
    btnGuardarMateria.setBackground(botonColor);
    btnGuardarMateria.setForeground(textoColor);
    gbc.gridy = 6;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    panelMateria.add(btnGuardarMateria, gbc);

    JPanel panelBotones = new JPanel();
    panelBotones.setBackground(fondoColor);

    btnCancelar = new JButton("Cancelar");
    btnCancelar.setBackground(botonColor);
    btnCancelar.setForeground(textoColor);
    panelBotones.add(btnCancelar);

    add(panelMateria, BorderLayout.CENTER);
    add(panelBotones, BorderLayout.SOUTH);

    btnGuardarMateria.addActionListener(e -> guardarMateria());
    btnCancelar.addActionListener(e -> dispose());

    setVisible(true);
}


    private void actualizarListaCorrelativas() {
        // Asegurar que la lista de checkboxes esté inicializada
        if (checkBoxesCorrelativas == null) {
            checkBoxesCorrelativas = new ArrayList<>();
        } else {
            checkBoxesCorrelativas.clear(); // Limpia la lista si ya estaba inicializada
        }

        panelCorrelativas.removeAll(); // Limpia la UI antes de actualizar

        String nombreCarreraSeleccionada = (String) comboCarreras.getSelectedItem();
        if (nombreCarreraSeleccionada == null || nombreCarreraSeleccionada.equals("Selecciona una carrera")) {
            return; // No actualizar si no se selecciona una carrera válida
        }

        Carrera carreraSeleccionada = facultad.getCarreras().stream()
                .filter(c -> c.getNombre().equals(nombreCarreraSeleccionada))
                .findFirst()
                .orElse(null);

        if (carreraSeleccionada != null) {
            List<Materia> todasLasMaterias = new ArrayList<>();
            todasLasMaterias.addAll(carreraSeleccionada.getMateriasObligatorias());
            todasLasMaterias.addAll(carreraSeleccionada.getMateriasOptativas());

            if (!todasLasMaterias.isEmpty()) {
                for (Materia materia : todasLasMaterias) {
                    JCheckBox checkBox = new JCheckBox(materia.getNombre());
                    checkBox.setBackground(new Color(50, 50, 100));
                    checkBox.setForeground(Color.WHITE);
                    checkBoxesCorrelativas.add(checkBox);
                    panelCorrelativas.add(checkBox);
                }
            } else {
                JLabel lblNoCorrelativas = new JLabel("No hay materias correlativas disponibles.");
                lblNoCorrelativas.setForeground(Color.WHITE);
                panelCorrelativas.add(lblNoCorrelativas);
            }
        }

        panelCorrelativas.revalidate();
        panelCorrelativas.repaint();
    }


    private void guardarMateria() {
        if (validarCamposMateria()) {
            String nombre = txtNombreMateria.getText();
            int cuatrimestre = Integer.parseInt(txtCuatrimestre.getText());
            boolean esObligatoria = chkEsObligatoria.isSelected();
            boolean tienePromocion = chkTienePromocion.isSelected();

            List<Materia> materiasCorrelativas = checkBoxesCorrelativas.stream()
                .filter(JCheckBox::isSelected)
                .map(checkbox -> facultad.getCarreras().stream()
                    .flatMap(carrera -> Stream.concat(
                        carrera.getMateriasObligatorias().stream(),
                        carrera.getMateriasOptativas().stream()
                    ))
                    .filter(materia -> materia.getNombre().equals(checkbox.getText()))
                    .findFirst()
                    .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

            String nombreCarreraSeleccionada = (String) comboCarreras.getSelectedItem();
            Carrera carreraSeleccionada = facultad.getCarreras().stream()
                .filter(c -> c.getNombre().equals(nombreCarreraSeleccionada))
                .findFirst()
                .orElse(null);

            if (carreraSeleccionada != null) {
                Materia nuevaMateria = new Materia(nombre, esObligatoria, tienePromocion, cuatrimestre);
                nuevaMateria.setCorrelativas(materiasCorrelativas);

                if (esObligatoria) {
                    carreraSeleccionada.agregarMateriaObligatoria(nuevaMateria);
                } else {
                    carreraSeleccionada.agregarMateriaOptativa(nuevaMateria);
                }

                JOptionPane.showMessageDialog(null, 
                    "Materia Guardada:\n" +
                    "Nombre: " + nombre + "\n" +
                    "Cuatrimestre: " + cuatrimestre + "\n" +
                    "Obligatoria: " + esObligatoria + "\n" +
                    "Promoción: " + tienePromocion + "\n" +
                    "Carrera: " + nombreCarreraSeleccionada + "\n" +
                    "Correlativas: " + materiasCorrelativas.stream().map(Materia::getNombre).collect(Collectors.joining(", ")));

                limpiarCamposMateria();
            }
        }
    }



    private void limpiarCamposMateria() {
        txtNombreMateria.setText("");
        txtCuatrimestre.setText("");
        chkEsObligatoria.setSelected(false);
        chkTienePromocion.setSelected(false);
        checkBoxesCorrelativas.forEach(checkBox -> checkBox.setSelected(false));
    }




    private boolean validarCamposMateria() {
        try {
            int cuatrimestre = Integer.parseInt(txtCuatrimestre.getText());
            if (cuatrimestre <= 0) {
                JOptionPane.showMessageDialog(null, "El cuatrimestre debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido en el campo Cuatrimestre.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txtNombreMateria.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de la materia no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (comboCarreras.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una carrera.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
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
