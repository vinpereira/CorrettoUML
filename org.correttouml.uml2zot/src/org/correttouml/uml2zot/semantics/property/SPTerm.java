package org.correttouml.uml2zot.semantics.property;

import org.correttouml.uml.diagrams.classdiagram.Object;
import org.correttouml.uml.diagrams.classdiagram.Operation;
import org.correttouml.uml.diagrams.classdiagram.Signal;
import org.correttouml.uml.diagrams.property.PTerm;
import org.correttouml.uml.diagrams.sequencediagram.Message;
import org.correttouml.uml.diagrams.sequencediagram.SequenceDiagram;
import org.correttouml.uml.diagrams.statediagram.State;
import org.correttouml.uml2zot.semantics.classdiagram.SOperation;
import org.correttouml.uml2zot.semantics.classdiagram.SSignal;
import org.correttouml.uml2zot.semantics.sequencediagram.SMessage;
import org.correttouml.uml2zot.semantics.sequencediagram.SSequenceDiagram;
import org.correttouml.uml2zot.semantics.statediagram.SState;
import org.correttouml.uml2zot.semantics.util.bool.BooleanFormulae;


public class SPTerm {

	private PTerm mades_pterm;

	public SPTerm(PTerm ff) {
		this.mades_pterm=ff;
	}
	
	public BooleanFormulae getSemantics(){
		if(mades_pterm.getElement() instanceof State){
			//TODO: When selecting a term the user must specify what object he is referring to
			Object obj=((State)mades_pterm.getElement()).getStateDiagram().getOwningClass().getObjects().iterator().next();
			return new SState((State)mades_pterm.getElement()).getPredicate(obj);
		}
		if(mades_pterm.getElement() instanceof Message){
			return new SMessage((Message) mades_pterm.getElement()).getPredicate();
		}
		if(mades_pterm.getElement() instanceof Signal){
			return new SSignal((Signal) mades_pterm.getElement()).getPredicate();
		}
		if(mades_pterm.getElement() instanceof SequenceDiagram){
			return new SSequenceDiagram((SequenceDiagram) mades_pterm.getElement()).getPredicate();
		}
		if(mades_pterm.getElement() instanceof Operation){
			//TODO: When selecting an operation the user must specify what object he is referring to
			Object obj=((Operation)mades_pterm.getElement()).getOwningClass().getObjects().iterator().next();
			return new SOperation((Operation) mades_pterm.getElement()).getPredicate(obj);
		}
		
		try {
			throw new Exception("The selected term in the property is not supported yet");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
