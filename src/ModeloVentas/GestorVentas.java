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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class GestorVentas {

    /**
     * modifa el abono de una venta especifica
     *
     * @param dto
     * @return
     */
    public boolean modificarVentaAbono(VentaDto dto) {
        PreparedStatement stm = null;
        boolean exito = false;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql="UPDATE ventas SET "
                    + "abono=" + dto.getAbono()
                    + " where fecha='" + dto.getFecha() + "' and moneda='" + dto.getMoneda()
                + "' and ruta='" + dto.getRuta() + "' and tipo='" + dto.getTipo() + "' and codigo_persona=" + dto.getPersona().getCodigo();
            
            stm = con.prepareStatement(sql);

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return exito;
        }
    }

    /**
     * metodo que consulta una venta especifica.
     *
     * @param dto
     * @return
     */
    public VentaDto consultarVentaEspecifica(VentaDto dto) {
  
        Connection con = null;

        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT venta,recaudo,saldo,comision,abono,nticket"
                    + " FROM ventas where fecha='" + dto.getFecha() + "' and moneda='" + dto.getMoneda()
                    + "' and ruta='" + dto.getRuta() + "' and tipo='" + dto.getTipo() + "' and codigo_persona=" + dto.getPersona().getCodigo();
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            if (rs.next()) {
                dto.setVenta(rs.getInt(1));
                dto.setRecaudo(rs.getInt(2));
                dto.setSaldo(rs.getInt(3));
                dto.setComision(rs.getInt(4));
                dto.setAbono(rs.getInt(5));
                dto.setnTicket(rs.getInt(6));
            } else {
                dto = null;
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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return dto;
        }

    }

    /**
     * registra los saldos registrados en el sistema
     *
     * @param lista
     */
    public boolean registrarSaldos(ArrayList<VentaDto> lista, int ano, int mes) {
        if (lista.isEmpty()) {
            return false;
        }
        PreparedStatement pst = null;
        Date hoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaFormato = formato.format(hoy);
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            for (VentaDto dto : lista) {

                //si ya se le registro un saldo no lo vuelva a registrar.
                if (!this.consultarSaldos(ano, mes, dto.getMoneda(), dto.getPersona().getCodigo())) {

                    if (dto.getPersona().getTipo().equalsIgnoreCase("V") && dto.getSaldo() < 0) {

                        pst = con.prepareStatement("insert into saldos (ano,mes,codigo,fecha,saldo,moneda) values(?,?,?,?,?,?)");
                        pst.setInt(1, ano);
                        pst.setInt(2, mes);
                        pst.setInt(3, dto.getPersona().getCodigo());
                        pst.setString(4, fechaFormato);
                        pst.setInt(5, dto.getSaldo());
                        pst.setString(6, dto.getMoneda());
                        pst.executeUpdate();
                    } else {

                        pst = con.prepareStatement("insert into saldos (ano,mes,codigo,fecha,saldo,moneda) values(?,?,?,?,?,?)");
                        pst.setInt(1, ano);
                        pst.setInt(2, mes);
                        pst.setInt(3, dto.getPersona().getCodigo());
                        pst.setString(4, fechaFormato);
                        pst.setInt(5, 0);
                        pst.setString(6, dto.getMoneda());
                        pst.executeUpdate();
                    }
                    ;
                }

            }

            pst.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return true;
        }

    }

    /**
     * consulta si ya se generaron los saldos para el mes el año y la moneda
     * escogida
     *
     * @param ano
     * @param mes
     * @param moneda
     * @return
     */
    public boolean consultarSaldos(int ano, int mes, String moneda, int codigo) {
        Connection con = null;
        boolean resultado = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT * FROM saldos where ano=" + ano + " and mes=" + mes + " and moneda='" + moneda + "' and codigo=" + codigo+" order by codigo";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                resultado = true;
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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return resultado;
        }

    }

    /**
     * añade a las ventas los saldos anteriores de cada persona este metodo es
     * utilizado para consultar los saldos del mes anterios que tiene los
     * empleados registrados en el sistema
     *
     * @param lista
     * @return
     */
    public ArrayList<VentaDto> añadirSaldoAnterior(ArrayList<VentaDto> lista, int mes) {
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            for (VentaDto dto : lista) {

                String sql = "SELECT saldo FROM saldos where codigo=" + dto.getPersona().getCodigo() + " and moneda='" + dto.getMoneda() + "' and mes != " + mes + "  order by mes desc limit 1";
                PreparedStatement str;
                str = con.prepareStatement(sql);
                ResultSet rs = str.executeQuery();

                while (rs.next()) {
                    dto.setSaldoAnterio(rs.getInt(1));

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }

    }

    public ArrayList<VentaDto> consultarVentasFecha(String fecha, String moneda, String ruta, String tipo) {

        ArrayList<VentaDto> lista = new ArrayList();
        Connection con = null;

        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.nuip,p.codigo,p.nombre,p.apellido,p.direccion,p.telefono,p.tipo,p.activo,p.porcentaje"
                    + ",v.fecha,v.venta,v.recaudo,v.saldo,v.moneda,v.comision,v.abono,v.nticket"
                    + " FROM ventas v,Persona p where v.codigo_persona=p.codigo and v.fecha='" + fecha + "' and v.moneda='" + moneda + "' and v.ruta='" + ruta + "' and v.tipo='" + tipo + "' order by p.codigo";
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
                VentaDto v = new VentaDto();
                v.setFecha(rs.getString(10));
                v.setVenta(rs.getInt(11));
                v.setRecaudo(rs.getInt(12));
                v.setSaldo(rs.getInt(13));
                v.setMoneda(rs.getString(14));
                v.setComision(rs.getInt(15));
                v.setAbono(rs.getInt(16));
                v.setnTicket(rs.getInt(17));

                v.setPersona(dto);

                lista.add(v);

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }

    }

    public ArrayList<VentaDto> consultarVentasXRangoFechaResumen(String fechaI, String fechaF, String moneda, String ruta, String tipo) {
        ArrayList<VentaDto> lista = new ArrayList();
        Connection con = null;
        try {

            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.nuip,p.codigo,p.nombre,p.apellido,p.direccion,p.telefono,p.tipo,p.activo,p.porcentaje"
                    + ",v.fecha,sum(v.venta),sum(v.recaudo),sum(v.saldo),v.moneda,sum(v.comision),sum(v.abono),sum(v.nticket)"
                    + " FROM ventas v,Persona p where v.codigo_persona=p.codigo and v.fecha>='" + fechaI + "' and v.fecha<='" + fechaF + "' and v.moneda='" + moneda + "' "
                    + "and v.ruta='" + ruta + "' and v.tipo='" + tipo + "' group by p.codigo order by p.codigo";
            PreparedStatement str;
            System.out.println(sql);
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
                VentaDto v = new VentaDto();
                v.setFecha(rs.getString(10));
                v.setVenta(rs.getInt(11));
                v.setRecaudo(rs.getInt(12));
                v.setSaldo(rs.getInt(13));
                v.setMoneda(rs.getString(14));
                v.setComision(rs.getInt(15));
                v.setAbono(rs.getInt(16));
                v.setnTicket(rs.getInt(17));

                v.setPersona(dto);

                lista.add(v);

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }

    }

    private void consultarVentasXMesBolivares(String fechai, String fechaf, ArrayList<VentaDto> lista, Connection con) throws SQLException {
        String sql = "SELECT p.nuip,p.codigo,p.nombre,p.apellido,p.direccion,p.telefono,p.tipo,p.activo,p.porcentaje"
                + ",v.fecha,sum(v.venta),sum(v.recaudo),sum(v.saldo),v.moneda,sum(v.comision),sum(v.abono),sum(v.nticket)"
                + " FROM ventas v,Persona p where v.codigo_persona=p.codigo and v.fecha>='" + fechai + "' and v.fecha<='" + fechaf + "' and v.moneda='Bolivares' "
                + " group by p.codigo";
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
            VentaDto v = new VentaDto();
            v.setFecha(rs.getString(10));
            v.setVenta(rs.getInt(11));
            v.setRecaudo(rs.getInt(12));
            v.setSaldo(rs.getInt(13));
            v.setMoneda(rs.getString(14));
            v.setComision(rs.getInt(15));
            v.setAbono(rs.getInt(16));
            v.setnTicket(rs.getInt(17));

            v.setPersona(dto);

            lista.add(v);

        }
        str.close();
        rs.close();
    }

    private void consultarVentasXMesPesos(String fechai, String fechaf, ArrayList<VentaDto> lista, Connection con) throws SQLException {
        String sql = "SELECT p.nuip,p.codigo,p.nombre,p.apellido,p.direccion,p.telefono,p.tipo,p.activo,p.porcentaje"
                + ",v.fecha,sum(v.venta),sum(v.recaudo),sum(v.saldo),v.moneda,sum(v.comision),sum(v.abono),sum(v.nticket)"
                + " FROM ventas v,Persona p where v.codigo_persona=p.codigo and v.fecha>='" + fechai + "' and v.fecha<='" + fechaf + "' and v.moneda='Pesos' order by p.codigo"
                + " group by p.codigo";
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
            VentaDto v = new VentaDto();
            v.setFecha(rs.getString(10));
            v.setVenta(rs.getInt(11));
            v.setRecaudo(rs.getInt(12));
            v.setSaldo(rs.getInt(13));
            v.setMoneda(rs.getString(14));
            v.setComision(rs.getInt(15));
            v.setAbono(rs.getInt(16));
            v.setnTicket(rs.getInt(17));

            v.setPersona(dto);

            lista.add(v);

        }
        str.close();
        rs.close();
    }

    /**
     * este metodo consulta las ventas por mes tanto para bolivares como para pesos.
     * @param fechai
     * @param fechaf
     * @return 
     */
    public ArrayList<VentaDto> consultarVentasXMes(String fechai, String fechaf) {
        ArrayList<VentaDto> lista = new ArrayList();
        Connection con = null;
        try {

            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            this.consultarVentasXMesBolivares(fechai, fechaf, lista, con);
            this.consultarVentasXMesPesos(fechai, fechaf, lista, con);

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
    
    
    /**
     * requerimiento nuevo
     * @param fechaInicial
     * @param fechaFinal
     * @param moneda
     * @param ruta
     * @param tipo
     * @return 
     */
      public ArrayList<VentaDto> consultarVentasXRangoFechaGeneralBolivares(String fechaInicial, String fechaFinal) {
        ArrayList<VentaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.nuip,p.codigo,p.nombre,p.apellido,p.direccion,p.telefono,p.tipo,p.activo,p.porcentaje"
                    + ",v.fecha,v.venta,v.recaudo,v.saldo,v.moneda,v.comision,v.abono,v.nticket,v.fecha"
                    + " FROM ventas v,Persona p where v.codigo_persona=p.codigo and v.fecha>='" + fechaInicial + "' and v.fecha<='" + fechaFinal + "'  and v.moneda='Bolivares' "
                    + " order by v.fecha";
            PreparedStatement str;
            System.out.println(sql);
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
                VentaDto v = new VentaDto();
                v.setFecha(rs.getString(10));
                v.setVenta(rs.getInt(11));
                v.setRecaudo(rs.getInt(12));
                v.setSaldo(rs.getInt(13));
                v.setMoneda(rs.getString(14));
                v.setComision(rs.getInt(15));
                v.setAbono(rs.getInt(16));
                v.setnTicket(rs.getInt(17));
                v.setFecha(rs.getString(18));

                v.setPersona(dto);

                lista.add(v);

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }
    
    
    /**
     * requerimiento nuevo
     * @param fechaInicial
     * @param fechaFinal
     * @param moneda
     * @param ruta
     * @param tipo
     * @return 
     */
      public ArrayList<VentaDto> consultarVentasXRangoFechaGeneralPesos(String fechaInicial, String fechaFinal) {
        ArrayList<VentaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.nuip,p.codigo,p.nombre,p.apellido,p.direccion,p.telefono,p.tipo,p.activo,p.porcentaje"
                    + ",v.fecha,v.venta,v.recaudo,v.saldo,v.moneda,v.comision,v.abono,v.nticket,v.fecha"
                    + " FROM ventas v,Persona p where v.codigo_persona=p.codigo and v.fecha>='" + fechaInicial + "' and v.fecha<='" + fechaFinal + "'  and v.moneda='Pesos' "
                    + " order by v.fecha";
            PreparedStatement str;
            System.out.println(sql);
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
                VentaDto v = new VentaDto();
                v.setFecha(rs.getString(10));
                v.setVenta(rs.getInt(11));
                v.setRecaudo(rs.getInt(12));
                v.setSaldo(rs.getInt(13));
                v.setMoneda(rs.getString(14));
                v.setComision(rs.getInt(15));
                v.setAbono(rs.getInt(16));
                v.setnTicket(rs.getInt(17));
                v.setFecha(rs.getString(18));

                v.setPersona(dto);

                lista.add(v);

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }
    
      
      
    

    public ArrayList<VentaDto> consultarVentasXRangoFechaDetallado(String fechaInicial, String fechaFinal, String moneda, String ruta, String tipo) {
        ArrayList<VentaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.nuip,p.codigo,p.nombre,p.apellido,p.direccion,p.telefono,p.tipo,p.activo,p.porcentaje"
                    + ",v.fecha,v.venta,v.recaudo,v.saldo,v.moneda,v.comision,v.abono,v.nticket,v.fecha"
                    + " FROM ventas v,Persona p where v.codigo_persona=p.codigo and v.fecha>='" + fechaInicial + "' and v.fecha<='" + fechaFinal + "' and v.moneda='" + moneda + "' "
                    + "and v.ruta='" + ruta + "' and v.tipo='" + tipo + "' order by v.fecha";
            PreparedStatement str;
            System.out.println(sql);
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
                VentaDto v = new VentaDto();
                v.setFecha(rs.getString(10));
                v.setVenta(rs.getInt(11));
                v.setRecaudo(rs.getInt(12));
                v.setSaldo(rs.getInt(13));
                v.setMoneda(rs.getString(14));
                v.setComision(rs.getInt(15));
                v.setAbono(rs.getInt(16));
                v.setnTicket(rs.getInt(17));
                v.setFecha(rs.getString(18));

                v.setPersona(dto);

                lista.add(v);

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }
    
    /**
     * requerimiento nuevo 14/11/2016
     * @param fechaInicial
     * @param fechaFinal
     * @param moneda
     * @param ruta
     * @param tipo
     * @param nacionalidad
     * @return 
     */
     public ArrayList<VentaDto> consultarVentasXRangoFechaNacionalidad(String fechaInicial, String fechaFinal, String nacionalidad) {
        ArrayList<VentaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.nuip,p.codigo,p.nombre,p.apellido,p.direccion,p.telefono,p.tipo,p.activo,p.porcentaje"
                    + ",v.fecha,v.venta,v.recaudo,v.saldo,v.moneda,v.comision,v.abono,v.nticket,v.fecha"
                    + " FROM ventas v,Persona p where v.codigo_persona=p.codigo and v.fecha>='" + fechaInicial + "' and v.fecha<='" + fechaFinal + "' "
                    + "and p.nacionalidad='"+nacionalidad+"'  order by v.fecha";
            PreparedStatement str;
            System.out.println(sql);
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
                VentaDto v = new VentaDto();
                v.setFecha(rs.getString(10));
                v.setVenta(rs.getInt(11));
                v.setRecaudo(rs.getInt(12));
                v.setSaldo(rs.getInt(13));
                v.setMoneda(rs.getString(14));
                v.setComision(rs.getInt(15));
                v.setAbono(rs.getInt(16));
                v.setnTicket(rs.getInt(17));
                v.setFecha(rs.getString(18));

                v.setPersona(dto);

                lista.add(v);

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public boolean registrarVenta(VentaDto dto) {
        PreparedStatement pst = null;
        Connection con = null;
        boolean valor = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            pst = con.prepareStatement("insert into ventas (fecha,codigo_persona,venta,recaudo,saldo,moneda,comision,abono,ruta,tipo,nticket) values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, dto.getFecha());
            pst.setInt(2, dto.getPersona().getCodigo());
            pst.setInt(3, dto.getVenta());
            pst.setInt(4, dto.getRecaudo());
            pst.setInt(5, dto.getSaldo());
            pst.setString(6, dto.getMoneda());
            pst.setInt(7, dto.getComision());
            pst.setInt(8, dto.getAbono());
            pst.setString(9, dto.getRuta());
            pst.setString(10, dto.getTipo());
            pst.setInt(11, dto.getnTicket());

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return valor;
        }

    }

    public boolean registrarSaldoInicial(VentaDto ventadto, int año, int mes) {
        PreparedStatement pst = null;
        Connection con = null;
        boolean valor = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            pst = con.prepareStatement("insert into saldos (ano,mes,codigo,fecha,saldo,moneda) values(?,?,?,?,?,?)");
            pst.setInt(1, año);
            pst.setInt(2, mes);
            pst.setInt(3, ventadto.getPersona().getCodigo());
            pst.setString(4, ventadto.getFecha());
            pst.setInt(5, ventadto.getSaldoAnterio());
            pst.setString(6, ventadto.getMoneda());
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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return valor;
        }

    }

    public boolean eliminar(String fecha, int codigo, String moneda, String ruta, String tipo) {
        Connection con = null;
        String instruccion = "delete from ventas where fecha='" + fecha + "' and codigo_persona=" + codigo + " and moneda='" + moneda + "'" + " and ruta='" + ruta + "'" + " and tipo='" + tipo + "'";
        boolean val = false;
        PreparedStatement pre;
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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return val;
        }
    }

    public ArrayList<VentaDto> consultarVentasPersonaXRangoFechaResumen(String fechaInicial, String fechaFinal, String moneda, String ruta, PersonaDto dto) {
        ArrayList<VentaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.nuip,p.codigo,p.nombre,p.apellido,p.direccion,p.telefono,p.tipo,p.activo,p.porcentaje"
                    + ",v.fecha,v.venta,v.recaudo,v.saldo,v.moneda,v.comision,v.abono,v.nticket,v.fecha"
                    + " FROM ventas v,Persona p where v.codigo_persona=p.codigo and v.fecha>='" + fechaInicial + "' and v.fecha<='" + fechaFinal + "' and v.moneda='" + moneda + "' "
                    + "and v.ruta='" + ruta + "' and v.codigo_persona='" + dto.getCodigo() + "' order by  v.fecha";
            PreparedStatement str;
            System.out.println(sql);
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PersonaDto dto1 = new PersonaDto();
                dto1.setNuip(rs.getInt(1));
                dto1.setCodigo(rs.getInt(2));
                dto1.setNombre(rs.getString(3));
                dto1.setApellido(rs.getString(4));
                dto1.setDireccion(rs.getString(5));
                dto1.setTelefono(rs.getString(6));
                dto1.setTipo(rs.getString(7));
                dto1.setActivo(rs.getString(8));
                dto1.setPorcentaje(rs.getInt(9));
                VentaDto v = new VentaDto();
                v.setFecha(rs.getString(10));
                v.setVenta(rs.getInt(11));
                v.setRecaudo(rs.getInt(12));
                v.setSaldo(rs.getInt(13));
                v.setMoneda(rs.getString(14));
                v.setComision(rs.getInt(15));
                v.setAbono(rs.getInt(16));
                v.setnTicket(rs.getInt(17));
                v.setFecha(rs.getString(18));

                v.setPersona(dto1);

                lista.add(v);

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
                    Logger.getLogger(GestorVentas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

}
