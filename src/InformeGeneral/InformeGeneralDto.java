/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InformeGeneral;

import java.text.NumberFormat;

/**
 *
 * @author carlos
 */
public class InformeGeneralDto 
{
   private String tipoUsuario;
    private long ventas;
    private long comision;
    private long premios;
   
    

    public long getVentas() {
        return ventas;
    }

    @Override
    public String toString() {
        return "InformeGeneralDto{" + "tipoUsuario=" + tipoUsuario + ", ventas=" + ventas + ", comision=" + comision + ", premios=" + premios + '}';
    }

     public String getTipoFormato() {
     return this.tipoUsuario;
    }
    
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    
    
    public void setVentas(long ventas) {
        this.ventas = ventas;
    }

    public long getComision() {
        return comision;
    }

    public void setComision(long comision) {
        this.comision = comision;
    }

    public long getPremios() {
        return premios;
    }

    public void setPremios(long premios) {
        this.premios = premios;
    }

    public String getVentasFormato() {
      NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.ventas);
    }

    public String getComisionesFormato() {
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.comision);
    }

    public String getPremiosFormato() {
    NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.premios);   
    }

    public String getUtilidadFormato() {
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.getUtilidad());
    }
    
    public long getUtilidad(){
    
    return (this.ventas-this.comision-this.premios);
    }
    
    
    
}
