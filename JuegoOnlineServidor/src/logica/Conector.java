package logica;

/**
 * Clase en donde se declaran todos los metodos y procedimientos a seguir con el
 * chat
 *
 * @version 1.0
 * @created 10-may-2015 04:27:33 p.m.
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author MICHAEL DAZA
 */
public class Conector extends Thread {

    /**
     * Espacio para reservar el Socket
     */
    private Socket s;
    /**
     * Espacio para reservar el SeverSocket
     */
    private ServerSocket ss;
    /**
     * Espacio para reservar el flujo de entrada
     */
    private InputStreamReader entradaSocket;
    /**
     * Espacio reservado para la lectura de los mensajes
     */
    private DataOutputStream salida;
    /**
     * Espacio reservado para la salida de los mensajes
     */
    private BufferedReader entrada;
    /**
     * Declaración del puerto a utilizar
     */
    final int PUERTO = 8000;

    /**
     * Metodo costructor
     */
    public Conector(String nombre) {
        super(nombre);
    }

    /**
     * metodo utilizado para enviar los mensajes
     */
    public void enviarMSG(String msg) {

        try {
            this.salida.writeUTF(msg + "\n");

        } catch (Exception e) {
        }
    }

    public String leerMSG() {
        try {
            return this.entrada.readLine();
        } catch (Exception e) {
        };
        return null;
    }

    /**
     * metodo para desconectar el chat
     */
    public void desconectar() {
        try {
            s.close();
        } catch (Exception e) {
        }

        try {
            ss.close();
        } catch (Exception e) {
        }
    }

    /**
     * metodo para correr el thread
     */
    public void run() {
        String text = "test";
        try {
            this.ss = new ServerSocket(PUERTO);
            this.s = ss.accept();

            //Creacion de entrada de datos para lectura de mensajes
            this.entradaSocket = new InputStreamReader(s.getInputStream());
            this.entrada = new BufferedReader(entradaSocket);

            //Cracion de salida de datos para el envio de mensajes
            this.salida = new DataOutputStream(s.getOutputStream());

            while (true) {
                text = entrada.readLine();
                System.out.println(text);
                Juego.Resumen.setText(Juego.Resumen.getText() + "\n" + text);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

    }
}
