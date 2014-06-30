package org.correttouml.uml2zot.semantics.classdiagram;

import org.correttouml.uml.diagrams.classdiagram.Object;
import org.correttouml.uml.diagrams.classdiagram.OperationParameter;
import org.correttouml.uml.diagrams.expressions.PrimitiveType;
import org.correttouml.uml2zot.semantics.expressions.SVariable;
import org.correttouml.uml2zot.semantics.util.bool.BooleanFormulae;
import org.correttouml.uml2zot.semantics.util.trio.Predicate;
import org.correttouml.uml2zot.semantics.util.trio.TrioVar;


public class SOperationParameter implements SVariable{

	private OperationParameter mades_operationparam;

	public SOperationParameter(OperationParameter mades_operationparam){
		this.mades_operationparam=mades_operationparam;
	}

	@Override
	public BooleanFormulae getPredicate(Object... obj) {
        if(mades_operationparam.getType() == PrimitiveType.INTEGER || mades_operationparam.getType()==PrimitiveType.REAL)
            return new TrioVar("$OBJ_"+obj[0].getName()+"_PARAM_"+mades_operationparam.getName(), mades_operationparam.getType());
        else if(mades_operationparam.getType() == PrimitiveType.BOOLEAN){
            return new Predicate("$OBJ_"+obj[0].getName()+"_PARAM_"+mades_operationparam.getName());
        }
		return null;
	}
	
	/**
	 * Get the Predicate of a Sequence Diagram parameter.
	 * @author Vinicius Pereira
	 * @return The predicate from Sequence Diagram parameter.
	 */
	public Predicate getPredicateForCDOperationParameter(Object obj) {
		Predicate predicate = new Predicate();
		predicate.setPredicateName("$"+"OBJ_"+obj.getName()+"_PARAM_"+this.mades_operationparam.getName());
		
		return predicate;
	}
	
}
