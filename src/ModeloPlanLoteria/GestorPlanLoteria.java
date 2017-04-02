/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloPlanLoteria;

import ModeloLoteria.LoteriaDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos modulo plan loteria esta clase implementa el patron fachada
 */
public class GestorPlanLoteria {

    private static GestorPlanLoteria instance;

    private GestorPlanLoteria() {
    }

    public static GestorPlanLoteria getInstance() {
        if (instance == null) {
            instance = new GestorPlanLoteria();
        }
        return instance;
    }

    /**
     * elimina un plan de loterias
     *
     * @return
     */
    public boolean eliminarPlanLoteria(int codigoLoteria, String fechaPlan) {
        Connection con = null;
        String instruccion = "delete from planloteria where cod_lot =" + codigoLoteria + " and fecha='" + fechaPlan + "'";
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
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return val;
        }

    }

    public boolean registrarNumeroSeriePlanLoteria(PlanLoteriaDto dto) {
        System.out.println("fecha " + dto.getFecha());
        PreparedStatement stm = null;
        boolean exito = false;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            stm = con.prepareStatement("UPDATE planloteria SET "
                    + " numero='" + dto.getNumero() + "', serie='" + dto.getSerie() + "'" + ", cerrado='" + dto.getCerrado() + "' "
                    + "WHERE cod_lot=" + dto.getCodigoLoteria() + " and fecha='" + dto.getFecha() + "'");

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
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            return exito;
        }
    }

    public boolean modificarHoraFechaPlanLoteria(int codigo, String fechaL, String fechaHora) {
        PreparedStatement stm = null;
        boolean exito = false;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            stm = con.prepareStatement("UPDATE planloteria SET "
                    + " cierre='" + fechaHora + "'"
                    + "WHERE cod_lot=" + codigo + " and fecha='" + fechaL + "'");

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
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return exito;

        }
    }

    /**
     * modifica un plan loteria registrado en el sistema
     *
     * @param dto
     * @return
     */
    public boolean modificarPlanLoteria(String fecha, int codigo, PlanLoteriaDto dto) {
        PreparedStatement stm = null;
        boolean exito = false;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            stm = con.prepareStatement("UPDATE planloteria SET "
                    + "cod_lot=" + dto.getCodigoLoteria()
                    + ",fecha='" + dto.getFecha() + "'"
                    + ",cierre='" + dto.getCierreBaseDatos() + "'"
                    + "WHERE cod_lot=" + codigo + " and fecha='" + fecha + "'");

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
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return exito;
        }

    }

    public ArrayList<LoteriaDto> listarPlanLoteria() {
        ArrayList<LoteriaDto> listaLoterias = new ArrayList();

        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.fecha,p.cierre,p.numero,p.serie,p.escrutado,p.cerrado,l.nombre,l.codigo FROM planloteria p,loteria l where p.cod_lot=l.codigo ";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                ArrayList<PlanLoteriaDto> lista = new ArrayList();
                PlanLoteriaDto plan = new PlanLoteriaDto();
                plan.setFecha(rs.getString(1));

                plan.setCierreBaseDatos(rs.getString(2));
                plan.setNumero(rs.getString(3));
                plan.setSerie(rs.getString(4));
                plan.setEscrutado(rs.getString(5));
                plan.setCerrado(rs.getString(6));

                lista.add(plan);
                LoteriaDto dto = new LoteriaDto();
                dto.setNombre(rs.getString(7));
                dto.setCodigo(rs.getInt(8));

                dto.setPlanLoterias(lista);
                listaLoterias.add(dto);
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
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return listaLoterias;
        }
    }

    /**
     * Consulta todos los planes loteria
     *
     * @param fecha
     * @return
     */
    public ArrayList<LoteriaDto> ListarPlanLoteriaNumero(String fecha) {
        //To change body of generated methods, choose Tools | Templates.
        ArrayList<LoteriaDto> listaLoterias = new ArrayList();
        ArrayList<PlanLoteriaDto> lista = null;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.fecha,Date_format(p.cierre,'%Y-%m-%d %H:%i'),p.numero,p.serie,p.escrutado,p.cerrado,l.nombre,l.codigo FROM planloteria p,loteria l where p.cod_lot=l.codigo and p.fecha='" + fecha + "'"
                    + " and p.numero IS NOT NULL";

            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                lista = new ArrayList();
                PlanLoteriaDto plan = new PlanLoteriaDto();
                plan.setFecha(rs.getString(1));
                plan.setCierreBaseDatos(rs.getString(2));
                plan.setNumero(rs.getString(3));
                plan.setSerie(rs.getString(4));
                plan.setEscrutado(rs.getString(5));
                plan.setCerrado(rs.getString(6));

                lista.add(plan);
                LoteriaDto dto = new LoteriaDto();
                dto.setNombre(rs.getString(7));
                dto.setCodigo(rs.getInt(8));

                dto.setPlanLoterias(lista);
                listaLoterias.add(dto);
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
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return listaLoterias;
        }
    }

    /**
     * Consulta todos los planes loteria segun una fecha estipulada
     *
     * @param fecha
     * @return
     */
    public ArrayList<LoteriaDto> listarPlanLoteriaXFecha(String fecha) {
        ArrayList<LoteriaDto> listaLoterias = new ArrayList();
        ArrayList<PlanLoteriaDto> lista = null;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT p.fecha,Date_format(p.cierre,'%Y-%m-%d %H:%i'),p.numero,p.serie,p.escrutado,p.cerrado,l.nombre,l.codigo FROM planloteria p,loteria l where p.cod_lot=l.codigo and p.fecha='" + fecha + "'";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                lista = new ArrayList();
                PlanLoteriaDto plan = new PlanLoteriaDto();
                plan.setFecha(rs.getString(1));
                plan.setCierreBaseDatos(rs.getString(2));
                plan.setNumero(rs.getString(3));
                plan.setSerie(rs.getString(4));
                plan.setEscrutado(rs.getString(5));
                plan.setCerrado(rs.getString(6));

                lista.add(plan);
                LoteriaDto dto = new LoteriaDto();
                dto.setNombre(rs.getString(7));
                dto.setCodigo(rs.getInt(8));

                dto.setPlanLoterias(lista);
                listaLoterias.add(dto);
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
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return listaLoterias;
        }
    }

    /**
     * consulta los planes de loteria por loteria
     *
     * @param dto
     * @return
     */
    public ArrayList<PlanLoteriaDto> consultarPlanLoteriaFechaCodigo(String fecha, int codigo) {
        ArrayList<PlanLoteriaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT fecha,cierre,numero,serie,escrutado,cerrado FROM planloteria where cod_lot=" + codigo + " and fecha='" + fecha + "' order by fecha DESC";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PlanLoteriaDto plan = new PlanLoteriaDto();
                plan.setFecha(rs.getString(1));
                plan.setCierreBaseDatos(rs.getString(2));
                plan.setNumero(rs.getString(3));
                plan.setSerie(rs.getString(4));
                plan.setEscrutado(rs.getString(5));
                plan.setCerrado(rs.getString(6));

                lista.add(plan);

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
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }
    }

    /**
     * consulta los planes de loteria por loteria
     *
     * @param dto
     * @return
     */
    public LoteriaDto consultarPlanLoteriaXLoteria(LoteriaDto dto) {
        Date hoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String fechaHoy = formato.format(hoy);
        ArrayList<PlanLoteriaDto> lista = new ArrayList();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            String sql = "SELECT fecha, Date_format(cierre,'%Y-%m-%d %H:%i'),numero,serie,escrutado,cerrado FROM planloteria where cod_lot=" + dto.getCodigo() + " and fecha>='" + fechaHoy + "'";
            PreparedStatement str;
            str = con.prepareStatement(sql);
            ResultSet rs = str.executeQuery();

            while (rs.next()) {
                PlanLoteriaDto plan = new PlanLoteriaDto();
                plan.setFecha(rs.getString(1));
                plan.setCierreBaseDatos(rs.getString(2));
                plan.setNumero(rs.getString(3));
                plan.setSerie(rs.getString(4));
                plan.setEscrutado(rs.getString(5));
                plan.setCerrado(rs.getString(6));

                lista.add(plan);

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
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dto.setPlanLoterias(lista);
            return dto;
        }

    }

    /**
     * registra un plan loteria en el sistema
     *
     * @param dto
     * @return
     */
    public String registrarPlanLoteria(LoteriaDto dto) {

        if (dto.getPlanLoterias().isEmpty()) {
            return "En el rango de  fechas estipulada no cae el dia que juega la loteria" + dto.getCodigoNombre();
        }

        boolean valor = false;
        PreparedStatement pst = null;
        PlanLoteriaDto planerror = new PlanLoteriaDto();
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement("insert into planloteria (cod_lot,fecha,cierre,escrutado,cerrado)  values(?,?,?,?,?)");

            for (PlanLoteriaDto plan : dto.getPlanLoterias()) {
                planerror = plan;
                pst.setInt(1, dto.getCodigo());
                pst.setString(2, plan.getFecha());
                pst.setString(3, plan.getCierre());
                pst.setString(4, plan.getEscrutado());
                pst.setString(5, plan.getCerrado());
                pst.executeUpdate();
            }
            con.commit();
            pst.close();
            valor = true;
        } catch (SQLException exc) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
            }
            exc.printStackTrace();

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (valor) {
                return "Los planes loteria se registraron exitosamente";
            } else {
                return "El plan loteria :" + dto.getCodigoNombre() + "\n"
                        + "ya tiene programado planes dentro del rango estipulado";
            }
        }

    }

    public void actualizarProcesoEscrutinio(PlanLoteriaDto dto) {
        PreparedStatement stm = null;
        Connection con = null;
        try {
            con = Recurso.Conexion.getPool().getDataSource().getConnection();
            stm = con.prepareStatement("UPDATE planloteria SET "
                    + "escrutado='S' WHERE cod_lot=" + dto.getCodigoLoteria() + " and fecha='" + dto.getFecha() + "'");

            int total = stm.executeUpdate();
            if (total > 0) {
                System.out.println("se actualizo con exito el escrutado");

            }
            stm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GestorPlanLoteria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}
