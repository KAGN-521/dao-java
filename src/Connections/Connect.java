package Connections;

import java.sql.Connection;

/**
 * Intefaz con los métodos que debe tener una conexión a la base de datos para
 * que esta pueda ser utilizada.
 */
public interface Connect {

    /**
     * Método que retorna una conexión a la base de datos.
     *
     * @return Connection Conexión a la base de datos.
     */
    public Connection getConnection();
}
