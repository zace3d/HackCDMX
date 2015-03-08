package mx.citydevs.hackcdmx.beans;

/**
 * Created by zace3d on 3/7/15.
 */
public class Officer {
    private String Nombre;
    private String Placa;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        this.Placa = placa;
    }

    public Officer(String Nombre) {
        this.Nombre = Nombre;
    }
}
