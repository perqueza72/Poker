package poker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiJuego extends JFrame {

    MenuInicio menuInicio;

    final int widthWindow = 1024, heightWindow = 768;
    public GuiJuego()
    {
        imprimirJuego();
        setTitle("Poker");
        this.setResizable(false);
        this.setSize(widthWindow, heightWindow);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void imprimirJuego()
    {
        this.getContentPane().removeAll();
        menuInicio = new MenuInicio();
        menuInicio.inicializar();
        menuInicio.setPreferredSize(new Dimension(widthWindow, heightWindow));
        add(menuInicio);

        this.repaint();
        this.revalidate();
    }
}