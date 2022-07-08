package dao;
import java.io.InputStream;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.Properties;

import java.util.logging.Level;

import java.util.logging.Logger;

public class Conexion {

    protected static Connection cnx = null;

    public static Connection conectar() throws Exception {

        try {

            String user = "sa";

            String pwd = "@abc123@";

            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

            String url = "jdbc:sqlserver://localhost:1433;databaseName=bdPizzaHut";

            Class.forName(driver).newInstance();

            cnx = DriverManager.getConnection(url, user, pwd);

        } catch (SQLException e) {

            System.out.println("Error en la Conexion" + e.getMessage());

        }

        return cnx;

    }

    public static void cerrarCnx() throws Exception {

        if (Conexion.cnx != null) {

            cnx.close();

        }

    }

    public static void main(String[] args) {

        Conexion cones = new Conexion();

        try {

            cones.conectar();

            if (cones.cnx == null) {

                System.out.println("Apagado");

            } else {

                System.out.println("Encendido");

            }

        } catch (SQLException e) {

            System.out.println("" + e.getMessage());

        } catch (Exception ex) {

            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
