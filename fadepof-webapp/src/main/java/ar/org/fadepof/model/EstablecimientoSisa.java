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
@Table(name = "establecimientos_sisa")
@AnalyzerDef(name = "establecimientosSisaAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "Spanish")
                })
        })
@JsonPropertyOrder({"id", "nombre", "tipologia", "dependencia", "origenFinanciamiento"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EstablecimientoSisa {

    @Id
    private Long id;

    @Field(name="nombreEstablecimiento")
    @Analyzer(definition = "establecimientosSisaAnalyzer")
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

    @Column(name = "tipo")
    private String tipoEstablecimiento;

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

    public String getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    public void setTipoEstablecimiento(String tipoEstablecimiento) {
        this.tipoEstablecimiento = tipoEstablecimiento;
    }
}
