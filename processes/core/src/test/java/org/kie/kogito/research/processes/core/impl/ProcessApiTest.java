package org.kie.kogito.research.processes.core.impl;

import org.junit.jupiter.api.Test;
import org.kie.kogito.research.application.api.Application;
import org.kie.kogito.research.application.api.Context;
import org.kie.kogito.research.application.api.RelativeId;
import org.kie.kogito.research.application.core.RelativeUriId;
import org.kie.kogito.research.processes.api.ProcessContainer;

public class ProcessApiTest {

    class Person implements Context {}

    class OutputModel implements Context {}

    @Test
    void test() {
        RelativeId processId = RelativeUriId.of("my-process-id");

        TestApp app = new TestApp();
        testApplication(app);
    }


    private void testApplication(Application app) {
        RelativeId processId = RelativeUriId.of("my-process-id");
        RelativeId instanceId = RelativeUriId.of("some-instance-id");
        RelativeId taskInstanceId = RelativeUriId.of("some-task");


        var processContainer = app.get(ProcessContainer.class);

        // createProcessInstance
        {
            var processInstance = processContainer
                    .get(processId)
                    .instances();
//                    .create(new Person());
//            instanceId = processInstance.id().segment();

//            processInstance.start();
        }

        // getProcessInstanceOutput + findById
//        OutputModel variables =
                processContainer.get(processId)
                .instances()
                .get(instanceId);
//                .context(OutputModel.class);

        // delete
        {
            var processInstance = processContainer.get(processId)
                    .instances()
                    .get(instanceId);
//            processInstance.abort();
//            Person result = processInstance.context(Person.class);// how to let these stick around?
        }

        // update
        {
            var processInstance = processContainer.get(processId)
                    .instances()
                    .get(instanceId);
//            processInstance
//                    .update(new Person(/* ... */));

//            OutputModel outputModel = processInstance.context(OutputModel.class);
        }

        // signalProcessInstance
        processContainer.get(processId)
                .instances()
                .get(instanceId);
//                .send(new SignalEvent());

        // get tasks
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .tasks();

        // signalTasks
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .tasks()
                .get(taskInstanceId);
//                .send(new SignalEvent());

        // getTaskByName
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .tasks()
                .get(taskInstanceId); // note: should be taskId !

        // completeTask
        {
            // policies
            processContainer.get(processId)
                    .instances()
                    .get(instanceId)
                    .tasks()
                    .get(taskInstanceId);// note: should be taskId !
//                    .complete(new OutputModel());
        }


        // saveTask
        {
            // policies
            processContainer.get(processId)
                    .instances()
                    .get(instanceId)
                    .tasks()
                    .get(taskInstanceId);// note: should be taskId !
//                    .save(new OutputModel());
        }

        // taskTransition
        {
            // policies
            processContainer.get(processId)
                    .instances()
                    .get(instanceId)
                    .tasks()
                    .get(taskInstanceId);// note: should be taskId !
//                    .transition(
//                            new OutputModel(),
//                            "some-phase");
        }

        // getTask
//        OutputModel output =
                processContainer.get(processId)
                .instances()
                .get(instanceId)
                .tasks()
                .get(taskInstanceId);// note: should be taskId !
//                .context(OutputModel.class);

        // abortTask
        {
            // policies
            processContainer.get(processId)
                    .instances()
                    .get(instanceId)
                    .tasks()
                    .get(taskInstanceId);// note: should be taskId !
//                    .abort("some-phase");
        }

        // -------------- comments --------------

        var commentContainerId = processContainer.get(processId)
                .instances()
                .get(instanceId)
                .tasks()
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .comments();//.id();

/*
        // add comment
        var commentId = processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .comments()
                .create(new HumanTaskCommentDataImpl("hello")).id().segment();

        // update comment
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .comments()
                .get(commentId)
                .update(new HumanTaskCommentDataImpl("info..."));

        // delete comment
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .comments()
                .get(commentId)
                .delete();

        // getComment
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .comments()
                .get(commentId);

        // getComments
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .comments();


        // -------------- attachments --------------

        var attachContainerId =
                processContainer.get(processId)
                        .instances()
                        .get(instanceId)
                        .get(TaskInstance.class)
                        .get(taskInstanceId)
                        .as(HumanTaskInstance.class)
                        .attachments().id();

        // add
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .attachments()
                .create(new HumanTaskAttachmentDataImpl("..."));

        // update attachment
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .attachments()
                .get(RelativeUriId.of("some-id"))
                .update(new HumanTaskAttachmentDataImpl("info..."));

        // delete attachment
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .attachments()
                .get(RelativeUriId.of("some-id"))
                .delete();

        // getAttachment
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .attachments()
                .get(RelativeUriId.of("some-id"));

        // getAttachments
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId)
                .as(HumanTaskInstance.class)
                .attachments();


        // get schema and phases
        processContainer.get(processId)
                .instances()
                .get(instanceId)
                .get(TaskInstance.class)
                .get(taskInstanceId);
//                .unit();  // asJsonSchema() ??

*/    }


}
