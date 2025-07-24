/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.hotel.view;

import com.mycompany.hotel.controller.HabitacionController;
import com.mycompany.hotel.dto.HabitacionDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mi pc
 */
public class ListaHabitaciones extends javax.swing.JPanel {

    /**
     * Creates new form ListaHabitaciones
     */
    private HabitacionController habitacionController;
    private HabitacionDTO habitacionDTO;
    
    public ListaHabitaciones() {
        this.habitacionController = new HabitacionController();
        initComponents();
        this.refrescar();
    }
    
    public void refrescar(){
        
        try {
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("ID");
            dtm.addColumn("Numero");
            dtm.addColumn("Cantidad de camas simple");
            dtm.addColumn("Cantidad de camas doble");
            dtm.addColumn("Precio por noche");
            List<HabitacionDTO> habitaciones = habitacionController.recuperarTodos();
            
            for(HabitacionDTO h: habitaciones){
                dtm.addRow(new Object[]{
                h.getId(),
                h.getNumero(),
                h.getCamasSimples(),
                h.getCamasDobles(),
                h.getPrecioPorNoche()
                });  
            }
            
            TablaHabitaciones.setModel(dtm);
            
        } catch (SQLException ex) {
            Logger.getLogger(ListaHabitaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void buscar() throws SQLException{
    try {
        String valor = TxtBuscar.getText().trim();
        String filtro = CboxBuscar.getSelectedItem().toString();

        List<HabitacionDTO> HabitacionesFiltradas = null;

       
        if(filtro.equals("Seleccione Filtro")){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un filtro");
        }else{
            if (filtro.equals("Numero")) {
               HabitacionesFiltradas = habitacionController.buscarPorNumero(valor);
            } else if (filtro.equals("Precio")) {
                HabitacionesFiltradas = habitacionController.buscarPorPrecio(valor);
            } else if (filtro.equals("Cantidad Pasajeros")) {
                HabitacionesFiltradas = habitacionController.buscarPorCapacidad(valor);
            }
        }

        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID");
        dtm.addColumn("Numero");
        dtm.addColumn("Cantidad de camas simple");
        dtm.addColumn("Cantidad de camas doble");
        dtm.addColumn("Precio por noche");
       

        for (HabitacionDTO h : HabitacionesFiltradas) {
            dtm.addRow(new Object[]{
                h.getId(),
                h.getNumero(),
                h.getCamasSimples(),
                h.getCamasDobles(),
                h.getPrecioPorNoche()
            });
        }

        TablaHabitaciones.setModel(dtm);
    } catch (SQLException ex) {
        Logger.getLogger(ListaHabitaciones.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error al buscar pasajeros: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    public void borrar(){
           int fila = TablaHabitaciones.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");

        } else {
            DefaultTableModel model = (DefaultTableModel) TablaHabitaciones.getModel();
            int id = (int) model.getValueAt(fila, 0);
            int opc = JOptionPane.showConfirmDialog(null, "Estas Seguro?");
            if (opc == 0) {
                try {
                    habitacionController.borrar(id);
                    this.refrescar();
                } catch (SQLException ex) {
                    Logger.getLogger(ListaPasajeros.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
     
     public void actualizar() {

        int fila = TablaHabitaciones.getSelectedRow();

        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
        } else {
            DefaultTableModel model = (DefaultTableModel) TablaHabitaciones.getModel();
            int id = (int) model.getValueAt(fila, 0); // Suponiendo que la primera columna es el ID
            String numero = (String) model.getValueAt(fila, 1);
            String CamaSimple = model.getValueAt(fila, 2).toString();
            String CamaDoble = model.getValueAt(fila, 3).toString();
            String precio = model.getValueAt(fila, 4).toString();
            

            // Crear el panel con los datos cargados
          
            NuevaHabitacion panelEditar = new NuevaHabitacion(id,numero,CamaSimple,CamaDoble,precio);
            // Mostrarlo dentro de un JDialog
            JDialog dialog = new JDialog();
            dialog.setTitle("Editar Habitacion");
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

        TxtBuscar = new javax.swing.JTextField();
        CboxBuscar = new javax.swing.JComboBox<>();
        BtnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaHabitaciones = new javax.swing.JTable();
        BtnEditar = new javax.swing.JButton();
        BtnBorrar = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setBackground(new java.awt.Color(0, 102, 102));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Habitaciones"));
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TxtBuscar.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        add(TxtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 42, 304, -1));

        CboxBuscar.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        CboxBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Filtro", "Numero", "Cantidad Pasajeros", "Precio" }));
        add(CboxBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(793, 39, -1, -1));

        BtnBuscar.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(formListener);
        add(BtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1066, 39, -1, -1));

        TablaHabitaciones.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        TablaHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "Numero", "Cama Simple", "Cama Doble", "Precio"
            }
        ));
        jScrollPane1.setViewportView(TablaHabitaciones);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 1158, 442));

        BtnEditar.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        BtnEditar.setText("Editar");
        BtnEditar.setPreferredSize(new java.awt.Dimension(123, 43));
        BtnEditar.addActionListener(formListener);
        add(BtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 546, -1, -1));

        BtnBorrar.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        BtnBorrar.setText("Borrar");
        BtnBorrar.setPreferredSize(new java.awt.Dimension(123, 43));
        BtnBorrar.addActionListener(formListener);
        add(BtnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 546, -1, -1));
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == BtnBorrar) {
                ListaHabitaciones.this.BtnBorrarActionPerformed(evt);
            }
            else if (evt.getSource() == BtnBuscar) {
                ListaHabitaciones.this.BtnBuscarActionPerformed(evt);
            }
            else if (evt.getSource() == BtnEditar) {
                ListaHabitaciones.this.BtnEditarActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBorrarActionPerformed
       this.borrar();
    }//GEN-LAST:event_BtnBorrarActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        try {
            this.buscar();
        } catch (SQLException ex) {
            Logger.getLogger(ListaHabitaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
       this.actualizar();
       this.refrescar();
    }//GEN-LAST:event_BtnEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBorrar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JComboBox<String> CboxBuscar;
    private javax.swing.JTable TablaHabitaciones;
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
