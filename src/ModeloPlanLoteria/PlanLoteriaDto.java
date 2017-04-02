/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloPlanLoteria;

import ModeloLoteria.LoteriaDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 * objeto de transferencia de datos
 */
public class PlanLoteriaDto {

    private int codigoLoteria;
    private String fecha;
    private String cierre;
    private String numero;
    private String serie;
    private String escrutado;
    private String cerrado;

    private String hora;
    private String minuto;

    @Override
    public String toString() {
        return "PlanLoteriaDto{" + "codigoLoteria=" + codigoLoteria + ", fecha=" + fecha + ", cierre=" + cierre + ", numero=" + numero + ", serie=" + serie + ", escrutado=" + escrutado + ", cerrado=" + cerrado + ", hora=" + hora + ", minuto=" + minuto + '}';
    }

    
    
    
    public String retornarTresUltimosNumeros(){
    String numeros=this.numero.substring(1, 4);
    return numeros;
    }
    
    public String retornarDosUltimosNumeros(){
    String numeros=this.numero.substring(2, 4);
    return numeros;
    }
    
    public String retornarUnoUltimosNumeros(){
    String numeros=this.numero.substring(3, 4);
    return numeros;
    }
    
    
    public static void main(String ar[]){
    String n="0000";
    String numero=n.substring(1, 4);
    System.out.println(numero);
    }
    
    public String getHora() {
        return hora;
    }

    public void setFechaHora(String fecha){
    String v[]=fecha.split(" ");
    this.fecha=v[0];
    }
    
    public void setCodigoNombreLoteria(String codigoNombre){
    String v[]=codigoNombre.split("-");
    this.codigoLoteria=Integer.parseInt(v[0]);
    }
    
    public void setCierreBaseDatos(String cierre) {
        String v[] = cierre.split(" ");
        String v1[] = v[1].split(":");
        this.hora = v1[0];
        this.minuto = v1[1];

        this.cierre = cierre;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinuto() {
       
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getCierreBaseDatos() {

        return cierre + " " + hora + ":" + minuto;
    }

    public int getCodigoLoteria() {
        return codigoLoteria;
    }

    public void setCodigoLoteria(int codigoLoteria) {
        this.codigoLoteria = codigoLoteria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCierre() {
        if(this.cierre==null){
        return "";
        }
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public String getNumero() {
        if(this.numero==null){
        return "";
        }
        return numero;
    }

    public void setNumero(String numero) {
        System.out.println(numero);
        this.numero = numero;
    }

    public String getSerie() {
         if(this.serie==null){
        return "";
        }
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getEscrutado() {
         if(this.escrutado==null){
        return "";
        }
        return escrutado;
    }

    public void setEscrutado(String escrutado) {
        this.escrutado = escrutado;
    }

    public String getCerrado() {
         if(this.cerrado==null){
        return "";
        }
        return cerrado;
    }

    public void setCerrado(String cerrado) {
        this.cerrado = cerrado;
    }

    public Date getCierreFormato() {
         String v[] = this.cierre.split(" ");
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print(v[0]);
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(v[0]);
        } catch (ParseException ex) {
            Logger.getLogger(LoteriaDto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fecha;
    }

    public Date getFechaFormato() {
       String v[] = this.fecha.split(" ");
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print(v[0]);
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(v[0]);
        } catch (ParseException ex) {
            Logger.getLogger(LoteriaDto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fecha;  
    }

}
