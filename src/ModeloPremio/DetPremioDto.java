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

/**
 *
 * @author carlos
 */
public class DetPremioDto {
    
    
    private LoteriaDto loteria;
     private TipoPremioDto tipoPremio;
     private int valor;
    private int premio;
     
     public DetPremioDto(){
      this.tipoPremio=new TipoPremioDto();
      this.loteria=new LoteriaDto();
     }
     
      public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "DetPremioDto{" + "loteria=" + loteria + ", tipoPremio=" + tipoPremio + ", valor=" + valor + ", premio=" + premio + '}';
    }

      
      
    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }
    
     public String getValorFormato() {
         NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.valor);
    }

    public String getPremioFormato() {
         NumberFormat formatoNumero = NumberFormat.getNumberInstance();
    return formatoNumero.format(this.premio);
    }
    
    

    public TipoPremioDto getTipoPremio() {
        return tipoPremio;
    }

    public void setTipoPremio(TipoPremioDto tipoPremio) {
        this.tipoPremio = tipoPremio;
    }

    public LoteriaDto getLoteria() {
        return loteria;
    }

    public void setLoteria(LoteriaDto loteria) {
        this.loteria = loteria;
    }
    
}
