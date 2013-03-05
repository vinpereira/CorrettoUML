package org.correttouml.uml2zot.semantics.sequencediagram;

import org.correttouml.uml.diagrams.classdiagram.Object;
import org.correttouml.uml.diagrams.sequencediagram.MessageEnd;
import org.correttouml.uml2zot.semantics.events.SEvent;
import org.correttouml.uml2zot.semantics.util.trio.Predicate;


public class SMessageEnd implements SInteractionFragment, SEvent{

	private MessageEnd mades_messageEndEvent;

	public SMessageEnd(MessageEnd mades_messageEndEvent){
		this.mades_messageEndEvent=mades_messageEndEvent;
	}

	@Override
	public Predicate getPredicate(Object... optObject) {
		return new Predicate("MESSAGE"+this.mades_messageEndEvent.getMessage().getUMLId().replace("-", "_")+"_End");
	}

	@Override
	public Predicate getPredicate() {
		return new Predicate("MESSAGE"+this.mades_messageEndEvent.getMessage().getUMLId().replace("-", "_")+"_End");
	}
}

