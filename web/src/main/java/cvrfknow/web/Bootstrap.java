package cvrfknow.web;

import gem.domain.Attribute;
import gem.domain.Entity;
import gem.service.EntityService;
import gem.service.exception.EntityRejectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class Bootstrap {

    @Autowired(required = true)
    private EntityService entityService;

    public Bootstrap() {
    }

    @PostConstruct
    public void init() throws EntityRejectedException {
        System.out.println("cvrfknow.web.Bootstrap >>> init");
        Entity e = new Entity(UUID.randomUUID().toString(), "TEST");
        e.addAttribute(new Attribute("name", "Sean", "U"));
        entityService.saveObject(e, false);

    }
}
