/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LATITUDE E6420
 */
public class Conexion {

    private Connection miConexion;
    private static final String url = "jdbc:mysql://localhost/facturacion";
    private static final String user = "root";
    private static final String pass = "";

    public Connection conectarConexion() {
        try {
            miConexion = (Connection) DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return miConexion;
    }

    public Connection cerrarConexion() {
        try {
            if (miConexion != null) {
                if (miConexion.isClosed() == false) {
                    miConexion.close();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return miConexion;
    }

}
