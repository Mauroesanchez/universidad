/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.universidad;

import com.mycompany.universidad.vista.FacultadGUI;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mauro
 */
public class Universidad {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FacultadGUI ventana = new FacultadGUI(); 
            ventana.setVisible(true);
        });
    }
}
