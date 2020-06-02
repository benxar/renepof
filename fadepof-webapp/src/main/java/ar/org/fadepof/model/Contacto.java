package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Embeddable;

@Embeddable
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contacto {

    private String telefono1;
    private String telefono2;
    private String email;
    private String web;
    private String facebook;

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
