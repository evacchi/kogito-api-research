package org.kie.kogito.research.application.api;

public interface Unit<T extends Unit<T>> extends Addressable {
    UnitInstanceContainer<T, ?> instances();
}