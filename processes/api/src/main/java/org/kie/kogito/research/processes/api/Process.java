package org.kie.kogito.research.processes.api;

import org.kie.kogito.research.application.api.Addressable;
import org.kie.kogito.research.application.api.Evaluable;
import org.kie.kogito.research.application.api.Unit;

// @{ID}
public interface Process extends Unit, Addressable, Evaluable<Process> {
    ProcessInstanceContainer instances();
}
