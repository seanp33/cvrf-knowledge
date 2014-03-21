package cvrfknow.store.gem.resolver;

import gem.support.datatype.exception.GemTypeDeserializationFailedException;
import gem.support.datatype.exception.GemTypeSerializationFailedException;
import gem.support.datatype.exception.GemTypeValidationFailedException;
import gem.support.datatype.resolver.GemTypeResolver;

import java.math.BigInteger;

public class BigIntTypeResolver implements GemTypeResolver<BigInteger> {

    @Override
    public BigInteger deserializeType(String value) throws GemTypeDeserializationFailedException {
        return new BigInteger(value);
    }

    @Override
    public String serializeType(BigInteger value) throws GemTypeSerializationFailedException, GemTypeValidationFailedException {
        if (!validate(value)) {
            throw new GemTypeValidationFailedException(new Exception());
        } else {
            String signPadding = value.intValue() < 0 ? "" : "0";
            return String.format("%s%010d", signPadding, value);

        }
    }

    @Override
    public boolean validate(BigInteger value) {
        return value != null;
    }

    @Override
    public BigInteger fromString(String value) throws Exception {
        return new BigInteger(value);
    }

    @Override
    public String asString(BigInteger value) {
        return value.toString();
    }
}
