package ar.org.fadepof.model;

import javax.persistence.Embeddable;

@Embeddable
public class Documento {

    public enum TipoDocumento {
        DNI,
        PASAPORTE
    }

    private TipoDocumento tipoDocumento = TipoDocumento.DNI;
    private String paisEmision = "AR";
    private String numeroDocumento;

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getPaisEmision() {
        return paisEmision;
    }

    public void setPaisEmision(String paisEmision) {
        this.paisEmision = paisEmision;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}
