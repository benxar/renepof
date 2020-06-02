package ar.org.fadepof.service;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.jboss.logging.Logger;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class SearchIndexService {

    private static Logger log = Logger.getLogger(SearchIndexService.class.getName());

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    private boolean indexed = false;

    public void index() {
        if (!indexed) {
            log.info("start indexing...");
            try {
                FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
                fullTextEntityManager.createIndexer().startAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            indexed = true;
            log.info("finish indexing");
        } else  {
            log.info("already indexed");
        }
    }
}
