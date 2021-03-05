package org.kie.kogito.research.processes.core.impl;

import org.junit.jupiter.api.Test;
import org.kie.kogito.research.application.api.context.Context;
import org.kie.kogito.research.application.api.context.Var;
import org.kie.kogito.research.processes.api.Process;
import org.kie.kogito.research.processes.api.ProcessContainer;
import org.kie.kogito.research.processes.api.ProcessId;
import org.kie.kogito.research.processes.api.ProcessInstance;
import org.kie.kogito.research.processes.api.ProcessInstanceId;

import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

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
public class ProcessScriptTaskTest {

    static final Logger LOGGER = Logger.getLogger(ProcessScriptTaskTest.class.getCanonicalName());

    @Test
    public void typedScriptTask() {
        // this is user-supplied
        class MyContext implements Context {
            @Var String user;
            @Var int age;
        }

        // this would be code-generated
        var runnableProcess = new RunnableProcess(SimpleProcessId.fromString("my.process")) {
            // this would be the internal process representation (as we have in kogito)
            public List<Node> nodes() {
                return List.of(new StartNode(),
                        // casts and other changes may be inserted by parsing the script and rewriting it
                        new ScriptTaskNode($$ctx -> {
                            /* original script */
                            /* System.out.printf("Hello, %s!\n", user); */
                            /* begin rewritten */
                            var $ctx = (MyContext)$$ctx;
                            System.out.printf("Hello, %s!\n", $ctx.user);
                        }),
                        new EndNode());
            }
        };

        var ctx = new MyContext();
        ctx.user = "paul";
        ctx.age = 50;

        var instance = runnableProcess.createInstance(ctx);

        instance.run(ctx);

    }




    interface Node { void run(Context context); }

    static class StartNode implements Node { public void run(Context ctx) { LOGGER.info("START"); }}

    static class ScriptTaskNode implements Node {
        private final Consumer<Context> action;
        ScriptTaskNode(Consumer<Context> action) { this.action = action; }
        public void run(Context ctx) { action.accept(ctx); }
    }

    static class EndNode implements Node { public void run(Context ctx) { LOGGER.info("END"); } }

    abstract static class RunnableProcess extends ProcessImpl {
        public RunnableProcess(ProcessId id) { super(null, id); }

        abstract List<Node> nodes();

        @Override
        public RunnableProcessInstance createInstance(Context ctx) {
            return new RunnableProcessInstance(SimpleProcessInstanceId.create(), this, ctx);
        }
    }

    static class RunnableProcessInstance extends ProcessInstanceImpl {
        public RunnableProcessInstance(ProcessInstanceId id, Process unit, Context context) {
            super(id, unit, context);
        }

        void run(Context ctx) {
            var p = (RunnableProcess) unit();
            p.nodes().forEach(n -> n.run(ctx));
        }
    }


}
