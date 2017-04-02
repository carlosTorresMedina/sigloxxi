/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloPremio;

import ModeloLoteria.LoteriaDto;
import ModeloTipoPremio.TipoPremioDto;
import ModeloVentas.PersonaDto;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class PremioDto {
 
    private String fecha;
    private String serial;
    private  PersonaDto persona;
    private String moneda;
    private int totalPagado;
    private String pagado;
    private int consecutivo;
    private DetPremioDto detPremio;
    
    //Numero ganador.
    private String numeroGanador;
    
    

   
    
    public PremioDto(){
    this.persona=new PersonaDto();  
    this.detPremio=new DetPremioDto();
    }

    @Override
    public String toString() {
        return "PremioDto{" + "fecha=" + fecha + ", serial=" + serial + ", persona=" + persona + ", moneda=" + moneda + ", totalPagado=" + totalPagado + ", pagado=" + pagado + ", consecutivo=" + consecutivo + ", detPremio=" + detPremio + '}';
    }

    public String getNumeroGanador() {
        return numeroGanador;
    }

    public void setNumeroGanador(String numeroGanador) {
        this.numeroGanador = numeroGanador;
    }
    
    
    
    
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(int totalPagado) {
        this.totalPagado = totalPagado;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

   

   

   public String getMonedaBaseDatos() {
       if(this.moneda.equalsIgnoreCase("Pesos")){
       return "P";
       }
       return "B";
    }

   public int getPagadoBaseDatos() {
       
        if(this.pagado.equalsIgnoreCase("Si")){
        return 1;
        }
        return 0;
    }

    void setTotalPagadoBaseDatos(int aInt) {
        
        if(aInt==1){
        this.pagado="Si";
        }else{
        this.pagado="No";
        }
    }

    public String getTotalPagadoFormato() {
          NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.totalPagado);
    
    }

   

    void setMonedaBaseDatos(String string) {
        if(string.equalsIgnoreCase("B")){
        this.moneda="Bolivares";
        }else{
        this.moneda="Pesos";
        }
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

    public PersonaDto getPersona() {
        return persona;
    }

    public DetPremioDto getDetPremio() {
        return detPremio;
    }

    public void setDetPremio(DetPremioDto detPremio) {
        this.detPremio = detPremio;
    }

   
    
    
    
}
