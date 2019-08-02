package poker;

import javafx.scene.layout.BorderWidths;

import javax.swing.*;
import java.awt.*;

public class MenuInicio extends JPanel {

    JButton botonPrueba;
    JPanel centro;
    JLabel fondo;
    Color colorCentro;
    Insets marginTextCenter, nonMargin;
    final int widthWindow = 1024, heightWindow = 768, tBoton = 100;

    public void inicializar()
    {
        this.setBounds(0,0,widthWindow,heightWindow);

        fondo = new JLabel();

        this.setLayout(new GridBagLayout());

        fondo.setBounds(0,0,widthWindow,heightWindow);
        fondo.setIcon(new ImageIcon("src/mazo/fondo3.jpg"));
        fondo.setLayout(new GridBagLayout());
        this.GUIzonaCentral();
        this.pintar();
    }

    public void GUIzonaCentral()
    {
        estilos();

        centro = new JPanel();
        centro.setLayout(new GridBagLayout());
        centro.setBackground(colorCentro);

        GridBagConstraints cC = new GridBagConstraints();
        cC.weighty = tBoton;
        cC.insets = marginTextCenter;

        botonPrueba = new JButton("Iniciar");
        botonPrueba.setPreferredSize(new Dimension( 2*tBoton, tBoton));

        JLabel titulo = new JLabel("Poker");
        JLabel instrucciones = new JLabel("Instrucciones");
        titulo.setPreferredSize(new Dimension(30,30));
        //titulo.setFont(null);
        cC.gridy=0;
        centro.add(titulo, cC);
        cC.gridy++;
        centro.add(instrucciones, cC);
        cC.gridy++;

        cC.insets = nonMargin;
        centro.add(botonPrueba, cC);
    }
    public void estilos()
    {
        marginTextCenter = new Insets(10,10,tBoton/2,10);
        colorCentro = new Color(255, 255,255,100);
        nonMargin = new Insets(0,0,0,0);
    }
    public void pintar()
    {
        this.add(fondo);
        fondo.add(centro);

    }

}
