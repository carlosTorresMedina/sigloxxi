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
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author carlos
 */
public class Pool {

    private DataSource dataSource;
 private  String driver = "org.gjt.mm.mysql.Driver";//es el driver varia segun la base de datos que usemos

 private  String url = "jdbc:mysql://localhost/sigloxxi";
 private  String contrasena = "1234";
 private  String usuario = "administrador";
    
 
//    private  String url = "jdbc:mysql://sandbox2.ufps.edu.co/ufps_76";
//    private  String contrasena = "ufps_29";
//    private  String usuario = "ufps_76";
  
        
    
    
    
    
    public Pool(){
    this.inicializarDataSource();
    }
    
   private void inicializarDataSource(){
        BasicDataSource basic=new BasicDataSource();
               basic.setDriverClassName(driver);
               basic.setUsername(usuario);
               basic.setPassword(contrasena);
               basic.setUrl(url);
               basic.setMaxActive(5);         
               this.dataSource=basic;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

   

   
    

}
