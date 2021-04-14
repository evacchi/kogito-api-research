package org.kie.kogito.research.application.api;

public interface Unit {
    Application application();
    UnitId id();
    UnitInstance createInstance(Class<? extends Context> ctx);
}