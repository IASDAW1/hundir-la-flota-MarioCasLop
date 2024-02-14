package HundirLaFlota;

public class Velero extends Barco {
    public Velero(int longitud, String nombre) {
        super(longitud, nombre);
    }
    public Velero() {
        super(1, "Velero");
    }

    @Override
    String getTipo(){
        return "Velero";
    }
    public int getlongitud(){
        return longitud;
    }
}
