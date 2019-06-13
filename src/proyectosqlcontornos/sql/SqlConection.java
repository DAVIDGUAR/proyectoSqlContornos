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
    
    /**
     * Metodo que devuelve la instancia del objeto unico( uso el patron de
     * diseño Singleton) para evitar multiples accesos simultaneos a la base de
     * datos.
     *
     * @return
     */
    public static SqlConection getInstance() {
        if (null == instancia) {
            instancia = new SqlConection();

        }
        return instancia;

    }
}
