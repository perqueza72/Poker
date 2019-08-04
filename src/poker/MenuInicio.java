package poker;


import javax.swing.*;
import java.awt.*;

public class MenuInicio extends JPanel {

    protected JButton botonPrueba;
    private JPanel centro;
    private JLabel fondo;
    private Color colorCentro;
    private Insets marginTextCenter, nonMargin;
    private Font fontTitle, fontButton, fontOther;

    private final int widthWindow = 1024, heightWindow = 768, tBoton = 100;
    private boolean pasar = false;

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
        this.estilos();

        centro = new JPanel();
        centro.setLayout(new GridBagLayout());
        centro.setBackground(colorCentro);

        GridBagConstraints cC = new GridBagConstraints();
        cC.weighty = tBoton;
        cC.insets = marginTextCenter;

        //BOTON
        botonPrueba = new JButton("JUGAR");
        botonPrueba.setName("boton");
        botonPrueba.setPreferredSize(new Dimension( 2*tBoton, tBoton));
        botonPrueba.setBackground(colorCentro);
        botonPrueba.setFont(fontButton);

        //TITULO
        JLabel titulo = new JLabel("Poker");
        titulo.setText("titulo");
        titulo.setFont(fontTitle);

        //OTROS TEXTOS
        JLabel instrucciones = new JLabel("Instrucciones");
        instrucciones.setName("instrucciones");
        instrucciones.setFont(fontOther);

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
        colorCentro = new Color(255, 255,255,120);
        nonMargin = new Insets(0,0,0,0);
        fontTitle = new Font ("Comic sans", Font.BOLD | Font.ITALIC, 30);
        fontOther = new Font ("TimesRoman", Font.BOLD, 18);
        fontButton = new Font ("TimesRoman", Font.BOLD, 20);
    }

    public void pintar()
    {
        this.add(fondo);
        fondo.add(centro);

    }


}
