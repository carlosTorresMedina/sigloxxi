/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaPrincipal;

import VistaVenta.RegistrarVentaInternalFrame;
import VistaPersonal.RegistrarPersonaInternalFrame;

import VistaPersonal.ConsultarPersonaInternalFrame;
import Fachada.Fachada;
import VistaInformeGeneral.VistaInformeGeneral;

import VistaInformeGeneral.VistaInformeGeneralVenta;
import VistaLoteria.ConsultarLoteriaInternalFrame;

import VistaLoteria.RegistrarLoteriaInternalFrame;
import VistaPlanLoteria.ConsultarPlanLoteriaInternalFrame;
import VistaPlanLoteria.RegistrarPlanLoteriaInternalFrame;
import VistaPrestamos.InformePrestamos;
import VistaPrestamos.PagoInternalFrame;
import VistaPrestamos.PrestamoInternalFrame;
import VistaTipoPremio.ConsultarTipoPremioInternalFrame;

import VistaTipoPremio.RegistrarTipoPremioInternalFrame;
import VistaVenta.GenerarReporteVentaInternalFrame;
import VistaVenta.GenerarReporteVentaPersonaInternalFrame1;
import VistaVenta.GenerarReporteVentaSaldosInternalFrame;
import VistaVenta.GenerarSaldosInternalFrame;
import VistaVenta.RegistrarSaldosInicialesInternalFrame;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import vistaPremios.ConsultarPremioIndividualInternalFrame;

import vistaPremios.ConsultarPremiosInternalFrame;
import vistaPremios.ConsultarVentasChanceInternalFrame;
import vistaPremios.RegistrarPremios2InternalFrame;

/**
 *
 * @author lenovo
 */
public class Principal extends javax.swing.JFrame {

    //objeto modelo me permite acceder a la logica del negocio
    private Fachada fachada;

    //pantallas getionn personas
    private RegistrarPersonaInternalFrame registrarpersonaJInternalFrame;
    private ConsultarPersonaInternalFrame consultarpersonaJInternalFrame;

    //pantalla gestion de ventas
    private RegistrarVentaInternalFrame registrarventaInternaFrame;
    private GenerarReporteVentaInternalFrame generarreporteventaInternalFrame;
    private GenerarSaldosInternalFrame generarsaldosInternalFrame;
    private RegistrarSaldosInicialesInternalFrame registrarsaldosinicialesInternalFrame;//este objeto se crea en el momento que es llamado
    private GenerarReporteVentaSaldosInternalFrame generarreporteventasaldoInternalFrame;
    private GenerarReporteVentaPersonaInternalFrame1 generarreporteventapersonaInternalFrame;

    //pantalla gestion loteria
    private RegistrarLoteriaInternalFrame registrarloteriaInternalFrame;//este objeto se crea en el momento que es llamado
    private ConsultarLoteriaInternalFrame consultarloteriaInternalFrame;

    //pantalla gestion plan loteria 
    private RegistrarPlanLoteriaInternalFrame registrarplanloteriaInternalFrame;//este objeto se crea en el momento de ser llamada
    private ConsultarPlanLoteriaInternalFrame consultarplanloteriaInternalFrame;

    //pantalla gestion tipo premio
    private RegistrarTipoPremioInternalFrame registrartipopremioInternalFrame;
    private ConsultarTipoPremioInternalFrame consultartipopremioInternalFrame;
    

    //pantalla gestion premio
    private RegistrarPremios2InternalFrame registrarpremios2InternalFrame;//este objeto se crea en el momento de ser llamado
    private ConsultarPremiosInternalFrame consultarpremiosInternalFrame;
    private ConsultarVentasChanceInternalFrame consultarventaschanceInternalFrame;
    private ConsultarPremioIndividualInternalFrame consultarpremioindividualinternalframe;//este objeto se crea en el momento de ser llamado
    
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setContentPane(this.panel);
        this.fachada = new Fachada();

        //creo las pantallas
        this.generarPantallasModuloPersonas();
        this.generarPantallasVentas();
        this.generarPantallasModuloLoteria();
        this.generarPantallasModuloPlanLoteria();
        this.generarPantallasModuloTipoPremio();
        this.generarPantallasModuloPremios();
    }

    public Fachada getFachada() {
        return fachada;
    }

    /**
     * genera las pantallas estaticas del modulo de premios
     */
    public void generarPantallasModuloPremios() {
        this.consultarpremiosInternalFrame = new ConsultarPremiosInternalFrame(this);
        this.consultarventaschanceInternalFrame = new ConsultarVentasChanceInternalFrame(this);

        this.add(this.consultarpremiosInternalFrame);
        this.add(this.consultarventaschanceInternalFrame);

    }

    /**
     * genera las pantallas estaticas del modulo de tipo premio
     */
    public void generarPantallasModuloTipoPremio() {
        this.registrartipopremioInternalFrame = new RegistrarTipoPremioInternalFrame(this);
        this.consultartipopremioInternalFrame = new ConsultarTipoPremioInternalFrame(this);

        this.add(this.registrartipopremioInternalFrame);
        this.add(this.consultartipopremioInternalFrame);

    }

    /**
     * genera las pantallas estaticas del modulo plan loteria
     */
    public void generarPantallasModuloPlanLoteria() {

        this.consultarplanloteriaInternalFrame = new ConsultarPlanLoteriaInternalFrame(this);
        this.add(this.consultarplanloteriaInternalFrame);
    }

    /**
     * genera las pantallas estaticas del modulo loteria
     */
    public void generarPantallasModuloLoteria() {

        this.consultarloteriaInternalFrame = new ConsultarLoteriaInternalFrame(this);
        this.add(this.consultarloteriaInternalFrame);

    }

    private void generarPantallasModuloPersonas() {
        this.registrarpersonaJInternalFrame = new RegistrarPersonaInternalFrame(this);
        this.consultarpersonaJInternalFrame = new ConsultarPersonaInternalFrame(this);

        this.add(this.registrarpersonaJInternalFrame);
        this.add(this.consultarpersonaJInternalFrame);

    }

    /**
     * genera las pantallas estaticas del modulo ventas
     */
    private void generarPantallasVentas() {
        this.registrarventaInternaFrame = new RegistrarVentaInternalFrame(this);
        this.generarreporteventaInternalFrame = new GenerarReporteVentaInternalFrame(this);
        this.generarsaldosInternalFrame = new GenerarSaldosInternalFrame(this);
        this.generarreporteventasaldoInternalFrame = new GenerarReporteVentaSaldosInternalFrame(this);

        this.add(this.registrarventaInternaFrame);
        this.add(this.generarreporteventaInternalFrame);
        this.add(this.generarsaldosInternalFrame);
        this.add(this.generarreporteventasaldoInternalFrame);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        panel = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenuItem4.setText("jMenuItem4");

        jMenu8.setText("jMenu8");

        jMenuItem15.setText("jMenuItem15");

        jMenuItem17.setText("jMenuItem17");

        jMenuItem21.setText("jMenuItem21");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Pantalla principal");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        jMenu9.setText("Principal");

        jMenu2.setText("Gestion personas");

        jMenuItem1.setText("Registrar persona");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setText("Consultar persona");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenu9.add(jMenu2);

        jMenu4.setText("Gestion loteria");

        jMenuItem7.setText("Registrar loteria");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Consultar loteria");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenu9.add(jMenu4);

        jMenu5.setText("Gestor plan loteria");

        jMenuItem11.setText("Registrar plan loteria");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuItem12.setText("Consultar plan loteria");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);

        jMenu9.add(jMenu5);

        jMenu6.setText("Gestor tipo premios");

        jMenuItem10.setText("Registrar tipo premio");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem13.setText("Consultar tipo premio");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenu9.add(jMenu6);

        jMenuBar1.add(jMenu9);

        jMenu11.setText("Gestor Ventas");

        jMenuItem5.setText("Registrar ventas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem5);

        jMenuItem9.setText("Generar saldos mes");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem9);

        jMenuItem14.setText("Registrar saldos iniciales");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem14);

        jMenuBar1.add(jMenu11);

        jMenu7.setText("Gestor premios");
        jMenu7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu7ActionPerformed(evt);
            }
        });

        jMenuItem16.setText("Registrar premio");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem16);

        jMenuItem27.setText("Consultar premio individual");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem27);

        jMenuBar1.add(jMenu7);

        jMenu10.setText("Informes");
        jMenu10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu10ActionPerformed(evt);
            }
        });

        jMenuItem28.setText("Reporte ventas general rango fechas");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem28);

        jMenuItem18.setText("Reporte ventas saldos");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem18);

        jMenuItem6.setText("Reporte ventas rango fechas");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem6);

        jMenuItem25.setText("Reporte ventas persona");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem25);

        jMenuItem2.setText("Reporte premios");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem2);

        jMenuItem19.setText("Reporte ventas chance");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem19);

        jMenuItem20.setText("Reporte general");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem20);

        jMenuBar1.add(jMenu10);

        jMenu13.setText("Control prestamo");
        jMenu13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu13ActionPerformed(evt);
            }
        });

        jMenuItem22.setText("Registrar prestamos");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem22);

        jMenuItem23.setText("Registrar pagos");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem23);

        jMenuItem24.setText("Informe prestamos");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem24);

        jMenuBar1.add(jMenu13);

        jMenu12.setText("Seguridad");
        jMenu12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu12ActionPerformed(evt);
            }
        });

        jMenuItem26.setText("Copia seguridad");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem26);

        jMenuBar1.add(jMenu12);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.registrarpersonaJInternalFrame.inHabilitarCampos();
        this.registrarpersonaJInternalFrame.vaciarCampos();
        this.registrarpersonaJInternalFrame.setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        this.consultarpersonaJInternalFrame.consultarTodasPersonas();
        this.consultarpersonaJInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        this.registrarventaInternaFrame.inicializarVentana();
        this.registrarventaInternaFrame.setVisible(true);

    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:

        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        this.generarreporteventaInternalFrame.inicializarVentana();
        this.generarreporteventaInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        this.registrarloteriaInternalFrame = new RegistrarLoteriaInternalFrame(this);
        this.add(this.registrarloteriaInternalFrame);
        this.registrarloteriaInternalFrame.deshabilitarCampos();
        this.registrarloteriaInternalFrame.vaciarCampos();
        this.registrarloteriaInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        this.consultarloteriaInternalFrame.consultarTodoLoteria();
        this.consultarloteriaInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        this.registrarplanloteriaInternalFrame = new RegistrarPlanLoteriaInternalFrame(this);
        this.add(this.registrarplanloteriaInternalFrame);
        this.registrarplanloteriaInternalFrame.cargarComboLoteria();

        this.registrarplanloteriaInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        this.consultarplanloteriaInternalFrame.consultarPlanLoterias();
        this.consultarplanloteriaInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:

        this.registrartipopremioInternalFrame.deshabilitarCampos();
        this.registrartipopremioInternalFrame.vaciarCampos();
        this.registrartipopremioInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        this.consultartipopremioInternalFrame.consultarTodo();
        this.consultartipopremioInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        this.registrarpremios2InternalFrame = new RegistrarPremios2InternalFrame(this);
        this.add(this.registrarpremios2InternalFrame);
        this.registrarpremios2InternalFrame.inicializarVentana();

        this.registrarpremios2InternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenu7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu7ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu7ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.consultarpremiosInternalFrame.cagarLoterias();
        this.consultarpremiosInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        this.generarsaldosInternalFrame.generarFecha();
        this.generarsaldosInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        this.registrarsaldosinicialesInternalFrame = new RegistrarSaldosInicialesInternalFrame(this);
        this.add(this.registrarsaldosinicialesInternalFrame);
        this.registrarsaldosinicialesInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenu10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu10ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu10ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        this.consultarventaschanceInternalFrame.consultarFechaActual();
        this.consultarventaschanceInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:
        this.guardarArchivo();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenu12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu12ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        this.generarreporteventasaldoInternalFrame.inicializarVentana();
        this.generarreporteventasaldoInternalFrame.setVisible(true);

    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
        VistaInformeGeneral vista = new VistaInformeGeneral(this);
        this.add(vista);
        vista.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenu13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu13ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
        PrestamoInternalFrame p = new PrestamoInternalFrame(this);
        this.add(p);
        p.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here
        PagoInternalFrame p = new PagoInternalFrame(this);
        this.add(p);
        p.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        InformePrestamos inf = new InformePrestamos(this);
        this.add(inf);
        inf.setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        // TODO add your handling code here:
        this.generarreporteventapersonaInternalFrame = new GenerarReporteVentaPersonaInternalFrame1(this);
        this.add(this.generarreporteventapersonaInternalFrame);
        this.generarreporteventapersonaInternalFrame.inicializarVentana();
        this.generarreporteventapersonaInternalFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        this.consultarpremioindividualinternalframe=new ConsultarPremioIndividualInternalFrame(this);
        this.add(this.consultarpremioindividualinternalframe);
        this.consultarpremioindividualinternalframe.setVisible(true);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        // TODO add your handling code here:
         VistaInformeGeneralVenta vista = new VistaInformeGeneralVenta(this);
        this.add(vista);
        vista.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void guardarArchivo() {

        JFileChooser file = new JFileChooser();
        file.showSaveDialog(this);
        File guarda = file.getSelectedFile();

        if (guarda != null) {

            if (guarda.exists()) {

                if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this, "El archivo existe,deseas reemplazarlo?", "Titulo", JOptionPane.YES_NO_OPTION)) {
                    this.getFachada().crearCopiaSeguridad(guarda.getAbsolutePath());
                }
            } else {
                this.getFachada().crearCopiaSeguridad(guarda.getAbsolutePath());
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JDesktopPane panel;
    // End of variables declaration//GEN-END:variables
}
