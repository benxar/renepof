package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Indexed
@Table(name = "organizaciones")
@AnalyzerDef(name = "organizacionanalyzer",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @org.hibernate.search.annotations.Parameter(name = "language", value = "Spanish")
                })
        })
@JsonPropertyOrder({"id", "nombre", "enfermedades", "omim", "descripcion", "contacto"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organizacion {

    @Id
    @GeneratedValue
    private Long id;

    @Field(name = "organizacion")
    @Analyzer(definition = "organizacionanalyzer")
    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="organizacion_enfermedad",
            joinColumns=@JoinColumn(name="organizacion_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="enfermedad_id", referencedColumnName="id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Enfermedad> enfermedades;

    @Transient
    @JsonProperty("codigosEnfermedad")
    private List<Long> codigosEnfermedad;

    @Transient
    @JsonProperty("codigosEnfermedadOMIM")
    private List<Long> codigosEnfermedadOMIM;

    @Transient
    @JsonProperty("orpha")
    private Long orphaNumber;

    @Transient
    private Long omim;

    private String descripcion;

    @Embedded
    private Contacto contacto;

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

    public Long getOrphaNumber() {
        return orphaNumber;
    }

    public void setOrphaNumber(Long orphaNumber) {
        this.orphaNumber = orphaNumber;
    }

    public Long getOmim() {
        return omim;
    }

    public void setOmim(Long omim) {
        this.omim = omim;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public List<Enfermedad> getEnfermedades() {
        if (this.enfermedades == null) {
            this.enfermedades = new ArrayList<Enfermedad>();
        }
        return enfermedades;
    }

    public void setEnfermedades(List<Enfermedad> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public List<Long> getCodigosEnfermedad() {
        return codigosEnfermedad;
    }

    public void setCodigosEnfermedad(List<Long> codigosEnfermedad) {
        this.codigosEnfermedad = codigosEnfermedad;
    }

    public List<Long> getCodigosEnfermedadOMIM() {
        return codigosEnfermedadOMIM;
    }

    public void setCodigosEnfermedadOMIM(List<Long> codigosEnfermedadOMIM) {
        this.codigosEnfermedadOMIM = codigosEnfermedadOMIM;
    }
}

