package proyectosqlcontornos.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author david
 */
public class SqlConection {
    
    private static SqlConection instancia = null;
/**
     * Conecta la base de datos con la aplicacion para poder configurarla y
     * diseñarla.
     *
     * @return
     * @throws SQLException
     */
    
    public Connection getConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection("jdbc:sqlite:database.db");

        return conexion;
    }
    
    
}
