package org.kie.kogito.research.decisions.api;

import org.kie.kogito.research.application.api.AddressableContainer;
import org.kie.kogito.research.application.api.RelativeId;

// @APIROOT
public interface DecisionContainer extends AddressableContainer<Decision> {
    Decision get(RelativeId id);
}
