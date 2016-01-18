package mx.citydevs.hackcdmx.beans;

import java.io.Serializable;

/**
 * Created by zace3d on 3/7/15.
 */
public class Officer implements Serializable {
    private String nombre_completo;
    private String placa;
    private String id_tipo_policia;

    public String getNombre() {
        return nombre_completo;
    }

    public void setNombre(String nombre) {
        this.nombre_completo = nombre;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return id_tipo_policia;
    }

    public void setTipo(String url) {
        this.id_tipo_policia = id_tipo_policia;
    }
}
