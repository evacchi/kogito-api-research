package org.kie.kogito.research.processes.core.impl;

import org.kie.kogito.research.application.api.Id;
import org.kie.kogito.research.application.core.AbstractAddressable;
import org.kie.kogito.research.processes.api.HumanTaskComment;

public class HumanTaskCommentImpl extends AbstractAddressable implements HumanTaskComment {

    public HumanTaskCommentImpl(Id id) {
        super(id);
    }

}
