/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wendellgonzalez
 */
public class DBconnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ClinicaApp;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "WendellAGPB@0305_";
    
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
//    String url = "jdbc:sqlserver://localhost:1433;databaseName=ClinicaApp;encrypt=true;trustServerCertificate=true";
//    String user = "sa";
//    String password = "WendellAGPB@0305_";
//    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    Connection cn;
//
//    /**
//     *
//     * @return @throws SQLException
//     * @throws ClassNotFoundException
//     * @throws InstantiationException
//     * @throws IllegalAccessException
//     */
//    public Connection obtenerConexion() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        if (cn == null) {
//            try {
//                Class.forName(driver);
//                cn = (Connection) DriverManager.getConnection(url, user, password);
//                System.out.println("Estamos conectados con exito.");
//
//            } catch (SQLException | ClassNotFoundException ex) {
//                System.out.println("Error en la conexion :" + ex);
//                Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
//                return null;
//            }
//        }
//        return cn;
//    }
//
//    public void desconectar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        try {
//            cn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
   
}
