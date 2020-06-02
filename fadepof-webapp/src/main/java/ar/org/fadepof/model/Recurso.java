package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Recurso {

    private List<Establecimiento> centrosSalud;
    private List<Organizacion> organizaciones;

    public List<Establecimiento> getCentrosSalud() {
        return centrosSalud;
    }

    public void setCentrosSalud(List<Establecimiento> centrosSalud) {
        this.centrosSalud = centrosSalud;
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }
}
