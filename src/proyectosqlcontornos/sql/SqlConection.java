package proyectosqlcontornos.sql;

/*
 Librerias necesarias para nuestra aplicacion
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    /**
     * Inserta las columnas de las tablas jugadores
     *
     * @param codJugador
     * @param nombre
     * @param codClub
     * @return
     */
    public boolean insertarJugador(Integer codJugador, String nombre, String codClub) {
        Boolean hecho = false;
        try {

            Connection connection = SqlConection.getInstance().getConexion();

            Statement statement = connection.createStatement();
            String aux = "insert into Jugadores  values(" + codJugador + ",'" + nombre + "','" + codClub + "');";
            System.out.println("insertar jugador " + aux);
            statement.executeUpdate(aux);

            statement.close();
            connection.close();
            hecho = true;

        } catch (SQLException ex) {
            System.out.println(ex);
            hecho = false;
        }

        return hecho;

    }

    /**
     * Inserta las columnas de las tablas de club
     *
     * @param codClub
     * @param nombre
     * @param mote
     * @return
     */
    public boolean insertarClub(String codClub, String nombre, String mote) {
        Boolean hecho = false;
        try {

            Connection connection = SqlConection.getInstance().getConexion();

            Statement statement = connection.createStatement();
            String aux = "insert into Club values('" + codClub + "','" + nombre + "','" + mote + "');";
            System.out.println("insert Club " + aux);
            statement.executeUpdate(aux);

            statement.close();
            connection.close();
            hecho = true;

        } catch (SQLException ex) {
            System.out.println(ex);
            hecho = false;
        }

        return hecho;

    }

    /**
     * Inserta las estructura de datos y los datos necesarios para comprobar la
     * aplicacion
     *
     * @return
     */
    public int crearDatosPorDefecto() {
        boolean hecho = false;
        int contador = 0;
        hecho = crearEstructuraDatos();
        if (true == hecho) {
            contador = contador + crearDatosPorDefectoClub();
            contador = contador + crearDatosPorDefectoJugador();
        }
        return contador;
    }

    private int crearDatosPorDefectoClub() {

        int contador = 0;

        if (insertarClub("c1", "Barsa", "Polacos")) {
            contador++;
        }
        if (insertarClub("c2", "Celta", "Portugueses")) {
            contador++;
        }
        if (insertarClub("c3", "Madrid", "Merengues")) {
            contador++;
        }
        if (insertarClub("c4", "Bilbao", "Leones")) {
            contador++;
        }

        return contador;
    }

    private int crearDatosPorDefectoJugador() {

        int contador = 0;

        if (insertarJugador(1, "Messi", "c1")) {
            contador++;
        }
        if (insertarJugador(2, "Aspas", "c2")) {
            contador++;
        }
        if (insertarJugador(3, "Ramos", "c3")) {
            contador++;
        }
        if (insertarJugador(4, "Munain", "c4")) {
            contador++;
        }

        return contador;
    }

    /**
     * Borra los datos de un club en particular.
     *
     * @param codClub
     */
    public boolean borrardatoClubPorCodigo(String codClub) {
        boolean hecho = false;
        try {

            Connection connection = SqlConection.getInstance().getConexion();

            Statement statement = connection.createStatement();
            String aux = "delete from Club where codClub = '" + codClub + "';";
            System.out.println("Borrado Club " + aux);
            statement.executeUpdate(aux);

            statement.close();
            connection.close();
            hecho = true;
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return hecho;
    }

    /**
     * Borra los datos de un jugador en particular.
     *
     * @param codJugador
     */
    public boolean borrardatoJugadorPorCodigo(int codJugador) {
        boolean hecho = false;
        try {

            Connection connection = SqlConection.getInstance().getConexion();

            Statement statement = connection.createStatement();
            String aux = "delete from Jugador where codJugador = '" + codJugador + "';";
            System.out.println("Borrado Jugador " + aux);
            statement.executeUpdate(aux);

            statement.close();
            connection.close();
            hecho = true;
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return hecho;
    }

    /**
     * Modifica los datos de un jugador en la base de datos.
     *
     * @param codJugador
     */
    public boolean modificarJugadorPorCodigo(int codJugador, String nombre, String codClub) {
        boolean hecho = false;
        try {

            Connection connection = SqlConection.getInstance().getConexion();

            Statement statement = connection.createStatement();
            String aux = "update Jugadores set nombre='" + nombre + "',codClub='" + codClub + "' where codJugadores= " + codJugador + ";";
            System.out.println("Modifica Jugador " + aux);
            statement.executeUpdate(aux);

            statement.close();
            connection.close();
            hecho = true;
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return hecho;
    }

    /**
     * Modifica los datos de un club en la base de datos.
     *
     * @param codClub
     */
    public boolean modificarJugadorPorCodigo(String codClub, String nombre, String mote) {
        boolean hecho = false;
        try {

            Connection connection = SqlConection.getInstance().getConexion();

            Statement statement = connection.createStatement();
            String aux = "update Club set nombre='" + nombre + "',mote='" + mote + "' where codClub= " + codClub + ";";
            System.out.println("Modifica club " + aux);
            statement.executeUpdate(aux);

            statement.close();
            connection.close();
            hecho = true;
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return hecho;
    }

    /**
     * Metodo que recupera los datos jugadores y los mete en un arraylist para
     * poder utilizarlos despues.
     *
     * @return
     */
    public ArrayList<String[]> ConseguirJugadores() {
        ArrayList<String[]> auxArrlist = new ArrayList<String[]>();

        try {
            Connection conexion = SqlConection.getInstance().getConexion();
            String consulta = "select codJugadores,Jugadores.Nombre,Club.Nombre from jugadores,club where Jugadores.codClub = Club.codClub ;";
            Statement statement = conexion.createStatement();

            System.out.println(consulta);
            ResultSet rs = statement.executeQuery(consulta);

            while (rs.next()) {
                String[] aux = new String[3];
                aux[0] = rs.getString(1);
                aux[1] = rs.getString(2);
                aux[2] = rs.getString(3);

                auxArrlist.add(aux);

            }
            rs.close();
            statement.close();
            conexion.close();

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return auxArrlist;

    }

    /**
     * Metodo que recupera los datos clubs y los mete en un arraylist para poder
     * utilizarlos despues.
     *
     * @return
     */
    public ArrayList<String[]> ConseguirClub() {
        ArrayList<String[]> auxArrlist = new ArrayList<String[]>();

        try {
            Connection conexion = SqlConection.getInstance().getConexion();
            String consulta = "select codClub,Nombre,mote from club;";
            Statement statement = conexion.createStatement();

            System.out.println(consulta);
            ResultSet rs = statement.executeQuery(consulta);

            while (rs.next()) {
                String[] aux = new String[3];
                aux[0] = rs.getString(1);
                aux[1] = rs.getString(2);
                aux[2] = rs.getString(3);

                auxArrlist.add(aux);

            }
            rs.close();
            statement.close();
            conexion.close();

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return auxArrlist;

    }

}
