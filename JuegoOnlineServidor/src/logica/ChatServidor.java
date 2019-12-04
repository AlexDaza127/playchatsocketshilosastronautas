/**
 * Clase principal
 *
 * @version 1.0
 * @created 10-may-2015 04:27:33 p.m.
 */
package logica;

/**
 *
 * @author MICHAEL DAZA
 */
public class ChatServidor {

    public static Conector servidor;

    public static void initServer() {
        servidor = new Conector("Conectado");
    servidor.start();
    }
}
