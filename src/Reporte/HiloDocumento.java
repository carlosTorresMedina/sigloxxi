/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author carlos
 */
public class HiloDocumento extends Thread {

    private String ruta;
    /**
     * tipo=1 indica reporte ventas tipo=2 indica reporte premios
     */
    private int tipo;

    public HiloDocumento(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public void run() {
        try {
            File path = new File(ruta);
            Desktop.getDesktop().open(path);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
