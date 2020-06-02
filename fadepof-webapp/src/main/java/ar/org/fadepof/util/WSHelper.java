package ar.org.fadepof.util;

import ar.gob.msal.sisa.server.ws.services.exposed.impl.ProfesionalServiceSoapImplService;
import ar.gob.msal.sisa.services._1_0.ProfesionalService;
import ar.org.hospitalitaliano.ws.TerminologiaWS;
import ar.org.hospitalitaliano.ws.TerminologiaWSService;

public class WSHelper {

    private static TerminologiaWS _instanceHI;
    private static ProfesionalService _instanceSISA;

    public static TerminologiaWS getInstanceHI() {
        if (_instanceHI == null) {
            _instanceHI = new TerminologiaWSService().getTerminologiaWSPort();
        }
        return _instanceHI;
    }

    public static ProfesionalService getInstanceSISA() {
        if (_instanceSISA == null) {
            _instanceSISA = new ProfesionalServiceSoapImplService().getProfesionalServiceSoapImplPort();
        }
        return _instanceSISA;
    }

}
