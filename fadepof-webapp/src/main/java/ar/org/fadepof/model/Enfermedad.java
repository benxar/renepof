package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

@Entity
@Indexed
@Table(name = "enfermedades")
@AnalyzerDef(name = "enfermedadAnalyzer",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "Spanish")
                })
        })
@JsonPropertyOrder({"id", "nombre", "orphaId", "orphaNumber"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Enfermedad implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "orpha_id")
    private Long orphaId;

    @Column(name = "orpha_number")
    private Long orphaNumber;

    @Field(name = "nombre")
    @Analyzer(definition = "enfermedadAnalyzer")
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fadepof_number")
    private String fadepofNumber;

    @Transient
    private String name;

    private URL link;
    /*
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="paciente_id")
    private Paciente paciente;
    */

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @JoinTable(
            name="organizacion_enfermedad",
            joinColumns=@JoinColumn(name="enfermedad_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="organizacion_id", referencedColumnName="id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Organizacion> organizaciones;

    public Enfermedad() {}

    public Enfermedad(Disorder disorder) {
        this.setOrphaId(disorder.getOrphaId());
        this.setOrphaNumber(disorder.getOrphaNumber());
        this.setNombre(disorder.getName());
        this.setLink(disorder.getExpertLink());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrphaId() {
        return orphaId;
    }

    public void setOrphaId(Long orphaId) {
        this.orphaId = orphaId;
    }

    public Long getOrphaNumber() {
        return orphaNumber;
    }

    public void setOrphaNumber(Long orphaNumber) {
        this.orphaNumber = orphaNumber;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFadepofNumber() {
        return fadepofNumber;
    }

    public void setFadepofNumber(String fadepofNumber) {
        this.fadepofNumber = fadepofNumber;
    }
}
