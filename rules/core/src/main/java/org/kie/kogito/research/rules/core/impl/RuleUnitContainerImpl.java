package org.kie.kogito.research.rules.core.impl;

import org.kie.kogito.research.application.api.Id;
import org.kie.kogito.research.application.api.RelativeId;
import org.kie.kogito.research.application.core.AbstractAddressable;
import org.kie.kogito.research.application.core.AbstractAddressableContainer;
import org.kie.kogito.research.application.core.RelativeUriId;
import org.kie.kogito.research.rules.api.RuleUnit;
import org.kie.kogito.research.rules.api.RuleUnitContainer;
import org.kie.kogito.research.rules.api.RuleUnitInstance;
import org.kie.kogito.research.rules.api.RuleUnitInstanceContainer;

// /processes
class RuleUnitContainerImpl extends AbstractAddressableContainer<RuleUnit> implements RuleUnitContainer {
    public RuleUnitContainerImpl(Id id) { super(id); }
    @Override public RuleUnit get(RelativeId id) {
        return new RuleUnitImpl(id().append(id));
    }
}

// /processes/$process_id
class RuleUnitImpl extends AbstractAddressable implements RuleUnit {
    public RuleUnitImpl(Id id) { super(id); }
    @Override public RuleUnitInstanceContainer instances() {
        return new RuleUnitInstanceContainerImpl(id().append(RelativeUriId.of("instances")));
    }
}

// /processes/$process_id/instances
class RuleUnitInstanceContainerImpl extends AbstractAddressableContainer<RuleUnitInstance> implements RuleUnitInstanceContainer {
    public RuleUnitInstanceContainerImpl(Id id) { super(id); }
    @Override public RuleUnitInstanceImpl get(RelativeId id) {
        return new RuleUnitInstanceImpl(id().append(id));
    }
}

// /processes/$process_id/instances/$process_instance_id
class RuleUnitInstanceImpl extends AbstractAddressable implements RuleUnitInstance {
    public RuleUnitInstanceImpl(Id processInstanceId) { super(processInstanceId); }
}

