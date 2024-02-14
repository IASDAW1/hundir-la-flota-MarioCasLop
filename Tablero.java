package HundirLaFlota;
/*
tablero (char[][]): una matriz que representa el tablero de juego.

barcos (Barco[]): un arreglo de objetos Barco que se colocarán en el tablero.
Tablero(int filas, int columnas, Barco[] barcos): constructor que inicializa el tablero y los barcos.

void inicializarTablero(): inicializa el tablero con caracteres que representan el agua.

void imprimirTablero(): imprime el estado actual del tablero.

void colocarBarcos(): coloca los barcos en posiciones aleatorias del tablero.

boolean puedeColocarBarco(Barco barco, int fila, int columna): verifica si es posible colocar un barco en una posición específica.

void colocarBarcoEnTablero(Barco barco, int fila, int columna): coloca un barco en el tablero.

void jugar(): inicia el juego, permitiendo al jugador ingresar coordenadas para disparar contra los barcos. El juego continúa hasta que todos los barcos sean hundidos.
 */

import java.util.Scanner;

class Tablero {
    private char[][] tableroJugador1;
    private char[][] tableroJugador2;
    private Barco[] barcosJugador1;
    private Barco[] barcosJugador2;

    public Tablero ( int filas , int columnas , Barco[] barcosJugador1 , Barco[] barcosJugador2 ) {
        this.tableroJugador1 = new char[filas][columnas];
        this.tableroJugador2 = new char[filas][columnas];
        this.barcosJugador1 = barcosJugador1;
        this.barcosJugador2 = barcosJugador2;
        inicializarTablero ( tableroJugador1 );
        inicializarTablero ( tableroJugador2 );
    }

    private void inicializarTablero ( char[][] tablero ) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    private void imprimirTablero(char[][] tablero, boolean ocultarBarcos) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (ocultarBarcos && tablero[i][j] != '-' && tablero[i][j] != 'H' && tablero[i][j] != 'A') {
                    System.out.print("- ");
                } else {
                    System.out.print(tablero[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


    private void colocarBarcosParaJugador ( Barco[] barcos , char[][] tablero ) throws Exception {
        Scanner scanner = new Scanner ( System.in );
        for (int k = 0; k < barcos.length; k++) {
            boolean colocado = false;
            while (!colocado) {
                System.out.println ( ""+"Elige una coordenada para colocar un barco " + barcos[k].getTipo ( ) + ": " );
                int fila = scanner.nextInt ( );
                int columna = scanner.nextInt ( );
                try {
                    if ( puedeColocarBarco ( barcos[k] , fila , columna , tablero ) ) {
                        colocarBarcoEnTablero ( barcos[k] , fila , columna , tablero );
                        colocado = true;
                    } else {
                        System.out.println ( "No se puede colocar el barco en esa posición. Inténtelo de nuevo." );
                    }
                } catch (Exception e) {
                    System.out.println ( "Error al colocar el barco: " + e.getMessage ( ) );
                }
            }
        }
    }

    private boolean puedeColocarBarco(Barco barco, int fila, int columna, char[][] tablero) {
        int longitudBarco;
        switch (barco.getTipo()) {
            case "Velero":
                longitudBarco = 1;
                break;
            case "Motovelero":
                longitudBarco = 2;
                break;
            case "Yate":
                longitudBarco = 3;
                break;
            default:
                longitudBarco = 0;
                break;
        }

        if (fila + longitudBarco > tablero.length || columna >= tablero[0].length) {
            return false;
        }

        for (int i = 0; i < longitudBarco; i++) {
            if (tablero[fila + i][columna] != '-') {
                return false;
            }
        }

        return true;
    }



    private void colocarBarcoEnTablero(Barco barco, int fila, int columna, char[][] tablero) {
        char tipoBarco;
        int longitudBarco;
        switch (barco.getTipo()) {
            case "Velero":
                tipoBarco = 'V';
                longitudBarco = 1;
                break;
            case "Motovelero":
                tipoBarco = 'M';
                longitudBarco = 2;
                break;
            case "Yate":
                tipoBarco = 'Y';
                longitudBarco = 3;
                break;
            default:
                tipoBarco = 'X';
                longitudBarco = 0;
                break;
        }

        for (int i = 0; i < longitudBarco; i++) {
            tablero[fila + i][columna] = tipoBarco;
        }
    }



    public void jugar() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Jugador 1, coloca tus barcos:");
            colocarBarcosParaJugador(barcosJugador1, tableroJugador1);

            System.out.println("Jugador 2, coloca tus barcos:");
            colocarBarcosParaJugador(barcosJugador2, tableroJugador2);
        } catch (Exception e) {
            System.out.println("Error al colocar los barcos: " + e.getMessage());
            return;
        }


        while (!todosHundidos(barcosJugador1, tableroJugador1) && !todosHundidos(barcosJugador2, tableroJugador2)) {
            System.out.println("Turno del Jugador 1");
            System.out.println("Tu tablero:");
            imprimirTablero(tableroJugador1, false);
            System.out.println("Tablero del oponente:");
            imprimirTablero(tableroJugador2, true);
            System.out.println("Es tu turno:");
            atacar(scanner, tableroJugador2, barcosJugador2);

            if (todosHundidos(barcosJugador2, tableroJugador2)) {
                System.out.println("Jugador 1 ha hundido todos los barcos del oponente");
                break;
            }

            System.out.println("Turno del Jugador 2");
            System.out.println("Tu tablero:");
            imprimirTablero(tableroJugador2, false);
            System.out.println("Tablero del oponente:");
            imprimirTablero(tableroJugador1, true);
            System.out.println("Es tu turno:");
            atacar(scanner, tableroJugador1, barcosJugador1);

            if (todosHundidos(barcosJugador1, tableroJugador1)) {
                System.out.println("Jugador 2 ha hundido todos los barcos del oponente");
                break;
            }
        }

        if (todosHundidos(barcosJugador1, tableroJugador1) && todosHundidos(barcosJugador2, tableroJugador2)) {
            System.out.println("¡Empate! Todos los barcos de ambos jugadores han sido hundidos.");
        }

        scanner.close();
    }


    private void atacar(Scanner scanner, char[][] tablero, Barco[] barcos) {
        System.out.println("Elija la fila y columna para atacar:");
        int fila = scanner.nextInt();
        int columna = scanner.nextInt();

        char tipoBarco = tablero[fila][columna];
        if (tipoBarco == '-' || tipoBarco == 'H' || tipoBarco == 'A') {
            System.out.println("Has fallado");
            tablero[fila][columna] = 'A';
        } else {
            String mensaje = "";
            switch (tipoBarco) {
                case 'V':
                    mensaje = "Velero";
                    break;
                case 'M':
                    mensaje = "Motovelero";
                    break;
                case 'Y':
                    mensaje = "Yate";
                    break;
            }
            System.out.println("Has acertado hundiendo un barco " + mensaje);
            tablero[fila][columna] = 'H';
        }
    }

    private boolean todosHundidos(Barco[] barcos, char[][] tablero) {
        for (int i = 0; i < barcos.length; i++) {
            if (!barcos[i].haSidoHundido(tablero)) {
                return true;
            }
        }
        return false;
    }
}
