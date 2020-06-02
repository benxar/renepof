package ar.org.fadepof.service;

import ar.org.fadepof.model.Sintoma;
import ar.org.fadepof.model.Symptom;
import ar.org.fadepof.util.Configuration;
import ar.org.fadepof.util.WSHelper;
import ar.org.hospitalitaliano.ws.Member;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SintomaService {

    public List<Sintoma> getSymptomsBy(String name) {

        List<Sintoma> sintomas = new ArrayList<Sintoma>();

        List<Member> members = WSHelper.getInstanceHI()
                .obtenerGenericosPorTxtYSubset(name, Configuration.getProblemasCode(), Configuration.getInstitucionCode());

        for (Member member: members) {
            Sintoma sintoma = new Sintoma();
            sintoma.setIdExterno(member.getDescId());
            sintoma.setDescripcion(member.getDescripcion());
            sintomas.add(sintoma);
        }
        return sintomas;
    }
}