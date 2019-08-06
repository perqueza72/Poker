package poker;

import javax.swing.*;

class Bid {
    private int pozoDeApuestas=0;

    private void addDinero(int DineroApostado)
    {
        pozoDeApuestas+=DineroApostado;
    }
    int pagarAGanador(int DineroUsuario)
    {
        int dineroTotal = DineroUsuario+pozoDeApuestas;
        pozoDeApuestas = 0;
        return dineroTotal;
    }
    boolean puedeApostar(int montoUsuario, int dineroAApostar)
    {
        if(montoUsuario>=dineroAApostar && dineroAApostar>=0) {
            this.addDinero(dineroAApostar);
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "No puede aportar esa cantidad", "Alerta", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
}
