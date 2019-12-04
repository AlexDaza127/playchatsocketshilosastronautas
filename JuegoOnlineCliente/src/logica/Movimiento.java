/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;
import static java.lang.Thread.sleep;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author MI EQUIPO
 */
public class Movimiento extends Thread {

    public JFrame tablero;
    public JLabel asteroide1;
    public JLabel asteroide2;
    public JLabel gold;
    public JLabel nave;
    private JTextField texto;

    public Movimiento(String nombre) {
        super(nombre);
    }

    public void cargarDatos(JFrame tablero, JLabel asteroide1,
            JLabel asteroide2, JLabel nave, JLabel gold, JTextField texto) {
        this.tablero = tablero;
        this.asteroide1 = asteroide1;
        this.asteroide2 = asteroide2;
        this.nave = nave;
        this.gold = gold;
        this.texto = texto;
    }

    public void run() {

        int dormir = 3;
        int a = 630;
        int dir1 = (int) ((Math.random() * 350));
        int dir2 = (int) ((Math.random() * 350));
        int dir3 = (int) ((Math.random() * 350));

        while (true) {
            if (!nave.getBounds().intersects(asteroide1.getBounds())
                    && !nave.getBounds().intersects(asteroide2.getBounds())) {

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

                if (nave.getBounds().intersects(gold.getBounds())) {
                    texto.setBackground(Color.GREEN);
                    texto.setText(texto.getText() + "    LIVE ");
                    JOptionPane.showMessageDialog(null, null, "FFELICITACIONES RESCATASTE AL ASTRONAUTA...", 0, new javax.swing.ImageIcon(getClass().getResource("/imagenes/winner.gif")));
                    stop();
                }

                if (asteroide1.getLocation().x == -50) {
                    run();
                }

            } else {
                texto.setBackground(Color.RED);
                texto.setText(texto.getText() + "    DEAD ");
                JOptionPane.showMessageDialog(null, null, "QUE MAL EL ASTRONAUTA MURIO...", 0, new javax.swing.ImageIcon(getClass().getResource("/imagenes/GameOver.gif")));
                stop();
            }
        }
    }

}
