package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static ConexionBD instance;

    private String url;
    private String user;
    private String password;
    private String driver;

    private ConexionBD() {
        loadProperties();
        loadDriver();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("database.properties no encontrado");
            }
            Properties props = new Properties();
            props.load(input);
            this.url      = props.getProperty("db.url");
            this.user     = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
            this.driver   = props.getProperty("db.driver");
        } catch (IOException e) {
            throw new RuntimeException("Error cargando database.properties", e);
        }
    }

    private void loadDriver() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se pudo cargar " + driver, e);
        }
    }

    public static synchronized ConexionBD getInstance() {
        if (instance == null) {
            instance = new ConexionBD();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}