/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recurso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
public class Conexion {

    private static Pool pool;

    public static Pool getPool() {
        if (pool == null) {
            pool = new Pool();
        }
        return pool;
    }

    public static void main(String ar[]) {
        Pool pool = Conexion.getPool();
        Connection con = null;
        try {
            con = pool.getDataSource().getConnection();
            System.out.print("pase");
            if (con != null) {
                JOptionPane.showMessageDialog(null, "Me conecte");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }

    }

}
