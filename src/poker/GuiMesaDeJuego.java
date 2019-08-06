package poker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiMesaDeJuego extends JPanel {
        private JLabel fondo;
        private final double auxTJugador = Math.round(1.2*(new ImageIcon("src/mazo/fichaVuelta.jpeg").getIconHeight()));
        protected final int widthWindow = 1024, heightWindow = 700, tJugador = (int)auxTJugador;
        public void inicializar(ArrayList<JLabel> cartaEnMesa)
        {
                this.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();

                fondo = new JLabel(new ImageIcon("src/mazo/mesa.png"));
                fondo.setPreferredSize(new Dimension((int) Math.round(widthWindow*0.7), heightWindow-2*tJugador));

                GuiMesa(cartaEnMesa);
                this.add(fondo, c);
        }

        public void reiniciar(ArrayList<JLabel> cartaEnMesa){
                GuiMesa(cartaEnMesa);
        }

        public void GuiMesa(ArrayList<JLabel> cartaEnMesa)
        {
                fondo.removeAll();
                fondo.setLayout(new GridBagLayout());
                GridBagConstraints cMesa = new GridBagConstraints();
                cMesa.gridx=0;
                for(int i=0; i<cartaEnMesa.size();i++)
                {
                        fondo.add(cartaEnMesa.get(i), cMesa);
                        cMesa.gridx++;
                }

        }

}
