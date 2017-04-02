/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTipoPremio;

import ModeloPremio.PremioDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos esta clase implementa el patron sigleon
 */
public class GestorTipoPremio {

    private static GestorTipoPremio instance;
    
    
    private GestorTipoPremio(){}
   
    public static GestorTipoPremio getInstance(){
    if(instance==null){
    instance=new GestorTipoPremio();
    }
    return instance;
    
    }
    
    public boolean modificarTipoPremio(int tipoA, TipoPremioDto dto) {
        PreparedStatement stm = null;
        boolean exito = false;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            stm = con.prepareStatement("UPDATE tipopremio SET "
                    + "tipo=" + dto.getTipo()
                    + ",nombre='" + dto.getNombre() + "'"
                    + ",vlrpesos='" + dto.getValorPesos() + "'"
                    + ",vlrbolivares='" + dto.getValorBolivares() + "'"
                    + "WHERE tipo=" + tipoA);

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
                    Logger.getLogger(GestorTipoPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return exito;
        }
    }

    public boolean eliminarTipoPremio(int tipo) {
        Connection con = null;
        String instruccion = "delete from tipopremio where tipo =" + tipo;
        boolean val = false;
        PreparedStatement pre;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            pre = con.prepareStatement(instruccion);
            pre.execute();
            val = true;
        } catch (SQLException ex) {
            System.err.println("Error 1 :" + ex.toString());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorTipoPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return val;
        }
    }

    /**
     * registra unn tipo premio en el sistema
     *
     * @param dto
     * @return
     */
    public boolean registrarTipoPremio(TipoPremioDto dto) {
        PreparedStatement pst = null;
        Connection con = null;
        boolean valor = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            pst = con.prepareStatement("insert into tipopremio values(?,?,?,?)");
            pst.setInt(1, dto.getTipo());
            pst.setString(2, dto.getNombre());
            pst.setInt(3, dto.getValorPesos());
            pst.setInt(4, dto.getValorBolivares());

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
                    Logger.getLogger(GestorTipoPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return valor;
        }
    }

    /**
     * lista los tipo premios por los tipos
     *
     * @param tipo
     * @return
     */
    public ArrayList<TipoPremioDto> listarTipoPremioXTipo(int tipo) {
        ArrayList<TipoPremioDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT tipo,nombre,vlrpesos,vlrbolivares FROM tipopremio where tipo=" + tipo;
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                TipoPremioDto dto = new TipoPremioDto();
                dto.setTipo(rs.getInt(1));
                dto.setNombre(rs.getString(2));
                dto.setValorPesos(rs.getInt(3));
                dto.setValorBolivares(rs.getInt(4));

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
                    Logger.getLogger(GestorTipoPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public ArrayList<TipoPremioDto> listarTipoPremios() {
        ArrayList<TipoPremioDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT tipo,nombre,vlrpesos,vlrbolivares FROM tipopremio ";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(2));
                TipoPremioDto dto = new TipoPremioDto();
                dto.setTipo(rs.getInt(1));
                dto.setNombre(rs.getString(2));
                dto.setValorPesos(rs.getInt(3));
                dto.setValorBolivares(rs.getInt(4));

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
                    Logger.getLogger(GestorTipoPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }

    }

    /**
     * metodo que calucla el premio ganado por el codigo
     *
     * @param dto
     * @return
     */
    public int CalcularPremioBaseTipo(PremioDto dto) {
        Connection con = null;
        int valor = 0;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            if (dto.getMonedaBaseDatos().equals("P")) {
                String sql = "SELECT vlrpesos FROM tipopremio where tipo=" + dto.getDetPremio().getTipoPremio().getTipo();
                PreparedStatement str;
                str = con.prepareStatement(sql);
                ResultSet rs = str.executeQuery();

                while (rs.next()) {

                    valor = rs.getInt(1);
                    valor = valor * dto.getDetPremio().getValor();
                    System.out.println(dto.getDetPremio().getValor() + "*" + valor);

                }
                str.close();
                rs.close();

            } else {
                String sql = "SELECT vlrbolivares FROM tipopremio where tipo=" + dto.getDetPremio().getTipoPremio().getTipo();
                PreparedStatement str;
                str = con.prepareStatement(sql);
                ResultSet rs = str.executeQuery();

                while (rs.next()) {

                    valor = rs.getInt(1);

                    valor = valor * dto.getDetPremio().getValor();
                    System.out.println(dto.getDetPremio().getValor() + "*" + valor);
                }
                str.close();
                rs.close();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorTipoPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return valor;
        }

    }

    /**
     * metodo que calucla el premio ganado por el nombre
     *
     * @param dto
     * @return
     */
    public PremioDto CalcularPremioBaseTipoNombre(PremioDto dto) {
        Connection con = null;
        int valor = 0;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            if (dto.getMonedaBaseDatos().equals("P")) {
                String sql = "SELECT vlrpesos,tipo FROM tipopremio where nombre='" + dto.getDetPremio().getTipoPremio().getNombre() + "'";
                PreparedStatement str;
                str = con.prepareStatement(sql);
                ResultSet rs = str.executeQuery();

                while (rs.next()) {

                    valor = rs.getInt(1);
                    valor = valor * dto.getDetPremio().getValor();
                    dto.getDetPremio().setPremio(valor);

                    dto.getDetPremio().getTipoPremio().setTipo(rs.getInt(2));

                }
                str.close();
                rs.close();

            } else {
                String sql = "SELECT vlrbolivares,tipo FROM tipopremio where nombre='" + dto.getDetPremio().getTipoPremio().getNombre() + "'";
                PreparedStatement str;
                str = con.prepareStatement(sql);
                ResultSet rs = str.executeQuery();

                while (rs.next()) {
                    valor = rs.getInt(1);
                    valor = valor * dto.getDetPremio().getValor();
                    dto.getDetPremio().setPremio(valor);
                    dto.getDetPremio().getTipoPremio().setTipo(rs.getInt(2));

                }
                str.close();
                rs.close();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorTipoPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return dto;
        }

    }

}
