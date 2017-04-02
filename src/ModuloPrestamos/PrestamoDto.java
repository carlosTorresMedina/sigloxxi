/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPrestamos;

import ModeloVentas.PersonaDto;
import java.text.NumberFormat;

/**
 *
 * @author carlos
 */
public class PrestamoDto {
      private String tipo;
    private PersonaDto persona;
    private String fecha;
    private int valor;
    private String moneda;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public PersonaDto getPersona() {
        return persona;
    }

    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }

   

   

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getValorFormato() {
         NumberFormat formatoNumero = NumberFormat.getNumberInstance();
         return formatoNumero.format(this.valor);
    }

    
    
}
