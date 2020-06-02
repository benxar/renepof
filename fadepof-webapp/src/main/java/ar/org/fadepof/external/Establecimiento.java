package ar.org.fadepof.external;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Establecimiento {

    private String resultado;

    private String caps;
    private String categoriaDeLaTipologia;
    private String codIndecDepto;
    private String codIndecLocalidad;
    private String codIndecProvincia;
    private Long codigo;
    private Long codigoSISA;
    private String dependencia;
    private String depto;
    private DomicilioSISA domicilio;
    private String fechaModificacion;
    private String fechaRegistro;
    private Long idCategoria;
    private List<ImagenSISA> imagenes;
    private String internacion;
    private String localidad;
    private String nombre;
    private String origenFinanciamiento;
    private ParticipacionesSISA participaciones;
    private String provincia;
    private TelefonoSISA telefono1;
    private TelefonoSISA telefono2;
    private TelefonoSISA telefono3;
    private TelefonoSISA telefono4;
    private String tipologia;
    private CoordenadasDeMapaSISA coordenadasDeMapa;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getCaps() {
        return caps;
    }

    public void setCaps(String caps) {
        this.caps = caps;
    }

    public String getCategoriaDeLaTipologia() {
        return categoriaDeLaTipologia;
    }

    public void setCategoriaDeLaTipologia(String categoriaDeLaTipologia) {
        this.categoriaDeLaTipologia = categoriaDeLaTipologia;
    }

    public String getCodIndecDepto() {
        return codIndecDepto;
    }

    public void setCodIndecDepto(String codIndecDepto) {
        this.codIndecDepto = codIndecDepto;
    }

    public String getCodIndecLocalidad() {
        return codIndecLocalidad;
    }

    public void setCodIndecLocalidad(String codIndecLocalidad) {
        this.codIndecLocalidad = codIndecLocalidad;
    }

    public String getCodIndecProvincia() {
        return codIndecProvincia;
    }

    public void setCodIndecProvincia(String codIndecProvincia) {
        this.codIndecProvincia = codIndecProvincia;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoSISA() {
        return codigoSISA;
    }

    public void setCodigoSISA(Long codigoSISA) {
        this.codigoSISA = codigoSISA;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public DomicilioSISA getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioSISA domicilio) {
        this.domicilio = domicilio;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<ImagenSISA> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenSISA> imagenes) {
        this.imagenes = imagenes;
    }

    public String getInternacion() {
        return internacion;
    }

    public void setInternacion(String internacion) {
        this.internacion = internacion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
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

    public ParticipacionesSISA getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(ParticipacionesSISA participaciones) {
        this.participaciones = participaciones;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public TelefonoSISA getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(TelefonoSISA telefono1) {
        this.telefono1 = telefono1;
    }

    public TelefonoSISA getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(TelefonoSISA telefono2) {
        this.telefono2 = telefono2;
    }

    public TelefonoSISA getTelefono3() {
        return telefono3;
    }

    public void setTelefono3(TelefonoSISA telefono3) {
        this.telefono3 = telefono3;
    }

    public TelefonoSISA getTelefono4() {
        return telefono4;
    }

    public void setTelefono4(TelefonoSISA telefono4) {
        this.telefono4 = telefono4;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public CoordenadasDeMapaSISA getCoordenadasDeMapa() {
        return coordenadasDeMapa;
    }

    public void setCoordenadasDeMapa(CoordenadasDeMapaSISA coordenadasDeMapa) {
        this.coordenadasDeMapa = coordenadasDeMapa;
    }
}
