package cvrfknow.model;

import org.icasi.cvrf.schema.cvrf.Cvrfdoc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.InputStream;

public class CvrfdocLoader {

    public static Cvrfdoc loadCvrfdoc(InputStream is) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(Cvrfdoc.class);
            return (Cvrfdoc) context.createUnmarshaller().unmarshal(is);
        } catch (JAXBException e) {
            throw new Exception(e);
        }
    }
}
