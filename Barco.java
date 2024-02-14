package HundirLaFlota;
/*
longitud (int): la longitud del barco.

nombre (String): el nombre del barco.

partes (boolean[]): un arreglo que indica si cada parte del barco ha sido golpeada.
Barco(int longitud, String nombre): constructor que inicializa los atributos del barco.

boolean haSidoHundido(): devuelve true si todas las partes del barco han sido golpeadas.

void hundirParte(int parte): marca una parte del barco como golpeada.
*/
abstract class Barco {
    protected  int longitud;
    protected String nombre;
    protected boolean[] partes;
    public Barco(int longitud, String nombre){}{
      this.longitud=longitud;
      this.nombre=nombre;
      this.partes=new boolean[longitud];
    }
    public Barco(){
        //constructor vacio
    }
    abstract String getTipo();

    public boolean haSidoHundido( char[][] tablero ){
        for (int i = 0; i < partes.length; i++) {
            if (!partes[i]) {
                return false;
            }
        }
        return true;
    }
}
