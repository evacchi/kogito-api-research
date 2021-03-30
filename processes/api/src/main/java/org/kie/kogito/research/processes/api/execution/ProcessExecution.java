package org.kie.kogito.research.processes.api.execution;

import org.kie.kogito.research.processes.api.ProcessInstance;

import java.util.EventListener;


/**
 * A listener for events related to process instance execution.
 */
public interface ProcessExecution {

    void beforeProcessStarted(ProcessInstance processInstance);

    void afterProcessStarted(ProcessInstance processInstance);

    void beforeProcessCompleted(ProcessInstance processInstance);

    void afterProcessCompleted(ProcessInstance processInstance);

    void beforeNodeTriggered(ProcessInstance processInstance, NodeInstance nodeInstance);

    void afterNodeTriggered(ProcessInstance processInstance, NodeInstance nodeInstance);

    void beforeNodeLeft(ProcessInstance processInstance, NodeInstance nodeInstance);

    void afterNodeLeft(ProcessInstance processInstance, NodeInstance nodeInstance);

    void beforeVariableChanged(ProcessInstance processInstance, NodeInstance nodeInstance);

    void afterVariableChanged(ProcessInstance processInstance, NodeInstance nodeInstance);

    void beforeSLAViolated(ProcessInstance processInstance, NodeInstance nodeInstance);

    void afterSLAViolated(ProcessInstance processInstance, NodeInstance nodeInstance);

    void onSignal(ProcessInstance processInstance, NodeInstance nodeInstance, String signalName, Object signal);

    void onMessage(ProcessInstance processInstance, NodeInstance nodeInstance, String messageName, Object signal);
}


// dummy
interface NodeInstance{}