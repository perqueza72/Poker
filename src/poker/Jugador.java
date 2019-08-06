package poker;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jugador  extends JPanel {

    private ArrayList<JLabel> barajaMia;
    private int fondos;

    Jugador() {

        barajaMia = new ArrayList<>();
        fondos = 100;
    }

    void iniciarBaraja(JLabel uno, JLabel dos) {

        barajaMia.add(uno);
        barajaMia.add(dos);
    }

    public void borrarCartas() {
        barajaMia.removeAll(barajaMia);
    }

    public void apostar(int apuesta) {
        fondos -= apuesta;
    }

    public void profits(int profit) {
        fondos += profit;
    }

    ArrayList<JLabel> getBarajaMia() {
        return barajaMia;
    }

    int getFondos() {
        return fondos;
    }

}
