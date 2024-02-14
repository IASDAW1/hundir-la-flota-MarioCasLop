package HundirLaFlota;

public class Motovelero extends Barco {
    public Motovelero(int longitud, String nombre) {
        super(longitud, nombre);
    }

    public Motovelero() {
        super(2, "Motovelero");
    }

    @Override
    String getTipo() {
        return "Motovelero";
    }
    public int getlongitud(){
        return longitud;
    }
}
