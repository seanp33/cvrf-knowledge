package cvrfknow.store.index;

import gem.domain.Entity;
import gem.rya.store.service.utils.RyaEntityUtils;

public class DefaultEntityIdentityResolver implements IdentityResolver<Entity, String>{

    @Override
    public String getIdentity(Entity entity) {
        return RyaEntityUtils.buildSubjectUri(entity.getType(), entity.getId());
    }
}
