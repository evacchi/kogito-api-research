package org.kie.kogito.research.decisions.api;

import org.kie.kogito.research.application.api.AddressableContainer;
import org.kie.kogito.research.application.api.RelativeId;

public interface DecisionInstanceContainer extends AddressableContainer<DecisionInstance> {
    DecisionInstance get(RelativeId id);
}
