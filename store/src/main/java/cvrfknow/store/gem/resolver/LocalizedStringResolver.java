package cvrfknow.store.gem.resolver;

import gem.support.datatype.exception.GemTypeDeserializationFailedException;
import gem.support.datatype.exception.GemTypeSerializationFailedException;
import gem.support.datatype.exception.GemTypeValidationFailedException;
import gem.support.datatype.resolver.GemTypeResolver;
import org.icasi.cvrf.schema.common.LocalizedString;

public class LocalizedStringResolver implements GemTypeResolver<LocalizedString> {

    @Override
    public LocalizedString deserializeType(String value) throws GemTypeDeserializationFailedException {
        LocalizedString ls = new LocalizedString();
        ls.setValue(value);
        return ls;
    }

    @Override
    public String serializeType(LocalizedString value) throws GemTypeSerializationFailedException, GemTypeValidationFailedException {
        return value.getValue();
    }

    @Override
    public boolean validate(LocalizedString value) {
        return value != null;
    }

    @Override
    public LocalizedString fromString(String value) throws Exception {
        LocalizedString ls = new LocalizedString();
        ls.setValue(value);
        return ls;
    }

    @Override
    public String asString(LocalizedString value) {
        return value.getValue();
    }
}
