package org.kie.kogito.research.rules.api;

import org.kie.kogito.research.application.api.Addressable;
import org.kie.kogito.research.application.api.Evaluable;
import org.kie.kogito.research.application.api.Unit;

public interface RuleUnitInstance extends Addressable, Unit, Evaluable<RuleUnitInstance> {

}
