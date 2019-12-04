/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author MI EQUIPO
 */
public class ChatCliente {

    public static Conector cliente;

    public static void initCliente(String ip) {
        cliente = new Conector(ip);
        cliente.start();
    }
}
