package org.correttouml.uml2zot.semantics.sequencediagram;

import org.correttouml.uml.diagrams.classdiagram.Object;
import org.correttouml.uml.diagrams.expressions.PrimitiveType;
import org.correttouml.uml.diagrams.sequencediagram.SequenceDiagramParameter;
import org.correttouml.uml2zot.semantics.expressions.SVariable;
import org.correttouml.uml2zot.semantics.util.bool.BooleanFormulae;
import org.correttouml.uml2zot.semantics.util.bool.Iff;
import org.correttouml.uml2zot.semantics.util.bool.Implies;
import org.correttouml.uml2zot.semantics.util.trio.EQ;
import org.correttouml.uml2zot.semantics.util.trio.Predicate;
import org.correttouml.uml2zot.semantics.util.trio.TrioVar;
import org.correttouml.uml2zot.semantics.util.trio.Yesterday;


public class SSequenceDiagramParameter implements SVariable{

	private SequenceDiagramParameter mades_sdp;

	public SSequenceDiagramParameter(SequenceDiagramParameter sdp) {
		this.mades_sdp = sdp;
	}

	public String getSemantics() {
		String sem="";
		Predicate sd_inside=new SSequenceDiagram(this.mades_sdp.getSequenceDiagram()).getPredicate();
		
		//TODO: Put back assignments for sequence diagrams parameters
//		// The sequence diagram parameter has always the same value
//		// if we do not make any assignment to it
//		And notAssignmentPredicates = new And();
//		for (SequenceDiagramParameterAssignment a : this.assignments) {
//			notAssignmentPredicates.addFormulae(new Not(a.getPredicate()));
//		}
		
//		sem= sem + new Implies(
//		new And(new Not(this.sequencediagram.getStartEvent()),notAssignmentPredicates, this.sequencediagram.getPredicate()),eq)
//		+ "\n";		
		
		BooleanFormulae eq = null;
		if (this.mades_sdp.getType() == PrimitiveType.BOOLEAN) {
			eq = new Iff(this.getPredicate(), new Yesterday(this.getPredicate()));
		} else {
			eq = new EQ(this.getPredicate(), new Yesterday(this.getPredicate()));
		}
		
		sem=sem+new Implies(sd_inside,eq) + "\n";
		
		return sem;
	}

	@Override
	public BooleanFormulae getPredicate(Object... obj) {
        if(this.mades_sdp.getType()== PrimitiveType.INTEGER || this.mades_sdp.getType()==PrimitiveType.REAL)
            return new TrioVar("$"+"SD_"+this.mades_sdp.getSequenceDiagram().getName()+"_PARAM_"+this.mades_sdp.getName(), this.mades_sdp.getType());
        else{
            return new Predicate("$"+"SD_"+this.mades_sdp.getSequenceDiagram().getName()+"_PARAM_"+this.mades_sdp.getName());
        } 
	}
	
	/**
	 * Get the Predicate of a Sequence Diagram parameter.
	 * @author Vinicius Pereira
	 * @return The predicate from Sequence Diagram parameter.
	 */
	public Predicate getPredicateForSDParameter() {
		Predicate predicate = new Predicate();
		predicate.setPredicateName("$"+"SD_"+this.mades_sdp.getSequenceDiagram().getName()+"_PARAM_"+this.mades_sdp.getName());
		
		return predicate;
	}

}
