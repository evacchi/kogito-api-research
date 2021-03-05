package org.kie.kogito.research.processes.core.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.kogito.research.application.api.Event;
import org.kie.kogito.research.application.api.ExecutionModel;
import org.kie.kogito.research.application.api.context.Context;
import org.kie.kogito.research.application.api.context.DataModel;
import org.kie.kogito.research.application.api.context.Var;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example for the following diagram:
 *
 * <pre>
 *              ┌──────────────┐
 *    .─.       │              │        .─.
 *   (   ──────▶│ Script Task  │──────▶(   )
 *    `─'       │              │        `─'
 *   Start      └──────────────┘        End
 *
 * </pre>
 *
 * with Process Variables:
 *     user: String
 *     age:  Integer
 *
 * with Script Task:
 *     System.out.printf("Hello, %s!\n", user);
 */
public class ProcessBindingExecutionModelTest {

    ProcessContainerImpl processContainer;
    {
        var pc = new ProcessContainerImpl(null, null);
        processContainer.register(List.of(new ProcessImpl(pc, SimpleProcessId.fromString("my.process"))));
    }

    @Test
    @DisplayName("Simple User-Provided Context")
    void userProvidedContext() {
        class Person implements Context, ExecutionModel {
            @Var String name;
            @Var int age;

            @Override public void onEvent(Event event) {
                /* e.g. if (event.payload() is startup) */
                System.out.printf("Started with %s\n", name);
                // /!\ is this usage safe?
                //      - thread unsafe?
                //      - and/or users should not assume object identity
                //          (i.e. this object may recreated and destroyed by the system)
            }
        }

        var person = new Person();
        person.name = "Paul";
        person.age = 50;

        var process = processContainer.get(SimpleProcessId.fromString("my.process"));
        process.createInstance(person);
    }

    // let us define a non-context Person class

    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    @DisplayName("User-defined Context object with data model")
    void processBinding() {
        class MyProcessContext implements Context, ExecutionModel {
            @DataModel final Person person;

            private MyProcessContext(Person person) {
                this.person = person;
            }

            @Override public void onEvent(Event event) {
                /* e.g. if (event.payload() is startup) */
                System.out.printf("Started with %s\n", person.name);
            }
        }

        var process = processContainer.get(SimpleProcessId.fromString("my.process"));
        var person = new Person("paul", 50);

        process.createInstance(new MyProcessContext(person));

    }



    @Test
    @DisplayName("Standard Library Context with object at root")
    void processBindingAnonymousContext() {

        var process = processContainer.get(SimpleProcessId.fromString("my.process"));
        var person = new Person("paul", 50);

        class MyExecutionModel implements ExecutionModel {

            @Override
            public void onEvent(Event event) {
                System.out.printf("Started with %s\n", person.name);
                // /!\ this is creating a closure!!
                //     most probably unexpected results!!
                //
                // i.e. with this usage pattern you won't be able to access
                //      context variables directly (unless they are in the Event payload)
                //
                // however: this may still nice to use with properly-scoped CDI annotations (?)
                // e.g. @RequestScoped ExecutionModel { @Inject Person person ... }
            }
        }

        var ctx = RootedContext.of(person, new MyExecutionModel());
        process.createInstance(ctx);
    }

    static class RootedContext<T> implements Context {
        @DataModel final T root;
        @org.kie.kogito.research.application.api.context.ExecutionModel
        final ExecutionModel executionModel;

        private RootedContext(T root, ExecutionModel executionModel) {
            this.root = root;
            this.executionModel = executionModel;
        }

        static <T> RootedContext<T> of(T root, ExecutionModel executionModel) {
            return new RootedContext<>(root, executionModel);
        }
    }

}

