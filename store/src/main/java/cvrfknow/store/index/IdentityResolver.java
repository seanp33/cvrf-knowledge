package cvrfknow.store.index;

public interface IdentityResolver<T,I> {

    I getIdentity(T t);

}
