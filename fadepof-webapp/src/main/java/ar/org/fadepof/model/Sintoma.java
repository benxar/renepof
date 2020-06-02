package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Indexed
@Table(name = "sintomas")
@JsonPropertyOrder({"id", "id_externo", "descripcion"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sintoma implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "id_externo")
    private Long idExterno;

    @Column(name = "descripcion")
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
