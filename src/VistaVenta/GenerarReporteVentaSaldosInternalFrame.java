/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaVenta;

import ModeloVentas.VentaDto;
import VistaPrincipal.Principal;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlos
 */
public class GenerarReporteVentaSaldosInternalFrame extends javax.swing.JInternalFrame {

    private Principal principal;
    private DefaultTableModel tabla;
    private int totalVentas;
    private int totalComision;
    private int totalRecaudo;
    private int totalSaldo;
    private int totalAbono;
    private int totalTicket;

    private ArrayList<VentaDto> listaVentas;
    int mes;
    String mesCadena;
    int año;
    String moneda;
    String ruta;
    String tipo;

    /**
     * Creates new form GenerarInformeInternalFrame
     */
    public GenerarReporteVentaSaldosInternalFrame(Principal principal) {
        initComponents();

        this.getContentPane().setBackground(Color.white);
        this.principal = principal;
        this.cargarComboMes();

    }

    private void cargarComboMes() {
        this.cmbMes.removeAllItems();

        this.cmbMes.addItem("Enero");
        this.cmbMes.addItem("Febrero");
        this.cmbMes.addItem("Marzo");
        this.cmbMes.addItem("Abril");
        this.cmbMes.addItem("Mayo");
        this.cmbMes.addItem("Junio");
        this.cmbMes.addItem("Julio");
        this.cmbMes.addItem("Agosto");
        this.cmbMes.addItem("Septiembre");
        this.cmbMes.addItem("Octubre");
        this.cmbMes.addItem("Noviembre");
        this.cmbMes.addItem("Diciembre");

    }

    public void inicializarVentana() {
        String tituloTabla[] = {"Codigo persona", "Nombre", "Venta", "Comision", "Abono", "Neto", "Saldo", "Ticket", "Moneda"};
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
        this.totalComision = 0;
        this.totalRecaudo = 0;
        this.totalSaldo = 0;
        this.totalVentas = 0;
        this.totalTicket = 0;
        this.txtTotalAbonos.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalVenta.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalComision.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalSaldo.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalTicket.setHorizontalAlignment(JTextField.RIGHT);
        this.txtAbonos.setHorizontalAlignment(JTextField.RIGHT);
        this.txtTotalAbonos.setText("");
        this.txtTotalComision.setText("");
        this.txtTotalSaldo.setText("");
        this.txtTotalVenta.setText("");
        this.txtTotalTicket.setText("");
        this.txtAno.setEnabled(true);
        this.cmbMes.setEnabled(true);
        this.cmbMoneda.setEnabled(true);
        this.listaVentas = new ArrayList();

        Calendar now = Calendar.getInstance();
        this.txtAno.setText(now.get(Calendar.YEAR) + "");

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
        cmdSalir = new javax.swing.JButton();
        cmdGenerarPdf = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtAno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbMes = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbMoneda = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmdConsultar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cmbRuta = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        cmdGenerarPdf1 = new javax.swing.JButton();
        txtTotalVenta = new javax.swing.JTextField();
        txtTotalComision = new javax.swing.JTextField();
        txtAbonos = new javax.swing.JTextField();
        txtTotalAbonos = new javax.swing.JTextField();
        txtTotalSaldo = new javax.swing.JTextField();
        txtTotalTicket = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setTitle("Reporte ventas saldos");

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo persona", "Nombre", "Venta", "Comision", "Abono", "Neto", "Saldo", "Ticket", "Moneda"
            }
        ));
        tablaVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaVentasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVentas);

        cmdSalir.setText("Salir");
        cmdSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSalirActionPerformed(evt);
            }
        });

        cmdGenerarPdf.setText("Generar pdf saldos positivos");
        cmdGenerarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGenerarPdfActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valores de consulta"));

        txtAno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnoKeyTyped(evt);
            }
        });

        jLabel1.setText("Año: ");

        jLabel2.setText("Mes: ");

        cmbMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bolivares", "Pesos" }));

        jLabel3.setText("Moneda: ");

        cmdConsultar.setText("Consultar");
        cmdConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdConsultarActionPerformed(evt);
            }
        });

        jLabel12.setText("Ruta: ");

        cmbRuta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dia", "Noche" }));

        jLabel13.setText("Tipo: ");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vendedor", "Promotor" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(cmdConsultar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdConsultar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        cmdGenerarPdf1.setText("Generar pdf saldos negativos");
        cmdGenerarPdf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGenerarPdf1ActionPerformed(evt);
            }
        });

        txtTotalVenta.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        txtTotalVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalVentaActionPerformed(evt);
            }
        });

        txtTotalComision.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        txtAbonos.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        txtAbonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbonosActionPerformed(evt);
            }
        });

        txtTotalAbonos.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        txtTotalAbonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalAbonosActionPerformed(evt);
            }
        });

        txtTotalSaldo.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        txtTotalSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalSaldoActionPerformed(evt);
            }
        });

        txtTotalTicket.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel6.setText("Valores totales:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdGenerarPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdGenerarPdf1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalComision, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalVenta)
                    .addComponent(txtTotalComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmdSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdGenerarPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdGenerarPdf1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalVentaActionPerformed

    private void txtTotalAbonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalAbonosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAbonosActionPerformed

    private void cmdSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSalirActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_cmdSalirActionPerformed

    private void txtAnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnoKeyTyped
        // TODO add your handling code here:
        int num = evt.getKeyChar();

        if (num < '0' || num > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtAnoKeyTyped


    private void cmdConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConsultarActionPerformed
        // TODO add your handling code here:

        if ("".equals(this.txtAno.getText())) {
            JOptionPane.showMessageDialog(null, "Debe llenar indicar el año");
            return;
        }
        this.año = Integer.parseInt(this.txtAno.getText());
        if (año < 0) {
            JOptionPane.showMessageDialog(null, "El año debe ser positivo");
            return;
        }
        moneda = this.cmbMoneda.getSelectedItem().toString();
        ruta = this.cmbRuta.getSelectedItem().toString();
        tipo = this.cmbTipo.getSelectedItem().toString();
        this.mesCadena = this.cmbMes.getSelectedItem().toString();
        this.mes = this.principal.getFachada().generarMes(this.cmbMes.getSelectedItem().toString());
        this.listaVentas = this.principal.getFachada().generarReporte(this.mes, this.año, this.moneda, ruta, tipo);
        if (!listaVentas.isEmpty()) {
            this.tabla.setRowCount(0);
            for (VentaDto dto : listaVentas) {
                this.llenarTabla(dto);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existen ventas registradas en los datos escogios.");
        }

    }//GEN-LAST:event_cmdConsultarActionPerformed

    private void llenarTabla(VentaDto dto) {
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        this.totalVentas += dto.getVenta();
        this.totalRecaudo += dto.getRecaudo();
        this.totalSaldo += dto.getSaldo();
        this.totalComision += dto.getComision();
        this.totalAbono += dto.getAbono();
        this.totalTicket += dto.getnTicket();

        this.txtTotalVenta.setText(formatoNumero.format(this.totalVentas));
        this.txtTotalComision.setText(formatoNumero.format(this.totalComision));
        this.txtTotalSaldo.setText(formatoNumero.format(this.totalSaldo));
        this.txtTotalAbonos.setText(formatoNumero.format(this.totalRecaudo));
        this.txtAbonos.setText(formatoNumero.format(this.totalAbono));
        this.txtTotalTicket.setText(formatoNumero.format(this.totalTicket));

        this.tabla.fireTableDataChanged();
        String registro[] = new String[9];

        registro[0] = dto.getPersona().getCodigo() + "";
        registro[1] = dto.getPersona().getNombre() + " " + dto.getPersona().getApellido();
        registro[2] = dto.getVentaFormato() + "";
        registro[3] = dto.getComisionFormato() + "";
        registro[4] = dto.getAbonoFormato() + "";
        registro[5] = dto.getRecaudoFormato() + "";
        registro[6] = dto.getSaldoFormato() + "";
        registro[7] = dto.getnTicketFormato() + "";
        registro[8] = dto.getMoneda() + "";
        this.tabla.addRow(registro);
        this.tabla.fireTableDataChanged();

    }


    private void cmdGenerarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGenerarPdfActionPerformed
        // TODO add your handling code here:
        this.guardarArchivoSaldosPositivos();
    }//GEN-LAST:event_cmdGenerarPdfActionPerformed

    private void tablaVentasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaVentasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaVentasKeyPressed

    private void cmdGenerarPdf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGenerarPdf1ActionPerformed
        // TODO add your handling code here:
        this.guardarArchivoSaldosNegativos();
    }//GEN-LAST:event_cmdGenerarPdf1ActionPerformed

    private void txtTotalSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalSaldoActionPerformed

    private void txtAbonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbonosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonosActionPerformed

    private void guardarArchivoSaldosNegativos() {

        boolean resultado = false;

        resultado = this.principal.getFachada().generarPdfVentasSaldosNegativos(this.listaVentas, "", this.mesCadena, this.moneda, this.ruta, this.tipo);

        if (resultado) {
            JOptionPane.showMessageDialog(null,
                    "El archivo se ha guardado Exitosamente",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "ya existe un documento con ese nombre \n el documento esta en uso");

        }
    }

    private void guardarArchivoSaldosPositivos() {

        boolean resultado = false;

        resultado = this.principal.getFachada().generarPdfVentasSaldosPositivos(this.listaVentas, "", this.mesCadena, this.moneda, this.ruta, this.tipo);

        if (resultado) {
            JOptionPane.showMessageDialog(null,
                    "El archivo se ha guardado Exitosamente",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "ya existe un documento con ese nombre \n el documento esta en uso");
        }
    
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbMes;
    private javax.swing.JComboBox cmbMoneda;
    private javax.swing.JComboBox cmbRuta;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JButton cmdConsultar;
    private javax.swing.JButton cmdGenerarPdf;
    private javax.swing.JButton cmdGenerarPdf1;
    private javax.swing.JButton cmdSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtAbonos;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtTotalAbonos;
    private javax.swing.JTextField txtTotalComision;
    private javax.swing.JTextField txtTotalSaldo;
    private javax.swing.JTextField txtTotalTicket;
    private javax.swing.JTextField txtTotalVenta;
    // End of variables declaration//GEN-END:variables
}