package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Indexed
@Table(name = "establecimientos")
//@AnalyzerDef(name = "establecimientosAnalyzer",
//        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
//        filters = {
//                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
//                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
//                        @Parameter(name = "language", value = "Spanish")
//                })
//        })
@JsonPropertyOrder({"id", "codigo", "nombre", "tipologia", "dependencia", "origenFinanciamiento"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Establecimiento {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "codigo_establecimiento")
    private Long codigo;

    //@Analyzer(definition = "establecimientosAnalyzer")
    //@Field(name="nombre")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipologia")
    private String tipologia;

    @Embedded
    private Direccion direccion;

    @Embedded
    private Contacto contacto;

    @Embedded
    private Coordenadas coordenadas;

    @Column(name = "dependencia")
    private String dependencia;

    @Column(name = "origenFinanciamiento")
    private String origenFinanciamiento;

    @Column(name = "sitio_web")
    private String sitioWeb;

    @Column(name = "tipo")
    private String tipoEstablecimiento;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="tratamiento_establecimiento",
            joinColumns=@JoinColumn(name="establecimiento_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="tratamiento_id", referencedColumnName="id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Tratamiento> tratamientos;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="profesional_id")
    private Profesional profesional;

    @Transient
    private List<Profesional> profesionales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        if (this.direccion == null) this.direccion = new Direccion();
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Contacto getContacto() {
        if (this.contacto == null) this.contacto = new Contacto();
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Coordenadas getCoordenadas() {
        if (this.coordenadas == null) this.coordenadas = new Coordenadas();
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getOrigenFinanciamiento() {
        return origenFinanciamiento;
    }

    public void setOrigenFinanciamiento(String origenFinanciamiento) {
        this.origenFinanciamiento = origenFinanciamiento;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public List<Profesional> getProfesionales() {
        if (this.profesionales == null) {
            this.profesionales = new ArrayList<Profesional>();
        }
        return profesionales;
    }

    public void setProfesionales(List<Profesional> profesionales) {
        if (this.profesionales == null) {
            this.profesionales = new ArrayList<Profesional>();
        }
        this.profesionales = profesionales;
    }

    public String getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    public void setTipoEstablecimiento(String tipoEstablecimiento) {
        this.tipoEstablecimiento = tipoEstablecimiento;
    }
}
