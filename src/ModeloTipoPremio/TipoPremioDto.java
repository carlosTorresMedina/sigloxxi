/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTipoPremio;

import java.text.NumberFormat;

/**
 *
 * @author carlos
 */
public class TipoPremioDto {
    
    private int tipo;
    private String nombre;
    private int valorPesos;
    private int valorBolivares;
    

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValorPesos() {
        return valorPesos;
    }

    public void setValorPesos(int valorPesos) {
        this.valorPesos = valorPesos;
    }

    public int getValorBolivares() {
        return valorBolivares;
    }

    public void setValorBolivares(int valorBolivares) {
        this.valorBolivares = valorBolivares;
    }
    
    public String getValorBolivaresFormato(){
    NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.valorBolivares);
    }
    
    public String getValorPesosFormato(){
    NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.valorPesos);
    }

    public String getTipoNombre() {
         return this.tipo+"-"+this.nombre;
    }
    
    
    public void setTipoNombre(String tipoNombre){
    String v[]=tipoNombre.split("-");
    this.tipo=Integer.parseInt(v[0]);
    this.nombre=v[1];
    
    } 
    
}
