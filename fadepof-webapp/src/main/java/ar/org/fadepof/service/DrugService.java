package ar.org.fadepof.service;

import ar.org.fadepof.model.Drug;
import ar.org.fadepof.util.Configuration;
import ar.org.fadepof.util.WSHelper;
import ar.org.hospitalitaliano.ws.Member;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DrugService {

    private static final String EMPTY_RESULTS_TEXT = "El generico ingresado no contiene presentaciones";
    private static Logger log = Logger.getLogger(DrugService.class.getName());

    public List<Drug> getDrugBy(String name) {

        List<Drug> drugs = new ArrayList<Drug>();

        List<Member> members = WSHelper.getInstanceHI()
                .obtenerGenericosPorTxtYSubset(name, Configuration.getSustanciasCode(), Configuration.getInstitucionCode());

        for (Member member: members) {
            Drug drug = new Drug();
            drug.setId(member.getDescId());
            drug.setDescription(member.getDescripcion());
            drugs.add(drug);
        }

        members = WSHelper.getInstanceHI()
                .obtenerGenericosPorTxtYSubset(name, Configuration.getSustanciasComercialesCode(), Configuration.getInstitucionCode());

        for (Member member: members) {
            Drug drug = new Drug();
            log.info("x: [ " + member.getDescripcion() + " | " + member.getDescripcion() + " ]");
            drug.setId(member.getDescId());
            drug.setDescription(member.getDescripcion());
            drugs.add(drug);
        }

        drugs.addAll(this.getDrugByGeneric(name));
        return drugs;
    }

    public List<Drug> getDrugByGeneric(String name) {
        log.info("search by generic "+ name);
        List<Drug> drugs = new ArrayList<Drug>();

        List<Member> aux1 = WSHelper.getInstanceHI()
                .obtenerGenericosPorTxtYSubset(name, Configuration.getSustanciasGenericoCode(), Configuration.getInstitucionCode());

        log.info("results for '"+ name +"':" + aux1.size());
        for (Member memberX: aux1) {

            log.info("codigo generico '"+ memberX.getDescId().toString() + "'");
            List<Member> aux2 = WSHelper.getInstanceHI()
                .obtenerPresentacionesComercialesXGenerico(memberX.getDescId(), Configuration.getInstitucionCode());
            for (Member memberY: aux2) {
                log.info("y: [ " + memberY.getDescripcion() + " | " + memberY.getDescripcion() + " ]");
                if (!EMPTY_RESULTS_TEXT.equals(memberY.getDescripcion())) {
                    Drug drug = new Drug();
                    drug.setId(memberY.getDescId());
                    drug.setDescription(memberY.getDescripcion());
                    drugs.add(drug);
                }
            }
        }

        return drugs;
    }
}
