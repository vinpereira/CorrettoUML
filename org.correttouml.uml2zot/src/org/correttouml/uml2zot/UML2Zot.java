package org.correttouml.uml2zot;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.log4j.Logger;
import org.correttouml.uml.MadesModel;
import org.correttouml.uml.diagrams.sequencediagram.Message;
import org.correttouml.uml.diagrams.sequencediagram.SequenceDiagram;
import org.correttouml.uml.diagrams.sequencediagram.SequenceDiagramParameter;
import org.correttouml.uml.diagrams.statediagram.Transition;
import org.correttouml.uml.helpers.UML2ModelHelper;
import org.correttouml.uml2zot.semantics.SMadesModel;
import org.correttouml.uml.diagrams.classdiagram.Object;
import org.correttouml.uml.diagrams.classdiagram.Operation;
import org.correttouml.uml.diagrams.classdiagram.OperationParameter;
import org.correttouml.uml2zot.semantics.classdiagram.SOperation;
import org.correttouml.uml2zot.semantics.classdiagram.SOperationParameter;
import org.correttouml.uml2zot.semantics.sequencediagram.SMessage;
import org.correttouml.uml2zot.semantics.sequencediagram.SSequenceDiagram;
import org.correttouml.uml2zot.semantics.sequencediagram.SSequenceDiagramParameter;
import org.correttouml.uml2zot.semantics.statediagram.SState;
import org.correttouml.uml2zot.semantics.statediagram.STransition;
import org.correttouml.uml2zot.semantics.util.trio.Predicate;
import org.correttouml.uml2zot.zotutil.ZOTConf;
import org.eclipse.uml2.uml.Model;


public class UML2Zot {
	
	private static final Logger LOGGER = Logger.getLogger(UML2Zot.class); 
	
	private Model uml_model;
	
	/** The mades model generated from the UML model */
	private MadesModel mades_model;
	
	/** The model containing the semantics */
	private SMadesModel s_mades_model;
	
	public UML2Zot(String uml_model_file){
		this.uml_model=UML2ModelHelper.loadModel(uml_model_file);
		this.mades_model=new MadesModel(uml_model);
		this.s_mades_model=new SMadesModel(mades_model);
	}
	
	public void generateMappingsFile(File mappings_file){
		try{
		LOGGER.info("Generate mappings file");
		
        //Finally, write
        FileWriter mpw = new FileWriter(mappings_file);
        BufferedWriter out = new BufferedWriter(mpw);
		
        out.write("============ State Diagrams Mapping ============" + "\n");
        
        // State Diagram mapping - by Alfredo
		for(org.correttouml.uml.diagrams.classdiagram.Class c: this.mades_model.getClassdiagram().getClasses()){
			for(org.correttouml.uml.diagrams.statediagram.StateDiagram std: c.getStateDiagrams()){
				// States - by Alfredo
				for(org.correttouml.uml.diagrams.statediagram.State s: std.getStates()){
					for(org.correttouml.uml.diagrams.classdiagram.Object obj: c.getObjects()){
						SState ss=new org.correttouml.uml2zot.semantics.statediagram.SState(s);
						Predicate p=ss.getPredicate(obj);
						out.write(p.getPredicateName()+","+s.getUMLId()+"\n");
					}
				}
				// Transitions - by Vinicius
				for(Transition t: std.getTransitions()) {
					for(Object obj: c.getObjects()) {
						STransition st = new STransition(t);
						Predicate p=st.getPredicate(obj);
						out.write(p.getPredicateName()+","+t.getUMLId()+"\n");
					}
				}
			}
		}
		
		out.write("\n" + "============ Sequence Diagrams Mapping ============" + "\n");
		
		// Sequence Diagram mapping - by Vinicius
		for(SequenceDiagram sd: this.mades_model.getSequenceDiagrams()) {
			// Parameter - by Vinicius
			for(SequenceDiagramParameter sdp: sd.getSequenceDiagramParameters()) {
				SSequenceDiagramParameter ssdp = new SSequenceDiagramParameter(sdp);
				Predicate p = ssdp.getPredicateForSDParameter();
				out.write(p.getPredicateName()+","+sdp.getUMLId()+"\n");
			}
			// Message - by Vinicius
			for(Message msg: sd.getMessages()) {
				SMessage smsg = new SMessage(msg);
				Predicate p = smsg.getPredicate();
				out.write(p.getPredicateName()+","+msg.getUMLId()+"\n");
			}
			// Sequence Diagram - by Vinicius
			SSequenceDiagram ssd = new SSequenceDiagram(sd);
			Predicate p = ssd.getPredicate();
			out.write(p.getPredicateName()+","+sd.getUMLId()+"\n");
		}
		
		out.write("\n" + "============ Class Diagrams Mapping ============" + "\n");
		
		// Class Diagram mapping - by Vinicius
		for(org.correttouml.uml.diagrams.classdiagram.Class c: this.mades_model.getClassdiagram().getClasses()){ 
			// Operation - by Vinicius
			for(Operation op: c.getOperations()) {
				for (Object obj: c.getObjects()) {
					SOperation sop = new SOperation(op);
					Predicate p = sop.getPredicate(obj);
					out.write(p.getPredicateName()+","+op.getUMLId()+"\n");	
				}
				
				// Operation Parameter - by Vinicius
				for(OperationParameter opp: op.getParameters()) {
					for (Object obj: c.getObjects()) {
						SOperationParameter sopp = new SOperationParameter(opp);
						Predicate p = sopp.getPredicateForCDOperationParameter(obj);
						out.write(p.getPredicateName()+","+opp.getUMLId()+"\n");
					}
				}
			}
		}
		
		// TODO Put Interactive Overview Diagram in mapping
		
		out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void generateZOTFile(String zot_file){		
		LOGGER.info("Build the ZOT file");
		ZOTConf zot=new ZOTConf(100, "ae2zot", "z3", this.s_mades_model);
		try {
			zot.writeVerificationZOTFile(zot_file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateZOTFile(int timebound, String plugin, String solver, String zot_file){		
		LOGGER.info("Build the ZOT file");
		ZOTConf zot=new ZOTConf(timebound, plugin, solver, this.s_mades_model);
		try {
			zot.writeVerificationZOTFile(zot_file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
