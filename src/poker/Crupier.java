package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Crupier
{
    private ArrayList<JLabel> barajaCompleta;
    protected ArrayList<JLabel> cartasMesa;
    private JLabel carta, card1, card2, auxiliar;
    private Random random;

    private String pinta1 = "corazon ", pinta2 = "diamante ", pinta3 = "pica ", pinta4 = "trebol ", pinta = "";
    private int aleatorio, apuestas, rondas, apuestaFija;
    private int nDeColor1, nDeColor2, noRepetidos;
    private int[] valoresEnMesa = new int[5], verificarEscalera = new int[7], sinRepetidos = new int[7], escaleraDeColor = new int[7];
    private String[] colores = new String[7], valorYPinta = new String[7];
    private boolean trio, color, escalera, poker, escaleraColor, escaleraReal, colorSeguido, As, K;

    public Crupier() {

        barajaCompleta = new ArrayList<>();
        cartasMesa = new ArrayList<>();
        random = new Random();
        rondas = 1;
        apuestaFija = 5;
    }

    public void iniciarBaraja() {

        apuestas = 0;
        rondas++;

        for(int i = 0; i < 4; i++) {
            switch(i) {
                case 0:
                    pinta = pinta1;
                    break;
                case 1:
                    pinta = pinta2;
                    break;
                case 2:
                    pinta = pinta3;
                    break;
                case 3:
                    pinta = pinta4;
                    break;
            }
            for(int j = 1; j <= 13; j++) {
                carta = new JLabel(new ImageIcon("src/mazo/" + pinta + "(" + j + ").png"));
                carta.setName(pinta.substring(0, 1) + j);
                barajaCompleta.add(carta);
            }
        }
    }

    public JLabel retirarCarta() {
        aleatorio = random.nextInt(barajaCompleta.size());

        carta = barajaCompleta.get(aleatorio);
        barajaCompleta.remove(aleatorio);
        return carta;
    }


    public void abrirMesa() {

        cartasMesa.add(retirarCarta());
        cartasMesa.add(retirarCarta());
        cartasMesa.add(retirarCarta());
    }

    public JLabel addToMesa(){
        carta = this.retirarCarta();
        cartasMesa.add(carta);
        return carta;
    }

    public void cerrarMesa() {

        cartasMesa.removeAll(cartasMesa);
        barajaCompleta.removeAll(barajaCompleta);
    }


    public int decisionDeJerarquia(ArrayList<JLabel> player) {

        /*
         * retorna un numero dependiendo de la jerarquia de victoria del poker como
         * se describe a continuacion:
         *
         * - 1 si es carta alta.
         * - 2 si es pareja.
         * - 3 si es doble pareja.
         * - 4 si es trio.
         * - 5 si es escalera.
         * - 6 si es color (cicnco cartas del mismo color).
         * - 7 si es un Full House.
         * - 8 si es un Poker.
         * - 9 si es una escalera de color.
         * - 10 si es una escalera real.
         */

        card1 = player.get(0);
        card2 = player.get(1);

        String color1 = card1.getName().substring(0, 1);
        String color2 = card2.getName().substring(0, 1);
        String nameAux = "";
        int valor1 = 0;
        int valor2 = 0;

        if((card1.getName().length()) == 3) {
            valor1 = Integer.parseInt((card1.getName().substring(1, 3)));
        }
        else
            valor1 = Integer.parseInt((card1.getName().substring(1, 2)));

        if(card2.getName().length() == 3)
            valor2 = Integer.parseInt(card2.getName().substring(1, 3));
        else
            valor2 = Integer.parseInt(card2.getName().substring(1, 2));

        nDeColor1 = 1;
        nDeColor2 = 1;

        noRepetidos = 0;

        trio = false;
        color = false;
        escalera = false;
        poker = false;
        escaleraColor = false;
        escaleraReal = false;
        colorSeguido = false;
        As = false;
        K = false;



        for(int i = 0; i < cartasMesa.size(); i++) {
            auxiliar = cartasMesa.get(i);
            nameAux = auxiliar.getName();
            valorYPinta[i] = nameAux;
            if(nameAux.length() == 3)
                verificarEscalera[i] = Integer.parseInt(nameAux.substring(1, 3));
            else
                verificarEscalera[i] = Integer.parseInt(nameAux.substring(1, 2));
            if(verificarEscalera[i] == 1)
                As = true;
            else if(verificarEscalera[i] == 13)
                K = true;
            valoresEnMesa[i] = verificarEscalera[i];
        }

        System.out.printf("Modified arr[] : %s",
                Arrays.toString(verificarEscalera));
        System.out.println("\n" + "Valor y pinta: " + valor1 + "   " + color1);
        System.out.println("Valor y pinta: " + valor2 + "   " + color2 + "\n");

        verificarEscalera[5] = valor1;
        verificarEscalera[6] = valor2;
        valorYPinta[5] = card1.getName();
        valorYPinta[6] = card2.getName();
        Arrays.parallelSort(verificarEscalera);
        Arrays.parallelSort(valoresEnMesa);
        Arrays.parallelSort(valorYPinta);

        sortByValue(verificarEscalera, valorYPinta);

        int repeticiones = 1;
        int iteraciones = 1;
        int nCeros = 0;

        for(int i = 0; i < 6; i++) {

            if(verificarEscalera[i] == verificarEscalera[i+1]) {
                iteraciones++;
                nCeros++;
                if(iteraciones >= repeticiones)
                    repeticiones = iteraciones;
            }
            else {
                sinRepetidos[noRepetidos] = verificarEscalera[i];
                noRepetidos++;
                iteraciones = 1;
            }
            if(i+1 == 6) {
                sinRepetidos[noRepetidos] = verificarEscalera[i+1];
                noRepetidos++;
                if(iteraciones >= repeticiones)
                    repeticiones = iteraciones;
            }
        }

        if(repeticiones == 4) {
            poker = true;
        }
        if(repeticiones == 3) {
            trio = true;
        }

        Arrays.parallelSort(sinRepetidos);


        if(valor1 == 1 || valor2 == 1)
            As = true;
        if(valor1 == 13 || valor2 == 13)
            K = true;

        for(int i = 0; i < cartasMesa.size(); i++) {
            auxiliar = cartasMesa.get(i);
            nameAux = auxiliar.getName();

            colores[i] = nameAux.substring(0, 1);
        }

        colores[5] = color1;
        colores[6] = color2;





        //Evaluar si es posible obtener la condicion de Color:

        String laLetra = "";
        repeticiones = 0;
        iteraciones = 0;

        for(int i = 0; i < 4; i++) {
            switch(i) {
                case 0:
                    pinta = "t";
                    break;
                case 1:
                    pinta = "p";
                    break;
                case 2:
                    pinta = "d";
                    break;
                case 3:
                    pinta = "c";
                    break;
            }
            for(int j = 0; j < 7; j++) {
                if(pinta.equals(colores[j])) {
                    iteraciones++;
                }
            }
            if(iteraciones >= repeticiones) {
                repeticiones = iteraciones;
                laLetra = pinta;
            }
            iteraciones = 0;
        }

        if(repeticiones >= 5)
            color = true;


        //Obtener Array con las cartas de la misma pinta (Solo cuando hay color):


        if(color) {

            for(int i = 0; i < 7; i++) {

                if(laLetra.equals(valorYPinta[i].substring(0, 1))) {
                    if(valorYPinta[i].length() == 2)
                        escaleraDeColor[i] = Integer.parseInt(valorYPinta[i].substring(1, 2));
                    else {
                        escaleraDeColor[i] = Integer.parseInt(valorYPinta[i].substring(1, 3));
                    }
                }
            }

            Arrays.parallelSort(escaleraDeColor);
        }


        //Verificar que tenga escalera de color:

        int contador = 1;
        int KIzq = 0;
        int contadorAuxiliar = 1;

        boolean AsAux = false;
        boolean KAux = false;

        if(color) {

            for(int i = 0; i < 7; i++) {

                if(escaleraDeColor[i] == 1)
                    AsAux = true;
                if(escaleraDeColor[i] == 13)
                    KAux = true;
            }

            if(AsAux && KAux) {

                contadorAuxiliar = 1;
                contador = 2;
                KIzq = 0;

                for(int i = 0; i < 6; i++) {
                    if(escaleraDeColor[i] == 1 || escaleraDeColor[i] == 0) {}
                    else {
                        if(escaleraDeColor[i]-contador == 0)
                            contador++;
                        else
                            break;
                    }
                }

                for(int i = 6; i >= 0; i--) {
                    if(escaleraDeColor[i] == 13) {}
                    else {
                        if(escaleraDeColor[i]+contadorAuxiliar == 13) {
                            contadorAuxiliar++;
                            KIzq++;
                        }
                        else
                            break;
                    }
                }

                if(contador+KIzq >= 5)
                    colorSeguido = true;

            }
            else {

                contador = 1;
                for(int i = 0; i < 6; i++) {
                    if(escaleraDeColor[i] == 0) {}
                    else {
                        if(escaleraDeColor[i] == escaleraDeColor[i+1]-1) {
                            contador++;
                        }
                        else {
                            if(contador >= 5) {
                                colorSeguido = true;
                            }
                            else {
                                if(escaleraDeColor[i] == escaleraDeColor[i+1]) {}
                                else
                                    contador = 1;
                            }
                        }
                    }
                    if(contador >= 5) {
                        colorSeguido = true;
                    }
                }
            }
        }



        //Verificar si tiene victoria por escalera:

        if(As && K) {

            contadorAuxiliar = 1;
            contador = 2;
            KIzq = 0;

            for(int i = 0; i < 6; i++) {
                if(verificarEscalera[i] == 1) {}
                else {
                    if(verificarEscalera[i]-contador == 0)
                        contador++;
                    else
                        break;
                }
            }

            for(int i = 6; i >= 0; i--) {
                if(verificarEscalera[i] == 13) {}
                else {
                    if(verificarEscalera[i]+contadorAuxiliar == 13) {

                        if(verificarEscalera[i-1]+contadorAuxiliar == 13) {}
                        else {
                            contadorAuxiliar++;
                            KIzq++;
                        }
                    }
                    else
                        break;
                }
            }

            if(KIzq >= 3 && colorSeguido && AsAux && KAux) {
                escaleraReal = true;
            }
            else if(contador+KIzq >= 5 && colorSeguido)
                escaleraColor = true;
            else if(contador+KIzq >= 5)
                escalera = true;
        }
        else {

            contador = 1;
            for(int i = 0; i < 6; i++) {
                if(verificarEscalera[i] == verificarEscalera[i+1]-1) {
                    contador++;
                }
                else {
                    if(contador >= 5) {
                        if(colorSeguido) {
                            escaleraColor = true;
                        }
                        else
                            escalera = true;
                        break;
                    }
                    else {
                        if(verificarEscalera[i] == verificarEscalera[i+1]) {}
                        else
                            contador = 1;
                    }
                }

                if(i+1 == 6 && contador >= 5) {
                    if(colorSeguido) {
                        escaleraColor = true;
                    }
                    else
                        escalera = true;
                }
            }
        }

//Reinicio de Arrays.

        for(int i = 0; i < 7; i++) {
            verificarEscalera[i] = 0;
        }

        for(int i = 0; i < 5; i++) {
            valoresEnMesa[i] = 0;
        }

        for(int i = 0; i < 7; i++) {
            sinRepetidos[i] = 0;
        }

        for(int i = 0; i < 7; i++) {
            escaleraDeColor[i] = 0;
        }

        for(int i = 0; i < 7; i++) {
            colores[i] = "";
        }

        for(int i = 0; i < 7; i++) {
            valorYPinta[i] = "";
        }
        // Evaluar algunas condiciones de victoria y retornar su Jerarquia si se cumplen

        if(escaleraReal) {
            System.out.println("Escalera Real");
            return 10;
        }

        else if(escaleraColor) {
            System.out.println("Escalera de Color");
            return 9;
        }

        else if(poker) {
            System.out.println("Poker");
            return 8;
        }


        else if(trio && nCeros >= 3) {
            System.out.println("Full House");
            return 7;
        }

        else if(color) {
            System.out.println("Color");
            return 6;
        }

        else if(escalera) {
            System.out.println("Escalera");
            return 5;
        }

        else if(trio) {
            System.out.println("Trio");
            return 4;
        }

        else if(nCeros >= 2) {
            System.out.println("Doble Pareja");
            return 3;
        }

        else if(nCeros == 1) {
            System.out.println("Pareja");
            return 2;
        }

        else if(nCeros == 0) {
            System.out.println("CartaAlta");
            return 1;
        }

        return valor1;
    }

    private void sortByValue(int[] numeros, String[] string) {

        String[] nuevo = new String[7];
        int aux = 0;

        for(int i = 0; i < 7; i++) {

            for(int j = 0; j < 7; j++) {

                if(string[j].length() == 3)
                    aux = Integer.parseInt(string[j].substring(1, 3));
                else
                    aux = Integer.parseInt(string[j].substring(1, 2));
                if(numeros[i] == aux) {
                    nuevo[i] = string[j];
                    string[j] = "g99";
                    break;
                }
            }
        }

        for(int i = 0; i < 7; i++) {
            string[i] = nuevo[i];
        }
    }

    public int decidirGanador(ArrayList<Jugador> jugadores)
    {
        int ganador = 0;
        int nJugador = jugadores.size(), puntajeMayor=0, empatan=0;
        int[] puntuacion = new int[nJugador];
        for(int i=0; i<nJugador; i++)
        {
            puntuacion[i] = decisionDeJerarquia(jugadores.get(i).getBarajaMia());
            if(puntajeMayor < puntuacion[i]) {
                puntajeMayor = puntuacion[i];
                ganador = i;
            }
        }
        for(int i=0; i<nJugador; i++)
        {
            if(puntajeMayor == puntuacion[i])
            {
                empatan++;
            }
        }
        if(empatan>1)
        {
            //ToDo
            ganador = -1;
        }
        return ganador;
    }

}
