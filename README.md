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

Además, como se puede observar, tenemos dos casillas de “Restart” y “Exit”. Si pulsamos la de “Restart”, esta hará que se reinicie automáticamente la partida. Y si pulsamos la de “Exit” automáticamente nos sacará del juego. También hay otra selección que es “Aceptar”. Esta está implícita ya que solo se ve cuando le damos a una de las dos casillas anteriores para cerciorarse de que queremos elegir esa opción y no nos hemos equivocado. 



La estructura que vamos a seguir es la que se encuentra representada en el siguiente diagrama de clases:

![imagen](https://github.com/koke2511/Ajedrez/assets/62717610/be047839-e2ca-4a4e-a9d6-3a89b3ca007b)

 
Como podemos ver en el esquema UML las clases heredan de la clase Controlador. Y esta, hereda del main, donde se ejecuta el código



 
