/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaLoteria;

import ModeloLoteria.LoteriaDto;
import ModeloVentas.PersonaDto;
import VistaPrincipal.Principal;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
public class RegistrarLoteriaInternalFrame extends javax.swing.JInternalFrame {

    private Principal principal;
    private int codigoAnterior;

    /**
     * Creates new form RegistrarLoteriaInternalFrame
     */
    public RegistrarLoteriaInternalFrame(Principal p) {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.principal = p;
        this.acomodarComboHora();
    }

    private void acomodarComboHora() {
        this.cmbHora.removeAllItems();
        this.cmbHora.addItem("00");
        this.cmbHora.addItem("01");
        this.cmbHora.addItem("02");
        this.cmbHora.addItem("03");
        this.cmbHora.addItem("04");
        this.cmbHora.addItem("05");
        this.cmbHora.addItem("06");
        this.cmbHora.addItem("07");
        this.cmbHora.addItem("08");
        this.cmbHora.addItem("09");
        this.cmbHora.addItem("10");
        this.cmbHora.addItem("11");
        this.cmbHora.addItem("12");
        this.cmbHora.addItem("13");
        this.cmbHora.addItem("14");
        this.cmbHora.addItem("15");
        this.cmbHora.addItem("16");
        this.cmbHora.addItem("17");
        this.cmbHora.addItem("18");
        this.cmbHora.addItem("19");
        this.cmbHora.addItem("20");
        this.cmbHora.addItem("21");
        this.cmbHora.addItem("22");
        this.cmbHora.addItem("23");

        this.txtMinuto.setText("00");
    }

    private int diaNumerico() {

        String valor = this.cmbDia.getSelectedItem().toString();
        if (valor.equals("Todos")) {
            return -1;
        }
        if (valor.equals("Lunes")) {
            return 1;
        }
        if (valor.equals("Martes")) {
            return 2;
        }
        if (valor.equals("Miercoles")) {
            return 3;
        }
        if (valor.equals("Jueves")) {
            return 4;
        }
        if (valor.equals("Viernes")) {
            return 5;
        }
        if (valor.equals("Sabado")) {
            return 6;
        }
        if (valor.equals("Domingo")) {
            return 0;
        }
        return -1;//todos los dias
    }

    public void deshabilitarCampos() {
        this.txtNombre.setEnabled(false);
        this.cmbDia.setEnabled(false);
        this.cmdModificar.setEnabled(false);
        this.cmdHabilitar.setEnabled(false);
        this.cmbHora.setEnabled(false);
        this.txtMinuto.setEnabled(false);
        this.txtMinuto.setEnabled(false);
        this.cmbHora.setEnabled(false);
        this.cmbRuta.setEnabled(false);
    }

    public void habilitarCampos() {
        this.txtMinuto.setEnabled(true);
        this.cmbHora.setEnabled(true);
        this.cmbDia.setEnabled(true);
        this.txtNombre.setEnabled(true);
        this.cmbHora.setEnabled(true);
        this.txtMinuto.setEnabled(true);
        this.cmbRuta.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        cmdRegistrar = new javax.swing.JButton();
        cmdSalir = new javax.swing.JButton();
        cmbDia = new javax.swing.JComboBox();
        cmbHora = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMinuto = new javax.swing.JTextField();
        cmdHabilitar = new javax.swing.JButton();
        cmdModificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbRuta = new javax.swing.JComboBox();

        setTitle("Registrar loteria");

        jLabel1.setText("Codigo:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Dia:");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        cmdRegistrar.setText("Registrar");
        cmdRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRegistrarActionPerformed(evt);
            }
        });

        cmdSalir.setText("Salir");
        cmdSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSalirActionPerformed(evt);
            }
        });

        cmbDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));
        cmbDia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDiaKeyPressed(evt);
            }
        });

        cmbHora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbHoraKeyPressed(evt);
            }
        });

        jLabel5.setText("Hora:");

        jLabel6.setText("Minuto: ");

        txtMinuto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMinutoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinutoKeyTyped(evt);
            }
        });

        cmdHabilitar.setText("Habilitar");
        cmdHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHabilitarActionPerformed(evt);
            }
        });

        cmdModificar.setText("Modificar");
        cmdModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdModificarActionPerformed(evt);
            }
        });

        jLabel4.setText("Ruta: ");

        cmbRuta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dia", "Noche" }));
        cmbRuta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRutaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(cmbDia, 0, 135, Short.MAX_VALUE)
                            .addComponent(cmbRuta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdHabilitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdSalir)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdRegistrar)
                    .addComponent(cmdHabilitar)
                    .addComponent(cmdModificar)
                    .addComponent(cmdSalir))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void cargarComboRuta(LoteriaDto dto){
    this.cmbRuta.removeAllItems();
    if(dto.getRuta().equalsIgnoreCase("Dia")){
    this.cmbRuta.addItem("Dia");
    this.cmbRuta.addItem("Noche");
    }else{
    this.cmbRuta.addItem("Noche");
    this.cmbRuta.addItem("Dia");
    }
    
    }
    
    private void cargarComboComboHoraMinuto(LoteriaDto dto) {
        this.cmbHora.removeAllItems();
        this.cmbHora.addItem(dto.getHora());

        this.cmbHora.addItem("00");
        this.cmbHora.addItem("01");
        this.cmbHora.addItem("02");
        this.cmbHora.addItem("03");
        this.cmbHora.addItem("04");
        this.cmbHora.addItem("05");
        this.cmbHora.addItem("06");
        this.cmbHora.addItem("07");
        this.cmbHora.addItem("08");
        this.cmbHora.addItem("09");
        this.cmbHora.addItem("10");
        this.cmbHora.addItem("11");
        this.cmbHora.addItem("12");
        this.cmbHora.addItem("13");
        this.cmbHora.addItem("14");
        this.cmbHora.addItem("15");
        this.cmbHora.addItem("16");
        this.cmbHora.addItem("17");
        this.cmbHora.addItem("18");
        this.cmbHora.addItem("19");
        this.cmbHora.addItem("20");
        this.cmbHora.addItem("21");
        this.cmbHora.addItem("22");
        this.cmbHora.addItem("23");

        this.txtMinuto.setText(dto.getMinuto() + "");
    }

    private void cargarComboDia(LoteriaDto dto) {
        this.cmbDia.removeAllItems();
        String dia = dto.getDiaFormato();
        if (dia.equals("Lunes")) {
            this.cmbDia.addItem(dia);
            this.cmbDia.addItem("Martes");
            this.cmbDia.addItem("Miercoles");
            this.cmbDia.addItem("Jueves");
            this.cmbDia.addItem("Viernes");
            this.cmbDia.addItem("Sabado");
            this.cmbDia.addItem("Domingo");
            this.cmbDia.addItem("Todos");
        }
        if (dia.equals("Martes")) {
            this.cmbDia.addItem(dia);
            this.cmbDia.addItem("Lunes");
            this.cmbDia.addItem("Miercoles");
            this.cmbDia.addItem("Jueves");
            this.cmbDia.addItem("Viernes");
            this.cmbDia.addItem("Sabado");
            this.cmbDia.addItem("Domingo");
            this.cmbDia.addItem("Todos");
        }
        if (dia.equals("Miercoles")) {
            this.cmbDia.addItem(dia);
            this.cmbDia.addItem("Lunes");
            this.cmbDia.addItem("Martes");
            this.cmbDia.addItem("Jueves");
            this.cmbDia.addItem("Viernes");
            this.cmbDia.addItem("Sabado");
            this.cmbDia.addItem("Domingo");
            this.cmbDia.addItem("Todos");
        }
        if (dia.equals("Jueves")) {
            this.cmbDia.addItem(dia);
            this.cmbDia.addItem("Lunes");
            this.cmbDia.addItem("Martes");
            this.cmbDia.addItem("Miercoles");
            this.cmbDia.addItem("Viernes");
            this.cmbDia.addItem("Sabado");
            this.cmbDia.addItem("Domingo");
            this.cmbDia.addItem("Todos");
        }
        if (dia.equals("Viernes")) {
            this.cmbDia.addItem(dia);
            this.cmbDia.addItem("Lunes");
            this.cmbDia.addItem("Martes");
            this.cmbDia.addItem("Miercoles");
            this.cmbDia.addItem("Jueves");
            this.cmbDia.addItem("Sabado");
            this.cmbDia.addItem("Domingo");
            this.cmbDia.addItem("Todos");
        }
        if (dia.equals("Sabado")) {
            this.cmbDia.addItem(dia);
            this.cmbDia.addItem("Lunes");
            this.cmbDia.addItem("Martes");
            this.cmbDia.addItem("Miercoles");
            this.cmbDia.addItem("Jueves");
            this.cmbDia.addItem("Viernes");
            this.cmbDia.addItem("Domingo");
            this.cmbDia.addItem("Todos");
        }
        if (dia.equals("Domingo")) {
            this.cmbDia.addItem(dia);
            this.cmbDia.addItem("Lunes");
            this.cmbDia.addItem("Martes");
            this.cmbDia.addItem("Miercoles");
            this.cmbDia.addItem("Jueves");
            this.cmbDia.addItem("Viernes");
            this.cmbDia.addItem("Sabado");
            this.cmbDia.addItem("Todos");
        }
        if (dia.equals("Todos")) {
            this.cmbDia.addItem("Todos");
            this.cmbDia.addItem("Lunes");
            this.cmbDia.addItem("Martes");
            this.cmbDia.addItem("Miercoles");
            this.cmbDia.addItem("Jueves");
            this.cmbDia.addItem("Viernes");
            this.cmbDia.addItem("Sabado");
        }

    }

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            ArrayList<LoteriaDto> lista = this.principal.getFachada().listarLoteriaXCodigo(Integer.parseInt(this.txtCodigo.getText()));
            if (!lista.isEmpty()) {

                for (LoteriaDto l : lista) {

                    this.txtCodigo.setText(l.getCodigo() + "");
                    this.txtNombre.setText(l.getNombre());
                    this.cargarComboDia(l);
                    this.cargarComboComboHoraMinuto(l);
                    this.cargarComboRuta(l);

                }
                this.deshabilitarCampos();
                this.cmdHabilitar.setEnabled(true);
                this.codigoAnterior = Integer.parseInt(this.txtCodigo.getText());

            } else {
                this.habilitarCampos();
                this.vaciarCamposSinCodigo();
                this.cmdHabilitar.setEnabled(false);
                this.cmdModificar.setEnabled(false);
                this.txtNombre.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed


    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            this.cmbRuta.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyPressed

    private void cmdSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSalirActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_cmdSalirActionPerformed

    private void cmdRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRegistrarActionPerformed
        // TODO add your handling code here:
        this.registrar();
    }//GEN-LAST:event_cmdRegistrarActionPerformed

    private void registrar() {
        if (!this.validarCampos()) {
            if (!this.validarMinuto()) {
                JOptionPane.showMessageDialog(null, "Los minutos no estan dentro del rango");
                return;
            }
            LoteriaDto dto = this.llenarDatosLoteria();
            boolean valor = this.principal.getFachada().registrarLoteria(dto);
            if (valor) {
                JOptionPane.showMessageDialog(null, "La loteria se registro exitosamente");
                this.deshabilitarCampos();
                this.vaciarCampos();
                return;
            }
            JOptionPane.showMessageDialog(null, "La loteria ya se encuentra registrada");
        } else {
            JOptionPane.showMessageDialog(null, "No puede dejar campos vacios");
        }
    }

    private void txtMinutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinutoKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }

    }//GEN-LAST:event_txtMinutoKeyTyped

    private void cmbDiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDiaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            this.cmbHora.requestFocus();
        }
    }//GEN-LAST:event_cmbDiaKeyPressed

    private void cmbHoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbHoraKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            this.txtMinuto.requestFocus();
        }
    }//GEN-LAST:event_cmbHoraKeyPressed

    private void txtMinutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinutoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            this.registrar();
        }
    }//GEN-LAST:event_txtMinutoKeyPressed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void cmdHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHabilitarActionPerformed
        // TODO add your handling code here:
        this.habilitarCampos();

        this.cmdModificar.setEnabled(true);
    }//GEN-LAST:event_cmdHabilitarActionPerformed

    private void cmdModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdModificarActionPerformed
        // TODO add your handling code here:
        if (!this.validarCampos()) {

            if (!this.validarMinuto()) {
                JOptionPane.showMessageDialog(null, "Los minutos no estan dentro del rango");
                return;
            }

            LoteriaDto dto = this.llenarDatosLoteria();
            boolean valor = this.principal.getFachada().modificarLoteria(this.codigoAnterior, dto);
            if (valor) {
                JOptionPane.showMessageDialog(null, "La loteria se modifico exitosamente");
                this.deshabilitarCampos();
                this.vaciarCampos();

                return;
            }
            JOptionPane.showMessageDialog(null, "La loteria que desea modificar no existe\n"
                    + "o ya tiene asociado planes de loteria");
        } else {
            JOptionPane.showMessageDialog(null, "No puede dejar campos vacios");
        }
    }//GEN-LAST:event_cmdModificarActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        String cadena = (this.txtNombre.getText()).toUpperCase();
        this.txtNombre.setText(cadena);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void cmbRutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRutaKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            this.cmbDia.requestFocus();
        }
    }//GEN-LAST:event_cmbRutaKeyPressed

    private LoteriaDto llenarDatosLoteria() {
        int codigo = Integer.parseInt(this.txtCodigo.getText());
        String nombre = this.txtNombre.getText();
        int dia = this.diaNumerico();
        LoteriaDto dto = new LoteriaDto();
        dto.setCodigo(codigo);
        dto.setNombre(nombre);
        dto.setDia(dia);
        dto.setRuta(this.cmbRuta.getSelectedItem().toString());

        dto.setHora(Integer.parseInt(this.cmbHora.getSelectedItem().toString()));
        dto.setMinuto(Integer.parseInt(this.txtMinuto.getText()));
        return dto;
    }

    public void vaciarCampos() {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtMinuto.setText("");

    }

    private boolean validarMinuto() {
        int minuto = Integer.parseInt(this.txtMinuto.getText());
        if (minuto < 0 || minuto > 59) {
            return false;
        }
        return true;
    }

    /**
     * retorna verdadero si existe un campo vacio y falso si todos estan llenos
     *
     * @return
     */
    private boolean validarCampos() {
        return (this.txtCodigo.getText().isEmpty() || this.txtNombre.getText().isEmpty() || this.cmbDia.getSelectedItem().toString().isEmpty()
                || this.txtMinuto.getText().isEmpty());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbDia;
    private javax.swing.JComboBox cmbHora;
    private javax.swing.JComboBox cmbRuta;
    private javax.swing.JButton cmdHabilitar;
    private javax.swing.JButton cmdModificar;
    private javax.swing.JButton cmdRegistrar;
    private javax.swing.JButton cmdSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtMinuto;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private void vaciarCamposSinCodigo() {

        this.txtNombre.setText("");
        this.txtMinuto.setText("");
    }
}
