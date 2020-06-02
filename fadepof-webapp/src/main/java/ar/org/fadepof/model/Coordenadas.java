package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embeddable;

@Embeddable
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Coordenadas {

    private String latitud;
    private String longitud;
    private Integer nivelZoom;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Integer getNivelZoom() {
        return nivelZoom;
    }

    public void setNivelZoom(Integer nivelZoom) {
        this.nivelZoom = nivelZoom;
    }
}
