/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaPlanLoteria;

import ModeloLoteria.LoteriaDto;
import ModeloPlanLoteria.PlanLoteriaDto;
import VistaPrincipal.Principal;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;

import java.awt.event.KeyEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
public class RegistrarPlanLoteriaInternalFrame extends javax.swing.JInternalFrame {

    private Principal principal;
    LoteriaDto loteria;
    final long milisegundosxdia = 24 * 60 * 60 * 1000; //Milisegundos al día 

    /**
     * Creates new form RegistrarPlanLoteriaInternalFrame
     */
    public RegistrarPlanLoteriaInternalFrame(Principal p) {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        this.principal = p;

        this.acomodarFechas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        cmbProgramar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtLoteria = new javax.swing.JTextField();
        labelMensaje = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        dateFecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dateCierre = new com.toedter.calendar.JDateChooser();

        setTitle("Registrar plan loteria");

        jLabel2.setText("Loteria: ");

        cmbProgramar.setText("Programar");
        cmbProgramar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProgramarActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtLoteria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoteriaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoteriaKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rango fechas"));

        dateFecha.setDateFormatString("yyyy-MM-dd");

        jLabel1.setText("Fecha inicio: ");

        jLabel3.setText("Fecha fin:");

        dateCierre.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(dateCierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbProgramar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtLoteria, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLoteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbProgramar)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acomodarFechas() {
        Date fecha = new Date();

        this.dateCierre.setDate(fecha);
        this.dateFecha.setDate(fecha);
    }


    private void cmbProgramarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProgramarActionPerformed
        // TODO add your handling code here:
        this.programar();
    }//GEN-LAST:event_cmbProgramarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    public void deshabilitarCampos() {
        this.dateCierre.setEnabled(false);

    }

    public void habilitarCampos() {
        this.dateCierre.setEnabled(true);

    }

    private void txtLoteriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteriaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String nombreLoteria = this.txtLoteria.getText();
            if (nombreLoteria.isEmpty()) {
                this.labelMensaje.setText("");
                return;
            }
            String v[] = nombreLoteria.split("-");
            ArrayList<LoteriaDto> listaLoteria = new ArrayList();

            try {

                listaLoteria = this.principal.getFachada().listarLoteriaXCodigo(Integer.parseInt(v[0]));
                if (listaLoteria.isEmpty()) {
                    this.labelMensaje.setText("");
                    JOptionPane.showMessageDialog(null, "Esa loteria no existe");
                    return;
                }

                this.loteria = listaLoteria.get(0);
                this.labelMensaje.setText("Juega: " + this.loteria.getDiaFormato() + " " + this.loteria.getHora() + ":" + this.loteria.getMinuto());
                this.habilitarCampos();

            } catch (NumberFormatException e) {
                e.printStackTrace();
                this.labelMensaje.setText("");
                JOptionPane.showMessageDialog(null, "Esa loteria no existe\n"
                        + "Debe digitar el codigo");
            }
        }
    }//GEN-LAST:event_txtLoteriaKeyPressed

    private void txtLoteriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteriaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoteriaKeyReleased

    private ArrayList<Date> crearRangoFechas(Date finicial, Date ffinal) {

        ArrayList<Date> rango = new ArrayList();
        long dias = (ffinal.getTime() - finicial.getTime()) / milisegundosxdia;
        int i = 0;

        while (dias >= 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(finicial);

            calendar.add(Calendar.DAY_OF_YEAR, i);
            rango.add(calendar.getTime());
            i++;
            dias--;
        }
        return rango;
    }

    /**
     * realiza la programacion de loteria en base al rango estipulado
     */
    private void programar() {
        try {
            if (!this.validarCampos()) {
                Date finicial = this.dateFecha.getDate();
                finicial.setHours(this.loteria.getHora());
                finicial.setMinutes(this.loteria.getMinuto());
                Date ffinal = this.dateCierre.getDate();
                ffinal.setHours(this.loteria.getHora());
                ffinal.setMinutes(this.loteria.getMinuto());

                if (finicial.compareTo(ffinal) > 0) {
                    JOptionPane.showMessageDialog(null, "la fecha inicial no puede ser mayor a la final");
                    return;
                }
                ArrayList<Date> rango = this.crearRangoFechas(finicial, ffinal);
                ArrayList<PlanLoteriaDto> rangoFinal = new ArrayList();
                for (Date fecha : rango) {
                    if (this.loteria.getDia() == -1) {
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                        String fechaBase = formato.format(fecha);

                        SimpleDateFormat formato2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                        String fechaHora = formato2.format(fecha);

                        PlanLoteriaDto dto = new PlanLoteriaDto();
                        dto.setCodigoLoteria(this.loteria.getCodigo());
                        dto.setFecha(fechaBase);
                        dto.setCierre(fechaHora);
                        dto.setCerrado("N");
                        dto.setEscrutado("N");
                        rangoFinal.add(dto);
                    } else if (fecha.getDay() == this.loteria.getDia()) {

                        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                        String fechaBase = formato.format(fecha);

                        SimpleDateFormat formato2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                        String fechaHora = formato2.format(fecha);

                        PlanLoteriaDto dto = new PlanLoteriaDto();
                        dto.setCodigoLoteria(this.loteria.getCodigo());
                        dto.setFecha(fechaBase);
                        dto.setCierre(fechaHora);
                        rangoFinal.add(dto);
                    }
                }
                this.loteria.setPlanLoterias(rangoFinal);
                this.registrar();

            } else {
                JOptionPane.showMessageDialog(null, "No puede dejar campos vacios");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Esa plan loteria no existe\n"
                    + "Debe digitar el codigo");

        }
    }

    private void registrar() {
        String valor = this.principal.getFachada().registrarPlanLoteria(this.loteria);
        JOptionPane.showMessageDialog(null, valor);

    }

    private boolean validarCampos() {
        return (this.dateFecha.getDate() == null || this.dateCierre.getDate() == null || this.txtLoteria.getText().isEmpty());
    }

    private void vaciarCampos() {
        this.txtLoteria.setText("");
        this.acomodarFechas();
    }

    public void cargarComboLoteria() {

        TextAutoCompleter text = new TextAutoCompleter(this.txtLoteria);
        ArrayList<LoteriaDto> lista = this.principal.getFachada().listarLoterias();
        for (LoteriaDto dto : lista) {

            text.addItem(dto.getCodigoNombre());

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmbProgramar;
    private com.toedter.calendar.JDateChooser dateCierre;
    private com.toedter.calendar.JDateChooser dateFecha;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelMensaje;
    private javax.swing.JTextField txtLoteria;
    // End of variables declaration//GEN-END:variables
}
