package ar.org.fadepof.service;

import ar.org.fadepof.model.*;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Stateless
public class DisorderService {

    private static Logger log = Logger.getLogger(DisorderService.class.getName());

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    public void load() {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initDB() {
        try {
            System.out.println("Loading database...");
            JAXBContext jaxbContext = JAXBContext.newInstance(Database.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("ORPHAnomenclature_es.xml");
            Database database = (Database) jaxbUnmarshaller.unmarshal(new InputStreamReader(is));


            StringBuffer omimCodes;
            StringBuffer icd10Codes;
            StringBuffer synonyms;

            for (Disorder disorder: database.getDisorders()) {

                omimCodes = new StringBuffer();
                icd10Codes = new StringBuffer();
                synonyms = new StringBuffer();
                /*
                for (ExternalReference externalReference: disorder.getExternalReferences()) {
                    if (Disorder.OMIM.equals(externalReference.getSource())) {
                        omimCodes.append(externalReference.getReference());
                        omimCodes.append(", ");
                    }
                    if (Disorder.ICD10.equals(externalReference.getSource())) {
                        icd10Codes.append(externalReference.getReference());
                        icd10Codes.append(", ");
                    }
                }*/

                for (String synonym : disorder.getSynonyms()) {
                    synonyms.append(synonym);
                    synonyms.append(", ");
                }

                disorder.setOmim(omimCodes.toString());
                disorder.setIcd10(icd10Codes.toString());
                disorder.setSynonymsText(synonyms.toString());

                em.persist(disorder);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Disorder> getDiseaseByName(String name) {

        List<Disorder> disorders = new ArrayList<Disorder>();

        System.out.println("buscando por: " + name);
        FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
        QueryBuilder disorderDB = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Disorder.class).get();

        StringTokenizer tokenizer = new StringTokenizer(name);

        Query fullTextQuery = disorderDB.keyword().fuzzy().onField("name").matching(name).createQuery();
        List<Disorder> aux = fullTextEm.createFullTextQuery(fullTextQuery).getResultList();

        List<List> allMatches = new ArrayList();
        while (tokenizer.hasMoreElements()) {
            fullTextQuery = disorderDB.keyword().fuzzy().onField("name").matching(tokenizer.nextElement()).createQuery();
            allMatches.add(fullTextEm.createFullTextQuery(fullTextQuery).getResultList());
        }

        log.info("pre filtro: "+aux.size()+" resultados");
        int i = 0;
        for (List<Disorder> matches: allMatches) {
            log.info("resultado "+ i++ +" tuvo "+matches.size() + " hits");
            aux.retainAll(matches);
            log.info("filtro: "+ i + " " +disorders.size()+" resultados");
        }
        log.info("post filtro: "+disorders.size()+" resultados");
        for (Disorder disorder: aux) {
            log.info("resultado: "+disorder.getName());
        }

        // WORKAROUND BUG
        for (Disorder d: aux) {
            Disorder disorder = new Disorder();
            disorder.setName(d.getName());
            disorder.setOrphaId(d.getOrphaId());
            disorder.setOrphaNumber(d.getOrphaNumber());
            disorder.setExpertLink(d.getExpertLink());
            disorder.setFadepofNumber(d.getFadepofNumber());
            //disorder.setType(d.getDisorderType().getDisorderType());
            disorders.add(disorder);
        }

        return disorders;
    }

    public List<Disorder> getDiseaseByDescription(String description) {

        List<Disorder> disorders = new ArrayList<Disorder>();

        log.info("buscando por: "+description);
        FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
        QueryBuilder disorderDB = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Disorder.class).get();

        Query fullTextQuery = disorderDB.keyword().onField("description").matching(description).createQuery();
        disorders = fullTextEm.createFullTextQuery(fullTextQuery).getResultList();

        return disorders;
    }

    public Disorder getDiseaseByOrphaId(Long orphaId) {

        log.info("buscando por orpha ID: "+orphaId);

        return em.createQuery("from Disorder d where d.orphaId = :orphaId", Disorder.class)
                .setParameter("orphaId", orphaId)
                .getSingleResult();

    }

    public Enfermedad getDiseaseByOrphaNumber(Long orphaNumber) {

        log.info("buscando por orpha number: "+orphaNumber);

        Disorder disorder = null;
        Enfermedad enfermedad = null;
        Enfermedad enfermedadLocal = null;

        try {
            disorder = em.createQuery("from Disorder d where d.orphaNumber = :orphaNumber", Disorder.class)
                    .setParameter("orphaNumber", orphaNumber)
                    .getSingleResult();
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }

        if (disorder != null) {
            enfermedadLocal = find(disorder);
            if (enfermedadLocal == null) {
                enfermedad = new Enfermedad(disorder);
                em.persist(enfermedad);
            } else {
                enfermedad = enfermedadLocal;
            }
        }
        return enfermedad;
    }


    public Enfermedad getDiseaseByFadepofNumber(String fadepofNumber) {

        log.info("buscando por fadepof number: "+fadepofNumber);

        Disorder disorder = null;
        Enfermedad enfermedad = null;
        Enfermedad enfermedadLocal = null;

        try {
            disorder = em.createQuery("from Disorder d where d.fadepofNumber = :fadepofNumber", Disorder.class)
                    .setParameter("fadepofNumber", fadepofNumber)
                    .getSingleResult();
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }

        if (disorder != null) {
            enfermedadLocal = find(disorder);
            if (enfermedadLocal == null) {
                enfermedad = new Enfermedad(disorder);
                em.persist(enfermedad);
            } else {
                enfermedad = enfermedadLocal;
            }
        }
        return enfermedad;
    }

    private Enfermedad find(Disorder disorder) {
        Enfermedad enfermedadLocal = null;
        if (disorder != null && disorder.getFadepofNumber() != null) {
            try {
                List<Enfermedad> results = em.createQuery("from Enfermedad where fadepofNumber = :fadepofNumber", Enfermedad.class)
                    .setParameter("fadepofNumber", disorder.getFadepofNumber())
                    .getResultList();
                if (results != null && !results.isEmpty()) {
                    enfermedadLocal = results.get(0);
                }
            } catch (NoResultException nre) {
                log.warn(nre.getMessage());
            }
        }
        return enfermedadLocal;
    }

}

