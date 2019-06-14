package proyectosqlcontornos;

import proyectosqlcontornos.Libraries.LibreriaIO;
import proyectosqlcontornos.sql.SqlConection;

/**
 *
 * @author david
 */
public class ProyectoSqlContornos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SqlConection.getInstance().crearDatosPorDefecto();
        LibreriaIO.monstrarJugadores(SqlConection.getInstance().ConseguirJugadores());
        LibreriaIO.monstrarClubs(SqlConection.getInstance().ConseguirClub());
    }

}
