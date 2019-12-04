/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author MI EQUIPO
 */
public class Movimiento extends Thread {

    private JFrame tablero;
    private JLabel asteroide1;
    private JLabel asteroide2;
    private JLabel gold;
    private JLabel ovni;
    private JTextField texto;

    public Movimiento(String nombre) {
        super(nombre);
    }

    public void cargarDatos(JFrame tablero, JLabel asteroide1,
            JLabel asteroide2, JLabel ovni, JLabel gold, JTextField texto) {
        this.tablero = tablero;
        this.asteroide1 = asteroide1;
        this.asteroide2 = asteroide2;
        this.gold = gold;
        this.ovni = ovni;
        this.texto = texto;
    }

    public void run() {
        int dormir = 3;
        int a = 630;
        int dir1 = (int) ((Math.random() * 350));
        int dir2 = (int) ((Math.random() * 350));
        int dir3 = (int) ((Math.random() * 350));

        while (true) {

            if (!ovni.getBounds().intersects(asteroide1.getBounds())
                    && !ovni.getBounds().intersects(asteroide2.getBounds())
                    && !ovni.getBounds().intersects(gold.getBounds())) {
                if (asteroide1.getLocation().x >= 0 || asteroide1.getLocation().x >= -50) {
                    try {
                        sleep(dormir);
                        a--;
                        asteroide1.setLocation(a, dir1);
                        asteroide2.setLocation(a, dir2 + 30);
                        gold.setLocation(a, dir3 + 50);
                    } catch (InterruptedException e) {
                    }
                }

                if (ovni.getBounds().intersects(gold.getBounds())) {
                    texto.setBackground(Color.GREEN);
                    texto.setText(texto.getText() + " LIVE ");
                    JOptionPane.showMessageDialog(null, null, "FELICITACIONES RESCATASTE AL ASTRONAUTA...", 0, new javax.swing.ImageIcon(getClass().getResource("/imagenes/winner.gif")));
                    stop();
                }
                if (asteroide1.getLocation().x == -50) {
                    run();
                }

            } else {
                texto.setBackground(Color.RED);
                texto.setText(texto.getText() + " DEAD ");
                JOptionPane.showMessageDialog(null, null, "QUE MAL EL ASTRONAUTA MURIO...", 0, new javax.swing.ImageIcon(getClass().getResource("/imagenes/GameOver.gif")));
                stop();
            }
        }
    }
}
