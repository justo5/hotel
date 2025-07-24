/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hotel;

import com.mycompany.hotel.view.Principal;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author justcode
 */
public class Program {

    public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Crear y mostrar la ventana principal
                new Principal().setVisible(true);
           



            }
        });    }
}
