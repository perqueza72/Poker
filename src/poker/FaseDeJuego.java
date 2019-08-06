package poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FaseDeJuego extends JPanel {
    private final double auxTJugador = Math.round(1.2*(new ImageIcon("src/mazo/fichaVuelta.jpeg").getIconHeight()));
    protected int widthWindow = 1024, heightWindow = 700, tJugador = (int)auxTJugador;
    private JPanel zonaBaja, zonaAlta, zonaCentral;
    private GuiJugador panelJugador = new GuiJugador(), panelJugador2 = new GuiJugador();
    private Jugador jugador1 = new Jugador(), jugador2 = new Jugador();
    private ArrayList<ArrayList> jugadores = new ArrayList<>();
    private GuiMesaDeJuego guiMesaDeJuego;
    ArrayList<JLabel> cartasEnMesa = new ArrayList<>();
    private MouseFase mouseFase = new MouseFase();
    private Bid bid = new Bid();
    private Crupier crupier = new Crupier();

    private int nJugadores = 2;
    public void imprimir()
    {
        zonaBaja = new JPanel();
        zonaAlta = new JPanel();
        zonaCentral = new JPanel();

        crupier.iniciarBaraja();
        crupier.abrirMesa();

        //CONVERTIR A Rn :v
        jugador1.iniciarBaraja(crupier.retirarCarta(), crupier.retirarCarta());
        jugador2.iniciarBaraja(crupier.retirarCarta(), crupier.retirarCarta());

        //for(int i=0; i<nJugadores; i++)
        //    jugadores.add(new Jugador());
        jugadores.add(jugador1.getBarajaMia());
        jugadores.add(jugador2.getBarajaMia());

        this.inicializarGuiJugadores(panelJugador, zonaBaja, true, jugador1.getBarajaMia());
        this.inicializarGuiJugadores(panelJugador2, zonaAlta, false, jugador2.getBarajaMia());
        this.inicializarGuiMesaDeJuego();

        this.setLayout(new BorderLayout());
        this.setBounds(0,0,widthWindow,heightWindow);

        this.add(zonaBaja, BorderLayout.SOUTH);
        this.add(zonaAlta, BorderLayout.NORTH);
        this.add(zonaCentral, BorderLayout.CENTER);
    }
    public void inicializarGuiJugadores(GuiJugador jugador, JPanel zona, boolean juegaAqui, ArrayList cartaJugador)
    {
        zona.setLayout(new GridBagLayout());
        zona.setPreferredSize(new Dimension(widthWindow, tJugador));

        GridBagConstraints c = new GridBagConstraints();
        jugador.inicializar(juegaAqui, cartaJugador);
        if(juegaAqui)
            jugador.btnApostar.addMouseListener(mouseFase);
        zona.add(jugador, c);
    }
    public void inicializarGuiMesaDeJuego()
    {

        guiMesaDeJuego = new GuiMesaDeJuego();

        zonaCentral.setLayout(new GridBagLayout());
        zonaCentral.setPreferredSize(new Dimension(widthWindow, heightWindow-2*tJugador));

        GridBagConstraints c = new GridBagConstraints();

        for(int i=0; i<3; i++)
            cartasEnMesa.add(crupier.cartasMesa.get(i));

        guiMesaDeJuego.inicializar(cartasEnMesa);
        zonaCentral.add(guiMesaDeJuego, c);
    }

    public void addCartaMesa()
    {
        cartasEnMesa.add(crupier.addToMesa());
        guiMesaDeJuego.GuiMesa(cartasEnMesa);
        this.repaint();
        this.revalidate();
    }

    public class MouseFase implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {
            System.out.println(panelJugador.txtBoxapostarNDinero.getText());
            String sDineroAApostar = panelJugador.txtBoxapostarNDinero.getText();
            int dineroAApostar = Integer.parseInt(sDineroAApostar);
            panelJugador.txtBoxapostarNDinero.setText("");
            if(bid.puedeApostar(panelJugador.montoUsuario, dineroAApostar)) {
                addCartaMesa();
                panelJugador.montoUsuario-=dineroAApostar;
                panelJugador.txtMonto.setText("Monto: " + panelJugador.montoUsuario);
            }
            if(crupier.cartasMesa.size() == 5)
            {
                int ganador = crupier.decidirGanador(jugadores);
                //ToDo
                if(ganador != -1) {
                    JOptionPane.showMessageDialog(null, "el ganador es: " + (ganador+1), "GANADOR", JOptionPane.INFORMATION_MESSAGE);
                    panelJugador.montoUsuario = bid.pagarAGanador(panelJugador.montoUsuario);
                    panelJugador.txtMonto.setText("Monto: " + panelJugador.montoUsuario);
                }
            }
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
