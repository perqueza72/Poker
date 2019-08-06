package poker;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jugador  extends JPanel {

    private ArrayList<JLabel> barajaMia;
    private int fondos;

    public Jugador() {

        barajaMia = new ArrayList<>();
        fondos = 100;
    }

    public void iniciarBaraja(JLabel uno, JLabel dos) {

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

    public ArrayList<JLabel> getBarajaMia() {
        return barajaMia;
    }

    public int getFondos() {
        return fondos;
    }

}
