package poker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FaseDeJuego1 extends JPanel {
    private final double auxTJugador = Math.round(1.2*(new ImageIcon("src/mazo/fichaVuelta.jpeg").getIconHeight()));
    protected int widthWindow = 1024, heightWindow = 700, tJugador = (int)auxTJugador;
    private JPanel zonaBaja, zonaAlta, zonaCentral;
    private GuiJugador panelJugador, panelJugador2;
    private GuiMesaDeJuego guiMesaDeJuego;

    public void imprimir()
    {
        zonaBaja = new JPanel();
        zonaAlta = new JPanel();
        zonaCentral = new JPanel();
        this.inicializarGuiJugadores(panelJugador, zonaBaja, true);
        this.inicializarGuiJugadores(panelJugador2, zonaAlta, false);
        this.inicializarGuiMesaDeJuego();

        this.setLayout(new BorderLayout());
        this.setBounds(0,0,widthWindow,heightWindow);

        this.add(zonaBaja, BorderLayout.SOUTH);
        this.add(zonaAlta, BorderLayout.NORTH);
        this.add(zonaCentral, BorderLayout.CENTER);
    }
    public void inicializarGuiJugadores(GuiJugador jugador, JPanel zona, boolean juegaAqui)
    {
        zona.setLayout(new GridBagLayout());
        zona.setPreferredSize(new Dimension(widthWindow, tJugador));

        GridBagConstraints c = new GridBagConstraints();
        jugador = new GuiJugador();
        jugador.inicializar(juegaAqui);
        zona.add(jugador, c);
    }
    public void inicializarGuiMesaDeJuego()
    {
        guiMesaDeJuego = new GuiMesaDeJuego();

        zonaCentral.setLayout(new GridBagLayout());
        zonaCentral.setPreferredSize(new Dimension(widthWindow, heightWindow-2*tJugador));

        GridBagConstraints c = new GridBagConstraints();
        ArrayList<JLabel> cartasEnMesa = new ArrayList<JLabel>();
        for(int i=1; i<6; i++)
            cartasEnMesa.add(new JLabel(new ImageIcon("src/mazo/pica ("  +i+ ").png")));

        guiMesaDeJuego.inicializar(cartasEnMesa);
        zonaCentral.add(guiMesaDeJuego, c);
    }
}
