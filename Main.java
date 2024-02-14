package HundirLaFlota;

public class Main {
    public static void main(String[] args) {
        try {
            Barco[] barcosJugador1 = {new Velero(), new Motovelero(), new Yate()};
            Barco[] barcosJugador2 = {new Velero(), new Motovelero(), new Yate()};
            Tablero tablero = new Tablero(10, 10, barcosJugador1, barcosJugador2);
            try {
                tablero.jugar();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        } catch (Exception ex) {
            System.out.println("Error al iniciar el juego: " + ex.getMessage());
        }
    }
}
