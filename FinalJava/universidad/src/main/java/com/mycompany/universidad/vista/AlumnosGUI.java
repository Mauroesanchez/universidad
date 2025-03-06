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
import com.mycompany.universidad.modelo.Alumno;
import com.mycompany.universidad.modelo.Facultad;

public class AlumnosGUI extends JFrame {
    private JTextField txtDni, txtNombre, txtApellido;
    private JButton btnGuardar, btnCancelar;
    private Facultad facultad; // Referencia a la facultad

    public AlumnosGUI(Facultad facultad) {
        this.facultad = facultad; // Recibe la facultad
        setTitle("Registrar Alumno");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(50, 50, 100));

        // Panel del título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelTitulo.setBackground(new Color(50, 50, 100));
        JLabel lblTitulo = new JLabel("Crear Alumno Nuevo");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitulo.add(lblTitulo);

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(5, 50, 20, 50));
        panelFormulario.setBackground(new Color(50, 50, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setForeground(Color.WHITE);
        panelFormulario.add(lblDni, gbc);

        gbc.gridx = 1;
        txtDni = crearCampoTexto();
        panelFormulario.add(txtDni, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.WHITE);
        panelFormulario.add(lblNombre, gbc);

        gbc.gridx = 1;
        txtNombre = crearCampoTexto();
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setForeground(Color.WHITE);
        panelFormulario.add(lblApellido, gbc);

        gbc.gridx = 1;
        txtApellido = crearCampoTexto();
        panelFormulario.add(txtApellido, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(new Color(50, 50, 100));

        btnGuardar = crearBoton("Guardar");
        btnCancelar = crearBoton("Cancelar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        // Agregar paneles al frame
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);

        // Eventos de botones
        btnGuardar.addActionListener(e -> registrarAlumno());
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void registrarAlumno() {
    if (validarCampos()) {
        try {
            int dni = Integer.parseInt(txtDni.getText());
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();

            if (facultad.buscarEstudiantePorDNI(dni) != null) { // Cambio de método
                JOptionPane.showMessageDialog(this, "Ya existe un alumno con ese DNI.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Alumno nuevoAlumno = new Alumno(dni, nombre, apellido);
            facultad.agregarEstudiante(nuevoAlumno); // Cambio de método

            JOptionPane.showMessageDialog(this, "Alumno registrado exitosamente.");
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El DNI debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setPreferredSize(new Dimension(200, 25));
        return campo;
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(100, 100, 200));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        return boton;
    }

    private boolean validarCampos() {
        return !txtDni.getText().isEmpty() && !txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty();
    }

    private void limpiarCampos() {
        txtDni.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
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
