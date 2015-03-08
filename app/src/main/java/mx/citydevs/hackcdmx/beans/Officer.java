package mx.citydevs.hackcdmx.beans;

import java.io.Serializable;

/**
 * Created by zace3d on 3/7/15.
 */
public class Officer implements Serializable {
    private String id;
    private String nombre;
    private String placa;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
