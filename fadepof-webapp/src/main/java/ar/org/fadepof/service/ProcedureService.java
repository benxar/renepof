package ar.org.fadepof.service;

import ar.org.fadepof.model.Procedure;
import ar.org.fadepof.util.Configuration;
import ar.org.fadepof.util.WSHelper;
import ar.org.hospitalitaliano.ws.Member;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProcedureService {

    public List<Procedure> getProcedureBy(String name) {

        List<Procedure> procedures = new ArrayList<Procedure>();

        List<Member> members = WSHelper.getInstanceHI()
                .obtenerGenericosPorTxtYSubset(name, Configuration.getProcedimientosCode(), Configuration.getInstitucionCode());

        for (Member member: members) {
            Procedure procedure = new Procedure();
            procedure.setId(member.getDescId());
            procedure.setDescription(member.getDescripcion());
            procedures.add(procedure);
        }
        return procedures;
    }
}