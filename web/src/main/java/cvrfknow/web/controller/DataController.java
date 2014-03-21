package cvrfknow.web.controller;

import cvrfknow.web.Bootstrap;
import gem.domain.Entity;
import gem.index.IndexQuery;
import gem.service.Auths;
import gem.service.EntityService;
import gem.service.IndexEntityService;
import gem.service.web.exeptions.EntityJsonSerializationException;
import org.apache.commons.collections.IteratorUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Controller
@RequestMapping("/data")
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    private static final Auths AUTHS = new Auths(cvrfknow.model.Constants.U);

    protected ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected EntityService entityService;

    @Autowired
    protected Bootstrap bootstrap;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String search(@RequestParam(required = true) String searchTerm,
                         @RequestParam(required = false) String entityType,
                         @RequestParam(required = false) String attributeName,
                         @RequestParam(required = false) Integer maxResults,
                         HttpServletRequest request) throws EntityJsonSerializationException {

        if (maxResults == null) {
            maxResults = Integer.MAX_VALUE;
        }

        IndexEntityService service = (IndexEntityService) entityService;

        IndexQuery indexQuery = new IndexQuery(searchTerm);

        if (attributeName != null) indexQuery.addAttribute(attributeName);

        if (entityType != null) indexQuery.addEntityType(entityType);

        Collection<Entity> entities = collect(service.search(indexQuery, AUTHS));

        try {
            return objectMapper.writeValueAsString(entities);
        } catch (IOException e) {
            e.printStackTrace();
            EntityJsonSerializationException ex = new EntityJsonSerializationException();
            ex.initCause(e);
            throw ex;
        }
    }

    protected Collection<Entity> collect(Iterator<Entity> iterator) {
        return (Collection<Entity>) IteratorUtils.toList(iterator);
    }
}
