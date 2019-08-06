package poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class FaseDeJuego extends JPanel {
    private final double auxTJugador = Math.round(1.2*(new ImageIcon("src/mazo/fichaVuelta.jpeg").getIconHeight()));
    final int nJugadores = 2;

    private int widthWindow = 1024, heightWindow = 700, tJugador = (int)auxTJugador;

    private JPanel mesaDeJuego;

    private GuiMesaDeJuego guiMesaDeJuego;

    private ArrayList<Jugador> jugadores;
    private ArrayList<GuiJugador> guisJugadores = new ArrayList<>();
    private ArrayList<JPanel> zona = new ArrayList<>();

    private Bid bid = new Bid();
    private Crupier crupier = new Crupier();

    private MouseFase mouseFase = new MouseFase();

    void imprimir()
    {
        final String []ubicacion = {"South", "North", "East", "West"};

        mesaDeJuego = new JPanel();
        jugadores = new ArrayList<>();

        for(int i=0; i<nJugadores; i++) {
            zona.add(new JPanel());
        }

        crupier.iniciarBaraja();
        crupier.abrirMesa();

        //ToDo CONVERTIR A Rn :v

        for(int i=0; i<nJugadores; i++) {
            Jugador jugador = new Jugador();
            jugador.iniciarBaraja(crupier.retirarCarta(),crupier.retirarCarta());
            jugadores.add(jugador);
        }

        //ToDo panele
        for(int i=0; i<nJugadores; i++){
            GuiJugador guiJugador = new GuiJugador();
            guisJugadores.add(guiJugador);
        }

        for(int i=0; i<nJugadores; i++) {
            boolean juegaAqui = false;
            if(i == 0)
                juegaAqui = true;
            this.inicializarGuiJugadores(guisJugadores.get(i), zona.get(i), juegaAqui, jugadores.get(i));
        }
        this.inicializarGuiMesaDeJuego();

        this.setLayout(new BorderLayout());
        this.setBounds(0,0,widthWindow,heightWindow);

        //AÑADIR EN PANTALLA
        for(int i=0; i<nJugadores; i++)
            this.add(zona.get(i), ubicacion[i]);

        this.add(mesaDeJuego, BorderLayout.CENTER);
    }
    private void inicializarGuiJugadores(GuiJugador guiJugador, JPanel zona, boolean juegaAqui, Jugador jugador)
    {
        zona.setLayout(new GridBagLayout());
        zona.setPreferredSize(new Dimension(widthWindow, tJugador));

        GridBagConstraints c = new GridBagConstraints();
        guiJugador.inicializar(juegaAqui, jugador);
        if(juegaAqui)
            guiJugador.btnApostar.addMouseListener(mouseFase);
        zona.add(guiJugador, c);
    }
    private void inicializarGuiMesaDeJuego()
    {

        guiMesaDeJuego = new GuiMesaDeJuego();

        mesaDeJuego.setLayout(new GridBagLayout());
        mesaDeJuego.setPreferredSize(new Dimension(widthWindow, heightWindow-2*tJugador));

        GridBagConstraints c = new GridBagConstraints();

        guiMesaDeJuego.inicializar(crupier.cartasMesa);
        mesaDeJuego.add(guiMesaDeJuego, c);
    }

    private void addCartaMesa()
    {
        crupier.addToMesa();
        guiMesaDeJuego.GuiMesa(crupier.cartasMesa);
        this.repaint();
        this.revalidate();
    }

    private void reiniciar(){

        crupier.cerrarMesa();
        for(int i = 0; i < nJugadores; i++){
            jugadores.get(i).borrarCartas();
        }

        crupier.iniciarBaraja();
        crupier.abrirMesa();

        for(int i = 0; i < nJugadores; i++){
            jugadores.get(i).iniciarBaraja(crupier.retirarCarta(), crupier.retirarCarta());
        }

        guiMesaDeJuego.reiniciar(crupier.cartasMesa);


        //Falta reiniciar los jugadores.

        this.repaint();
        this.revalidate();
    }

    public class MouseFase implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {
            JPanel panel = (JPanel) ((JButton) e.getSource()).getParent();
            GuiJugador jugador = (GuiJugador) panel.getParent();
            JTextField cajaDeTexto = jugador.txtBoxapostarNDinero;
            String sDineroAApostar = cajaDeTexto.getText();
            int dineroAApostar = Integer.parseInt(sDineroAApostar);
            cajaDeTexto.setText("");
            if(bid.puedeApostar(jugador.montoUsuario, dineroAApostar)) {
                addCartaMesa();
                jugador.montoUsuario-=dineroAApostar;
                jugador.txtMonto.setText("Monto: " + (jugador.montoUsuario));
            }
            if(crupier.cartasMesa.size() == 5)
            {
                int ganador = crupier.decidirGanador(jugadores);
                //ToDo
                if(ganador >= -1) {

                    JOptionPane.showMessageDialog(null, "el ganador es: " + (ganador+1), "GANADOR", JOptionPane.INFORMATION_MESSAGE);
                    jugador.montoUsuario = bid.pagarAGanador(jugador.montoUsuario);
                    jugador.txtMonto.setText("Monto: " + (jugador.montoUsuario));

                    reiniciar();
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
