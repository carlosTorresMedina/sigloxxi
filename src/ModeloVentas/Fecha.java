/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVentas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
public class Fecha {

    private int ano;
    private int mes;

    public Fecha() {
    }

    public int getAño() {
        return ano;
    }

    public int getMes() {
        return mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * metodo que en base al numero de mes genera los dias limites del mismo
     *
     * @param mes
     * @return
     */
    public int[] retornaLimitesFecha(int mes) {
        int limites[] = new int[2];
        limites[0] = 1;
        switch (mes) {
            case 1:
                limites[1] = 31;
                break;
            case 2:
                limites[1] = 29;
                break;
            case 3:
                limites[1] = 31;
                break;
            case 4:
                limites[1] = 30;
                break;
            case 5:
                limites[1] = 31;
                break;
            case 6:
                limites[1] = 30;
                break;
            case 7:
                limites[1] = 31;
                break;
            case 8:
                limites[1] = 31;
                break;
            case 9:
                limites[1] = 30;
                break;
            case 10:
                limites[1] = 31;
                break;
            case 11:
                limites[1] = 30;
                break;
            case 12:
                limites[1] = 31;
                break;

        }

        return limites;
    }

    /**
     * genera la fecha en base a un año mes y dia digitado
     *
     * @param ano
     * @param mes
     * @param dia
     * @return
     */
    public String generarFecha(int ano, int mes, int dia) {

        Date fecha = new Date(ano + "/" + mes + "/" + dia);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaformato = formato.format(fecha);
        return fechaformato;
       
    }

  

    /**
     * metodo que en base a el parametro mes lo pasa a formato numero
     *
     * @param mes
     */
    public void setFecha(String mes) {

        if (mes.equals("Enero")) {
            this.mes = 1;
        }
        if (mes.equals("Febrero")) {
            this.mes = 2;
        }
        if (mes.equals("Marzo")) {
            this.mes = 3;
        }
        if (mes.equals("Abril")) {
            this.mes = 4;
        }
        if (mes.equals("Mayo")) {
            this.mes = 5;
        }
        if (mes.equals("Junio")) {
            this.mes = 6;
        }
        if (mes.equals("Julio")) {
            this.mes = 7;
        }
        if (mes.equals("Agosto")) {
            this.mes = 8;
        }
        if (mes.equals("Septiembre")) {
            this.mes = 9;
        }
        if (mes.equals("Octubre")) {
            this.mes = 10;
        }
        if (mes.equals("Noviembre")) {
            this.mes = 11;
        }
        if (mes.equals("Diciembre")) {
            this.mes = 12;
        }
    }

   

}
