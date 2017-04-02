/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPrestamos;

import ModeloVentas.GestorPersona;
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
 * @author carlos este metodo utiliza el patron singleton
 */
public class GestorPrestamos {

    private static GestorPrestamos instance;

    private GestorPrestamos() {
    }

    public static GestorPrestamos getInstance() {
        if (instance == null) {

            instance = new GestorPrestamos();
        }
        return instance;
    }

    public boolean registrarPrestamo(PrestamoDto pre) {
        PreparedStatement pst = null;
        Connection con = null;
        boolean valor = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            pst = con.prepareStatement("insert into prestamos (fecha,codigo,moneda,tipo,valor) values(?,?,?,?,?)");
            pst.setString(1, pre.getFecha());
            pst.setInt(2, pre.getPersona().getCodigo());
            pst.setString(3, pre.getMoneda());
            pst.setString(4, pre.getPersona().getTipo());
            pst.setInt(5, pre.getValor());
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
                    ex.printStackTrace();
                }
            }
            return valor;
        }
    }

    public boolean registrarPago(PrestamoDto pre) {
        PreparedStatement pst = null;
        Connection con = null;
        boolean valor = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            pst = con.prepareStatement("insert into pagoprestamos (fecha,codigo,moneda,tipo,valor) values(?,?,?,?,?)");
            pst.setString(1, pre.getFecha());
            pst.setInt(2, pre.getPersona().getCodigo());
            pst.setString(3, pre.getMoneda());
            pst.setString(4, pre.getPersona().getTipo());
            pst.setInt(5, pre.getValor());
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
                    ex.printStackTrace();
                }
            }
            return valor;
        }
    }

    public ArrayList<PrestamoDto> consultarPrestamoEspecifico(PersonaDto persona, String moneda,String fechaI,String fechaF) {
        ArrayList<PrestamoDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT fecha, codigo, moneda, tipo, valor FROM prestamos where codigo=" + persona.getCodigo() + " and moneda='" + moneda + "' and fecha>='"+fechaI+"' and fecha<='"+fechaF+"'";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PrestamoDto dto = new PrestamoDto();
                dto.setFecha(rs.getString(1));
                dto.setPersona(persona);
                dto.setMoneda(rs.getString(3));
                dto.setTipo(rs.getString(4));
                dto.setValor(rs.getInt(5));

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

    public ArrayList<PrestamoDto> consultarPagoEspecifico(PersonaDto persona, String moneda,String fechaI,String fechaF) {
        ArrayList<PrestamoDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT fecha,codigo,moneda,tipo,valor FROM pagoprestamos where codigo=" + persona.getCodigo() + " and moneda='" + moneda + "' and fecha>='"+fechaI+"' and fecha<='"+fechaF+"'";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PrestamoDto dto = new PrestamoDto();
                dto.setFecha(rs.getString(1));
                dto.setPersona(persona);
                dto.setMoneda(rs.getString(3));
                dto.setTipo(rs.getString(4));
                dto.setValor(rs.getInt(5));

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
                    ex.printStackTrace();
                }
            }
            return lista;
        }
    }

}
