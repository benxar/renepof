package ar.org.fadepof.external;

import ar.org.fadepof.model.Coordenadas;
import ar.org.fadepof.model.Direccion;
import ar.org.fadepof.model.Establecimiento;

import java.util.ArrayList;
import java.util.List;

public class SISATypesFactory {

    public static List<Establecimiento> getEstablecimiento(EstablecimientoSearchResponse sisaResponse) {
        List<Establecimiento> result =  new ArrayList<>();
        if (sisaResponse != null && sisaResponse.getEstablecimientos() != null) {
            for (EstablecimientoReducido item: sisaResponse.getEstablecimientos()) {
                Establecimiento establecimiento = new Establecimiento();
                establecimiento.setCodigo(Long.valueOf(item.getCodigo()));
                Coordenadas coordenadas = new Coordenadas();
                //coordenadas.setLatitud(item.getCoordenadasDeMapa().getLatitud());
                //coordenadas.setLongitud(item.getCoordenadasDeMapa().getLongitud());
                coordenadas.setNivelZoom(item.getCoordenadasDeMapa().getNivelZoom());
                Direccion direccion = new Direccion();
                direccion.setProvincia(item.getProvincia());
                establecimiento.setDireccion(direccion);
                establecimiento.setCoordenadas(coordenadas);
                establecimiento.setDependencia(item.getDependencia());
                establecimiento.setNombre(item.getNombre());
                establecimiento.setOrigenFinanciamiento(item.getOrigenFinanciamiento());
                establecimiento.setTipologia(item.getTipologia());
                result.add(establecimiento);
            }
        }
        return result;
    }

    public static Establecimiento getEstablecimiento(ar.org.fadepof.external.Establecimiento sisaResponse) {
        Establecimiento establecimiento = new Establecimiento();
        establecimiento.setCodigo(Long.valueOf(sisaResponse.getCodigo()));
        //establecimiento.getCoordenadas().setLatitud(sisaResponse.getCoordenadasDeMapa().getLatitud());
        //stablecimiento.getCoordenadas().setLongitud(sisaResponse.getCoordenadasDeMapa().getLongitud());
        establecimiento.getCoordenadas().setNivelZoom(sisaResponse.getCoordenadasDeMapa().getNivelZoom());
        establecimiento.setDependencia(sisaResponse.getDependencia());
        establecimiento.setNombre(sisaResponse.getNombre());
        establecimiento.setOrigenFinanciamiento(sisaResponse.getOrigenFinanciamiento());
        establecimiento.setTipologia(sisaResponse.getTipologia());
        if (!(sisaResponse.getTelefono1() == null || "null".equals(sisaResponse.getTelefono1().getNumero()))) {
            establecimiento.getContacto().setTelefono1(sisaResponse.getTelefono1().getNumero());
        }
        if (!(sisaResponse.getTelefono2() == null || "null".equals(sisaResponse.getTelefono2().getNumero()))) {
            establecimiento.getContacto().setTelefono2(sisaResponse.getTelefono2().getNumero());
        }
        establecimiento.getDireccion().setPais("AR");
        establecimiento.getDireccion().setProvinciaLibre(sisaResponse.getProvincia());
        establecimiento.getDireccion().setLocalidadLibre(sisaResponse.getLocalidad());
        establecimiento.getDireccion().setDireccion(sisaResponse.getDomicilio().getDireccion());
        establecimiento.getDireccion().setCodigoPostal(sisaResponse.getDomicilio().getCodigoPostal());
        return establecimiento;
    }
}
