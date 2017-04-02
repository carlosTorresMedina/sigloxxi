/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaPersonal;

import ModeloVentas.PersonaDto;
import VistaPrincipal.Principal;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
public class RegistrarPersonaInternalFrame extends javax.swing.JInternalFrame {

    private Principal principal;
    private int cedulaAnterior;
    private int codigoAnterior;

    public RegistrarPersonaInternalFrame(Principal aThis) {
        initComponents();
        this.principal = aThis;
        this.getContentPane().setBackground(Color.white);

        //cargo los combos
        this.cargarComboTipo();
        this.cargarComboActivo();

    }

    public void inHabilitarCampos() {

        this.txtNombre.setEnabled(false);
        this.txtApellido.setEnabled(false);
        this.txtDireccion.setEnabled(false);
        this.txtTelefono.setEnabled(false);
        this.cmbTipo.setEnabled(false);
        this.cmbActivo.setEnabled(false);
        this.txtPorcentaje.setEnabled(false);
        this.cmdModificar.setEnabled(false);
        this.cmdHabilitar.setEnabled(false);
        this.cmbNacionalidad.setEnabled(false);

    }

    public void habilitarCampos() {

        this.txtNombre.setEnabled(true);

        this.txtApellido.setEnabled(true);
        this.txtDireccion.setEnabled(true);
        this.txtTelefono.setEnabled(true);
        this.cmbTipo.setEnabled(true);
        this.cmbActivo.setEnabled(true);
        this.txtPorcentaje.setEnabled(true);
        this.cmdModificar.setEnabled(true);
        this.cmbNacionalidad.setEnabled(true);
    }

    private void registrarPersona() {
        if (!this.validarCampos()) {

            int cedula = Integer.parseInt(this.txtCedula.getText());
            String nombre = this.txtNombre.getText();
            String apellido = this.txtApellido.getText();
            String direccion = this.txtDireccion.getText();
            String telefono = this.txtTelefono.getText();
            int codigo = Integer.parseInt(this.txtCodigo.getText());
            int porcentaje = Integer.parseInt(this.txtPorcentaje.getText());
            String tipo = this.acomodarTipo(this.cmbTipo.getSelectedItem().toString());
            String activo = this.acomodarActivo(this.cmbActivo.getSelectedItem().toString());
            String nacionalidad = this.cmbNacionalidad.getSelectedItem().toString();

            PersonaDto dto = new PersonaDto();

            dto.setNuip(cedula);
            dto.setNombre(nombre);
            dto.setApellido(apellido);
            dto.setDireccion(direccion);
            dto.setTelefono(telefono);
            dto.setCodigo(codigo);
            dto.setPorcentaje(porcentaje);
            dto.setTipo(tipo);
            dto.setActivo(activo);
            dto.setNacionalidad(nacionalidad);
            boolean valor = this.principal.getFachada().registrarPersona(dto);
            if (valor) {
                JOptionPane.showMessageDialog(null, "la persona se registro correctamente");
                this.vaciarCampos();
                this.inHabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "la persona ya se encuentra registrada");
            }
        } else {
            this.MostrarMensaje("los unicos campos que se pueden enviar vacios son "
                    + "el telefono y la direccion");
        }
    }

    private String acomodarTipo(String tipo) {
        if (tipo.equals("Vendedor")) {
            return "V";
        }
        if (tipo.equals("Promotor")) {
            return "P";
        }
        if (tipo.equals("Administrativo")) {
            return "A";
        }
        return null;
    }

    private String acomodarActivo(String activo) {
        if (activo.equals("Si")) {
            return "S";
        }
        if (activo.equals("No")) {
            return "N";
        }
        return null;
    }

    private void MostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * retorna true si existe un campo vacio y false si todos los campos estan
     * llenos
     *
     * @return
     */
    private boolean validarCampos() {
        return (this.txtNombre.getText().isEmpty() || this.txtCedula.getText().isEmpty() || this.txtApellido.getText().isEmpty()
                || this.txtCodigo.getText().isEmpty()
                || this.txtPorcentaje.getText().isEmpty());

    }

    public void vaciarCampos() {
        this.txtNombre.setText("");
        this.txtCedula.setText("");
        this.txtApellido.setText("");
        this.txtDireccion.setText("");
        this.txtTelefono.setText("");
        this.txtCodigo.setText("");
        this.txtPorcentaje.setText("");

    }

    public void vaciarCamposNuevo() {
        this.txtNombre.setText("");
        this.txtApellido.setText("");
        this.txtDireccion.setText("");
        this.txtTelefono.setText("");
        this.txtPorcentaje.setText("");
    }

    private void cargarComboTipo() {
        this.cmbTipo.removeAllItems();

        this.cmbTipo.addItem("Vendedor");
        this.cmbTipo.addItem("Promotor");
        this.cmbTipo.addItem("Administrativo");
    }

    private void cargarComboActivo() {
        this.cmbActivo.removeAllItems();
        this.cmbActivo.addItem("Si");
        this.cmbActivo.addItem("No");

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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox();
        txtCodigo = new javax.swing.JTextField();
        txtPorcentaje = new javax.swing.JTextField();
        cmbActivo = new javax.swing.JComboBox();
        btnRegistrar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cmdHabilitar = new javax.swing.JButton();
        cmdModificar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cmbNacionalidad = new javax.swing.JComboBox();

        setTitle("Registro personas");

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Direccion");

        jLabel5.setText("Telefono");

        jLabel6.setText("Tipo");

        jLabel7.setText("Codigo");

        jLabel8.setText("Porcentaje");

        jLabel9.setText("Activo");

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoKeyReleased(evt);
            }
        });

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipoKeyPressed(evt);
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

        txtPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyTyped(evt);
            }
        });

        cmbActivo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbActivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbActivoKeyPressed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        jLabel10.setText("Nacionalidad");

        cmbNacionalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VENEZUELA", "COLOMBIA" }));
        cmbNacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbNacionalidadKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)))
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbActivo, javax.swing.GroupLayout.Alignment.LEADING, 0, 155, Short.MAX_VALUE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPorcentaje, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo)
                            .addComponent(txtTelefono)
                            .addComponent(cmbNacionalidad, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdHabilitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdModificar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(cmbActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(cmdHabilitar)
                    .addComponent(cmdModificar)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.vaciarCampos();
        this.inHabilitarCampos();
        this.setVisible(false);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void cargarComboTipo(String tipo) {
        this.cmbTipo.removeAllItems();
        if (tipo.equals("V")) {
            this.cmbTipo.addItem("Vendedor");
            this.cmbTipo.addItem("Promotor");
            this.cmbTipo.addItem("Administrativo");
        }
        if (tipo.equals("P")) {
            this.cmbTipo.addItem("Promotor");
            this.cmbTipo.addItem("Vendedor");
            this.cmbTipo.addItem("Administrativo");
        }
        if (tipo.equals("A")) {
            this.cmbTipo.addItem("Administrativo");
            this.cmbTipo.addItem("Promotor");
            this.cmbTipo.addItem("Administrativo");
        }
    }

    private void cargarComboNacionalidad(String nacionalidad) {
        this.cmbNacionalidad.removeAllItems();

        if (nacionalidad == null || nacionalidad.equals("NO SELECCION")) {
            this.cmbNacionalidad.addItem("NO SELECCION");
            this.cmbNacionalidad.addItem("VENEZUELA");
            this.cmbNacionalidad.addItem("COLOMBIA");
        } else if (nacionalidad.equals("VENEZUELA")) {
            this.cmbNacionalidad.addItem("VENEZUELA");
        } else if (nacionalidad.equals("COLOMBIA")) {
            this.cmbNacionalidad.addItem("COLOMBIA");
        }

    }

    private void cargarComboActivo(String activo) {
        this.cmbActivo.removeAllItems();
        if (activo.equals("S")) {
            this.cmbActivo.addItem("Si");
            this.cmbActivo.addItem("No");
        }
        if (activo.equals("N")) {
            this.cmbActivo.addItem("No");
            this.cmbActivo.addItem("Si");
        }
    }

    private void txtCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            ArrayList<PersonaDto> lista = this.principal.getFachada().listarPersonaXCedula(Integer.parseInt(this.txtCedula.getText()));
            if (!lista.isEmpty()) {
                for (PersonaDto p : lista) {
                    this.cedulaAnterior = p.getNuip();
                    this.codigoAnterior = p.getCodigo();
                    this.txtCedula.setText(p.getNuip() + "");
                    this.txtCodigo.setText(p.getCodigo() + "");
                    this.txtNombre.setText(p.getNombre());
                    this.txtApellido.setText(p.getApellido());
                    this.txtDireccion.setText(p.getDireccion());
                    this.txtTelefono.setText(p.getTelefono());
                    this.cargarComboTipo(p.getTipo());
                    this.cargarComboActivo(p.getActivo());
                    this.txtPorcentaje.setText(p.getPorcentaje() + "");
                    this.cargarComboNacionalidad(p.getNacionalidad());

                }

                this.inHabilitarCampos();
                this.cmdHabilitar.setEnabled(true);

            } else {
                this.txtCodigo.setText("");
                this.vaciarCamposNuevo();
                this.cmdHabilitar.setEnabled(false);
                this.cmdModificar.setEnabled(false);
                this.txtCodigo.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCedulaKeyPressed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.txtApellido.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.txtDireccion.requestFocus();
        }
    }//GEN-LAST:event_txtApellidoKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.cmbTipo.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void cmbTipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.cmbActivo.requestFocus();
        }
    }//GEN-LAST:event_cmbTipoKeyPressed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ArrayList<PersonaDto> lista = this.principal.getFachada().listarPersonaXCodigo(Integer.parseInt(this.txtCodigo.getText()));
            if (!lista.isEmpty()) {
                for (PersonaDto p : lista) {
                    this.cedulaAnterior = p.getNuip();
                    this.codigoAnterior = p.getCodigo();
                    this.txtCedula.setText(p.getNuip() + "");
                    this.txtCodigo.setText(p.getCodigo() + "");
                    this.txtNombre.setText(p.getNombre());
                    this.txtApellido.setText(p.getApellido());
                    this.txtDireccion.setText(p.getDireccion());
                    this.txtTelefono.setText(p.getTelefono());
                    this.cargarComboTipo(p.getTipo());
                    this.cargarComboActivo(p.getActivo());
                    this.txtPorcentaje.setText(p.getPorcentaje() + "");
                    this.cargarComboNacionalidad(p.getNacionalidad());

                }
                this.inHabilitarCampos();
                this.cmdHabilitar.setEnabled(true);
                this.txtNombre.requestFocus();

            } else {

                this.vaciarCamposNuevo();
                this.habilitarCampos();
                this.txtNombre.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void cmbActivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbActivoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.txtPorcentaje.requestFocus();
        }
    }//GEN-LAST:event_cmbActivoKeyPressed

    private void txtPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyTyped
        // TODO add your handling code here:
        // TODO add your handling code here:
        int num = evt.getKeyChar();
        String cadena = this.txtPorcentaje.getText();
        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtPorcentajeKeyTyped

    private void txtPorcentajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.registrarPersona();
        }

    }//GEN-LAST:event_txtPorcentajeKeyPressed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        this.registrarPersona();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void cmdHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHabilitarActionPerformed
        // TODO add your handling code here:
        this.habilitarCampos();
    }//GEN-LAST:event_cmdHabilitarActionPerformed

    private void cmdModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdModificarActionPerformed
        // TODO add your handling code here:
        this.modificarPersona();
    }//GEN-LAST:event_cmdModificarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        String cadena = (this.txtNombre.getText()).toUpperCase();
        this.txtNombre.setText(cadena);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
        // TODO add your handling code here:
        String cadena = (this.txtApellido.getText()).toUpperCase();
        this.txtApellido.setText(cadena);
    }//GEN-LAST:event_txtApellidoKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        // TODO add your handling code here:
        String cadena = (this.txtDireccion.getText()).toUpperCase();
        this.txtDireccion.setText(cadena);
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void cmbNacionalidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbNacionalidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNacionalidadKeyPressed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void modificarPersona() {
        if (this.validarCampos()) {
            JOptionPane.showMessageDialog(null, "Los unicos campos que se pueden dejar vacios son la direccion y el telefono");
            return;
        }
        PersonaDto dto = new PersonaDto();
        dto.setNuip(Integer.parseInt(this.txtCedula.getText()));
        dto.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
        dto.setNombre(this.txtNombre.getText());
        dto.setApellido(this.txtApellido.getText());
        dto.setDireccion(this.txtDireccion.getText());
        dto.setTelefono(this.txtTelefono.getText());
        dto.setTipo(this.acomodarTipo(this.cmbTipo.getSelectedItem().toString()));
        dto.setActivo(this.acomodarActivo(this.cmbActivo.getSelectedItem().toString()));
        dto.setPorcentaje(Integer.parseInt(this.txtPorcentaje.getText()));
        dto.setNacionalidad(this.cmbNacionalidad.getSelectedItem().toString());
        boolean valor = this.principal.getFachada().modifcarPersona(this.cedulaAnterior, dto);
        if (valor) {
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");

            this.vaciarCampos();
            this.inHabilitarCampos();

        } else {
            JOptionPane.showMessageDialog(null, "No existe ese usuario registrado en el sistema\n"
                    + "o el usuario al que desea cambiar el codigo ya  tiene asociado ventas");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox cmbActivo;
    private javax.swing.JComboBox cmbNacionalidad;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JButton cmdHabilitar;
    private javax.swing.JButton cmdModificar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPorcentaje;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
