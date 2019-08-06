package poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuiJuego extends JFrame {

    MenuInicio menuInicio;
    MouseInicio mouseInicio = new MouseInicio();
    FaseDeJuego faseDeJuego;

    final int widthWindow = 1024, heightWindow = 700;
    public GuiJuego()
    {
        imprimirMenuInicio();
        setTitle("Poker");
        this.setResizable(false);
        this.setSize(widthWindow, heightWindow);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void imprimirMenuInicio()
    {
        this.getContentPane().removeAll();

        menuInicio = new MenuInicio();
        menuInicio.inicializar();
        menuInicio.setPreferredSize(new Dimension(widthWindow, heightWindow));
        menuInicio.botonPrueba.addMouseListener(mouseInicio);

        add(menuInicio);

        this.repaint();
        this.revalidate();
    }
    public void imprimirFase1()
    {
        this.getContentPane().removeAll();
        this.setBackground(Color.BLACK);
        faseDeJuego = new FaseDeJuego();
        faseDeJuego.imprimir();

        this.add(faseDeJuego);

        this.repaint();
        this.revalidate();
    }

    public class MouseInicio implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
                imprimirFase1();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}