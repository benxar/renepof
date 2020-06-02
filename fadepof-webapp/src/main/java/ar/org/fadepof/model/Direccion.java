package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Direccion {

    private String pais;
    private String provincia;
    private String localidad;
    @Transient
    private String provinciaText;
    private String provinciaLibre;
    @Transient
    private String localidadText;
    private String localidadLibre;
    private String direccion;
    private String codigoPostal;
    private String departamento;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvinciaText() {
        return provinciaText;
    }

    public void setProvinciaText(String provinciaText) {
        this.provinciaText = provinciaText;
    }

    public String getLocalidadText() {
        return localidadText;
    }

    public void setLocalidadText(String localidadText) {
        this.localidadText = localidadText;
    }

    public String getLocalidadLibre() {
        return localidadLibre;
    }

    public void setLocalidadLibre(String localidadLibre) {
        this.localidadLibre = localidadLibre;
    }

    public String getProvinciaLibre() {
        return provinciaLibre;
    }

    public void setProvinciaLibre(String provinciaLibre) {
        this.provinciaLibre = provinciaLibre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
