/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloLoteria;

import ModeloPlanLoteria.PlanLoteriaDto;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 * objeto de transferencia de datos
 */
public class LoteriaDto {

    private int codigo;
    private String nombre;
    private int dia;
   private int hora;
   private int minuto;
   private String ruta;
    private ArrayList<PlanLoteriaDto> planLoterias;
    

    public LoteriaDto(){
    this.planLoterias=new  ArrayList();
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "LoteriaDto{" + "codigo=" + codigo + ", nombre=" + nombre + ", dia=" + dia + ", hora=" + hora + ", minuto=" + minuto + ", ruta=" + ruta + ", planLoterias=" + planLoterias + '}';
    }

    
    
    
    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
    
    
    
    public String getCodigoNombre(){
    return this.codigo+"-"+this.nombre;
    }
    
    
    public void setCodigoNombre(String codigoNombre){
    String v[]=codigoNombre.split("-");
    this.codigo=Integer.parseInt(v[0]);
    this.nombre=v[1];
  
    } 
    
    public ArrayList<PlanLoteriaDto> getPlanLoterias() {
        return planLoterias;
    }

    public void setPlanLoterias(ArrayList<PlanLoteriaDto> planLoterias) {
        this.planLoterias = planLoterias;
    }

   

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

     public void adicionarPlanLoteria(PlanLoteriaDto dto){
    this.planLoterias.add(dto);
    }

    public String getDiaFormato() {
        if(this.dia==-1){
        return "Todos";
        }       
        if (this.dia == 1) {
            return "Lunes";
        }
        if (this.dia == 2) {
            return "Martes";
        }
        if (this.dia == 3) {
            return "Miercoles";
        }
        if (this.dia == 4) {
            return "Jueves";
        }
        if (this.dia == 5) {
            return "Viernes";
        }
        if (this.dia == 6) {
            return "Sabado";
        }
        if (this.dia == 0) {
            return "Domingo";
        }
        return "";
    }

}
