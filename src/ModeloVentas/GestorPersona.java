/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVentas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 * esta clase implementa el metodo singleton
 */
public class GestorPersona {

    private static GestorPersona instance;
    
    private GestorPersona(){}
    
    public static GestorPersona getInstance(){
    if(instance==null){
    instance=new GestorPersona();
    }
    return instance;
    }
    
    public boolean registrarPersona(PersonaDto dto) {
        PreparedStatement pst = null;
        Connection con = null;
        boolean valor = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            pst = con.prepareStatement("insert into persona values(?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, dto.getNuip());
            pst.setInt(2, dto.getCodigo());
            pst.setString(3, dto.getNombre());
            pst.setString(4, dto.getApellido());
            pst.setString(5, dto.getDireccion());
            pst.setString(6, dto.getTelefono());
            pst.setString(7, dto.getTipo());
            pst.setString(8, dto.getActivo());
            pst.setInt(9, dto.getPorcentaje());
            pst.setString(10,dto.getNacionalidad());
            pst.executeUpdate();
            pst.close();
            valor = true;
        } catch (SQLException exc) {
            exc.printStackTrace();

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return valor;
        }
    }

    public ArrayList<PersonaDto> listarPersonaXCedula(int cedula) {
        ArrayList<PersonaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT nuip,codigo,nombre,apellido,direccion,telefono,tipo,activo,porcentaje,nacionalidad FROM persona where nuip=" + cedula;
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PersonaDto dto = new PersonaDto();
                dto.setNuip(rs.getInt(1));
                dto.setCodigo(rs.getInt(2));
                dto.setNombre(rs.getString(3));
                dto.setApellido(rs.getString(4));
                dto.setDireccion(rs.getString(5));
                dto.setTelefono(rs.getString(6));
                dto.setTipo(rs.getString(7));
                dto.setActivo(rs.getString(8));
                dto.setPorcentaje(rs.getInt(9));
                dto.setNacionalidad(rs.getString(10));
                lista.add(dto);

            }
            str.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public ArrayList<PersonaDto> listarPersonaXCodigo(int codigo) {
        ArrayList<PersonaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con=Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT nuip,codigo,nombre,apellido,direccion,telefono,tipo,activo,porcentaje FROM persona where codigo=" + codigo;
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PersonaDto dto = new PersonaDto();
                dto.setNuip(rs.getInt(1));
                dto.setCodigo(rs.getInt(2));
                dto.setNombre(rs.getString(3));
                dto.setApellido(rs.getString(4));
                dto.setDireccion(rs.getString(5));
                dto.setTelefono(rs.getString(6));
                dto.setTipo(rs.getString(7));
                dto.setActivo(rs.getString(8));
                dto.setPorcentaje(rs.getInt(9));
                lista.add(dto);

            }
            str.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
        }

      
    }
    
     public ArrayList<PersonaDto> listarPersonaXCodigoTipo(int codigo, String tipo) {
        ArrayList<PersonaDto> lista = new ArrayList();
        Connection con = null;
        
        try {
            con=Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT nuip,codigo,nombre,apellido,direccion,telefono,tipo,activo,porcentaje FROM persona where codigo=" + codigo+" and tipo='"+tipo.charAt(0)+"'";
           System.out.println(sql);
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PersonaDto dto = new PersonaDto();
                dto.setNuip(rs.getInt(1));
                dto.setCodigo(rs.getInt(2));
                dto.setNombre(rs.getString(3));
                dto.setApellido(rs.getString(4));
                dto.setDireccion(rs.getString(5));
                dto.setTelefono(rs.getString(6));
                dto.setTipo(rs.getString(7));
                dto.setActivo(rs.getString(8));
                dto.setPorcentaje(rs.getInt(9));
                lista.add(dto);

            }
            str.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
        }
    }

    public ArrayList<PersonaDto> listarPersonas() {
        ArrayList<PersonaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con=Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT nuip,codigo,nombre,apellido,direccion,telefono,tipo,activo,porcentaje,nacionalidad FROM persona";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PersonaDto dto = new PersonaDto();
                dto.setNuip(rs.getInt(1));
                dto.setCodigo(rs.getInt(2));
                dto.setNombre(rs.getString(3));
                dto.setApellido(rs.getString(4));
                dto.setDireccion(rs.getString(5));
                dto.setTelefono(rs.getString(6));
                dto.setTipo(rs.getString(7));
                dto.setActivo(rs.getString(8));
                dto.setPorcentaje(rs.getInt(9));
                dto.setNacionalidad(rs.getString(10));
                lista.add(dto);

            }
            str.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
        }

       
    }

    public ArrayList<PersonaDto> listarVendedores() {
        ArrayList<PersonaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con=Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT nuip,codigo,nombre,apellido,direccion,telefono,tipo,activo,porcentaje FROM persona where tipo='V'";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PersonaDto dto = new PersonaDto();
                dto.setNuip(rs.getInt(1));
                dto.setCodigo(rs.getInt(2));
                dto.setNombre(rs.getString(3));
                dto.setApellido(rs.getString(4));
                dto.setDireccion(rs.getString(5));
                dto.setTelefono(rs.getString(6));
                dto.setTipo(rs.getString(7));
                dto.setActivo(rs.getString(8));
                dto.setPorcentaje(rs.getInt(9));
                lista.add(dto);

            }
            str.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            return lista;
        }

        
    }

    public boolean eliminarPersona(int cedula) {
        Connection con = null;
        String instruccion = "delete from persona where nuip =" + cedula;
        boolean val = false;
        PreparedStatement pre;
        try {
            con=Recurso.Conexion.getPool().getDataSource().getConnection();
            pre = con.prepareStatement(instruccion);
            pre.execute();
            val = true;
        } catch (SQLException ex) {
            System.err.println("Error 1 :" + ex.toString());
        }finally{
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return val;
        }

    }

    public boolean modifcarPersona(int cedulaAnterior, PersonaDto dto) {

        PreparedStatement stm = null;
        boolean exito = false;
        Connection con = null;
        try {
con=Recurso.Conexion.getPool().getDataSource().getConnection();
            stm = con.prepareStatement("UPDATE persona SET "
                    + "nuip=" + dto.getNuip()
                    + ",codigo=" + dto.getCodigo()
                    + ",nombre='" + dto.getNombre() + "'"
                    + ",apellido='" + dto.getApellido() + "'"
                    + ",direccion='" + dto.getDireccion() + "'"
                    + ",telefono='" + dto.getTelefono() + "'"
                    + ",tipo='" + dto.getTipo() + "'"
                    + ",activo='" + dto.getActivo() + "'"
                    + ",porcentaje='" + dto.getPorcentaje() + "'"
                    +",nacionalidad='"+dto.getNacionalidad()+"'"
                    + "WHERE nuip=" + cedulaAnterior);

            int total = stm.executeUpdate();
            if (total > 0) {
                exito = true;

            }
            stm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exito;
        }
        
    }

   
}
