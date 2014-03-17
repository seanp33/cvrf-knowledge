package cvrfknow.model;

import gem.domain.Entity;
import org.icasi.cvrf.schema.cvrf.Cvrfdoc;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class EntityGraphAssemblerTest {

    private Cvrfdoc doc = null;

    @Before
    public void setup() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/test-2014.xml");
            JAXBContext context = JAXBContext.newInstance(Cvrfdoc.class);
            this.doc = (Cvrfdoc) context.createUnmarshaller().unmarshal(is);
        } catch (JAXBException e) {
            fail(e.toString());
            e.printStackTrace();
        }
    }

    @Test
    public void testAssemble() {
        assertNotNull(doc);
        List<Entity> entities = EntityGraphAssembler.assemble(doc.getVulnerability().get(0));
        assertEquals(13, entities.size());
        Entity entity = entities.get(entities.size()-1);
        assertEquals("CVE-2014-0001", entity.getId());
    }
}
