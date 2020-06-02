package ar.org.fadepof.util;

import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Properties prop;

    static {
        prop = new Properties();
        try {
            prop.load(Configuration.class.getResourceAsStream("/configuration.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long getInstitucionCode() {
        return new Long(prop.getProperty("institucion"));
    }

    public static Long getSustanciasCode() {
        return new Long(prop.getProperty("sustancias"));
    }

    public static Long getSustanciasComercialesCode() {
        return new Long(prop.getProperty("sustanciasComerciales"));
    }

    public static Long getProblemasCode() {
        return new Long(prop.getProperty("problemas"));
    }

    public static Long getProcedimientosCode() {
        return new Long(prop.getProperty("procedimientos"));
    }

    public static Long getSintomasCode() {
        return new Long(prop.getProperty("sintomas"));
    }

    public static Long getSustanciasGenericoCode() { return new Long(prop.getProperty("sustanciasGenerico")); }

}
