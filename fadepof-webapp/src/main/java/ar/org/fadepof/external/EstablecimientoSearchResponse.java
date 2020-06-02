package ar.org.fadepof.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EstablecimientoSearchResponse {

    @JsonProperty("resultado")
    private String resultado;

    @JsonProperty("cantidadDeResultados")
    private String cantidadDeResultados;

    @JsonProperty("establecimientos")
    private EstablecimientoReducido[] establecimientos;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getCantidadDeResultados() {
        return cantidadDeResultados;
    }

    public void setCantidadDeResultados(String cantidadDeResultados) {
        this.cantidadDeResultados = cantidadDeResultados;
    }

    public EstablecimientoReducido[] getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(EstablecimientoReducido[] establecimientos) {
        this.establecimientos = establecimientos;
    }
}
