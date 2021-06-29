package org.kie.kogito.research.processes.api;

import org.kie.kogito.research.application.api.AddressableContainer;

// @Name("processes")
public interface ProcessContainer extends AddressableContainer<Process> {

}

// @Template({ ProcessContainer.class, Process.class, ProcessInstanceContainer.class, ProcessInstance.class })
class Driver {}