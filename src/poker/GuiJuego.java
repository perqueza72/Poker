package poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class GuiJuego extends JFrame {

    final int widthWindow = 1024, heightWindow = 700;
    private JLabel fondo;
    private MouseInicio mouseInicio;
    GuiJuego()
    {
        mouseInicio = new MouseInicio();
        this.imprimirMenuInicio();
        setTitle("Poker");
        this.setResizable(false);
        this.setSize(widthWindow, heightWindow);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    private void imprimirMenuInicio()
    {
        this.getContentPane().removeAll();

        MenuInicio menuInicio = new MenuInicio();
        menuInicio.inicializar();
        menuInicio.setPreferredSize(new Dimension(widthWindow, heightWindow));
        menuInicio.botonPrueba.addMouseListener(mouseInicio);

        add(menuInicio);

        this.repaint();
        this.revalidate();
    }
    private void imprimirFase()
    {
        this.getContentPane().removeAll();
        fondo = new JLabel(new ImageIcon("src/mazo/try2.jpg"));
        fondo.setLayout(null);
        fondo.setBounds(0,0,widthWindow,heightWindow);

        FaseDeJuego faseDeJuego = new FaseDeJuego();
        faseDeJuego.imprimir();

        fondo.add(faseDeJuego);
        this.add(fondo);
        this.repaint();
        this.revalidate();
    }

    public class MouseInicio implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
                imprimirFase();
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