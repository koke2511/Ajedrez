# Ajedrez-nuevo
El trabajo que vamos a realizar es modelar el juego del ajedrez. 
El tablero que vamos a usar es el siguiente:

![imagen](https://github.com/koke2511/Ajedrez-nuevo/assets/62717610/e483b501-16b5-443b-a019-a2fb93c2ca34)

 
Como se puede observar vamos a usar piezas blancas y negras.
Movimientos de las piezas.
Rey: Se mueve una casilla en cualquier dirección: horizontal, vertical o diagonal.
Reina: Se mueve en cualquier dirección y distancia, tanto horizontal, vertical como diagonal.
Torre: Se mueve en línea recta, horizontal o vertical, cualquier cantidad de casillas.
Alfil: Se mueve diagonalmente, cualquier cantidad de casillas.
Caballo: Realiza un movimiento especial en forma de "L". Se mueve dos casillas en una dirección (horizontal o vertical) y luego una casilla en una dirección perpendicular.
Peón: Se mueve hacia adelante una casilla en la columna en la que se encuentra, en su primer movimiento tiene la opción de avanzar dos casillas. Además, capturan en diagonal moviéndose una casilla hacia adelante en diagonal hacia una pieza enemiga.

La estructura que vamos a seguir es la que se encuentra representada en el siguiente diagrama de clases:

![imagen](https://github.com/koke2511/Ajedrez-nuevo/assets/62717610/7389f7e6-5616-49e5-a678-29b1ab1ddb86)

 
Los primeros que seguiremos es la creación de clases. El tipo de lo que será cada atributo está definido junto a ellos. 
Clase Tablero.
Tiene unos atributos que serán privados como: casillas: Casillas [], turnoBlancas : boolean. 
Los atributos públicos serán: moverPieza : boolean, inicializarPieza (pieza Casilla[]): void, imprimirPieza(): void, estaVacio (): boolean y validarDestino.
Clase Casilla.
Como atributos privados tiene: blanca: boolean, coordenadaX : int y coordenadaY: int. 
Como atributos públicos tiene: mover (origen Casilla, tablero Tablero): boolena, validarDesplazamiento y mover (origen Casilla, destino Casilla, tablero Tablero): boolean.
Las clases de las piezas (Peón, Alfil, Caballo, Dama, Torre, Rey) no tendrán ningún atributo en privado, todos serán públicos y tendrán los mismos atributos: mover (origen Casilla, destino Casilla): boolean y ValidarDesplazamiento. 



 
