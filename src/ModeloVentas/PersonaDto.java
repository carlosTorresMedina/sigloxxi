/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVentas;

/**
 *
 * @author lenovo
 */
public class PersonaDto {

    private int nuip;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String tipo;
    private String activo;
    private int porcentaje;
    private int codigo;
    private String nacionalidad;

    public int getNuip() {
        return nuip;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    
    
    public String getNuipNombre() {
        return this.nuip + "-" + this.getNombre();
    }

    public void setNuipNombre(String codigoNombre) {
        String v[] = codigoNombre.split("-");
        if (v.length >= 2) {
            this.nuip = Integer.parseInt(v[0]);
            this.nombre = v[1];
        } else {
            this.nuip = Integer.parseInt(v[0]);
        }

    }

    public void setCodigoNombre(String text) {
        String v[] = text.split("-");
        if (v.length >= 2) {
            this.codigo = Integer.parseInt(v[0]);
            this.nombre = v[1];
        } else {
            this.codigo = Integer.parseInt(v[0]);
        }
    }

    public void setNuip(int nuip) {
        this.nuip = nuip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "PersonaDto{" + "nuip=" + nuip + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", telefono=" + telefono + ", tipo=" + tipo + ", activo=" + activo + ", porcentaje=" + porcentaje + ", codigo=" + codigo + '}';
    }

    public String getCodigoNombre() {
        return codigo + "-" + nombre;
    }

}
