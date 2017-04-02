/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InformeGeneral;

import ModeloPremio.GestorPremio;
import ModeloVentas.GestorVentas;
import ModeloVentas.PersonaDto;
import ModeloVentas.VentaDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 * esta clase implementa el patron singleton
 */
public class GestorInformeGeneral {
    private static GestorInformeGeneral instance;
    private InformeGeneralDto Promotor;
    private InformeGeneralDto Vendedor;

    private GestorInformeGeneral() {

    }
    
    public static GestorInformeGeneral getInstance(){
    if(instance==null){
    instance=new GestorInformeGeneral();
    }
    return instance;
    }

    private void inicializarTiposUsuario() {
        this.Promotor = new InformeGeneralDto();
        this.Promotor.setTipoUsuario("Promotores");
        this.Vendedor = new InformeGeneralDto();
        this.Vendedor.setTipoUsuario("Vendedores");
    }

    public ArrayList<InformeGeneralDto> listaNochePesos(String fechaI, String fechaF) {
        ArrayList<InformeGeneralDto> lista = new ArrayList();
        Connection con = null;
        this.inicializarTiposUsuario();
     try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            PreparedStatement str;
            //genero las sentencias
            String sql = "select pe.tipo,sum(v.venta),sum(comision) "
                    + " from ventas v,persona pe"
                    + " where v.codigo_persona=pe.codigo "
                    + " and v.ruta='Noche' and v.moneda='Pesos'"
                    + " and v.fecha>='" + fechaI
                    + "' and v.fecha<='" + fechaF + "' group by pe.tipo";

            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
            this.inicializarTiposUsuario();
            while (rs.next()) {

                if (rs.getString(1).equals("P")) {
                    
                    this.Promotor.setVentas(rs.getLong(2));
                    this.Promotor.setComision(rs.getLong(3));
                    
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setVentas(rs.getLong(2));
                    this.Vendedor.setComision(rs.getLong(3));
                    
                }
            }
           

            sql = "select pe.tipo, sum(d.premio) "
                    + " from persona pe,premio p,detpremio d"
                    + " where  pe.codigo=p.codigo"
                    + " and p.serial=d.serial and p.fecha=d.fecha "
                    + " and p.moneda='P' and d.ruta='Noche' "
                    + " and p.fecha>='" + fechaI 
                    + " ' and p.fecha<='" + fechaF + "' group by pe.tipo";

            str = con.prepareStatement(sql);
            rs = str.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("P")) {
                    
                 
                    this.Promotor.setPremios(rs.getLong(2));
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setPremios(rs.getLong(2));
                }
            }
            lista.add(this.Promotor);
            lista.add(this.Vendedor);
            str.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public ArrayList<InformeGeneralDto> listaDiaPesos(String fechaI, String fechaF) {
        ArrayList<InformeGeneralDto> lista = new ArrayList();
        Connection con = null;

        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            PreparedStatement str;
            //genero las sentencias
            String sql = "select pe.tipo,sum(v.venta),sum(comision) "
                    + " from ventas v,persona pe"
                    + " where v.codigo_persona=pe.codigo "
                    + " and v.ruta='Dia' and v.moneda='Pesos'"
                    + " and v.fecha>='" + fechaI
                    + "' and v.fecha<='" + fechaF + "' group by pe.tipo";

            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
            this.inicializarTiposUsuario();
            while (rs.next()) {

                if (rs.getString(1).equals("P")) {
                   
                    this.Promotor.setVentas(rs.getLong(2));
                    this.Promotor.setComision(rs.getLong(3));
                 
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setVentas(rs.getLong(2));
                    this.Vendedor.setComision(rs.getLong(3));
                  
                }
            }
         

            sql = "select pe.tipo, sum(d.premio) "
                    + " from persona pe,premio p,detpremio d"
                    + " where  pe.codigo=p.codigo"
                    + " and p.serial=d.serial and p.fecha=d.fecha "
                    + " and p.moneda='P' and d.ruta='Dia' "
                    + " and p.fecha>='" + fechaI 
                    + " ' and p.fecha<='" + fechaF + "' group by pe.tipo";

            str = con.prepareStatement(sql);
            rs = str.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("P")) {
                  
                 
                    this.Promotor.setPremios(rs.getLong(2));
                }
                if (rs.getString(1).equals("V")) {
                  
                    this.Vendedor.setPremios(rs.getLong(2));
                }
            }
            lista.add(this.Promotor);
            lista.add(this.Vendedor);
            str.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public ArrayList<InformeGeneralDto> listaNocheBolivares(String fechaI, String fechaF) {
        ArrayList<InformeGeneralDto> lista = new ArrayList();
        Connection con = null;
           
         try {
             this.inicializarTiposUsuario();
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            PreparedStatement str;
            //genero las sentencias
            String sql = "select pe.tipo,sum(v.venta),sum(comision) "
                    + " from ventas v,persona pe"
                    + " where v.codigo_persona=pe.codigo "
                    + " and v.ruta='Noche' and v.moneda='Bolivares'"
                    + " and v.fecha>='" + fechaI
                    + "' and v.fecha<='" + fechaF + "' group by pe.tipo";
 
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
           
            while (rs.next()) {

                if (rs.getString(1).equals("P")) {
                  
                    this.Promotor.setVentas(rs.getLong(2));
                    this.Promotor.setComision(rs.getLong(3));
                   
                }
                if (rs.getString(1).equals("V")) {
                 
                    this.Vendedor.setVentas(rs.getLong(2));
                    this.Vendedor.setComision(rs.getLong(3));
                 
                }
            }
           

            sql = "select pe.tipo, sum(d.premio) "
                    + " from persona pe,premio p,detpremio d"
                    + " where  pe.codigo=p.codigo"
                    + " and p.serial=d.serial and p.fecha=d.fecha "
                    + " and p.moneda='B' and d.ruta='Noche' "
                    + " and p.fecha>='" + fechaI 
                    + "'  and p.fecha<='" + fechaF + "' group by pe.tipo";

            str = con.prepareStatement(sql);
            rs = str.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("P")) {
                  
                    this.Promotor.setPremios(rs.getLong(2));
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setPremios(rs.getLong(2));
                }
            }
            lista.add(this.Promotor);
            lista.add(this.Vendedor);
            str.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public ArrayList<InformeGeneralDto> listaDiaBolivares(String fechaI, String fechaF) {
        ArrayList<InformeGeneralDto> lista = new ArrayList();
        Connection con = null;

        try {
               this.inicializarTiposUsuario();
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            PreparedStatement str;
            //genero las sentencias
            String sql = "select pe.tipo,sum(v.venta),sum(comision) "
                    + " from ventas v, persona pe"
                    + " where v.codigo_persona=pe.codigo "
                    + " and v.ruta='Dia' and v.moneda='Bolivares'"
                    + " and v.fecha>='" + fechaI
                    + "' and v.fecha<='" + fechaF + "' group by pe.tipo";

            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
         
           
            while (rs.next()) {

                if (rs.getString(1).equals("P")) {
                    
                    this.Promotor.setVentas(rs.getLong(2));
                    this.Promotor.setComision(rs.getLong(3));
                 
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setVentas(rs.getLong(2));
                    this.Vendedor.setComision(rs.getLong(3));
           
                }
            }
           

            sql = "select pe.tipo, sum(d.premio) "
                    + " from persona pe,premio p,detpremio d"
                    + " where  pe.codigo=p.codigo"
                    + " and p.serial=d.serial and p.fecha=d.fecha "
                    + " and p.moneda='B' and d.ruta='Dia' "
                    + " and p.fecha>='" + fechaI 
                    + "'  and p.fecha<='" + fechaF + "' group by pe.tipo";

            str = con.prepareStatement(sql);
            rs = str.executeQuery();
  
            while (rs.next()) {
                if (rs.getString(1).equals("P")) {
                   ;
                    this.Promotor.setPremios(rs.getLong(2));
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setPremios(rs.getLong(2));
                }
            }
            lista.add(this.Promotor);
            lista.add(this.Vendedor);
            str.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public static void main(String ar[]) {
        GestorInformeGeneral g = new GestorInformeGeneral();
        ArrayList<InformeGeneralDto> lista1 = g.listaNocheBolivares("2016-04-1", "2016-04-30");
 ArrayList<InformeGeneralDto> lista2 = g.listaDiaBolivares("2016-04-1", "2016-04-30");
  ArrayList<InformeGeneralDto> lista3 = g.listaDiaPesos("2016-04-1", "2016-04-30");
   ArrayList<InformeGeneralDto> lista4 = g.listaNochePesos("2016-04-1", "2016-04-30");
   for(InformeGeneralDto dto:lista2){
   System.out.println(dto);
   }
    }

    public ArrayList<InformeGeneralDto> listaNochePesosNacionalidadTipo(String fechaI, String fechaF, String nacionalidad) {
        ArrayList<InformeGeneralDto> lista = new ArrayList();
        Connection con = null;
        this.inicializarTiposUsuario();
     try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            PreparedStatement str;
            //genero las sentencias
            String sql = "select pe.tipo,sum(v.venta),sum(comision) "
                    + " from ventas v,persona pe"
                    + " where v.codigo_persona=pe.codigo "
                    + " and v.ruta='Noche' and v.moneda='Pesos'"
                    + " and v.fecha>='" + fechaI
                    + "' and v.fecha<='" + fechaF + "'  and pe.nacionalidad='"+nacionalidad+"' group by pe.tipo";

            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
            this.inicializarTiposUsuario();
            while (rs.next()) {

                if (rs.getString(1).equals("P")) {
                    
                    this.Promotor.setVentas(rs.getLong(2));
                    this.Promotor.setComision(rs.getLong(3));
                    
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setVentas(rs.getLong(2));
                    this.Vendedor.setComision(rs.getLong(3));
                    
                }
            }
           

            sql = "select pe.tipo, sum(d.premio) "
                    + " from persona pe,premio p,detpremio d"
                    + " where  pe.codigo=p.codigo"
                    + " and p.serial=d.serial and p.fecha=d.fecha "
                    + " and p.moneda='P' and d.ruta='Noche' "
                    + " and p.fecha>='" + fechaI 
                    + " ' and p.fecha<='" + fechaF + "'  and pe.nacionalidad='"+nacionalidad+ "' group by pe.tipo";

            str = con.prepareStatement(sql);
            rs = str.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("P")) {
                    
                 
                    this.Promotor.setPremios(rs.getLong(2));
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setPremios(rs.getLong(2));
                }
            }
            lista.add(this.Promotor);
            lista.add(this.Vendedor);
            str.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public ArrayList<InformeGeneralDto> listaDiaPesosNacionalidadTipo(String fechaI, String fechaF, String nacionalidad) {
         ArrayList<InformeGeneralDto> lista = new ArrayList();
        Connection con = null;

        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            PreparedStatement str;
            //genero las sentencias
            String sql = "select pe.tipo,sum(v.venta),sum(comision) "
                    + " from ventas v,persona pe"
                    + " where v.codigo_persona=pe.codigo "
                    + " and v.ruta='Dia' and v.moneda='Pesos'"
                    + " and v.fecha>='" + fechaI
                    + "' and v.fecha<='" + fechaF + "'  and pe.nacionalidad='"+nacionalidad+"' group by pe.tipo";

            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
            this.inicializarTiposUsuario();
            while (rs.next()) {

                if (rs.getString(1).equals("P")) {
                   
                    this.Promotor.setVentas(rs.getLong(2));
                    this.Promotor.setComision(rs.getLong(3));
                 
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setVentas(rs.getLong(2));
                    this.Vendedor.setComision(rs.getLong(3));
                  
                }
            }
         

            sql = "select pe.tipo, sum(d.premio) "
                    + " from persona pe,premio p,detpremio d"
                    + " where  pe.codigo=p.codigo"
                    + " and p.serial=d.serial and p.fecha=d.fecha "
                    + " and p.moneda='P' and d.ruta='Dia' "
                    + " and p.fecha>='" + fechaI 
                    + " ' and p.fecha<='" + fechaF + "'  and pe.nacionalidad='"+nacionalidad+ "' group by pe.tipo";

            str = con.prepareStatement(sql);
            rs = str.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("P")) {
                  
                 
                    this.Promotor.setPremios(rs.getLong(2));
                }
                if (rs.getString(1).equals("V")) {
                  
                    this.Vendedor.setPremios(rs.getLong(2));
                }
            }
            lista.add(this.Promotor);
            lista.add(this.Vendedor);
            str.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public ArrayList<InformeGeneralDto> listaNocheBolivaresNacionalidadTipo(String fechaI, String fechaF, String nacionalidad) {
         ArrayList<InformeGeneralDto> lista = new ArrayList();
        Connection con = null;
           
         try {
             this.inicializarTiposUsuario();
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            PreparedStatement str;
            //genero las sentencias
            String sql = "select pe.tipo,sum(v.venta),sum(comision) "
                    + " from ventas v,persona pe"
                    + " where v.codigo_persona=pe.codigo "
                    + " and v.ruta='Noche' and v.moneda='Bolivares'"
                    + " and v.fecha>='" + fechaI
                    + "' and v.fecha<='" + fechaF + "'  and pe.nacionalidad='"+nacionalidad+ "' group by pe.tipo";
 
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
           
            while (rs.next()) {

                if (rs.getString(1).equals("P")) {
                  
                    this.Promotor.setVentas(rs.getLong(2));
                    this.Promotor.setComision(rs.getLong(3));
                   
                }
                if (rs.getString(1).equals("V")) {
                 
                    this.Vendedor.setVentas(rs.getLong(2));
                    this.Vendedor.setComision(rs.getLong(3));
                 
                }
            }
           

            sql = "select pe.tipo, sum(d.premio) "
                    + " from persona pe,premio p,detpremio d"
                    + " where  pe.codigo=p.codigo"
                    + " and p.serial=d.serial and p.fecha=d.fecha "
                    + " and p.moneda='B' and d.ruta='Noche' "
                    + " and p.fecha>='" + fechaI 
                    + "'  and p.fecha<='" + fechaF + "'  and pe.nacionalidad='"+nacionalidad+"' group by pe.tipo";

            str = con.prepareStatement(sql);
            rs = str.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("P")) {
                  
                    this.Promotor.setPremios(rs.getLong(2));
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setPremios(rs.getLong(2));
                }
            }
            lista.add(this.Promotor);
            lista.add(this.Vendedor);
            str.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public ArrayList<InformeGeneralDto> listaDiaBolivaresNacionalidadTipo(String fechaI, String fechaF, String nacionalidad) {
        ArrayList<InformeGeneralDto> lista = new ArrayList();
        Connection con = null;

        try {
               this.inicializarTiposUsuario();
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            PreparedStatement str;
            //genero las sentencias
            String sql = "select pe.tipo,sum(v.venta),sum(comision) "
                    + " from ventas v, persona pe"
                    + " where v.codigo_persona=pe.codigo "
                    + " and v.ruta='Dia' and v.moneda='Bolivares'"
                    + " and v.fecha>='" + fechaI
                    + "' and v.fecha<='" + fechaF + "'  and pe.nacionalidad='"+nacionalidad+  "' group by pe.tipo";

            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
         
           
            while (rs.next()) {

                if (rs.getString(1).equals("P")) {
                    
                    this.Promotor.setVentas(rs.getLong(2));
                    this.Promotor.setComision(rs.getLong(3));
                 
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setVentas(rs.getLong(2));
                    this.Vendedor.setComision(rs.getLong(3));
           
                }
            }
           

            sql = "select pe.tipo, sum(d.premio) "
                    + " from persona pe,premio p,detpremio d"
                    + " where  pe.codigo=p.codigo"
                    + " and p.serial=d.serial and p.fecha=d.fecha "
                    + " and p.moneda='B' and d.ruta='Dia' "
                    + " and p.fecha>='" + fechaI 
                    + "'  and p.fecha<='" + fechaF + "'  and pe.nacionalidad='"+nacionalidad+ "' group by pe.tipo";

            str = con.prepareStatement(sql);
            rs = str.executeQuery();
  
            while (rs.next()) {
                if (rs.getString(1).equals("P")) {
                   ;
                    this.Promotor.setPremios(rs.getLong(2));
                }
                if (rs.getString(1).equals("V")) {
                   
                    this.Vendedor.setPremios(rs.getLong(2));
                }
            }
            lista.add(this.Promotor);
            lista.add(this.Vendedor);
            str.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

 
}
