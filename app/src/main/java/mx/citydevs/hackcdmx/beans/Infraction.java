package mx.citydevs.hackcdmx.beans;

/**
 * Created by zace3d on 3/8/15.
 */
public class Infraction {
    private String id;
    private String infraccion;
    private String monto;
    private String corralon;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(String infraccion) {
        this.infraccion = infraccion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getCorralon() {
        return corralon;
    }

    public void setCorralon(String corralon) {
        this.corralon = corralon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
