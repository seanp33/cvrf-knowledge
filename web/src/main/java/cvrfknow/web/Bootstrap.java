package cvrfknow.web;

import cvrfknow.model.CvrfdocLoader;
import cvrfknow.model.EntityGraphAssembler;
import gem.domain.Entity;
import gem.service.EntityService;
import gem.service.exception.EntityRejectedException;
import org.icasi.cvrf.schema.cvrf.Cvrfdoc;
import org.icasi.cvrf.schema.vuln.Vulnerability;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;


public class Bootstrap {

    @Autowired(required = true)
    private EntityService entityService;

    private List<String> cvrfSeed = null;

    public Bootstrap(List<String> cvrfSeed) {
        this.cvrfSeed = cvrfSeed;
    }

    @PostConstruct
    public void init() throws Exception {
        for (String seed : cvrfSeed) {
            InputStream is = this.getClass().getResourceAsStream(seed);
            if (is != null) {
                Cvrfdoc doc = CvrfdocLoader.loadCvrfdoc(is);
                for (Vulnerability v : doc.getVulnerability()) {
                    saveEntities(EntityGraphAssembler.assemble(v));
                }
            }
        }
    }

    private void saveEntities(List<Entity> entities) throws EntityRejectedException {
        entityService.saveObjects(entities.iterator(), false);
    }
}
