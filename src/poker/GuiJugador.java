package poker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiJugador extends JPanel {

    private final double auxTJugador = Math.round(1.2*(new ImageIcon("src/mazo/fichaVuelta.jpeg").getIconHeight()));
    protected final int widthWindow = 1024, tJugador = (int)auxTJugador;
    protected int montoUsuario;

    private JPanel guiCartas, guiMonto;
    private JLabel txtApostar, fondoCartas;
    protected JLabel txtMonto;
    private Color colorPanelJugadores;
    protected JButton btnApostar;
    protected JTextField txtBoxapostarNDinero;

    private Insets regularMargin, nonMargin;
    private Font fontMonto;

        public void inicializar(boolean juegaAqui, Jugador jugador)
        {
            montoUsuario = jugador.getFondos();

            this.setLayout(new BorderLayout(0,0));
            this.setBackground(colorPanelJugadores);
            this.setPreferredSize(new Dimension(widthWindow/2,tJugador));

            this.estilos();
            this.guiCartas(juegaAqui, jugador.getBarajaMia());
            this.guiMonto();
            this.pintar(juegaAqui);
        }

        public JLabel darVuelta(JLabel carta)
        {
            carta.setIcon(new ImageIcon("src/mazo/fichaVuelta2.jpeg"));
            return carta;
        }

        public void guiCartas(boolean juegaAqui, ArrayList<JLabel> cartaJugador)
        {
            fondoCartas = new JLabel(new ImageIcon("src/mazo/ponerMazo.jpg"));
            fondoCartas.setLayout(new GridBagLayout());


            GridBagConstraints cCartas = new GridBagConstraints();
            cCartas.gridx=0;
            cCartas.insets=new Insets(10,0,10,0);

            guiCartas = new JPanel();
            guiCartas.setLayout(new GridBagLayout());

            for(int i=0; i<2; i++) {
                if(juegaAqui)
                    guiCartas.add(cartaJugador.get(i), cCartas);
                else
                    guiCartas.add(darVuelta(cartaJugador.get(i)), cCartas);
                cCartas.gridx += 1;
            }

            guiCartas.setOpaque(false);
        }


        public void guiMonto()
        {
            guiMonto = new JPanel();
            guiMonto.setLayout(new GridBagLayout());
            GridBagConstraints cMonto = new GridBagConstraints();


            //TEXTOS
            txtMonto = new JLabel("Monto: "+ montoUsuario);
            txtMonto.setFont(fontMonto);

            txtApostar = new JLabel("Cuanto quieres apostar?");

            //CAJAS DE INPUTS

            txtBoxapostarNDinero = new JTextField(10);
            txtBoxapostarNDinero.setPreferredSize(new Dimension(guiMonto.getWidth()/2,20));
            //BOTONES

            btnApostar = new JButton("BID");


            //PINTAR
            cMonto.gridy=0;
            guiMonto.add(txtMonto, cMonto);
            cMonto.gridy++;
            cMonto.insets = regularMargin;
            guiMonto.add(txtApostar, cMonto);
            cMonto.insets = nonMargin;
            cMonto.gridy++;
            guiMonto.add(txtBoxapostarNDinero, cMonto);
            cMonto.insets = regularMargin;
            cMonto.gridy++;
            guiMonto.add(btnApostar, cMonto);
        }

        public void pintar(boolean juegaAqui)
        {
            if(juegaAqui) {
                this.add(Box.createVerticalStrut(-30), BorderLayout.NORTH);
                this.add(guiMonto, BorderLayout.EAST);

                for(int i=0; i<this.getComponentCount();i++)
                    this.getComponent(i).setBackground(colorPanelJugadores);}

            fondoCartas.add(guiCartas);
            this.add(fondoCartas, BorderLayout.CENTER);
        }

        public void estilos()
        {
            colorPanelJugadores = new Color(153, 0,76, 100);
            fontMonto = new Font ("Comic sans", Font.BOLD | Font.ITALIC, 25);
            regularMargin = new Insets(10,10,10,10);
            nonMargin = new Insets(0,0,0,0);

        }
}
