/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloPremio;

/**
 *
 * @author carlos
 * esta clase es utilizada como dto para la creacion del informe de ventas de chance
 */
public class TicketDto {
    private String fecha;
    private String codigo;
    private String serial;
    private int valor;
    String moneda;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

   public void setCodigo(String codigo){
   this.codigo=codigo;
   }
   
   public String getCodigo(){
   return this.codigo;
   }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    
    
            
}
