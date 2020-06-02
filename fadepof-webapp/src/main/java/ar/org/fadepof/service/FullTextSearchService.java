package ar.org.fadepof.service;

import javax.annotation.PostConstruct;

import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.logging.Logger;

@Startup
@Singleton
public class FullTextSearchService {

    private static Logger log = Logger.getLogger(FullTextSearchService.class.getName());

    @Inject
    DisorderService disorderService;

    @PostConstruct
    public void init() {
        log.info("initializing cache...");
        disorderService.load();
        log.info("done");
    }
}
