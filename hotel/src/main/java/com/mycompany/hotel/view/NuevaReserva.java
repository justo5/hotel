/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.hotel.view;

import com.mycompany.hotel.controller.HabitacionController;
import com.mycompany.hotel.controller.PasajeroController;
import com.mycompany.hotel.controller.ReservaController;
import com.mycompany.hotel.dto.HabitacionDTO;
import com.mycompany.hotel.dto.PasajeroDTO;
import com.mycompany.hotel.dto.ReservaDTO;
import com.toedter.calendar.JCalendar;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author mi pc
 */
public class NuevaReserva extends javax.swing.JPanel {

    /**
     * Creates new form NuevaReserva
     */
    private ReservaController reservaController;
    private ReservaDTO reservaDTO;
    private PasajeroController pasajeroController;
    private HabitacionController habitacionController;

    public NuevaReserva() {
        initComponents();
        this.habitacionController = new HabitacionController();
        this.pasajeroController = new PasajeroController();

       
    }
    public NuevaReserva(int id,HabitacionDTO h, Date checkin, Date checkout,float senia,PasajeroDTO p, int capacidad){
        this.reservaController = new ReservaController();
        this.habitacionController = new HabitacionController();
        this.pasajeroController = new PasajeroController();
        initComponents();
        this.LblId.setText(String.valueOf(id));
        this.FechaCheckin.setDate(checkin);
        this.FechaCheckout.setDate(checkout);
        this.TxtSeña.setText(String.valueOf(senia));
        this.TxtCantidadPersonas.setText(String.valueOf(capacidad));
        this.CboxHabitaciones.setSelectedItem(h);
        this.CboxPasajeros.setSelectedItem(p.getDNI());
        
    }

    public NuevaReserva(JComboBox<String> CboxCantidadPersonas, JComboBox<HabitacionDTO> CboxHabitaciones, JCalendar FechaCheckin, JCalendar FechaCheckout, JTextField TxtNombrePasajero, JTextField TxtSeña) {

        this.CboxHabitaciones = CboxHabitaciones;
        this.FechaCheckin = FechaCheckin;
        this.FechaCheckout = FechaCheckout;
        this.TxtDniPasajero = TxtNombrePasajero;
        this.TxtSeña = TxtSeña;
    }

    public void CargarPasajeros() {
        List<PasajeroDTO> pasajeros = null;
        String dni = TxtDniPasajero.getText().trim();

        try {
            pasajeros = pasajeroController.buscarPorDni(dni);
        } catch (SQLException ex) {
            Logger.getLogger(NuevaReserva.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al buscar pasajero por DNI.");
            return;
        }

        CboxPasajeros.removeAllItems();

        if (pasajeros == null || pasajeros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron pasajeros con ese DNI.");
            return;
        }

        for (PasajeroDTO p : pasajeros) {
            CboxPasajeros.addItem(p);
        }
    }

    public void CargarHabitacionesDisponibles() {
        try {
            // Obtener y validar fechas
            java.util.Date checkinUtil = FechaCheckin.getDate();
            java.util.Date checkoutUtil = FechaCheckout.getDate();

            if (checkinUtil == null || checkoutUtil == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar ambas fechas.");
                return;
            }

            // Convertir a java.sql.Date
            java.sql.Date checkin = new java.sql.Date(checkinUtil.getTime());
            java.sql.Date checkout = new java.sql.Date(checkoutUtil.getTime());

            // Obtener y validar cantidad de personas
            String cantidadPasajeros = TxtCantidadPersonas.getText().trim();
            if (cantidadPasajeros.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar la cantidad de personas.");
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadPasajeros);
                if (cantidad < 1 || cantidad > 5) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe estar entre 1 y 5 personas.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
                return;
            }

            // Buscar habitaciones disponibles
            List<HabitacionDTO> habitaciones = habitacionController.buscarDisponibles(checkin, checkout, cantidad);

            // Limpiar ComboBox
            CboxHabitaciones.removeAllItems();

            if (habitaciones == null || habitaciones.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron habitaciones disponibles.");
                return;
            }

            // Agregar habitaciones al combo
            for (HabitacionDTO h : habitaciones) {
                CboxHabitaciones.addItem(h);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar habitaciones: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void Guardar() {
         // Obtener y validar fechas
            java.util.Date checkinUtil = FechaCheckin.getDate();
            java.util.Date checkoutUtil = FechaCheckout.getDate();

            if (checkinUtil == null || checkoutUtil == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar ambas fechas.");
                return;
            }

            // Convertir a java.sql.Date
            java.sql.Date checkin = new java.sql.Date(checkinUtil.getTime());
            java.sql.Date checkout = new java.sql.Date(checkoutUtil.getTime());
            
        // Obtener habitación seleccionada
        HabitacionDTO habitacionSeleccionada = (HabitacionDTO) CboxHabitaciones.getSelectedItem();
        if (habitacionSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una habitación.");
            return;
        }

// Obtener pasajero seleccionado
        PasajeroDTO pasajeroSeleccionado = (PasajeroDTO) CboxPasajeros.getSelectedItem();
        if (pasajeroSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pasajero.");
            return;
        }

// Obtener y validar seña
        String seniaStr = TxtSeña.getText().trim();
        float senia = 0;
        try {
            senia = Float.parseFloat(seniaStr);
            if (senia < 0) {
                JOptionPane.showMessageDialog(this, "La seña no puede ser negativa.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor válido para la seña.");
            return;
        }



// Crear ReservaDTO
        ReservaDTO nuevaReserva = new ReservaDTO();
        //nuevaReserva.setId(id); // Si es null, es nueva. Si no, es edición.
        nuevaReserva.setChekin(checkin);
        nuevaReserva.setCheckout(checkout);
        nuevaReserva.setId_pasajero(pasajeroSeleccionado.getId());
        nuevaReserva.setId_habitacion(habitacionSeleccionada.getId());
        nuevaReserva.setSenia(senia);
      

// Guardar reserva
        if (reservaController == null) {
            reservaController = new ReservaController(); // Asegurar que no sea null
        }
        try {
            reservaController.crear(nuevaReserva);
            JOptionPane.showMessageDialog(this, "Reserva guardada correctamente.");
           // LimpiarCampos(); // Si querés limpiar el formulario luego de guardar
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la reserva: " + e.getMessage());
            e.printStackTrace();
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

        LblNombrePasajero = new javax.swing.JLabel();
        TxtDniPasajero = new javax.swing.JTextField();
        BtnNuevoPasajero = new javax.swing.JButton();
        LblCantidadPersonas = new javax.swing.JLabel();
        LblHabitacion = new javax.swing.JLabel();
        CboxHabitaciones = new javax.swing.JComboBox<>();
        LblCheckin = new javax.swing.JLabel();
        FechaCheckout = new com.toedter.calendar.JCalendar();
        FechaCheckin = new com.toedter.calendar.JCalendar();
        LblCheckout = new javax.swing.JLabel();
        LblSeña = new javax.swing.JLabel();
        TxtSeña = new javax.swing.JTextField();
        BtnGuardar = new javax.swing.JButton();
        LblId = new javax.swing.JLabel();
        CboxPasajeros = new javax.swing.JComboBox<>();
        TxtCantidadPersonas = new javax.swing.JTextField();

        FormListener formListener = new FormListener();

        setBackground(new java.awt.Color(0, 102, 102));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva Reserva"));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblNombrePasajero.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        LblNombrePasajero.setText("DNI de Pasajero:");
        add(LblNombrePasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 39, -1, 26));

        TxtDniPasajero.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TxtDniPasajero.addActionListener(formListener);
        add(TxtDniPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 39, 169, -1));

        BtnNuevoPasajero.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        BtnNuevoPasajero.setText("Nuevo Pasajero");
        BtnNuevoPasajero.addActionListener(formListener);
        add(BtnNuevoPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 78, -1, -1));

        LblCantidadPersonas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        LblCantidadPersonas.setText("Cantidad de Personas");
        add(LblCantidadPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 377, -1, -1));

        LblHabitacion.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        LblHabitacion.setText("Numero de Habitacion");
        add(LblHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 377, -1, -1));

        CboxHabitaciones.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        add(CboxHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 416, 371, -1));

        LblCheckin.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        LblCheckin.setText("Checkin");
        add(LblCheckin, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 130, -1, -1));
        add(FechaCheckout, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 169, 338, 195));
        add(FechaCheckin, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 169, 338, 195));

        LblCheckout.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        LblCheckout.setText("Checkout");
        add(LblCheckout, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, -1, -1));

        LblSeña.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        LblSeña.setText("Seña");
        add(LblSeña, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 481, -1, 26));

        TxtSeña.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        add(TxtSeña, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 507, 310, -1));

        BtnGuardar.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.addActionListener(formListener);
        add(BtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 559, 234, 39));

        LblId.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        add(LblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(975, 39, 26, 26));

        add(CboxPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 78, 585, 39));

        TxtCantidadPersonas.addActionListener(formListener);
        add(TxtCantidadPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 416, 364, 39));
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == TxtDniPasajero) {
                NuevaReserva.this.TxtDniPasajeroActionPerformed(evt);
            }
            else if (evt.getSource() == BtnNuevoPasajero) {
                NuevaReserva.this.BtnNuevoPasajeroActionPerformed(evt);
            }
            else if (evt.getSource() == TxtCantidadPersonas) {
                NuevaReserva.this.TxtCantidadPersonasActionPerformed(evt);
            }
            else if (evt.getSource() == BtnGuardar) {
                NuevaReserva.this.BtnGuardarActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNuevoPasajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoPasajeroActionPerformed
        // Crear el panel con los datos cargados
        NuevoPasajero panelEditar = new NuevoPasajero();

        // Mostrarlo dentro de un JDialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Pasajero");
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(panelEditar);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_BtnNuevoPasajeroActionPerformed

    private void TxtDniPasajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDniPasajeroActionPerformed
        CargarPasajeros();

    }//GEN-LAST:event_TxtDniPasajeroActionPerformed

    private void TxtCantidadPersonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCantidadPersonasActionPerformed
        CargarHabitacionesDisponibles();
    }//GEN-LAST:event_TxtCantidadPersonasActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_BtnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevoPasajero;
    private javax.swing.JComboBox<HabitacionDTO> CboxHabitaciones;
    private javax.swing.JComboBox<PasajeroDTO> CboxPasajeros;
    private com.toedter.calendar.JCalendar FechaCheckin;
    private com.toedter.calendar.JCalendar FechaCheckout;
    private javax.swing.JLabel LblCantidadPersonas;
    private javax.swing.JLabel LblCheckin;
    private javax.swing.JLabel LblCheckout;
    private javax.swing.JLabel LblHabitacion;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblNombrePasajero;
    private javax.swing.JLabel LblSeña;
    private javax.swing.JTextField TxtCantidadPersonas;
    private javax.swing.JTextField TxtDniPasajero;
    private javax.swing.JTextField TxtSeña;
    // End of variables declaration//GEN-END:variables
}
