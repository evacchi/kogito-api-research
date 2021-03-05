package org.kie.kogito.research.application.api;

import org.kie.kogito.research.application.api.context.Context;

public interface Unit {
    Application application();
    UnitId id();
    UnitInstance createInstance(Context ctx);
    MessageBus<? extends Event> messageBus();
}