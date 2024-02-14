package HundirLaFlota;

public class Yate extends Barco {
    public Yate(int longitud, String nombre) {
        super(longitud, nombre);
    }

    public Yate() {
        super(3, "Yate");
    }

    @Override
    String getTipo() {
        return "Yate";
    }
    public int getlongitud(){
        return longitud;
    }
}