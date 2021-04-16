package org.kie.kogito.research.application.core;

import org.kie.kogito.research.application.api.*;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractUnitInstance<T extends Unit<T>> implements Instance<T> {

    private final Id id;
    private final T unit;
    private final Context context;
    private final Map<Class<?>, UnitInstanceContainer<?,?>> containers;

    public AbstractUnitInstance(Id id, T unit, Context context) {
        this.id = id;
        this.unit = unit;
        this.context = context;
        this.containers = new HashMap<>();
    }

    @Override
    public Id id() {
        return id;
    }

    @Override
    public T unit() {
        return unit;
    }

    public <T extends Context> T context(Class<T> cls) {
        return null; //cls.cast(context); // should remap if they don't match!
    }

    protected final <U extends Unit<U>, C extends UnitInstanceContainer<U, ? extends Instance<U>>> void register(Class<C> cls, C ctr) {
        containers.put(cls, ctr);
    }

    @Override
    public <U extends Unit<U>, C extends UnitInstanceContainer<U, ? extends Instance<U>>> C get(Class<C> cls) {
        return (C) containers.get(cls);
    }
}