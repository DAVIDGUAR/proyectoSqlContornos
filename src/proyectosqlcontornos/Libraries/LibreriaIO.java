package proyectosqlcontornos.Libraries;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class LibreriaIO {

    public static void monstrarJugadores(ArrayList<String[]> arrjugadores) {

        for (int i = 0; i < arrjugadores.size(); i++) {
            String[] aux = arrjugadores.get(i);
            System.out.println("CodJugador: " + aux[0] + ", Nombre: " + aux[1] + ", Club: " + aux[2]);

        }

    }

    public static void monstrarClubs(ArrayList<String[]> arrjugadores) {

        for (int i = 0; i < arrjugadores.size(); i++) {
            String[] aux = arrjugadores.get(i);
            System.out.println("CodClub: " + aux[0] + ", Nombre: " + aux[1] + ", MoteClub: " + aux[2]);
        }
    }
}
