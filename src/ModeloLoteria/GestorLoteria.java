/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloLoteria;

import ModeloVentas.PersonaDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos modulo de loterias.
 * esta clase implementa el patron singleton
 */
public class GestorLoteria {

    private static GestorLoteria instance;
    
    private GestorLoteria(){}
    
    public static GestorLoteria getInstance(){
    if(instance==null){
    instance=new GestorLoteria();
    }
    return instance;
    }
    
    /**
     * metodo que modifica los datos de una determinada loteria
     *
     * @param codigoA
     * @param dto
     * @return
     */
    public boolean modificarLoteria(int codigoA, LoteriaDto dto) {
        PreparedStatement stm = null;
        boolean exito = false;
        Connection con = null;

        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            stm = con.prepareStatement("UPDATE loteria SET "
                    + "codigo=" + dto.getCodigo()
                    + ",nombre='" + dto.getNombre() + "'"
                    + ",dia=" + dto.getDia()
                    + ",hora=" + dto.getHora()
                    + ",minuto=" + dto.getMinuto()
                    +", ruta='"+dto.getRuta()+"'"
                    + " where codigo = " + codigoA);

            int total = stm.executeUpdate();
            if (total > 0) {
                exito = true;

            }
            stm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            return exito;
        }

    }

    /**
     * elimina una determinada loteria segun el codigo
     *
     * @param codigo
     * @return
     */
    public boolean eliminarLoteria(int codigo) {

        String instruccion = "delete from loteria where codigo =" + codigo;
        boolean val = false;
        PreparedStatement pre;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            pre = con.prepareStatement(instruccion);
            pre.execute();
            val = true;
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return val;
        }

    }

    /**
     * lista todas las loterias registradas en el sistema
     *
     * @return
     */
    public ArrayList<LoteriaDto> listarLoterias() {

        ArrayList<LoteriaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT codigo,nombre,dia,hora,minuto,ruta FROM loteria";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                LoteriaDto dto = new LoteriaDto();
                dto.setCodigo(rs.getInt(1));
                dto.setNombre(rs.getString(2));
                dto.setDia(rs.getInt(3));
                dto.setHora(rs.getInt(4));
                dto.setMinuto(rs.getInt(5));
                dto.setRuta(rs.getString(6));
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
                    Logger.getLogger(GestorLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    /**
     * metodo que permite registrar una loteria en el sistema
     *
     * @param dto
     * @return
     */
    public boolean registrarLoteria(LoteriaDto dto) {
        PreparedStatement pst = null;
        Connection con = null;
        boolean valor = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            pst = con.prepareStatement("insert into loteria values(?,?,?,?,?,?)");
            pst.setInt(1, dto.getCodigo());
            pst.setString(2, dto.getNombre());
            pst.setInt(3, dto.getDia());
            pst.setInt(4, dto.getHora());
            pst.setInt(5, dto.getMinuto());
            pst.setString(6, dto.getRuta());
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
                    Logger.getLogger(GestorLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return valor;
        }
    }

    /**
     * lista las loterias segun el codigo
     *
     * @param codigo
     * @return
     */
    public ArrayList<LoteriaDto> listarLoteriaXCodigo(int codigo) {
        ArrayList<LoteriaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT codigo,nombre,dia,hora,minuto,ruta FROM loteria where codigo=" + codigo;
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                LoteriaDto dto = new LoteriaDto();
                dto.setCodigo(rs.getInt(1));
                dto.setNombre(rs.getString(2));
                dto.setDia(rs.getInt(3));
                dto.setHora(rs.getInt(4));
                dto.setMinuto(rs.getInt(5));
                dto.setRuta(rs.getString(6));

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
                    Logger.getLogger(GestorLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    /**
     * lista las loterias segun el nombre
     *
     * @param nombre
     * @return
     */
    public ArrayList<LoteriaDto> listarLoteriaXNombre(String nombre) {

        ArrayList<LoteriaDto> lista = new ArrayList();
        Connection con =null;
        try {
            con=Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT codigo,nombre,dia,hora,minuto,ruta FROM loteria where nombre='" + nombre + "'";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                LoteriaDto dto = new LoteriaDto();
                dto.setCodigo(rs.getInt(1));
                dto.setNombre(rs.getString(2));
                dto.setDia(rs.getInt(3));
                dto.setHora(rs.getInt(4));
                dto.setMinuto(rs.getInt(5));
                dto.setRuta(rs.getString(6));
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
                Logger.getLogger(GestorLoteria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
        }

    }

}
