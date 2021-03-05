package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * Clase encargada de realizar una conexión a la base de datos MySQL.
 */
public class MySQL implements Connect {

    private static Connection connection = null;
    private static Configs conf = new Configs();

    /**
     * Conexíon a la base de datos MySQL.
     */
    public MySQL() {
        try {
            String USERNAME = conf.from("Properties").file("MySQL").get("USERNAME").toString();
            String PASSWORD = conf.from("Properties").file("MySQL").get("PASSWORD").toString();
            String DB_NAME = conf.from("Properties").file("MySQL").get("DB_NAME").toString();
            String DRIVER = conf.from("Properties").file("MySQL").get("DRIVER").toString();
            String URL = conf.from("Properties").file("MySQL").get("URL").toString() + DB_NAME + "?useTimezone=true&serverTimezone=UTC";

            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Parece que no tienes configurada la Base de Datos\nIngrese a Properties/MySQL para configurarla.", "¡Ups!", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Retorno de dicha conexión.
     *
     * @return Connextion Conexión con MySQL.
     */
    @Override
    public Connection getConnection() {
        return connection;
    }
}
