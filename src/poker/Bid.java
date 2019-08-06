package poker;

import javax.swing.*;

public class Bid {
    private int pozoDeApuestas=0;

    public int getPozoDeApuestas()
    {
        return pozoDeApuestas;
    }
    public void addDinero(int DineroApostado)
    {
        pozoDeApuestas+=DineroApostado;
    }
    public int pagarAGanador(int DineroUsuario)
    {
        int dineroTotal = DineroUsuario+pozoDeApuestas;
        pozoDeApuestas = 0;
        return dineroTotal;
    }
    public boolean puedeApostar(int montoUsuario, int dineroAApostar)
    {
        if(montoUsuario>=dineroAApostar && dineroAApostar>=0) {
            this.addDinero(dineroAApostar);
            return true;
        }
        else {
            this.alertBox("No puede aportar esa cantidad","Alerta");
            return false;
        }
    }

    public static void alertBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
