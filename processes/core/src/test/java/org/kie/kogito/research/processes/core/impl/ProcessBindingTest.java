package org.kie.kogito.research.processes.core.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
public class ProcessBindingTest {

    ProcessContainerImpl processContainer;
    {
        var pc = new ProcessContainerImpl(null, null);
        processContainer.register(List.of(new ProcessImpl(pc, SimpleProcessId.fromString("my.process"))));
    }

    @Test
    @DisplayName("Simple User-Provided Context")
    void userProvidedContext() {
        class Person implements Context {
            @Var String name;
            @Var int age;
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
        class MyProcessContext implements Context {
            @DataModel final Person person;

            private MyProcessContext(Person person) {
                this.person = person;
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

        process.createInstance(RootedContext.of(person));
    }

    static class RootedContext<T> implements Context {
        @DataModel final T root;

        private RootedContext(T root) {
            this.root = root;
        }

        static <T> RootedContext<T> of(T root) {
            return new RootedContext<T>(root);
        }
    }

    @Test
    @DisplayName("Standard Library Context with Map")
    void processBindingMapContext() {
        var process = processContainer.get(SimpleProcessId.fromString("my.process"));
        var ctx = MapContext.create();
        ctx.get().put("name", "paul");
        ctx.get().put("age", 50);
        process.createInstance(ctx);
    }

    static class MapContext implements Context {
        @DataModel final Map<String, Object> root = new HashMap<>();

        private MapContext() {
        }

        Map<String, Object> get() {
            return root;
        }

        static MapContext create() {
            return new MapContext();
        }
    }



}

