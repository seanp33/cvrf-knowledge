import org.icasi.cvrf.schema.cvrf._1.Cvrfdoc;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class ParseTest {

    @Test
    public void testit() {
        try {
            // setup object mapper using the AppConfig class
            JAXBContext context = JAXBContext.newInstance(Cvrfdoc.class);
            // parse the XML and return an instance of the AppConfig class

            Cvrfdoc doc = (Cvrfdoc) context.createUnmarshaller().unmarshal(new File("/Users/smonaghan/Desktop/cvrf-data/allitems-cvrf-year-2014.xml"));
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
