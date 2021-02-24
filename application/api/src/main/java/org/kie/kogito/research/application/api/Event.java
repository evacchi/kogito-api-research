package org.kie.kogito.research.application.api;

import java.io.Serializable;

public interface Event extends Serializable {
    Id senderId();
    Id targetId();
    Serializable payload();
}
