package org.kie.kogito.research.rules.api;

import org.kie.kogito.research.application.api.Addressable;
import org.kie.kogito.research.application.api.Evaluable;
import org.kie.kogito.research.application.api.Unit;

// @{ID}
public interface RuleUnit extends Unit, Addressable, Evaluable<RuleUnit> {
    RuleUnitInstanceContainer instances();
}
