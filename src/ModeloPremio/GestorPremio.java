/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloPremio;

import ModeloPlanLoteria.PlanLoteriaDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos esta clase implementa el patron singleton
 */
public class GestorPremio {

    private ArrayList<PremioDto> ganadores;
    private static GestorPremio instancia;

    private GestorPremio() {
    }

    public static GestorPremio getInstance() {
        if (instancia == null) {
            instancia = new GestorPremio();
        }
        return instancia;
    }

    /**
     * consulta la cantidad de chances que ha vendido cada persona en el sistema
     *
     * @param fecha
     * @return
     */
    public ArrayList<TicketDto> consultarVentasChance(String fecha, String moneda) {
        ArrayList<TicketDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT codigo,sum(vrl_apuesta) "
                    + "FROM ticket"
                    + " where "
                    + " fecha='" + fecha + "' and moneda='" + moneda + "' group by codigo";

            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                TicketDto dto = new TicketDto();
                dto.setCodigo(rs.getString(1));
                dto.setValor(rs.getInt(2));
                dto.setMoneda(moneda);
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
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    /**
     * consulta el total de pago de fecha y moneda
     *
     * @param fecha
     * @param moneda
     * @return
     */
    public long consultarTotalPagoFechaMoneda(String fecha, String moneda) {
        long total = 0;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT sum(p.total) "
                    + "FROM premio p"
                    + " where "
                    + " p.fecha='" + fecha + "' and p.moneda='" + moneda + "'";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                total = rs.getLong(1);
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
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return total;
        }

    }

    public ArrayList<PremioDto> adicionarNumeroLoteria(ArrayList<PremioDto> lista) {

        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            for (PremioDto dto : lista) {

                String sql = "SELECT numero "
                        + "FROM planloteria"
                        + " where cod_lot=" + dto.getDetPremio().getLoteria().getCodigo() + " and fecha='" + dto.getFecha() + "'";

                PreparedStatement str;
                str = con.prepareStatement(sql);
                ResultSet rs = str.executeQuery();
                while (rs.next()) {

                    dto.setNumeroGanador(rs.getString(1));
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
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    public ArrayList<PremioDto> consultarPremiosXFechaXSeriall(String fecha, String serial) {
        ArrayList<PremioDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.serial,p.fecha,p.moneda,p.total,p.pagado,p.consegure,"
                    + "d.loteria,d.tipo,d.valor,d.premio,"
                    + "l.nombre,"
                    + "t.nombre,"
                    + "p.codigo "
                    + "FROM premio p,detpremio d,loteria l,tipopremio t"
                    + " where p.serial=d.serial and "
                    + " p.fecha=d.fecha and "
                    + " d.loteria=l.codigo and "
                    + " t.tipo=d.tipo and "
                    + " p.fecha='" + fecha + "' and p.serial='" + serial + "' order by serial,tipo";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
            while (rs.next()) {
                PremioDto dto = new PremioDto();
              
                dto.setSerial(rs.getString(1));
                dto.setFecha(rs.getString(2));
                dto.setMonedaBaseDatos(rs.getString(3));
                dto.setTotalPagado(rs.getInt(4));
                dto.setTotalPagadoBaseDatos(rs.getInt(5));
                dto.setConsecutivo(rs.getInt(6));
                dto.getDetPremio().getLoteria().setCodigo(rs.getInt(7));
                dto.getDetPremio().getTipoPremio().setTipo(rs.getInt(8));
                dto.getDetPremio().setValor(rs.getInt(9));
                dto.getDetPremio().setPremio(rs.getInt(10));
                dto.getDetPremio().getLoteria().setNombre(rs.getString(11));
                dto.getDetPremio().getTipoPremio().setNombre(rs.getString(12));
                dto.getPersona().setCodigo(rs.getInt(13));
               
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
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }

    }

    public ArrayList<PremioDto> consultarPremiosXFechaXMoneda(String fecha, String moneda, int loteria) {
        ArrayList<PremioDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.serial,p.fecha,p.moneda,p.total,p.pagado,p.consegure,"
                    + "d.loteria,d.tipo,d.valor,d.premio,"
                    + "l.nombre,"
                    + "t.nombre,"
                    + "p.codigo "
                    + "FROM premio p,detpremio d,loteria l,tipopremio t"
                    + " where p.serial=d.serial and "
                    + " p.fecha=d.fecha and "
                    + " d.loteria=l.codigo and "
                    + " t.tipo=d.tipo and "
                    + " p.fecha='" + fecha + "' and p.moneda='" + moneda + "' and l.codigo=" + loteria + " order by serial,tipo";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();
            while (rs.next()) {
                PremioDto dto = new PremioDto();
                dto.setSerial(rs.getString(1));
                dto.setFecha(rs.getString(2));
                dto.setMonedaBaseDatos(rs.getString(3));
                dto.setTotalPagado(rs.getInt(4));
                dto.setTotalPagadoBaseDatos(rs.getInt(5));
                dto.setConsecutivo(rs.getInt(6));
                dto.getDetPremio().getLoteria().setCodigo(rs.getInt(7));
                dto.getDetPremio().getTipoPremio().setTipo(rs.getInt(8));
                dto.getDetPremio().setValor(rs.getInt(9));
                dto.getDetPremio().setPremio(rs.getInt(10));
                dto.getDetPremio().getLoteria().setNombre(rs.getString(11));
                dto.getDetPremio().getTipoPremio().setNombre(rs.getString(12));
                dto.getPersona().setCodigo(rs.getInt(13));
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
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }

    }

    public boolean validarExistenciaPremio(PremioDto prem) {
        ArrayList<PremioDto> lista = new ArrayList();
        Connection con = null;
        boolean valor = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT *"
                    + "FROM premio "
                    + " where  serial='" + prem.getSerial() + "' and fecha='" + prem.getFecha() + "' limit 1";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                valor = true;

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
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return valor;
        }
    }

    public ArrayList<PremioDto> listarPremioXFechaCodigo(String fecha, String serial) {

        ArrayList<PremioDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.serial,p.fecha,p.nuip,p.moneda,p.total,p.pagado,p.consegure,"
                    + "d.loteria,d.tipo,d.valor,d.premio,"
                    + "l.nombre,"
                    + "t.nombre "
                    + "FROM premio p,detpremio d,loteria l,tipopremio t"
                    + " where p.serial=d.serial and "
                    + " p.fecha=d.fecha and "
                    + " d.loteria=l.codigo and"
                    + " t.tipo=d.tipo and "
                    + " p.serial='" + serial + "' and p.fecha='" + fecha + "'";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PremioDto dto = new PremioDto();
                dto.setSerial(rs.getString(1));
                dto.setFecha(rs.getString(2));
                dto.getPersona().setNuip(rs.getInt(3));
                dto.setMonedaBaseDatos(rs.getString(4));
                dto.setTotalPagado(rs.getInt(5));
                dto.setTotalPagadoBaseDatos(rs.getInt(6));
                dto.setConsecutivo(rs.getInt(7));
                dto.getDetPremio().getLoteria().setCodigo(rs.getInt(8));
                dto.getDetPremio().getTipoPremio().setTipo(rs.getInt(9));
                dto.getDetPremio().setValor(rs.getInt(10));
                dto.getDetPremio().setPremio(rs.getInt(11));
                dto.getDetPremio().getLoteria().setNombre(rs.getString(12));
                dto.getDetPremio().getTipoPremio().setNombre(rs.getString(13));

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
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }

    }

    public ArrayList<PremioDto> consultarPremios() {
        ArrayList<PremioDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.serial,p.fecha,p.nuip,p.moneda,p.total,p.pagado,p.consegure,"
                    + "d.loteria,d.tipo,d.valor,d.premio,"
                    + "l.nombre,"
                    + "t.nombre "
                    + "FROM premio p,detpremio d,loteria l,tipopremio t"
                    + " where p.serial=d.serial and "
                    + " p.fecha=d.fecha and "
                    + " d.loteria=l.codigo and "
                    + " t.tipo=d.tipo";

            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PremioDto dto = new PremioDto();
                dto.setSerial(rs.getString(1));
                dto.setFecha(rs.getString(2));
                dto.getPersona().setNuip(rs.getInt(3));
                dto.setMonedaBaseDatos(rs.getString(4));
                dto.setTotalPagado(rs.getInt(5));
                dto.setTotalPagadoBaseDatos(rs.getInt(6));
                dto.setConsecutivo(rs.getInt(7));
                dto.getDetPremio().getLoteria().setCodigo(rs.getInt(8));
                dto.getDetPremio().getTipoPremio().setTipo(rs.getInt(9));
                dto.getDetPremio().setValor(rs.getInt(10));
                dto.getDetPremio().setPremio(rs.getInt(11));
                dto.getDetPremio().getLoteria().setNombre(rs.getString(12));
                dto.getDetPremio().getTipoPremio().setNombre(rs.getString(13));

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
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            return lista;
        }

    }

    public boolean registrarPremio(PremioDto dto) {
        Connection con = null;

        boolean resu = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            con.setAutoCommit(false);

            boolean x = this.registrarPremio1(dto, con);

            boolean y = this.registrarDetPremio(dto, con);
            boolean z = this.actualizarCantidad(dto, con);
            if (y & x & z) {
                resu = true;
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.print(ex.toString());
            throw ex;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return resu;
        }
    }

    /**
     * agrega los premios determinados a un premio
     *
     * @param dto
     * @return
     */
    public boolean agregarDetPremio(PremioDto dto) {
        Connection con = null;

        boolean x = false;
        boolean y = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            con.setAutoCommit(false);
            x = this.registrarDetPremio(dto, con);
            y = this.actualizarCantidad(dto, con);
            con.commit();

        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.print(ex.toString());

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return (x & y);
        }

    }

    public boolean actualizarCantidad(PremioDto dto, Connection con) {
        PreparedStatement stm = null;
        boolean exito = false;

        try {

            stm = con.prepareStatement("UPDATE premio SET total=total + " + dto.getDetPremio().getPremio() + " where serial='" + dto.getSerial() + "' and fecha='" + dto.getFecha() + "'"
            );

            int total = stm.executeUpdate();
            if (total > 0) {
                System.out.println("Entre a actualizar");
                exito = true;

            }
            stm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return exito;
    }

    private boolean registrarDetPremio(PremioDto dto, Connection con) throws SQLException {
        PreparedStatement stmt = null;
        boolean exito = false;

        stmt = con.prepareStatement("INSERT INTO detpremio ( serial,fecha,loteria,tipo,valor,premio,ruta) values (?, ?, ?,?,?,?,?)");

        stmt.setString(1, dto.getSerial());
        stmt.setString(2, dto.getFecha());
        stmt.setInt(3, dto.getDetPremio().getLoteria().getCodigo());
        stmt.setInt(4, dto.getDetPremio().getTipoPremio().getTipo());
        stmt.setInt(5, dto.getDetPremio().getValor());
        stmt.setInt(6, dto.getDetPremio().getPremio());
        stmt.setString(7, dto.getDetPremio().getLoteria().getRuta());

        int total = stmt.executeUpdate();

        if (total > 0) {

            exito = true;
        }

        stmt.close();
        return exito;
    }

    private boolean registrarPremio1(PremioDto dto, Connection con) throws SQLException {
        PreparedStatement stmt = null;
        boolean exito = false;

        stmt = con.prepareStatement("INSERT INTO premio ( serial,fecha,codigo,moneda,total,pagado) values (?, ?, ?,?,?,?)");

        stmt.setString(1, dto.getSerial());
        stmt.setString(2, dto.getFecha());
        stmt.setInt(3, dto.getPersona().getCodigo());
        stmt.setString(4, dto.getMonedaBaseDatos());
        stmt.setInt(5, dto.getTotalPagado());
        stmt.setInt(6, dto.getPagadoBaseDatos());

        int total = stmt.executeUpdate();

        if (total > 0) {

            exito = true;
        }

        stmt.close();
        return exito;
    }

    public boolean eliminarPremio(String serial, String fecha) {
        Connection con = null;

        boolean resu = false;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            con.setAutoCommit(false);
            resu = this.eliminarPremio1(serial, fecha, con);
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.print(ex.toString());
            throw ex;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return resu;
        }
    }

    private boolean eliminarPremio1(String serial, String fecha, Connection con) throws SQLException {

        String instruccion = "delete from premio where serial='" + serial + "' and fecha='" + fecha + "'";
        boolean val = false;
        PreparedStatement pre;
        pre = con.prepareStatement(instruccion);
        pre.execute();
        val = true;

        return val;
    }

    /**
     * lista todos los premios ganadores en el plan loteria estipulado
     *
     * @param dto
     * @return
     */
    public ArrayList<PremioDto> listarPremiosGanadores(PlanLoteriaDto dto) {
        this.ganadores = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();

            this.consultarGanadoresSuperPleno(dto, con);
            this.consultarGanadoresPleno(dto, con);
            this.consultarGanadoresPata(dto, con);
            this.consultarGanadoresUña(dto, con);
            this.consultarGanadoresCombinado(dto, con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorPremio.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            return this.ganadores;
        }

    }

    private void consultarGanadoresSuperPleno(PlanLoteriaDto dto, Connection con) throws SQLException {

        String sql = "SELECT i.serial,i.fecha,i.codigo,t.moneda,i.pleno,lo.ruta "
                + "FROM item i,lotxticket l,ticket t,loteria lo "
                + " where t.serial=i.serial and t.fecha=i.fecha and t.codigo=i.codigo and "
                + " i.serial=l.serial and "
                + " i.fecha=l.fecha and "
                + " i.codigo=l.codigo and l.cod_lot=" + dto.getCodigoLoteria() + " and lo.codigo=l.cod_lot "
                + " and i.fecha='" + dto.getFecha() + "' and "
                + "i.pleno>0 and i.numero='" + dto.getNumero() + "'";

        PreparedStatement str;
        str = con.prepareStatement(sql);

        ResultSet rs = str.executeQuery();

        while (rs.next()) {

            PremioDto premio = new PremioDto();
            premio.setSerial(rs.getString(1));
            premio.setFecha(rs.getString(2));
            premio.getPersona().setCodigo(rs.getInt(3));
            premio.setMoneda(rs.getString(4));
            premio.getDetPremio().getTipoPremio().setNombre("SUPER PLENO");
            premio.getDetPremio().setValor(rs.getInt(5));
            premio.getDetPremio().getLoteria().setCodigo(dto.getCodigoLoteria());
            premio.setPagado("No");
            premio.getDetPremio().getLoteria().setRuta(rs.getString(6));
            this.ganadores.add(premio);
        }
        str.close();
        rs.close();
    }

    private void consultarGanadoresPleno(PlanLoteriaDto dto, Connection con) throws SQLException {

        String sql = "SELECT i.serial,i.fecha,i.codigo,t.moneda,i.pleno,lo.ruta "
                + "FROM item i,lotxticket l,ticket t,loteria lo "
                + " where t.serial=i.serial and t.fecha=i.fecha and t.codigo=i.codigo and "
                + "i.serial=l.serial and "
                + " i.fecha=l.fecha and "
                + " i.codigo=l.codigo and l.cod_lot=" + dto.getCodigoLoteria() + " and lo.codigo=l.cod_lot  " + " and i.fecha='" + dto.getFecha() + "' and "
                + "i.pleno>0 and i.numero ='" + dto.retornarTresUltimosNumeros() + "'";

        PreparedStatement str;
        str = con.prepareStatement(sql);

        ResultSet rs = str.executeQuery();

        while (rs.next()) {

            PremioDto premio = new PremioDto();
            premio.setSerial(rs.getString(1));
            premio.setFecha(rs.getString(2));
            premio.getPersona().setCodigo(rs.getInt(3));
            premio.setMoneda(rs.getString(4));
            premio.getDetPremio().getTipoPremio().setNombre("PLENO");
            premio.getDetPremio().setValor(rs.getInt(5));
            premio.getDetPremio().getLoteria().setCodigo(dto.getCodigoLoteria());
            premio.setPagado("No");
            premio.getDetPremio().getLoteria().setRuta(rs.getString(6));
            this.ganadores.add(premio);
        }
        str.close();
        rs.close();
    }

    private void consultarGanadoresPata(PlanLoteriaDto dto, Connection con) throws SQLException {
        String sql = "SELECT i.serial,i.fecha,i.codigo,t.moneda,i.pata,lo.ruta "
                + "FROM item i,lotxticket l,ticket t,loteria lo "
                + " where t.serial=i.serial and t.fecha=i.fecha and t.codigo=i.codigo and "
                + "i.serial=l.serial and "
                + " i.fecha=l.fecha and "
                + " i.codigo=l.codigo and l.cod_lot=" + dto.getCodigoLoteria() + " and lo.codigo=l.cod_lot  " + "and lo.codigo=l.cod_lot  " + " and i.fecha='" + dto.getFecha() + "' and "
                + "i.pata>0 and i.numero like '%" + dto.retornarDosUltimosNumeros() + "'";

        PreparedStatement str;
        str = con.prepareStatement(sql);

        ResultSet rs = str.executeQuery();

        while (rs.next()) {

            PremioDto premio = new PremioDto();
            premio.setSerial(rs.getString(1));
            premio.setFecha(rs.getString(2));
            premio.getPersona().setCodigo(rs.getInt(3));
            premio.setMoneda(rs.getString(4));
            premio.getDetPremio().getTipoPremio().setNombre("PATA");
            premio.getDetPremio().setValor(rs.getInt(5));
            premio.getDetPremio().getLoteria().setCodigo(dto.getCodigoLoteria());
            premio.setPagado("No");
            premio.getDetPremio().getLoteria().setRuta(rs.getString(6));
            this.ganadores.add(premio);
        }
        str.close();
        rs.close();
    }

    private void consultarGanadoresUña(PlanLoteriaDto dto, Connection con) throws SQLException {
        String sql = "SELECT i.serial,i.fecha,i.codigo,t.moneda,i.uña,lo.ruta "
                + "FROM item i,lotxticket l,ticket t,loteria lo "
                + " where t.serial=i.serial and t.fecha=i.fecha and t.codigo=i.codigo and "
                + "i.serial=l.serial and "
                + " i.fecha=l.fecha and "
                + " i.codigo=l.codigo and l.cod_lot=" + dto.getCodigoLoteria() + " and lo.codigo=l.cod_lot  " + " and i.fecha='" + dto.getFecha() + "' and "
                + "i.uña>0 and i.numero like '%" + dto.retornarUnoUltimosNumeros() + "'";

        PreparedStatement str;
        str = con.prepareStatement(sql);

        ResultSet rs = str.executeQuery();

        while (rs.next()) {

            PremioDto premio = new PremioDto();
            premio.setSerial(rs.getString(1));
            premio.setFecha(rs.getString(2));
            premio.getPersona().setCodigo(rs.getInt(3));
            premio.setMoneda(rs.getString(4));
            premio.getDetPremio().getTipoPremio().setNombre("UÑA");
            premio.getDetPremio().setValor(rs.getInt(5));
            premio.getDetPremio().getLoteria().setCodigo(dto.getCodigoLoteria());
            premio.setPagado("No");
            premio.getDetPremio().getLoteria().setRuta(rs.getString(6));
            this.ganadores.add(premio);
        }
        str.close();
        rs.close();
    }

    private void consultarGanadoresCombinado(PlanLoteriaDto dto, Connection con) throws SQLException {
        String sql = "SELECT i.serial,i.fecha,i.codigo,t.moneda,i.combinado,i.numero,lo.ruta "
                + "FROM item i,lotxticket l,ticket t,loteria lo "
                + " where t.serial=i.serial and t.fecha=i.fecha and t.codigo=i.codigo and "
                + "i.serial=l.serial and "
                + " i.fecha=l.fecha and "
                + " i.codigo=l.codigo and l.cod_lot=" + dto.getCodigoLoteria() + " and lo.codigo=l.cod_lot  " + " and i.fecha='" + dto.getFecha() + "' and "
                + "i.combinado>0 ";

        PreparedStatement str;
        str = con.prepareStatement(sql);

        ResultSet rs = str.executeQuery();

        while (rs.next()) {
            if (isCombinado(rs.getString(6), dto)) {
                PremioDto premio = new PremioDto();
                premio.setSerial(rs.getString(1));
                premio.setFecha(rs.getString(2));
                premio.getPersona().setCodigo(rs.getInt(3));
                premio.setMoneda(rs.getString(4));
                if (rs.getString(6).length() == 4) {
                    premio.getDetPremio().getTipoPremio().setNombre("SUPER COMBINADO");
                }
                if (rs.getString(6).length() == 3) {
                    premio.getDetPremio().getTipoPremio().setNombre("COMBINADO");
                }

                premio.getDetPremio().setValor(rs.getInt(5));
                premio.getDetPremio().getLoteria().setCodigo(dto.getCodigoLoteria());
                premio.setPagado("No");
                premio.getDetPremio().getLoteria().setRuta(rs.getString(7));
                this.ganadores.add(premio);
            }
        }
        str.close();
        rs.close();
    }

    private boolean isCombinado(String string, PlanLoteriaDto dto) {
        boolean valor = false;
        if (string.length() == 4) {
            char[] numero = dto.getNumero().toCharArray();
            char[] itemNum = string.toCharArray();
            int v = 0;
            for (int i = 0; i < itemNum.length; i++) {
                for (int j = 0; j < numero.length; j++) {
                    if (numero[j] != '@') {
                        if (numero[j] == itemNum[i]) {
                            numero[j] = '@';
                            v++;
                            break;
                        }
                    }
                }
            }
            valor = (v == numero.length);
        }
        if (string.length() == 3) {
            char[] numero = dto.retornarTresUltimosNumeros().toCharArray();
            char[] itemNum = string.toCharArray();
            int v = 0;
            for (int i = 0; i < itemNum.length; i++) {
                for (int j = 0; j < numero.length; j++) {
                    if (numero[j] != '@') {
                        if (numero[j] == itemNum[i]) {
                            numero[j] = '@';
                            v++;
                            break;
                        }
                    }
                }
            }
            valor = (v == numero.length);
        }

        return valor;
    }
}
