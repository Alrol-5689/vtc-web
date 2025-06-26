package com.vtc.base_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mariadb://localhost:3306/vtc-autogestion"; 
    private static final String USUARIO = "root"; // o el usuario que uses
    private static final String CONTRASENA = "";  // o la contraseña si la pusiste en XAMPP

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    // Para probar desde consola
    public static void main(String[] args) {
        try (Connection conn = conectar()) {
            System.out.println("✅ Conexión establecida correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión:");
            e.printStackTrace();
        }
    }
}
