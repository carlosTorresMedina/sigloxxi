/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaVenta;

import ModeloVentas.PersonaDto;
import ModeloVentas.VentaDto;
import VistaPrincipal.Principal;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lenovo
 */
public class RegistrarVentaInternalFrame extends javax.swing.JInternalFrame {

    private Principal principal;
    private DefaultTableModel tabla;
    private int totalVenta;
    private int totalRecaudo;
    private int totalSaldo;
    private int totalComision;
    private int totalAbono;
    private int totalTicket;
    private LinkedList<VentaDto> listaRegistrosTabla;

    //esta variable indica al frame si permite registrar pesos o bolivares
    private String monedaFrame;
    private PersonaDto persona;
    private String ruta;
    private String tipo;

    //esta variable indica la venta a la cual se le va a modificar los datos
    private VentaDto venta;

    /**
     * Creates new form RegistrarVentaInternalFrame
     */
    public RegistrarVentaInternalFrame(Principal principal) {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.principal = principal;
        this.acomodarFecha();

    }

    public void inicializarVentana() {
        this.listaRegistrosTabla = new LinkedList();
        String tituloTabla[] = {"fecha", "Codigo persona", "Venta", "Comision", "Abono", "Neto", "Saldo", "Ticket", "Moneda"};
        this.tabla = new DefaultTableModel(null, tituloTabla);
        this.tablaVentas.setModel(tabla);
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.RIGHT);
        this.tablaVentas.getColumnModel().getColumn(2).setCellRenderer(Alinear);
        this.tablaVentas.getColumnModel().getColumn(3).setCellRenderer(Alinear);
        this.tablaVentas.getColumnModel().getColumn(4).setCellRenderer(Alinear);
        this.tablaVentas.getColumnModel().getColumn(5).setCellRenderer(Alinear);
        this.tablaVentas.getColumnModel().getColumn(6).setCellRenderer(Alinear);
        this.tablaVentas.getColumnModel().getColumn(7).setCellRenderer(Alinear);
        this.txtTotalbonos.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalVenta.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalComision.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalSaldo.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalTicket.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalAbono.setHorizontalAlignment(JTextField.RIGHT);
        this.vaciarCampos();
        this.inhabilitarCampos();

    }

    
    public void vaciarCamposSinPersona(){
       this.txtComision.setText("");
        this.txtVenta.setText("");
        this.txtRecaudo.setText("");
        this.txtAbono.setText("");
        this.txtnTicket.setText("");
    
    }
    
    public void vaciarCampos() {
        this.txtCodigoPersona.setText("");
        this.txtComision.setText("");
        this.txtVenta.setText("");
        this.txtRecaudo.setText("");
        this.txtAbono.setText("");
        this.txtnTicket.setText("");
        

    }

    private void acomodarFecha() {
        Date hoy = new Date();
        this.dateFecha.setDate(hoy);
    }

    public void inhabilitarCampos() {

        this.txtVenta.setEnabled(false);
        this.txtRecaudo.setEnabled(false);
        this.txtAbono.setEnabled(false);
        this.txtnTicket.setEnabled(false);

        this.txtTotalVenta.setText("");
        this.txtTotalComision.setText("");
        this.txtTotalSaldo.setText("");
        this.txtTotalbonos.setText("");
        this.txtTotalTicket.setText("");
        this.txtTotalAbono.setText("");

    }

    private void inhabilitarCamposModificar() {
        this.txtVenta.setEnabled(false);
        this.txtRecaudo.setEnabled(false);
        this.txtAbono.setEnabled(false);
        this.txtnTicket.setEnabled(false);

    }

    private void inhabilitarCamposSinFecha() {
        this.txtTotalVenta.setText("");
        this.txtTotalComision.setText("");
        this.txtTotalSaldo.setText("");
        this.txtComision.setText("");
        this.txtTotalTicket.setText("");
        this.txtVenta.setEnabled(false);
        this.txtRecaudo.setEnabled(false);
        this.txtAbono.setEnabled(false);
        this.txtnTicket.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtRecaudo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtComision = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtVenta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoPersona = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbMoneda = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        dateFecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtAbono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cmbRuta = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        cmdConsultar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtnTicket = new javax.swing.JTextField();
        txtTotalVenta = new javax.swing.JTextField();
        txtTotalComision = new javax.swing.JTextField();
        txtTotalAbono = new javax.swing.JTextField();
        txtTotalbonos = new javax.swing.JTextField();
        txtTotalSaldo = new javax.swing.JTextField();
        txtTotalTicket = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setTitle("Registro ventas");

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Codigo persona", "Venta", "Comision", "Abono", "Neto", "Saldo", "Ticket", "Moneda"
            }
        ));
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVentas);

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valores de registro"));

        txtRecaudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRecaudoActionPerformed(evt);
            }
        });
        txtRecaudo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRecaudoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRecaudoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRecaudoKeyTyped(evt);
            }
        });

        jLabel4.setText("Neto");

        txtComision.setEnabled(false);

        jLabel5.setText("Comision");

        txtVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVentaKeyTyped(evt);
            }
        });

        jLabel3.setText("Venta");

        txtCodigoPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoPersonaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoPersonaKeyTyped(evt);
            }
        });

        jLabel2.setText("Codigo persona");

        cmbMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bolivares", "Pesos" }));
        cmbMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMonedaActionPerformed(evt);
            }
        });

        jLabel9.setText("Moneda");

        dateFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateFechaKeyPressed(evt);
            }
        });

        jLabel1.setText("Fecha");

        jLabel11.setText("Abono");

        txtAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbonoActionPerformed(evt);
            }
        });
        txtAbono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAbonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbonoKeyTyped(evt);
            }
        });

        jLabel12.setText("Ruta: ");

        cmbRuta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dia", "Noche" }));

        jLabel13.setText("Tipo: ");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vendedor", "Promotor" }));

        cmdConsultar.setText("Consultar las ventas.");
        cmdConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdConsultarActionPerformed(evt);
            }
        });

        jLabel16.setText("Ticket");

        txtnTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnTicketActionPerformed(evt);
            }
        });
        txtnTicket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnTicketKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnTicketKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnTicketKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdConsultar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtCodigoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRecaudo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(txtnTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 23, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(cmbRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRecaudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        txtTotalVenta.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        txtTotalComision.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        txtTotalAbono.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        txtTotalbonos.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        txtTotalSaldo.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        txtTotalTicket.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel6.setText("Total valores: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalComision, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalAbono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.totalComision = 0;
        this.totalRecaudo = 0;
        this.totalSaldo = 0;
        this.totalVenta = 0;
        this.monedaFrame = "";
        this.totalAbono=0;
        this.setVisible(false);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void consultarVentasFecha() {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fecha = formato.format(this.dateFecha.getDate());
        String moneda = this.cmbMoneda.getSelectedItem().toString();
        String ruta = this.cmbRuta.getSelectedItem().toString();
        String tipo = this.cmbTipo.getSelectedItem().toString();

        ArrayList<VentaDto> lista = this.principal.getFachada().consultarVentasFecha(fecha, moneda, ruta, tipo);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No existen ventas registrados en los \n"
                    + "los datos estipulados");
            return;
        }
        this.totalComision = 0;
        this.totalRecaudo = 0;
        this.totalSaldo = 0;
        this.totalVenta = 0;
        this.totalAbono = 0;
        this.totalTicket = 0;
        this.monedaFrame = this.cmbMoneda.getSelectedItem().toString();
        this.ruta = this.cmbRuta.getSelectedItem().toString();
        this.tipo = this.cmbTipo.getSelectedItem().toString();
        this.listaRegistrosTabla = new LinkedList();
        this.tabla.setRowCount(0);

        for (VentaDto dto : lista) {
            this.llenarTabla(dto);
        }
    }

    private void calcularComision() {
        String ventaS = this.txtVenta.getText().replace(".", "");
        int venta = Integer.parseInt(ventaS);
        int comision = (venta * this.persona.getPorcentaje()) / 100;
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        String form = formatoNumero.format(comision);
        this.txtComision.setText(form);
    }

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        // TODO add your handling code here:
        if (this.tablaVentas.getSelectedRow() >= 0) {

            int seleccion = this.ventana();
            if (seleccion == 0) {

                int codigo = Integer.parseInt(this.tablaVentas.getValueAt(this.tablaVentas.getSelectedRow(), 1).toString());
                String fecha = this.tablaVentas.getValueAt(this.tablaVentas.getSelectedRow(), 0).toString();
                String moneda = this.cmbMoneda.getSelectedItem().toString();
                String ruta = this.cmbRuta.getSelectedItem().toString();
                String tipo = this.cmbTipo.getSelectedItem().toString();
                boolean valor = this.principal.getFachada().eliminarVenta(fecha, codigo, moneda, ruta, tipo);
                if (valor) {
                    this.acomodarTotalesEliminar();
                    this.tabla.removeRow(this.tablaVentas.getSelectedRow());

                } else {
                    JOptionPane.showMessageDialog(null, "existe un error al eliminar la venta");

                }
            }
        }
    }//GEN-LAST:event_tablaVentasMouseClicked

    private void acomodarTotalesEliminar() {
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        this.totalVenta -= Integer.parseInt(this.tablaVentas.getValueAt(this.tablaVentas.getSelectedRow(), 2).toString().replace(".", ""));
        this.totalComision -= Integer.parseInt(this.tablaVentas.getValueAt(this.tablaVentas.getSelectedRow(), 3).toString().replace(".", ""));
        this.totalAbono -= Integer.parseInt(this.tablaVentas.getValueAt(this.tablaVentas.getSelectedRow(), 4).toString().replace(".", ""));
        this.totalRecaudo -= Integer.parseInt(this.tablaVentas.getValueAt(this.tablaVentas.getSelectedRow(), 5).toString().replace(".", ""));
        this.totalSaldo -= Integer.parseInt(this.tablaVentas.getValueAt(this.tablaVentas.getSelectedRow(), 6).toString().replace(".", ""));
        this.totalTicket -= Integer.parseInt(this.tablaVentas.getValueAt(this.tablaVentas.getSelectedRow(), 8).toString().replace(".", ""));

        this.txtTotalVenta.setText(formatoNumero.format(this.totalVenta));
        this.txtTotalComision.setText(formatoNumero.format(this.totalComision));
        this.txtTotalSaldo.setText(formatoNumero.format(this.totalSaldo));
        this.txtTotalbonos.setText(formatoNumero.format(this.totalRecaudo));
        this.txtTotalTicket.setText(formatoNumero.format(this.totalTicket));
        this.txtTotalAbono.setText(formatoNumero.format(this.totalAbono));

    }

    private int ventana() {
        int seleccion = JOptionPane.showOptionDialog(
                rootPane,
                "desea eliminar la venta ",
                "Selector de opciones ",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{" si", "Cancelar"},
                "opcion 1");
        return seleccion;
    }

    private void cmdConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConsultarActionPerformed
        // TODO add your handling code here:
        this.consultarVentasFecha();
    }//GEN-LAST:event_cmdConsultarActionPerformed

    private void txtAbonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtAbonoKeyTyped

    private void txtAbonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoKeyReleased
        // TODO add your handling code here:
        if (!this.txtAbono.getText().isEmpty()) {
            NumberFormat formatoNumero = NumberFormat.getNumberInstance();
            String cadena = this.txtAbono.getText().replace(".", "");
            this.txtAbono.setText(formatoNumero.format(Integer.parseInt(cadena)));
        }
    }//GEN-LAST:event_txtAbonoKeyReleased

    private void txtAbonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (venta != null) {
                this.modificarVenta();
            } else {
                this.txtnTicket.requestFocus();

            }
        }
    }//GEN-LAST:event_txtAbonoKeyPressed

    private void modificarVenta() {
        int abonoAux=venta.getAbono();
        int abono = Integer.parseInt(this.txtAbono.getText().replace(".", ""));
        venta.setAbono(abono);
        boolean valor = this.principal.getFachada().modificarVentaAbono(venta);
        if (valor) {
            if (!estaEnTabla()) {
                this.llenarTabla(venta);
                
            }else{
                  NumberFormat formatoNumero = NumberFormat.getNumberInstance();
            this.totalAbono=this.totalAbono-abonoAux;
            this.totalAbono+=abono;
             this.txtTotalAbono.setText(formatoNumero.format(this.totalAbono));
            
            }
            this.vaciarCampos();
            this.txtCodigoPersona.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar la venta");
        }
        this.venta = null;
    }

    private boolean estaEnTabla() {
        int filas = this.tablaVentas.getRowCount();
        String fecha = "";
        String codigo = "";
        for (int i = 0; i < filas; i++) {
            
            fecha = this.tablaVentas.getValueAt(i, 0).toString();
            codigo = this.tablaVentas.getValueAt(i, 1).toString();
          System.out.println(venta.getPersona().getCodigo()+"-"+venta.getFecha()+" ->"+codigo+"-"+fecha);
            if (codigo.equalsIgnoreCase(venta.getPersona().getCodigo()+"") && fecha.equalsIgnoreCase(venta.getFecha())) {
                  
                this.tablaVentas.setValueAt(venta.getAbonoFormato(), i, 4);
                return true;
            }
        }
        return false;
    }

    private void txtAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonoActionPerformed

    private void dateFechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateFechaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.txtCodigoPersona.requestFocus();
        }
    }//GEN-LAST:event_dateFechaKeyPressed

    private void cmbMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMonedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMonedaActionPerformed

    private void txtCodigoPersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPersonaKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoPersonaKeyTyped

    private void txtCodigoPersonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPersonaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.txtCodigoPersona.getText().equals("")) {
                return;
            }
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formato.format(this.dateFecha.getDate());
            int codigo = Integer.parseInt(this.txtCodigoPersona.getText());
            String tipo = this.cmbTipo.getSelectedItem().toString();
            String moneda = this.cmbMoneda.getSelectedItem().toString();
            String ruta = this.cmbRuta.getSelectedItem().toString();
            //consulto si el cliente existe por el codigo y tipo.
            ArrayList<PersonaDto> lista = this.principal.getFachada().listarPersonaXCodigoTipo(codigo, tipo);
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No existe una persona con ese codigo para el tipo escogido");
                return;
            }
            this.vaciarCamposSinPersona();
            this.habilitarCampos();
            this.persona = lista.get(0);
            //consulto si esa venta ya se ha registrado en la fecha, moneda ruta y tipo especifico.
            this.venta = new VentaDto();
            venta.setPersona(persona);
            venta.setFecha(fecha);
            venta.setTipo(tipo);
            venta.setMoneda(moneda);
            venta.setRuta(ruta);
            //obtengo la venta con los datos del abono
            venta = this.principal.getFachada().consultarVentaEspecifica(venta);
            if (venta != null) {
                this.acomodarModifcar();
            } else {
                this.txtVenta.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoPersonaKeyPressed

    private void acomodarModifcar() {
        this.txtVenta.setText(venta.getVentaFormato());

        this.txtComision.setText(venta.getComisionFormato());
        this.txtRecaudo.setText(venta.getRecaudoFormato());
        this.txtnTicket.setText(venta.getnTicketFormato());
        this.txtAbono.setText(venta.getAbonoFormato());
        this.inhabilitarCamposModificar();
        this.txtAbono.setEnabled(true);
        this.txtAbono.requestFocus();
    }

    private void txtVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentaKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtVentaKeyTyped

    private void txtVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentaKeyReleased
        // TODO add your handling code here:
        if (!this.txtVenta.getText().isEmpty()) {
            NumberFormat formatoNumero = NumberFormat.getNumberInstance();
            String cadena = this.txtVenta.getText().replace(".", "");
            this.txtVenta.setText(formatoNumero.format(Integer.parseInt(cadena)));
        }
    }//GEN-LAST:event_txtVentaKeyReleased

    private void txtVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.calcularComision();
            this.txtRecaudo.requestFocus();
        }
    }//GEN-LAST:event_txtVentaKeyPressed

    private void txtRecaudoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecaudoKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtRecaudoKeyTyped

    private void txtRecaudoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecaudoKeyReleased
        // TODO add your handling code here:
        if (!this.txtRecaudo.getText().isEmpty()) {
            NumberFormat formatoNumero = NumberFormat.getNumberInstance();
            String cadena = this.txtRecaudo.getText().replace(".", "");
            this.txtRecaudo.setText(formatoNumero.format(Integer.parseInt(cadena)));
        }
    }//GEN-LAST:event_txtRecaudoKeyReleased

    private void txtRecaudoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecaudoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.txtAbono.requestFocus();
        }
    }//GEN-LAST:event_txtRecaudoKeyPressed

    private void txtRecaudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRecaudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRecaudoActionPerformed

    private void txtnTicketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnTicketKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.tablaVentas.getRowCount() > 0) {
                if (this.monedaFrame.equals(this.cmbMoneda.getSelectedItem().toString())
                        && this.ruta.equals(this.cmbRuta.getSelectedItem().toString())
                        && this.tipo.equals(this.cmbTipo.getSelectedItem().toString())) {
                    this.registrarVenta();
                } else {
                    JOptionPane.showMessageDialog(null, "No puede registrar una venta con una moneda diferente\n"
                            + "Una ruta diferente o un tipo diferente al primer registro que realizo");
                }
            } else {

                this.registrarVenta();
                this.monedaFrame = this.cmbMoneda.getSelectedItem().toString();
                this.ruta = this.cmbRuta.getSelectedItem().toString();
                this.tipo = this.cmbTipo.getSelectedItem().toString();
            }

        }
    }//GEN-LAST:event_txtnTicketKeyPressed

    private void txtnTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnTicketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnTicketActionPerformed

    private void txtnTicketKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnTicketKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtnTicketKeyTyped

    private void txtnTicketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnTicketKeyReleased
        // TODO add your handling code here:
        if (!this.txtnTicket.getText().isEmpty()) {
            NumberFormat formatoNumero = NumberFormat.getNumberInstance();
            String cadena = this.txtnTicket.getText().replace(".", "");
            this.txtnTicket.setText(formatoNumero.format(Integer.parseInt(cadena)));
        }

    }//GEN-LAST:event_txtnTicketKeyReleased

    private void registrarVenta() {

        if (!this.validarCampos()) {
            int codigo = Integer.parseInt(this.txtCodigoPersona.getText());
            ArrayList<PersonaDto> lista = this.principal.getFachada().listarPersonaXCodigoTipo(codigo, this.cmbTipo.getSelectedItem().toString());
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El codigo de la persona no hace para al tipo escogido");
                return;
            }
            VentaDto dto = new VentaDto();
            String venta = this.txtVenta.getText().replace(".", "");
            String recaudo = this.txtRecaudo.getText().replace(".", "");
            String comision = this.txtComision.getText().replace(".", "");

            if (this.txtnTicket.getText().equals("")) {
                dto.setnTicket(0);
            } else {
                String ticket = this.txtnTicket.getText().replace(".", "");
                dto.setnTicket(Integer.parseInt(ticket));
            }

            if (this.txtAbono.getText().equals("")) {
                dto.setAbono(0);
            } else {
                String abono = this.txtAbono.getText().replace(".", "");
                dto.setAbono(Integer.parseInt(abono));
            }

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formato.format(this.dateFecha.getDate());

            dto.setFecha(fecha);
            dto.setVenta(Integer.parseInt(venta));
            dto.setRecaudo(Integer.parseInt(recaudo));
            dto.setPersona(persona);
            dto.setComision(Integer.parseInt(comision));

            dto.setRuta(this.cmbRuta.getSelectedItem().toString());
            dto.setTipo(this.cmbTipo.getSelectedItem().toString());
            dto.setMoneda(this.cmbMoneda.getSelectedItem().toString());

            boolean valor = this.principal.getFachada().registrarVenta(dto);

            if (valor) {
                this.txtCodigoPersona.requestFocus();
                this.vaciarCampos();
                this.inhabilitarCamposSinFecha();
                this.llenarTabla(dto);

            } else {
                JOptionPane.showMessageDialog(null, "error en el registro de la venta \n"
                        + " debido a que ya se ha registrado una venta con ese codigo de la persona en la fecha  ");
            }

        } else {
            JOptionPane.showMessageDialog(null, "no puede dejar campos vacios");
        }
    }

    private void llenarTabla(VentaDto dto) {
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        this.totalVenta += dto.getVenta();
        this.totalRecaudo += dto.getRecaudo();
        this.totalSaldo += dto.getSaldo();
        this.totalComision += dto.getComision();
        this.totalAbono += dto.getAbono();
        this.totalTicket += dto.getnTicket();

        this.txtTotalVenta.setText(formatoNumero.format(this.totalVenta));
        this.txtTotalComision.setText(formatoNumero.format(this.totalComision));
        this.txtTotalSaldo.setText(formatoNumero.format(this.totalSaldo));
        this.txtTotalbonos.setText(formatoNumero.format(this.totalRecaudo));
        this.txtTotalTicket.setText(formatoNumero.format(this.totalTicket));
        this.txtTotalAbono.setText(formatoNumero.format(this.totalAbono));
        this.tabla.setRowCount(0);
        this.tabla.fireTableDataChanged();

        this.listaRegistrosTabla.addFirst(dto);

        for (VentaDto dos : this.listaRegistrosTabla) {
            String registros[] = new String[9];
            registros[0] = dos.getFecha() + "";
            registros[1] = dos.getPersona().getCodigo() + "";
            registros[2] = dos.getVentaFormato() + "";
            registros[3] = dos.getComisionFormato() + "";
            registros[4] = dos.getAbonoFormato() + "";
            registros[5] = dos.getRecaudoFormato() + "";
            registros[6] = dos.getSaldoFormato() + "";
            registros[7] = dos.getnTicketFormato() ;
            registros[8] = dos.getMoneda() ;;
            this.tabla.addRow(registros);
        }

        this.tabla.fireTableDataChanged();

    }

    /**
     * retorna verdadero si existe un campo vacio y falso si todos estan llenos
     *
     * @return
     */
    private boolean validarCampos() {
        return (this.txtCodigoPersona.getText().isEmpty() || this.txtComision.getText().isEmpty() || this.txtVenta.getText().isEmpty()
                || this.txtRecaudo.getText().isEmpty() || this.dateFecha.getDate() == null);
    }

    private void habilitarCampos() {

        this.txtVenta.setEnabled(true);
        this.txtRecaudo.setEnabled(true);
        this.txtAbono.setEnabled(true);
        this.txtnTicket.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbMoneda;
    private javax.swing.JComboBox cmbRuta;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JButton cmdConsultar;
    private com.toedter.calendar.JDateChooser dateFecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtAbono;
    private javax.swing.JTextField txtCodigoPersona;
    private javax.swing.JTextField txtComision;
    private javax.swing.JTextField txtRecaudo;
    private javax.swing.JTextField txtTotalAbono;
    private javax.swing.JTextField txtTotalComision;
    private javax.swing.JTextField txtTotalSaldo;
    private javax.swing.JTextField txtTotalTicket;
    private javax.swing.JTextField txtTotalVenta;
    private javax.swing.JTextField txtTotalbonos;
    private javax.swing.JTextField txtVenta;
    private javax.swing.JTextField txtnTicket;
    // End of variables declaration//GEN-END:variables
}
