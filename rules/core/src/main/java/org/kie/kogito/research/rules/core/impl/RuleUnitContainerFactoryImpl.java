package org.kie.kogito.research.rules.core.impl;

import org.kie.kogito.research.application.api.Addressable;
import org.kie.kogito.research.application.api.AddressableContainer;
import org.kie.kogito.research.application.api.AddressableContainerFactory;
import org.kie.kogito.research.application.api.Id;
import org.kie.kogito.research.application.core.RelativeUriId;
import org.kie.kogito.research.rules.api.RuleUnitContainer;

public class RuleUnitContainerFactoryImpl implements AddressableContainerFactory {
    @Override
    public AddressableContainer<? extends Addressable> create(Id id) {
        return new RuleUnitContainerImpl(id.append(RelativeUriId.of("rules")));
    }

    @Override
    public Class<? extends AddressableContainer<? extends Addressable>> type() {
        return RuleUnitContainer.class;
    }
}
