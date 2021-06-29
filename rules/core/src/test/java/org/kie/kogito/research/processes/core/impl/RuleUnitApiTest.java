package org.kie.kogito.research.processes.core.impl;

import org.junit.jupiter.api.Test;
import org.kie.kogito.research.application.api.Application;
import org.kie.kogito.research.application.api.Context;
import org.kie.kogito.research.application.api.RelativeId;
import org.kie.kogito.research.application.core.ApplicationImpl;
import org.kie.kogito.research.application.core.RelativeUriId;
import org.kie.kogito.research.rules.api.RuleUnitContainer;

public class RuleUnitApiTest {

    class MyUnit implements Context {}

    @Test
    void test() {
        RelativeId ruleUnitId = RelativeUriId.of("my-rule-unit-id");

        Application app = new ApplicationImpl();
        var ruleUnitContainer = app.get(RuleUnitContainer.class);
        var ruleUnit = ruleUnitContainer.get(RelativeUriId.of("my-unit-id"));



    }

}
