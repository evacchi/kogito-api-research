package org.kie.kogito.research.processes.core.tasks.impl;

import org.kie.kogito.research.application.api.Id;
import org.kie.kogito.research.application.core.AbstractAddressable;
import org.kie.kogito.research.processes.api.tasks.HumanTaskAttachment;

public class HumanTaskAttachmentImpl extends AbstractAddressable implements HumanTaskAttachment {

    public HumanTaskAttachmentImpl(Id id) {
        super(id);
    }
}
