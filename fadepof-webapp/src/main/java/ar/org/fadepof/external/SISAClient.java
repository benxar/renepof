package ar.org.fadepof.external;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SISAClient {

    private static final String URL = "https://sisa.msal.gov.ar/sisa/services/rest";
    private Credencial credencial;

    public SISAClient() {
        this.credencial = new Credencial("lescati","f0d3p0f2018");
    }

    public Establecimiento getEstablecimientosByCodigo(Long codigo) {
        return this.getEstablecimientosByCodigo(codigo.toString());
    }


    public Establecimiento getEstablecimientosByCodigo(String codigo) {

        Client client = ClientBuilder.newClient();

        Response response = client
            .target(URL)
            .path("establecimiento")
            .path("{codigo}")
            .resolveTemplate("codigo", codigo)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .accept(MediaType.APPLICATION_JSON_TYPE)
            .post(Entity.entity(this.credencial, MediaType.APPLICATION_JSON_TYPE));

        Establecimiento result = response.readEntity(Establecimiento.class);

        response.close();
        client.close();

        return result;
    }

    public EstablecimientoSearchResponse getEstablecimientosByNombre(String nombre) {

        Client client = ClientBuilder.newClient();

        Response response = client
                .target(URL)
                .path("establecimiento")
                .path("buscar")
                .queryParam("nombre", "{nombre}")
                .resolveTemplate("nombre", nombre)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(this.credencial, MediaType.APPLICATION_JSON_TYPE));

        EstablecimientoSearchResponse result = response.readEntity(EstablecimientoSearchResponse.class);

        response.close();
        client.close();

        return result;
    }

    public MedicoSisa getMedicoByDocumento(String documento) {

        MedicoSisaRequest request = new MedicoSisaRequest();
        request.setUsuario(this.credencial.getUsuario());
        request.setClave(this.credencial.getClave());
        request.setNroDoc(documento);

        Client client = ClientBuilder.newClient();

        Response response = client
                .target(URL)
                .path("profesional")
                .path("obtener")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));

        MedicoSisa result = response.readEntity(MedicoSisa.class);

        response.close();
        client.close();

        return result;
    }

}
