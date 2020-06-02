package ar.org.fadepof.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EstablecimientoReducido {

    private String caps;
    private String codIndecProvincia;
    private String codigo;
    private CoordenadasDeMapaSISA coordenadasDeMapa;
    private String dependencia;
    private String fechaModificacion;
    private String fechaRegistro;
    private String idCategoria;
    private String nombre;
    private String origenFinanciamiento;
    private String provincia;
    private String tipologia;

    public String getCaps() {
        return caps;
    }

    public void setCaps(String caps) {
        this.caps = caps;
    }

    public String getCodIndecProvincia() {
        return codIndecProvincia;
    }

    public void setCodIndecProvincia(String codIndecProvincia) {
        this.codIndecProvincia = codIndecProvincia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public CoordenadasDeMapaSISA getCoordenadasDeMapa() {
        return coordenadasDeMapa;
    }

    public void setCoordenadasDeMapa(CoordenadasDeMapaSISA coordenadasDeMapa) {
        this.coordenadasDeMapa = coordenadasDeMapa;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigenFinanciamiento() {
        return origenFinanciamiento;
    }

    public void setOrigenFinanciamiento(String origenFinanciamiento) {
        this.origenFinanciamiento = origenFinanciamiento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
}
