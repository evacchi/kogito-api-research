package org.kie.kogito.research.application.api;

public interface Instance<T> extends Addressable {
    <C extends Context> C context(Class<C> cls);
//    <T> T get(Class<T> c);
}
