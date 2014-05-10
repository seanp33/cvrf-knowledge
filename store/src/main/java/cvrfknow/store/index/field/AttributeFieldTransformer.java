package cvrfknow.store.index.field;

import com.google.common.base.Function;
import gem.domain.Attribute;
import org.apache.lucene.document.Field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class AttributeFieldTransformer implements Function<Map.Entry<String, Set<Attribute>>, Collection<Field>> {

    @Override
    public Collection<Field> apply(Map.Entry<String, Set<Attribute>> stringSetEntry) {
        Collection<Field> fields = new ArrayList<Field>(stringSetEntry.getValue().size());
        for(Attribute a : stringSetEntry.getValue()){

        }
        return fields;
    }
}
