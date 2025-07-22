/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.hotel.view;

import com.mycompany.hotel.controller.PasajeroController;
import com.mycompany.hotel.dto.PasajeroDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mi pc
 */
public class ListaPasajeros extends javax.swing.JPanel {

    /**
     * Creates new form ListaPasajeros
     */
    private PasajeroController pasajeroController;
    private PasajeroDTO pasajeroDto;

    public ListaPasajeros() {
        this.pasajeroController = new PasajeroController();
        initComponents();
        this.refrescar();
    }

    public void refrescar() {
        try {
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("ID");
            dtm.addColumn("Nombre");
            dtm.addColumn("Apellido");
            dtm.addColumn("DNI");
            dtm.addColumn("Telefono");
            dtm.addColumn("Correo");

            List<PasajeroDTO> pasajeros = pasajeroController.recuperarTodos();

            for (PasajeroDTO p : pasajeros) {
                dtm.addRow(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getDNI(),
                    p.getTelefono(),
                    p.getEmail()
                });
            }

            TablaPasajeros.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(ListaPasajeros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscar() throws SQLException {
        try {
            String valor = txtBuscar.getText().trim();
            String filtro = CboxBuscar.getSelectedItem().toString();

            List<PasajeroDTO> pasajerosFiltrados = null;

            if (filtro.equals("Seleccione Filtro")) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un filtro");
            } else {
                if (filtro.equals("Nombre")) {
                    pasajerosFiltrados = pasajeroController.buscarPorNombre(valor);
                } else if (filtro.equals("Apellido")) {
                    pasajerosFiltrados = pasajeroController.buscarPorApellido(valor);
                } else if (filtro.equals("Dni")) {
                    pasajerosFiltrados = pasajeroController.buscarPorDni(valor);
                } else if (filtro.equals("Correo")) {
                    pasajerosFiltrados = pasajeroController.buscarPorCorreo(valor);
                } else if (filtro.equals("Telefono")) {
                    pasajerosFiltrados = pasajeroController.buscarPorTelefono(valor);
                }
            }

            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("#");
            dtm.addColumn("Nombre");
            dtm.addColumn("Apellido");
            dtm.addColumn("DNI");
            dtm.addColumn("Telefono");
            dtm.addColumn("Correo");

            for (PasajeroDTO p : pasajerosFiltrados) {
                dtm.addRow(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getDNI(),
                    p.getTelefono(),
                    p.getEmail()
                });
            }

            TablaPasajeros.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(ListaPasajeros.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al buscar pasajeros: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void borrar() {
        int fila = TablaPasajeros.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");

        } else {
            DefaultTableModel model = (DefaultTableModel) TablaPasajeros.getModel();
            int id = (int) model.getValueAt(fila, 0);
            int opc = JOptionPane.showConfirmDialog(null, "Estas Seguro?");
            if (opc == 0) {
                try {
                    pasajeroController.borrar(id);
                    this.refrescar();
                } catch (SQLException ex) {
                    Logger.getLogger(ListaPasajeros.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public void actualizar() {

        int fila = TablaPasajeros.getSelectedRow();

        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
        } else {
            DefaultTableModel model = (DefaultTableModel) TablaPasajeros.getModel();
            int id = (int) model.getValueAt(fila, 0); // Suponiendo que la primera columna es el ID
            String nombre = (String) model.getValueAt(fila, 1);
            String apellido = (String) model.getValueAt(fila, 2);
            String dni = model.getValueAt(fila, 3).toString();
            String telefono = model.getValueAt(fila, 4).toString();
            String correo = (String) model.getValueAt(fila, 5);

            // Crear el panel con los datos cargados
            NuevoPasajero panelEditar = new NuevoPasajero(id, nombre, apellido, dni, telefono, correo);

            // Mostrarlo dentro de un JDialog
            JDialog dialog = new JDialog();
            dialog.setTitle("Editar Pasajero");
            dialog.setModal(true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.getContentPane().add(panelEditar);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
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

        txtBuscar = new javax.swing.JTextField();
        BtnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPasajeros = new javax.swing.JTable();
        BtnEditar = new javax.swing.JButton();
        BtnBorrar = new javax.swing.JButton();
        CboxBuscar = new javax.swing.JComboBox<>();

        FormListener formListener = new FormListener();

        setBackground(new java.awt.Color(0, 102, 102));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Pasajeros"));
        setToolTipText("");
        setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 39, 295, -1));

        BtnBuscar.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(formListener);
        add(BtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1053, 39, -1, -1));

        TablaPasajeros.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TablaPasajeros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "#", "Nombre", "Apellido", "Dni", "Telefono", "Correo"
            }
        ));
        jScrollPane1.setViewportView(TablaPasajeros);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 95, 1170, 429));

        BtnEditar.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        BtnEditar.setText("Editar");
        BtnEditar.addActionListener(formListener);
        add(BtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 546, 143, -1));

        BtnBorrar.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        BtnBorrar.setText("Borrar");
        BtnBorrar.addActionListener(formListener);
        add(BtnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 546, 143, -1));

        CboxBuscar.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        CboxBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Filtro", "Nombre", "Apellido", "Dni", "Telefono", "Correo" }));
        CboxBuscar.addActionListener(formListener);
        add(CboxBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(806, 39, 234, -1));
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == BtnBuscar) {
                ListaPasajeros.this.BtnBuscarActionPerformed(evt);
            }
            else if (evt.getSource() == BtnEditar) {
                ListaPasajeros.this.BtnEditarActionPerformed(evt);
            }
            else if (evt.getSource() == BtnBorrar) {
                ListaPasajeros.this.BtnBorrarActionPerformed(evt);
            }
            else if (evt.getSource() == CboxBuscar) {
                ListaPasajeros.this.CboxBuscarActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBorrarActionPerformed
        this.borrar();
    }//GEN-LAST:event_BtnBorrarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed

        this.actualizar();
        this.refrescar();
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void CboxBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboxBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CboxBuscarActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        try {
            this.buscar();
        } catch (SQLException ex) {
            Logger.getLogger(ListaPasajeros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBorrar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JComboBox<String> CboxBuscar;
    private javax.swing.JTable TablaPasajeros;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
