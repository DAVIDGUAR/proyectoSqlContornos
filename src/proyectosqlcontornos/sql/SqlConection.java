package proyectosqlcontornos.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    /**
     * Crea las estructuras de datos necesarias para el programa
     *
     * @return
     */
    private Boolean crearEstructuraDatos() {

        Boolean hecho = false;

        // consigo la conexion 
        Connection connection;
        try {
            connection = getConexion();

            // consigo el statement
            Statement statement = connection.createStatement();
            // añado las instrucicones para crear la base de datos
            statement.addBatch("DROP TABLE if exists Jugadores;");
            statement.addBatch("DROP TABLE if exists Club;");
            statement.addBatch("CREATE TABLE Jugadores\n"
                    + "    ( \n"
                    + "     CodJugadores integer (10)  NOT NULL , \n"
                    + "     Nombre VARCHAR (15) , \n"
                    + "     codClub VARCHAR(5),\n"
                    + "          PRIMARY KEY ( codJugadores ),\n"
                    + "          FOREIGN KEY (codClub) REFERENCES Club(codClub)\n"
                    + "    ) ;");
            statement.addBatch("CREATE TABLE Club \n"
                    + "    ( \n"
                    + "     codClub VARCHAR (5)  NOT NULL ,\n"
                    + "     Nombre VARCHAR2 (20) unique ,  \n"
                    + "     Mote VARCHAR2 (20) , \n"
                    + "          PRIMARY KEY ( codClub )\n"
                    + "         ) ;");

            statement.executeBatch();

            statement.close();
            connection.close();
            hecho = true;

        } catch (SQLException ex) {
            Logger.getLogger(SqlConection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hecho;
    }
}
