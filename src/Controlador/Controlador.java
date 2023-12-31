/**
 * Controlador del juego de ajedrez.
 * Implementa la lógica del juego y gestiona las interacciones entre la vista y el modelo.
 */
package Controlador;

import Modelo.Bot;
import Modelo.Movimientos;
import Vista.MateHaciaBlancas;
import Vista.VentanaEleccionFicha;
import Vista.VistaTablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

// Importaciones de clases y paquetes necesarios
public class Controlador implements ActionListener
{
    
	//Patron singleton
	 private static Controlador instancia;
/**
 * Método estático para obtener la instancia única del Controlador (Singleton).
 *
 * @return La instancia única del Controlador.
 */
    public static synchronized Controlador getInstancia() {
        // Implementación del patrón Singleton
        if (instancia == null) {
            instancia = new Controlador();
        }
        return instancia;
    }

/**
 * Representa si el enroque para el rey del jugador 1 está disponible.
 */
    public static boolean enrroqueRey1 = true;
    public static boolean enrroqueTorreIzquierda1 = true;
    public static boolean enrroqueTorreDerecha1 = true;
    public static boolean enrroqueRey2 = true;
    public static boolean enrroqueTorreIzquierda2 = true;
    public static boolean enrroqueTorreDerecha2 = true;

/**
 * Representa el estado actual del tablero de juego.
 */
    public static String[][] tablero = new String[8][8];
    private char turnoJugador = '1';
    private String viejaPosicion = null;
    private String nuevaPoscion = null;
    private String posicionActual;
    public static String fichaElegida;
    public static ImageIcon imagenElegida;
    Movimientos movimientos;
    Bot bot = new Bot();

/**
 * Constructor de la clase Controlador.
 * Inicializa el tablero, crea la vista del tablero y añade eventos.
 */
    public Controlador() {
        // Implementación del constructor
        tableroIniciar();
        VistaTablero vista = new VistaTablero();
        vista.setVisible(true);
        añadirEventos();
        movimientos = new Movimientos();
    }

    private void tableroIniciar() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = "";
            }
        }

        for (int i = 0; i < 8; i++) {
            tablero[1][i] = "2_peon";
            tablero[6][i] = "1_peon";
        }

        tablero[0][0] = "2_torre";
        tablero[0][1] = "2_caballo";
        tablero[0][2] = "2_alfil";
        tablero[0][3] = "2_reina";
        tablero[0][4] = "2_rey";
        tablero[0][5] = "2_alfil";
        tablero[0][6] = "B_caballo";
        tablero[0][7] = "2_torre";

        tablero[7][0] = "1_torre";
        tablero[7][1] = "1_caballo";
        tablero[7][2] = "1_alfil";
        tablero[7][3] = "1_reina";
        tablero[7][4] = "1_rey";
        tablero[7][5] = "1_alfil";
        tablero[7][6] = "1_caballo";
        tablero[7][7] = "1_torre";
    }

    private void añadirEventos() {
        VistaTablero.c00.addActionListener(this);
        VistaTablero.c01.addActionListener(this);
        VistaTablero.c02.addActionListener(this);
        VistaTablero.c03.addActionListener(this);
        VistaTablero.c04.addActionListener(this);
        VistaTablero.c05.addActionListener(this);
        VistaTablero.c06.addActionListener(this);
        VistaTablero.c07.addActionListener(this);

        VistaTablero.c10.addActionListener(this);
        VistaTablero.c11.addActionListener(this);
        VistaTablero.c12.addActionListener(this);
        VistaTablero.c13.addActionListener(this);
        VistaTablero.c14.addActionListener(this);
        VistaTablero.c15.addActionListener(this);
        VistaTablero.c16.addActionListener(this);
        VistaTablero.c17.addActionListener(this);

        VistaTablero.c20.addActionListener(this);
        VistaTablero.c21.addActionListener(this);
        VistaTablero.c22.addActionListener(this);
        VistaTablero.c23.addActionListener(this);
        VistaTablero.c24.addActionListener(this);
        VistaTablero.c25.addActionListener(this);
        VistaTablero.c26.addActionListener(this);
        VistaTablero.c27.addActionListener(this);

        VistaTablero.c30.addActionListener(this);
        VistaTablero.c31.addActionListener(this);
        VistaTablero.c32.addActionListener(this);
        VistaTablero.c33.addActionListener(this);
        VistaTablero.c34.addActionListener(this);
        VistaTablero.c35.addActionListener(this);
        VistaTablero.c36.addActionListener(this);
        VistaTablero.c37.addActionListener(this);

        VistaTablero.c40.addActionListener(this);
        VistaTablero.c41.addActionListener(this);
        VistaTablero.c42.addActionListener(this);
        VistaTablero.c43.addActionListener(this);
        VistaTablero.c44.addActionListener(this);
        VistaTablero.c45.addActionListener(this);
        VistaTablero.c46.addActionListener(this);
        VistaTablero.c47.addActionListener(this);

        VistaTablero.c50.addActionListener(this);
        VistaTablero.c51.addActionListener(this);
        VistaTablero.c52.addActionListener(this);
        VistaTablero.c53.addActionListener(this);
        VistaTablero.c54.addActionListener(this);
        VistaTablero.c55.addActionListener(this);
        VistaTablero.c56.addActionListener(this);
        VistaTablero.c57.addActionListener(this);

        VistaTablero.c60.addActionListener(this);
        VistaTablero.c61.addActionListener(this);
        VistaTablero.c62.addActionListener(this);
        VistaTablero.c63.addActionListener(this);
        VistaTablero.c64.addActionListener(this);
        VistaTablero.c65.addActionListener(this);
        VistaTablero.c66.addActionListener(this);
        VistaTablero.c67.addActionListener(this);

        VistaTablero.c70.addActionListener(this);
        VistaTablero.c71.addActionListener(this);
        VistaTablero.c72.addActionListener(this);
        VistaTablero.c73.addActionListener(this);
        VistaTablero.c74.addActionListener(this);
        VistaTablero.c75.addActionListener(this);
        VistaTablero.c76.addActionListener(this);
        VistaTablero.c77.addActionListener(this);
    }

/**
 * Realiza las acciones correspondientes cuando se activa un evento en la interfaz.
 *
 * @param ae El evento de acción que se ha activado.
 */
    @Override

    public void actionPerformed(ActionEvent ae) {
        if (turnoJugador == '1') {
            posicionActual = getBotonPosicion(ae.getSource());

            if (comprobarFichaBlanca(posicionActual)) {
                viejaPosicion = posicionActual;
            } else if (viejaPosicion != null) {
                nuevaPoscion = posicionActual;
                if (movimientos.posibleMovimiento(tablero, viejaPosicion, nuevaPoscion)){
                    cambiarFichas(viejaPosicion, nuevaPoscion);
                    nuevaPoscion = null;
                    viejaPosicion = null;
                    vezMaquina();
                    comprobarJaqueBlancas();
                }
            }
        }
    }
    
    private void comprobarJaqueBlancas(){
        boolean jaqueMate = true;
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                String posicion = ""+i+""+j;
                if(comprobarFichaBlanca(posicion)){
                    String[] movimientosF = movimientos.movimientos1Modificados(tablero, posicion);
                    if(!movimientosF[0].equals("")){
                        jaqueMate = false;
                        break;
                    }
                }
            }
        }
        
        if(jaqueMate == true){
            MateHaciaBlancas ventana = new MateHaciaBlancas(null, true);
            ventana.setVisible(true);
        }
        
    }
    
    private void vezMaquina(){
            String[] movimientos = bot.movimientoDelBot(tablero);
            cambiarFichas(movimientos[0], movimientos[1]);
    }
    
    
    private void cambiarFichas(String posAntigua, String posNueva) {
        cambiarString(posAntigua, posNueva);
        cambiarPantalla(posAntigua, posNueva);
        comprobarPeonUltimaFila();
        comprobarEnrroque(posAntigua, posNueva);
    }

    private void cambiarString(String posAntigua, String posNueva) {
        int xA = Character.getNumericValue(posAntigua.charAt(1));
        int yA = Character.getNumericValue(posAntigua.charAt(0));

        int xN = Character.getNumericValue(posNueva.charAt(1));
        int yN = Character.getNumericValue(posNueva.charAt(0));
               
        tablero[yN][xN] = tablero[yA][xA];
        tablero[yA][xA] = "";
    }

    private void cambiarPantalla(String posAntigua, String posNueva) {
        boton(posNueva).setIcon(boton(posAntigua).getIcon());
        boton(posAntigua).setIcon(null);
    }

    private void comprobarPeonUltimaFila() {
        for (int i = 0; i < 8; i++) {
            if (tablero[0][i].equals("1_peon")) {
                //Se mostrara la tabla de eleccion de ficha
                elegirPeon();
                tablero[0][i] = fichaElegida;
                String posicion = "0" + i;
                boton(posicion).setIcon(imagenElegida);
            }

            if (tablero[7][i].equals("2_peon")) {
                tablero[7][i] = "2_reina";
                String posicion = "7" + i;
                boton(posicion).setIcon(new ImageIcon(getClass().getResource("/Imagenes/ReinaNegra.png")));
            }
        }
    }

    private void elegirPeon() {
        VentanaEleccionFicha ventanaElec = new VentanaEleccionFicha(null, true);
        ventanaElec.setVisible(true);
    }

    private void comprobarEnrroque(String posAntigua, String posNueva) {
        int xN = Character.getNumericValue(posNueva.charAt(1));
        int yN = Character.getNumericValue(posNueva.charAt(0));

        int xA = Character.getNumericValue(posAntigua.charAt(1));
        int yA = Character.getNumericValue(posAntigua.charAt(0));

        if (tablero[yN][xN].equals("1_rey") || tablero[yN][xN].equals("2_rey")) {
            if (xA + 2 == xN) {
                //Derecha
                tablero[yN][xN - 1] = tablero[yN][7];
                tablero[yN][xN + 1] = "";

                String posicionTorreAntigua = "" + yN + 7;
                String posicionTorreNueva = "" + yN + (xN - 1);

                boton(posicionTorreNueva).setIcon(boton(posicionTorreAntigua).getIcon());
                boton(posicionTorreAntigua).setIcon(null);
            } else if (xA - 2 == xN) {
                //Izquierda
                tablero[yN][xA - 1] = tablero[yN][0];
                tablero[yN][0] = "";

                String posicionTorreAntigua = "" + yN + 0;
                String posicionTorreNueva = "" + yN + (xA - 1);

                boton(posicionTorreNueva).setIcon(boton(posicionTorreAntigua).getIcon());
                boton(posicionTorreAntigua).setIcon(null);
            }
        }
    }

    private JButton boton(String posicion) {
        if (posicion.equals("00")) {
            return VistaTablero.c00;
        } else if (posicion.equals("01")) {
            return VistaTablero.c01;
        } else if (posicion.equals("02")) {
            return VistaTablero.c02;
        } else if (posicion.equals("03")) {
            return VistaTablero.c03;
        } else if (posicion.equals("04")) {
            return VistaTablero.c04;
        } else if (posicion.equals("05")) {
            return VistaTablero.c05;
        } else if (posicion.equals("06")) {
            return VistaTablero.c06;
        } else if (posicion.equals("07")) {
            return VistaTablero.c07;
        } else if (posicion.equals("10")) {
            return VistaTablero.c10;
        } else if (posicion.equals("11")) {
            return VistaTablero.c11;
        } else if (posicion.equals("12")) {
            return VistaTablero.c12;
        } else if (posicion.equals("13")) {
            return VistaTablero.c13;
        } else if (posicion.equals("14")) {
            return VistaTablero.c14;
        } else if (posicion.equals("15")) {
            return VistaTablero.c15;
        } else if (posicion.equals("16")) {
            return VistaTablero.c16;
        } else if (posicion.equals("17")) {
            return VistaTablero.c17;
        } else if (posicion.equals("20")) {
            return VistaTablero.c20;
        } else if (posicion.equals("21")) {
            return VistaTablero.c21;
        } else if (posicion.equals("22")) {
            return VistaTablero.c22;
        } else if (posicion.equals("23")) {
            return VistaTablero.c23;
        } else if (posicion.equals("24")) {
            return VistaTablero.c24;
        } else if (posicion.equals("25")) {
            return VistaTablero.c25;
        } else if (posicion.equals("26")) {
            return VistaTablero.c26;
        } else if (posicion.equals("27")) {
            return VistaTablero.c27;
        } else if (posicion.equals("30")) {
            return VistaTablero.c30;
        } else if (posicion.equals("31")) {
            return VistaTablero.c31;
        } else if (posicion.equals("32")) {
            return VistaTablero.c32;
        } else if (posicion.equals("33")) {
            return VistaTablero.c33;
        } else if (posicion.equals("34")) {
            return VistaTablero.c34;
        } else if (posicion.equals("35")) {
            return VistaTablero.c35;
        } else if (posicion.equals("36")) {
            return VistaTablero.c36;
        } else if (posicion.equals("37")) {
            return VistaTablero.c37;
        } else if (posicion.equals("40")) {
            return VistaTablero.c40;
        } else if (posicion.equals("41")) {
            return VistaTablero.c41;
        } else if (posicion.equals("42")) {
            return VistaTablero.c42;
        } else if (posicion.equals("43")) {
            return VistaTablero.c43;
        } else if (posicion.equals("44")) {
            return VistaTablero.c44;
        } else if (posicion.equals("45")) {
            return VistaTablero.c45;
        } else if (posicion.equals("46")) {
            return VistaTablero.c46;
        } else if (posicion.equals("47")) {
            return VistaTablero.c47;
        } else if (posicion.equals("50")) {
            return VistaTablero.c50;
        } else if (posicion.equals("51")) {
            return VistaTablero.c51;
        } else if (posicion.equals("52")) {
            return VistaTablero.c52;
        } else if (posicion.equals("53")) {
            return VistaTablero.c53;
        } else if (posicion.equals("54")) {
            return VistaTablero.c54;
        } else if (posicion.equals("55")) {
            return VistaTablero.c55;
        } else if (posicion.equals("56")) {
            return VistaTablero.c56;
        } else if (posicion.equals("57")) {
            return VistaTablero.c57;
        } else if (posicion.equals("60")) {
            return VistaTablero.c60;
        } else if (posicion.equals("61")) {
            return VistaTablero.c61;
        } else if (posicion.equals("62")) {
            return VistaTablero.c62;
        } else if (posicion.equals("63")) {
            return VistaTablero.c63;
        } else if (posicion.equals("64")) {
            return VistaTablero.c64;
        } else if (posicion.equals("65")) {
            return VistaTablero.c65;
        } else if (posicion.equals("66")) {
            return VistaTablero.c66;
        } else if (posicion.equals("67")) {
            return VistaTablero.c67;
        } else if (posicion.equals("70")) {
            return VistaTablero.c70;
        } else if (posicion.equals("71")) {
            return VistaTablero.c71;
        } else if (posicion.equals("72")) {
            return VistaTablero.c72;
        } else if (posicion.equals("73")) {
            return VistaTablero.c73;
        } else if (posicion.equals("74")) {
            return VistaTablero.c74;
        } else if (posicion.equals("75")) {
            return VistaTablero.c75;
        } else if (posicion.equals("76")) {
            return VistaTablero.c76;
        } else if (posicion.equals("77")) {
            return VistaTablero.c77;
        }
        return null;
    }

    private String getBotonPosicion(Object boton) {
        if (boton == VistaTablero.c00) {
            return "00";
        } else if (boton == VistaTablero.c01) {
            return "01";
        } else if (boton == VistaTablero.c02) {
            return "02";
        } else if (boton == VistaTablero.c03) {
            return "03";
        } else if (boton == VistaTablero.c04) {
            return "04";
        } else if (boton == VistaTablero.c05) {
            return "05";
        } else if (boton == VistaTablero.c06) {
            return "06";
        } else if (boton == VistaTablero.c07) {
            return "07";
        } else if (boton == VistaTablero.c10) {
            return "10";
        } else if (boton == VistaTablero.c11) {
            return "11";
        } else if (boton == VistaTablero.c12) {
            return "12";
        } else if (boton == VistaTablero.c13) {
            return "13";
        } else if (boton == VistaTablero.c14) {
            return "14";
        } else if (boton == VistaTablero.c15) {
            return "15";
        } else if (boton == VistaTablero.c16) {
            return "16";
        } else if (boton == VistaTablero.c17) {
            return "17";
        } else if (boton == VistaTablero.c20) {
            return "20";
        } else if (boton == VistaTablero.c21) {
            return "21";
        } else if (boton == VistaTablero.c22) {
            return "22";
        } else if (boton == VistaTablero.c23) {
            return "23";
        } else if (boton == VistaTablero.c24) {
            return "24";
        } else if (boton == VistaTablero.c25) {
            return "25";
        } else if (boton == VistaTablero.c26) {
            return "26";
        } else if (boton == VistaTablero.c27) {
            return "27";
        } else if (boton == VistaTablero.c30) {
            return "30";
        } else if (boton == VistaTablero.c31) {
            return "31";
        } else if (boton == VistaTablero.c32) {
            return "32";
        } else if (boton == VistaTablero.c33) {
            return "33";
        } else if (boton == VistaTablero.c34) {
            return "34";
        } else if (boton == VistaTablero.c35) {
            return "35";
        } else if (boton == VistaTablero.c36) {
            return "36";
        } else if (boton == VistaTablero.c37) {
            return "37";
        } else if (boton == VistaTablero.c40) {
            return "40";
        } else if (boton == VistaTablero.c41) {
            return "41";
        } else if (boton == VistaTablero.c42) {
            return "42";
        } else if (boton == VistaTablero.c43) {
            return "43";
        } else if (boton == VistaTablero.c44) {
            return "44";
        } else if (boton == VistaTablero.c45) {
            return "45";
        } else if (boton == VistaTablero.c46) {
            return "46";
        } else if (boton == VistaTablero.c47) {
            return "47";
        } else if (boton == VistaTablero.c50) {
            return "50";
        } else if (boton == VistaTablero.c51) {
            return "51";
        } else if (boton == VistaTablero.c52) {
            return "52";
        } else if (boton == VistaTablero.c53) {
            return "53";
        } else if (boton == VistaTablero.c54) {
            return "54";
        } else if (boton == VistaTablero.c55) {
            return "55";
        } else if (boton == VistaTablero.c56) {
            return "56";
        } else if (boton == VistaTablero.c57) {
            return "57";
        } else if (boton == VistaTablero.c60) {
            return "60";
        } else if (boton == VistaTablero.c61) {
            return "61";
        } else if (boton == VistaTablero.c62) {
            return "62";
        } else if (boton == VistaTablero.c63) {
            return "63";
        } else if (boton == VistaTablero.c64) {
            return "64";
        } else if (boton == VistaTablero.c65) {
            return "65";
        } else if (boton == VistaTablero.c66) {
            return "66";
        } else if (boton == VistaTablero.c67) {
            return "67";
        } else if (boton == VistaTablero.c70) {
            return "70";
        } else if (boton == VistaTablero.c71) {
            return "71";
        } else if (boton == VistaTablero.c72) {
            return "72";
        } else if (boton == VistaTablero.c73) {
            return "73";
        } else if (boton == VistaTablero.c74) {
            return "74";
        } else if (boton == VistaTablero.c75) {
            return "75";
        } else if (boton == VistaTablero.c76) {
            return "76";
        } else if (boton == VistaTablero.c77) {
            return "77";
        }
        return null;
    }

    private boolean comprobarFichaBlanca(String posicion) {
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));
        if (!tablero[y][x].equals("")) {
            return (tablero[y][x].charAt(0) == '1') ? true : false;
        }
        return false;
    }

}
