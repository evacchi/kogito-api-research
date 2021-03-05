package org.kie.kogito.research.application.api;

import org.kie.kogito.research.application.api.context.Context;

public interface UnitInstance {
    UnitInstanceId id();
    Unit unit();
    Context context();
    MessageBus<? extends Event> messageBus();
}
