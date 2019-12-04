/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 * Clase en donde se declaran todos los metodos y procedimientos a seguir con el
 * chat
 *
 * @version 1.0
 * @created 10-may-2015 04:27:33 p.m.
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author MICHAEL DAZA
 */
public class Conector extends Thread {
    /**
     * Espacio para reservar el Socket
     */
    public Socket s;
    /**
     * Espacio para reservar el SeverSocket
     */
    public ServerSocket ss;
    /**
     * Espacio para reservar el flujo de entrada
     */
    public InputStreamReader entradaSocket;
    /**
     * Espacio reservado para la lectura de los mensajes
     */
    public DataOutputStream salida;
    /**
     * Espacio reservado para la salida de los mensajes
     */
    public BufferedReader entrada;
    /**
     * Declaracin del puerto a utilizar
     */
    final int PUERTO = 8000;
    /**
     * Metodo constructor
     */
    public Conector(String ip) {
        try {

            this.s = new Socket(ip, this.PUERTO);

            //Creacion de entrada de datos para lectura de mensajes
            this.entradaSocket = new InputStreamReader(s.getInputStream());
            this.entrada = new BufferedReader(entradaSocket);

            //Cracion de salida de datos para el envio de mensajes
            this.salida = new DataOutputStream(s.getOutputStream());
            this.salida.writeUTF("Conectado  \n");
        } catch (Exception e) {
        }
    }

    /**
     * metodo utilizado para enviar los mensajes
     */
    public void enviarMSG(String msg) {

        try {
            this.salida = new DataOutputStream(s.getOutputStream());
            this.salida.writeUTF(msg + "\n");
        } catch (Exception e) {
            System.out.println("Problema al enviar");
        }

    }

    /**
     * metodo para correr el thread
     */
    @Override
    public void run() {
        String texto;
        while (true) {
            try {
                texto = entrada.readLine();
                Juego.Resumen.setText(Juego.Resumen.getText() + "\n" + texto);

            } catch (Exception e) {
            }
        }
    }
}
