package org.correttouml.uml2zot.semantics.iod;

import org.correttouml.uml.diagrams.iod.SequenceDiagramNode;
import org.correttouml.uml2zot.semantics.util.trio.Predicate;


public class SSequenceDiagramNode {

	private SequenceDiagramNode mades_sequencediagramnode;

	public SSequenceDiagramNode(SequenceDiagramNode curr) {
		this.mades_sequencediagramnode=curr;
	}

	public Predicate getPredicateStart(){
		String iod_id=this.mades_sequencediagramnode.getIOD().getUMLId();
		return new Predicate("IOD_" + iod_id + "_" + this.mades_sequencediagramnode.getSequenceDiagram().getName() + "_START");
	}

	public Predicate getPredicateEnd() {
		String iod_id=this.mades_sequencediagramnode.getIOD().getUMLId();
		return new Predicate("IOD_" + iod_id + "_" + this.mades_sequencediagramnode.getSequenceDiagram().getName() + "_END");
	}

	public Predicate getPredicateStop() {
		String iod_id=this.mades_sequencediagramnode.getIOD().getUMLId();
		return new Predicate("IOD_" + iod_id + "_" + this.mades_sequencediagramnode.getSequenceDiagram().getName() + "_STOP");
	}
	
}
