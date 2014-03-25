import org.icasi.cvrf.schema.cvrf.Cvrfdoc;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class JaxbTest {

    @Test
    public void testit() {
        try {
            JAXBContext context = JAXBContext.newInstance(Cvrfdoc.class);
            Cvrfdoc doc = (Cvrfdoc) context.createUnmarshaller().unmarshal(new File("/Development/projects/cvrf-knowledge/web/src/main/resources/allitems-cvrf-year-2014.xml"));
            System.out.println("Vulnerabilities in 2014: " + doc.getVulnerability().size());
        } catch (JAXBException e) {
            // if things went wrong...
            System.err.println("error parsing xml: ");
            e.printStackTrace();
            // force quit
            System.exit(1);
        }
    }
}
