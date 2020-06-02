package ar.org.fadepof;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by benja on 6/22/17.
 */
@ApplicationPath("/api")
public class FadepofApp extends Application {

  public FadepofApp() {
    //System.setProperty("javax.net.ssl.trustStore", "/Users/csostari/Platforms/jboss-eap-7.0/standalone/configuration/truststore.jks");
    System.setProperty("javax.net.ssl.trustStore", "/opt/eap/standalone/configuration/keystores/truststore.jks");
    System.setProperty("javax.net.ssl.trustStorePassword", "redhat01");
  }
}

