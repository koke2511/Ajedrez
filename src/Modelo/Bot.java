package Modelo;

import Vista.MateHaciaNegras;

/**
 * Clase que representa el bot del juego de ajedrez.
 */
public class Bot {
// Variables miembro

/**
 * Representación del tablero de ajedrez en forma de matriz de cadenas.
 */
    String[][] tablero;

/**
 * Instancia de la clase Movimientos para gestionar los movimientos de las fichas.
 */
    Movimientos movimientos = new Movimientos();
    boolean movimientoPrimero = true;

/**
 * Puntuación asignada a las posiciones en el tablero.
 */
    int[][] puntuacionDePosiciones = new int[8][8];
    // Métodos

/**
 * Realiza el movimiento del bot en el tablero de ajedrez.
 *
 * @param tablero Matriz que representa el estado actual del tablero.
 * @return Array de cadenas con la posición antigua [0] y la posición nueva [1] del movimiento.
 */

    public String[] movimientoDelBot(String[][] tablero)
    {
        // Implementación del movimiento del bot
        this.tablero = tablero;
        examinarCasillasAtacadas();
        return arregloFinal();
    }
/**
 * Genera el arreglo con la posición antigua y la nueva para realizar el movimiento.
 *
 * @return Array de cadenas con la posición antigua [0] y la posición nueva [1].
 */

    private String[] arregloFinal()
    // Implementación para determinar la posición antigua y nueva del movimiento
    {
        int puntuacionMaxima = -600;
        int yInit = 1;
        int xInit = 4;
        int yFinal = 3;
        int xFinal = 4;
        boolean jaqueMate = true;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (comprobarFichaEsnegra(tablero, i, j))
                {
                    String posicionInicial = "" + i + "" + j;
                    String[] movimientosPosibles = movimientos.movimientosFichas(tablero, posicionInicial);

                    String ficha = tablero[i][j];

                    for (int k = 0; k < movimientosPosibles.length; k++)
                    {
                        String posicionFinal = movimientosPosibles[k];
                        try {
                            char ejeY = posicionFinal.charAt(0);
                            char ejeX = posicionFinal.charAt(1);
                            int intEjeY = Integer.parseInt(String.valueOf(ejeY));
                            int intEjeX = Integer.parseInt(String.valueOf(ejeX));
                            System.out.println(ficha + " : " + posicionFinal);
                            int puntuacionPosicionEspecifica = movimientoPuntuacion(i, j, intEjeY, intEjeX);
                            System.out.println(puntuacionPosicionEspecifica);

                            if (puntuacionMaxima <= puntuacionPosicionEspecifica)
                            {
                                yInit = i;
                                xInit = j;
                                yFinal = intEjeY;
                                xFinal = intEjeX;
                                puntuacionMaxima = puntuacionPosicionEspecifica;
                            }

                            if (puntuacionPosicionEspecifica > -600)
                            {
                                jaqueMate = false;
                            }

                        } catch (Exception ex) {
                        }
                    }
                }
            }
        }

        String posicionAntigua = "" + yInit + "" + xInit;
        String posicionNueva = "" + yFinal + "" + xFinal;

        if (movimientoPrimero)
        {
            posicionAntigua = "14";
            posicionNueva = "34";
            movimientoPrimero = false;
        }
        System.out.println("Turno acabado");

        if (jaqueMate == true)
        {
            MateHaciaNegras ventana = new MateHaciaNegras(null, true);
            ventana.setVisible(true);
        }
        String arrayPosiciones[] = {posicionAntigua, posicionNueva};
        return arrayPosiciones;

    }

/**
 * Calcula la puntuación del movimiento basada en varios criterios.
 *
 * @param yInit   Coordenada Y inicial del movimiento.
 * @param xInit   Coordenada X inicial del movimiento.
 * @param yFinal  Coordenada Y final del movimiento.
 * @param xFinal  Coordenada X final del movimiento.
 * @return La puntuación del movimiento.
 */
    private int movimientoPuntuacion(int yInit, int xInit, int yFinal, int xFinal)
    {
        // Implementación para calcular la puntuación del movimiento

        int puntuacion = 0;
        puntuacion += puntuacionFichaAtaque(yInit, xInit, yFinal, xFinal);
        puntuacion += puntuacionDePosiciones[yInit][xInit];
        puntuacion += puntuacionDefensa(yInit, xInit);
        puntuacion += puntuacionDeLaCasillaSegura(yFinal, xFinal);
        puntuacion += puntuacionMovimientoFuturo(yInit, xInit, yFinal, xFinal);
        puntuacion += puntuacionDefenderComiendo(tablero, yInit, xInit, yFinal, xFinal);
        return puntuacion;
    }

/**
 * Calcula la puntuación del movimiento futuro simulando el tablero.
 *
 * @param yInit   Coordenada Y inicial del movimiento.
 * @param xInit   Coordenada X inicial del movimiento.
 * @param yFinal  Coordenada Y final del movimiento.
 * @param xFinal  Coordenada X final del movimiento.
 * @return La puntuación del movimiento futuro.
 */

    private int puntuacionMovimientoFuturo(int yInit, int xInit, int yFinal, int xFinal)
    {
        // Implementación para calcular la puntuación del movimiento futuro
        int puntuacion = 0;
        String tableroFuturo[][] = new String[8][8];
        copiarTablero(tableroFuturo);
        tableroFuturo[yFinal][xFinal] = tableroFuturo[yInit][xInit];
        tableroFuturo[yInit][xInit] = "";

        if (reyEnJaque(tableroFuturo))
        {
            return -2000;
        } else if (jaqueMate(tableroFuturo))
        {
            return 1000;
        } else if (posibleJaqueMate(tableroFuturo))
        {
            return -2000;
        } else
        {
            puntuacion += fichaDefender(tableroFuturo, yFinal, xFinal);
            puntuacion += fichaAtacar(tableroFuturo, yFinal, xFinal);
            return puntuacion;
        }
    }

/**
 * Calcula la puntuación de la defensa de una ficha.
 *
 * @param tableroM Matriz del tablero.
 * @param y       Coordenada Y de la ficha.
 * @param x       Coordenada X de la ficha.
 * @return La puntuación de la defensa.
 */

    private int fichaDefender(String[][] tableroM, int y, int x)
    {
        // Implementación para calcular la puntuación de defensa
        String[] ataquesEsaFicha = movimientos.ataqueFicha2(tableroM, y, x);
        int valor = 0;
        for (int i = 0; i < ataquesEsaFicha.length; ++i)
        {
            int yAtaque = Integer.parseInt(String.valueOf(ataquesEsaFicha[i].charAt(0)));
            int xAtaque = Integer.parseInt(String.valueOf(ataquesEsaFicha[i].charAt(1)));

            if (comprobarFichaEsnegra(tableroM, yAtaque, xAtaque))
            {
                valor += 2;
            }

        }
        return valor;
    }

    private int fichaAtacar(String[][] tableroM, int y, int x)
    {
        String[] ataquesEsaFicha = movimientos.ataqueFicha2(tableroM, y, x);
        int valor = 0;
        for (int i = 0; i < ataquesEsaFicha.length; ++i)
        {
            int yAtaque = Integer.parseInt(String.valueOf(ataquesEsaFicha[i].charAt(0)));
            int xAtaque = Integer.parseInt(String.valueOf(ataquesEsaFicha[i].charAt(1)));

            if (comprobarFichaEsBlanca(tableroM, yAtaque, xAtaque))
            {
                valor += 2;
            }

        }
        return valor;
    }

/**
 * Calcula si hay un posible jaque mate en el siguiente movimiento.
 *
 * @param tableroFuturo Matriz del tablero simulado.
 * @return True si hay un posible jaque mate, de lo contrario False.
 */

    private boolean posibleJaqueMate(String[][] tableroFuturo)
    {
        // Implementación para verificar un posible jaque mate
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (comprobarFichaEsBlanca(tablero, i, j))
                {
                    String posicion = "" + i + "" + j;
                    String[] movimientosBlanca = movimientos.movimientos1Modificados(tableroFuturo, posicion);
                    if (!movimientosBlanca[0].equals(""))
                    {
                        for (int k = 0; k < movimientosBlanca.length; k++)
                        {

                            int iF = Integer.parseInt(String.valueOf(movimientosBlanca[k].charAt(0)));
                            int jF = Integer.parseInt(String.valueOf(movimientosBlanca[k].charAt(1)));

                            String[][] tableroFichaCambiada = new String[8][8];
                            copiarTablero(tableroFuturo, tableroFichaCambiada);

                            tableroFichaCambiada[iF][jF] = tableroFichaCambiada[i][j];
                            tableroFichaCambiada[i][j] = "";
                            if (reyBlancoJaqueMate(tableroFichaCambiada))
                            {
                                return true;
                            }
                        }
                    }

                }
            }
        }
        return false;
    }

/**
 * Verifica si el rey blanco está en jaque mate.
 *
 * @param tableroM Matriz del tablero.
 * @return True si el rey blanco está en jaque mate, de lo contrario False.
 */
    private boolean reyBlancoJaqueMate(String[][] tableroM)
    {
        // Implementación para verificar el jaque mate del rey blanco
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (comprobarFichaEsnegra(tableroM, i, j))
                {
                    String posicion = "" + i + "" + j;
                    String[] movimientosNegra = movimientos.movimientos2Modificados(tableroM, posicion);
                    if (movimientosNegra != null)
                    {
                        if (!movimientosNegra[0].equals(""))
                        {
                            return false;
                        }
                    }

                }
            }
        }
        return true;
    }

/**
 * Calcula la puntuación para defender atacando.
 *
 * @param tableroM Matriz del tablero.
 * @param "y"        Coordenada Y de la ficha.
 * @param "x"        Coordenada X de la ficha.
 * @return La puntuación de defender atacando.
 */
    private int puntuacionDefenderComiendo(String[][] tableroM, int yInit, int xInit, int yFinal, int xFinal)
    {
        String posicionFichaQueAtacaValiosa = posicionFichaAtacaMasValiosa(tableroM);
        if (!posicionFichaQueAtacaValiosa.equals("ninguna"))
        {
            if (comprobarPosicionAtaqueCoincideConLaQueAtaca(yFinal, xFinal, posicionFichaQueAtacaValiosa))
            {
                int valorFichaMasValiosa = FichaMasValiosaAtacada(tableroM);
                if (fichaDefendidaPor1(tableroM, posicionFichaQueAtacaValiosa) == false)
                {
                    return valorFichaMasValiosa * 3;
                } else
                {
                    if (fichaQueAtacaValiosa(tableroM, yInit, xInit, yFinal, xFinal))
                    {
                        return valorFichaMasValiosa * 2;
                    }
                }
            }
        }
        return 0;
    }

    private boolean fichaQueAtacaValiosa(String[][] tableroM, int yInit, int xInit, int yFinal, int xFinal)
    {
        String fichaQueAtacaMasValiosa = tableroM[yFinal][xFinal];
        String fichaQueLaAtaca = tableroM[yInit][xInit];

        int valorAtacaMasValiosa = valorFicha(fichaQueAtacaMasValiosa);
        int valorQueLaAtaca = valorFicha(fichaQueLaAtaca);

        return (valorAtacaMasValiosa >= valorQueLaAtaca) ? true : false;

    }

    private boolean comprobarPosicionAtaqueCoincideConLaQueAtaca(int yFinal, int xFinal, String posAtaque)
    {
        String posicionAtaca = "" + yFinal + "" + xFinal;
        return (posicionAtaca.equals(posAtaque)) ? true : false;
    }

    private int FichaMasValiosaAtacada(String[][] tableroM)
    {
        String[] movimientosFichas1 = movimientos.MovimientosFichas1(tableroM);
        int valorMayor = 0;
        if (!movimientosFichas1[0].equals(""))
        {
            for (int i = 0; i < movimientosFichas1.length; i++)
            {
                int y = Integer.parseInt(String.valueOf(movimientosFichas1[i].charAt(0)));
                int x = Integer.parseInt(String.valueOf(movimientosFichas1[i].charAt(1)));
                if (comprobarFichaEsnegra(tableroM, y, x))
                {
                    String ficha = tableroM[y][x];
                    int valorTemporal = valorFicha(ficha);
                    if (valorTemporal > valorMayor)
                    {
                        valorMayor = valorTemporal;
                    }

                }

            }
        }
        return valorMayor;
    }

    //Funcion para que ataque a la ficha de mayor valor.
    private String posicionFichaAtacaMasValiosa(String[][] tableroM)
    {
        String posicionAtacanteMasValiosa = "ninguna";
        int valorMayor = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (comprobarFichaEsBlanca(tableroM, i, j))
                {
                    String[] movimientosFichaBlanca = movimientos.ataqueFicha1(tableroM, i, j);
                    if (!movimientosFichaBlanca[0].equals(""))
                    {
                        for (int k = 0; k < movimientosFichaBlanca.length; k++)
                        {
                            int y = Integer.parseInt(String.valueOf(movimientosFichaBlanca[k].charAt(0)));
                            int x = Integer.parseInt(String.valueOf(movimientosFichaBlanca[k].charAt(1)));
                            if (comprobarFichaEsnegra(tableroM, y, x))
                            {
                                String ficha = tableroM[y][x];
                                int valorTemporal = valorFicha(ficha);
                                if (valorTemporal > valorMayor)
                                {
                                    posicionAtacanteMasValiosa = "" + i + "" + j;
                                    valorMayor = valorTemporal;
                                }

                            }
                        }
                    }

                }
            }
        }
        return posicionAtacanteMasValiosa;
    }

    //funcion para hacer jaque mate
    private boolean jaqueMate(String[][] tableroM)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                String posicion = "" + i + "" + j;
                if (comprobarFichaEsBlanca(tableroM, i, j))
                {
                    String[] movimientosBlancos = movimientos.movimientos1Modificados(tableroM, posicion);
                    if (!movimientosBlancos[0].equals("")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Funcion para saber si mi rey está en jaque.
    private boolean reyEnJaque (String[][] tableroM) {
        //Buscamos primero al rey y luego vemos todas los movimientos posibles de 1
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (tableroM[i][j].equals("2_rey"))
                {
                    String posicionRey = "" + i + "" + j;
                    String[] movimientosBlancas = movimientos.MovimientosFichas1(tableroM);
                    for (int k = 0; k < movimientosBlancas.length; k++)
                    {
                        if (posicionRey.equals(movimientosBlancas[k]))
                        {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    private void copiarTablero(String[][] tableroM)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                tableroM[i][j] = tablero[i][j];
            }
        }
    }

    private void copiarTablero(String[][] tableroOriginal, String[][] tableroCopia)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                tableroCopia[i][j] = tableroOriginal[i][j];
            }
        }
    }

    private int puntuacionDeLaCasillaSegura(int y, int x)
    {
        String posicionFinal = "" + y + "" + x;
        String movimientosAtaque1[] = movimientos.MovimientosFichas1(tablero);
        for (int i = 0; i < movimientosAtaque1.length; i++)
        {
            if (posicionFinal.equals(movimientosAtaque1[i]))
            {
                return -8;
            }
        }
        return 3;
    }

    private int puntuacionDefensa(int y, int x)
    {
        int puntuacion = 0;
        String[] movimientosDefensora = movimientos.ataqueFicha2(tablero, y, x);
        for (int i = 0; i < movimientosDefensora.length; i++)
        {
            int ejeY = Integer.parseInt(String.valueOf(movimientosDefensora[i].charAt(0)));
            int ejeX = Integer.parseInt(String.valueOf(movimientosDefensora[i].charAt(1)));
            String fichaDefendida = tablero[ejeY][ejeX];
            if (!fichaDefendida.equals(""))
            {
                if (fichaDefendida.charAt(0) == 'B')
                {
                    puntuacion -= valorFicha(fichaDefendida) / 4;
                }
            }
        }
        return puntuacion;
    }

    private int puntuacionFichaAtaque(int yInit, int xInit, int yFinal, int xFinal)
    {
        String fichaInicial = fichaCasilla(tablero, yInit, xInit);
        if (comprobarFichaEsBlanca(tablero, yFinal, xFinal))
        {

            String fichaFinal = fichaCasilla(tablero, yFinal, xFinal);
            
            String posicionFinal = "" + yFinal + "" + xFinal;

            if (fichaDefendidaPor1(tablero, posicionFinal) == false)
            {
                return valorFicha(fichaFinal) * 3;
            } else if (primeraMasAlta(fichaFinal, fichaInicial))
            {
                return valorFicha(fichaFinal) * 3;
            } else if (mismoValor(fichaFinal, fichaFinal))
            {
                return valorFicha(fichaFinal) * 2;
            } else
            {
                return 0;
            }
        }

        if (fichaInicial.equals("2_rey"))
        {
            return -10;
        }

        return 0;
    }

    private String devolverFichaSintetizada(String ficha)
    {
        String[] array = ficha.split("_");
        return array[1];
    }

    private void examinarCasillasAtacadas()
    {
        ajustarPuntuacion();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (comprobarFichaEsBlanca(tablero, i, j))
                {
                    String posicion = "" + i + "" + j;
                    String fichaAtacante = tablero[i][j];
                    String[] movimientosPosibles = movimientos.movimientosFichas(tablero, posicion);
                    try
                    {
                        for (int k = 0; k < movimientosPosibles.length; k++)
                        {
                            int yM = Character.getNumericValue(movimientosPosibles[k].charAt(0));
                            int xM = Character.getNumericValue(movimientosPosibles[k].charAt(1));
                            String fichaAtacada = tablero[yM][xM];
                            if (comprobarFichaEsnegra(tablero, yM, xM))
                            {
                                String posicionFichaAtacada = "" + yM + "" + xM;
                                if (primeraMasAlta(fichaAtacada, fichaAtacante))
                                {
                                    puntuacionDePosiciones[yM][xM] += valorFichaSinRey(fichaAtacada);
                                } else if (FichaDefendidaPor2(tablero, posicionFichaAtacada) == false)
                                {
                                    puntuacionDePosiciones[yM][xM] += valorFichaSinRey(fichaAtacada);
                                }
                            }
                        }
                    } catch (Exception ex)
                    {
                    }
                }
            }
        }
    }

    private boolean fichaDefendidaPor1(String[][] tableroM, String posicionFicha)
    {
        String[] movimientosVariables = movimientos.MovimientosFichas1(tableroM);
        for (int k = 0; k < movimientosVariables.length; k++) {
            if (posicionFicha.equals(movimientosVariables[k])) {
                return true;
            }
        }
        return false;
    }

    private boolean FichaDefendidaPor2(String[][] tableroM, String posicionFicha)
    {
        String[] movimientosVariables = movimientos.MovimientosFichas2(tableroM);
        for (int k = 0; k < movimientosVariables.length; k++)
        {
            if (posicionFicha.equals(movimientosVariables[k]))
            {
                return true;
            }
        }
        return false;
    }

    //Comprueba si la ficha seleccionada es de color blanco
    private boolean comprobarFichaEsBlanca(String[][] tableroM, int y, int x)
    {
        if (!tableroM[y][x].equals(""))
        {
            return (tableroM[y][x].charAt(0) == '1') ? true : false;
        }
        return false;
    }

    //Comprobar si la ficha es de color negro.
    private boolean comprobarFichaEsnegra(String[][] tableroM, int y, int x)
    {
        if (!tableroM[y][x].equals(""))
        {
            return (tableroM[y][x].charAt(0) == '2') ? true : false;
        }
        return false;
    }

    //Comprueba que ficha esta en esa casilla.
    private String fichaCasilla(String[][] tableroM, int y, int x)
    {
        String ficha = tableroM[y][x];
        return ficha;
    }


    private void ajustarPuntuacion()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                puntuacionDePosiciones[i][j] = 0;
            }
        }
    }

    //El valor de la primera ficha que comprueba es mas alto.
    private boolean primeraMasAlta(String ficha1, String ficha2)
    {
        String[] ficha1Split = ficha1.split("_");
        String[] ficha2Split = ficha2.split("_");

        int valor1 = valorFicha(ficha1Split[1]);
        int valor2 = valorFicha(ficha2Split[1]);

        return (valor1 > valor2) ? true : false;
    }

    //Teinen el mismo valor las os fichas a comprobar
    private boolean mismoValor(String ficha1, String ficha2)
    {
        String[] ficha1Split = ficha1.split("_");
        String[] ficha2Split = ficha2.split("_");

        int valor1 = valorFicha(ficha1Split[1]);
        int valor2 = valorFicha(ficha2Split[1]);

        return (valor1 == valor2) ? true : false;
    }

    //Saber el valor de la ficha
    private int valorFicha(String ficha)
    {
        String ficha1 = ficha;

        if (ficha.charAt(0) == '1' || ficha.charAt(0) == '2')
        {
            ficha1 = devolverFichaSintetizada(ficha);
        }

        int valor = 0;

        if (ficha1.equals("peon"))
        {
            valor = 1;
        } else if (ficha1.equals("alfil"))
        {
            valor = 8;
        } else if (ficha1.equals("torre"))
        {
            valor = 12;
        } else if (ficha1.equals("caballo"))
        {
            valor = 12;
        } else if (ficha1.equals("reina"))
        {
            valor = 18;
        } else if (ficha1.equals("rey"))
        {
            valor = 20;
        }
        return valor;
    }

    private int valorFichaSinRey(String ficha)
    {
        String ficha1 = ficha;

        if (ficha.charAt(0) == '1' || ficha.charAt(0) == '2')
        {
            ficha1 = devolverFichaSintetizada(ficha);
        }

        int valor = 0;

        if (ficha1.equals("peon"))
        {
            valor = 1;
        }
        else if (ficha1.equals("alfil"))
        {
            valor = 8;
        }
        else if (ficha1.equals("torre"))
        {
            valor = 12;
        }
        else if (ficha1.equals("caballo"))
        {
            valor = 12;
        }
        else if (ficha1.equals("reina"))
        {
            valor = 18;
        }
        return valor;
    }
}
