/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVentas;

import java.sql.Date;
import java.text.NumberFormat;

/**
 *
 * @author lenovo
 */
public class VentaDto {
    
    private String fecha;
    private PersonaDto persona;
    private int venta;
    private int recaudo;
    private int saldo;
    private String moneda;
    private String tipo;
    private String ruta;
    private int abono;
    //la comision se calcula en base al porcentaje del codigo de la persona  y al valor dela venta
    private int comision;
    private int nTicket;

    private int saldoAnterio;

    public int getSaldoAnterio() {
        return saldoAnterio;
    }

    public int getnTicket() {
        return nTicket;
    }

    public void setnTicket(int nTicket) {
        this.nTicket = nTicket;
    }
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    
    
    public int getAbono() {
        return abono;
    }

    public void setAbono(int abono) {
        this.abono = abono;
    }

    
    
    
    public void setSaldoAnterio(int saldoAnterio) {
        this.saldoAnterio = saldoAnterio;
    }
    
    
    public String getFecha() {
        return fecha;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "VentaDto{" + "fecha=" + fecha + ", persona=" + persona + ", venta=" + venta + ", recaudo=" + recaudo + ", saldo=" + saldo + ", moneda=" + moneda + ", comision=" + comision + ", saldoAnterio=" + saldoAnterio + '}';
    }

    

    
    
    public PersonaDto getPersona() {
        return persona;
    }

    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
 

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public int getRecaudo() {
        return recaudo;
    }

    public void setRecaudo(int recaudo) {
        this.recaudo = recaudo;
    }

    public int getSaldo() {
        this.saldo=((this.venta-this.comision-this.recaudo-this.abono)*-1)+this.saldoAnterio;
        return saldo;
    }

    public String getSaldoAnterioFormato(){    
    NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.saldoAnterio);
    }
    
    public String getVentaFormato(){    
    NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.venta);
    }
    
    public String getSaldoFormato(){    
    NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.saldo);
    }
    
    public String getRecaudoFormato(){    
    NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.recaudo);
    }
    
    public String getComisionFormato(){    
    NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.comision);
    }

    public String getAbonoFormato() {
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.abono);
    }
    
    public String getnTicketFormato(){
     NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.nTicket);
    }

    
   
   
    
    
}
