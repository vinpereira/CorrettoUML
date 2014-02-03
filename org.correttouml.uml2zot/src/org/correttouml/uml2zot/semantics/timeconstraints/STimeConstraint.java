package org.correttouml.uml2zot.semantics.timeconstraints;

import org.correttouml.uml.diagrams.classdiagram.Object;
import org.correttouml.uml.diagrams.timeconstraints.TimeConstraint;
import org.correttouml.uml2zot.semantics.events.SEventFactory;
import org.correttouml.uml2zot.semantics.util.bool.And;
import org.correttouml.uml2zot.semantics.util.bool.BooleanFormulae;
import org.correttouml.uml2zot.semantics.util.bool.Iff;
import org.correttouml.uml2zot.semantics.util.bool.Implies;
import org.correttouml.uml2zot.semantics.util.bool.Not;
import org.correttouml.uml2zot.semantics.util.trio.Lasted;
import org.correttouml.uml2zot.semantics.util.trio.Lasted_ie;
import org.correttouml.uml2zot.semantics.util.trio.Past;
import org.correttouml.uml2zot.semantics.util.trio.Predicate;
import org.correttouml.uml2zot.semantics.util.trio.Since_ei;
//import org.correttouml.uml2zot.semantics.util.trio.Since_ie;
import org.correttouml.uml2zot.semantics.util.trio.SomP;
import org.correttouml.uml2zot.semantics.util.trio.WithinP;


public class STimeConstraint{

	private TimeConstraint mades_ti;

	public STimeConstraint(TimeConstraint te){
		this.mades_ti=te;
	}
	
    public BooleanFormulae getSemantics(Object... object) {
        And sem=new And();
        Predicate ev1=null;
        Predicate ev2=null;
        
        if(!mades_ti.isEvent1Now()) ev1=SEventFactory.getInstance(mades_ti.getEvent1()).getPredicate(object);
        if(!mades_ti.isEvent2Now()) ev2=SEventFactory.getInstance(mades_ti.getEvent2()).getPredicate(object);
        int timeunits=mades_ti.getTimeUnits();
        
        switch(mades_ti.getTimeOperator()){
            case NEQ:{
                if(mades_ti.isEvent2Now()){
                    sem.addFormulae(new Not(new Past(ev1,timeunits)));
                }else{
                	sem.addFormulae(new Implies(ev2,new Not(new Past(ev1,timeunits))));
                }
                break;
            }
            case EQ:{
                if(mades_ti.isEvent2Now()){
                	sem.addFormulae(new Past(ev1,timeunits));
                }else{
                	sem.addFormulae(new Implies(ev2,new Past(ev1,timeunits)));
                }
                break;
            }
            case LT:{
                if(mades_ti.isEvent2Now()){
                	sem.addFormulae(new And(new WithinP(ev1, timeunits-1), new Since_ei(new Not(ev1), ev1)));
                }else{
                	sem.addFormulae(new Iff(ev2, new And(new WithinP(ev1, timeunits-1), new Since_ei(new And(new Not(ev2),new Not(ev1)), ev1))));
                }
                break;
            }
            case LTE:{
                if(mades_ti.isEvent2Now()){
                	sem.addFormulae(new And(new WithinP(ev1, timeunits), new Since_ei(new Not(ev1), ev1)));
                }else{
                	sem.addFormulae(new Iff(ev2, new And(new WithinP(ev1, timeunits), new Since_ei(new And(new Not(ev2),new Not(ev1)), ev1))));
                }
                break;
            }
            case GT:{
                if(mades_ti.isEvent2Now()){
                	sem.addFormulae(new And(new Lasted_ie(new Not(ev1), timeunits+1), new SomP(ev1)));
                }else{
                	sem.addFormulae(new And(ev2, new Lasted_ie(new Not(ev1),timeunits+1),new Since_ei(new Not(ev2),ev1)));
                }
                break;
            }                   
            case GTE:{
                if(mades_ti.isEvent2Now()){
                	sem.addFormulae(new Lasted(new Not(ev1), timeunits-1));
                }else{
                	sem.addFormulae(new Iff(ev2, new And(new Lasted(new Not(ev1),timeunits-1),new Since_ei(new Not(ev2),ev1))));
                }
                break;
            }
        }
        return sem;
    }
	
}
