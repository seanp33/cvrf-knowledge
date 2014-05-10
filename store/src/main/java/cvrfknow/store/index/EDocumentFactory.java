package cvrfknow.store.index;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import gem.domain.Attribute;
import gem.domain.Entity;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;

import java.util.Map;
import java.util.Set;

public class EDocumentFactory {

    public static final String IDENTITY = "identity";

    private static IdentityResolver<Entity, String> identity = new DefaultEntityIdentityResolver();

    Document getDocument(Entity entity){
        Document doc = new Document();
        doc.add(new StringField(IDENTITY, identity.getIdentity(entity), Field.Store.YES));
        Iterators.transform(entity.getAttributes().entrySet().iterator(), new Function<Map.Entry<String, Set<Attribute>>, Object>() {
            @Override
            public Object apply(Map.Entry<String, Set<Attribute>> stringSetEntry) {
                return null;
            }
        });

        return doc;
    }
}
