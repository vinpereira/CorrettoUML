/*
* generated by Xtext
*/
package org.correttouml.grammars.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.correttouml.grammars.services.StateMachineActionsGrammarAccess;

public class StateMachineActionsParser extends AbstractContentAssistParser {
	
	@Inject
	private StateMachineActionsGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.correttouml.grammars.ui.contentassist.antlr.internal.InternalStateMachineActionsParser createParser() {
		org.correttouml.grammars.ui.contentassist.antlr.internal.InternalStateMachineActionsParser result = new org.correttouml.grammars.ui.contentassist.antlr.internal.InternalStateMachineActionsParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getActionAccess().getAlternatives(), "rule__Action__Alternatives");
					put(grammarAccess.getEXPRESSIONAccess().getAlternatives(), "rule__EXPRESSION__Alternatives");
					put(grammarAccess.getTERMAccess().getAlternatives(), "rule__TERM__Alternatives");
					put(grammarAccess.getParametersAccess().getAlternatives(), "rule__Parameters__Alternatives");
					put(grammarAccess.getEventExtensionsAccess().getAlternatives(), "rule__EventExtensions__Alternatives");
					put(grammarAccess.getAssignmentAccess().getGroup(), "rule__Assignment__Group__0");
					put(grammarAccess.getEXPRESSIONAccess().getGroup_0(), "rule__EXPRESSION__Group_0__0");
					put(grammarAccess.getEventActionAccess().getGroup(), "rule__EventAction__Group__0");
					put(grammarAccess.getEventActionAccess().getGroup_1(), "rule__EventAction__Group_1__0");
					put(grammarAccess.getEventActionAccess().getGroup_1_1(), "rule__EventAction__Group_1_1__0");
					put(grammarAccess.getParametersAccess().getGroup_1(), "rule__Parameters__Group_1__0");
					put(grammarAccess.getModelAccess().getActionAssignment(), "rule__Model__ActionAssignment");
					put(grammarAccess.getActionAccess().getAssignmentAssignment_0(), "rule__Action__AssignmentAssignment_0");
					put(grammarAccess.getActionAccess().getEventActionAssignment_1(), "rule__Action__EventActionAssignment_1");
					put(grammarAccess.getAssignmentAccess().getLeftvarAssignment_0(), "rule__Assignment__LeftvarAssignment_0");
					put(grammarAccess.getAssignmentAccess().getExpressionAssignment_2(), "rule__Assignment__ExpressionAssignment_2");
					put(grammarAccess.getEXPRESSIONAccess().getFirstTermAssignment_0_0(), "rule__EXPRESSION__FirstTermAssignment_0_0");
					put(grammarAccess.getEXPRESSIONAccess().getOperatorAssignment_0_1(), "rule__EXPRESSION__OperatorAssignment_0_1");
					put(grammarAccess.getEXPRESSIONAccess().getSecondTermAssignment_0_2(), "rule__EXPRESSION__SecondTermAssignment_0_2");
					put(grammarAccess.getEXPRESSIONAccess().getAloneAssignment_1(), "rule__EXPRESSION__AloneAssignment_1");
					put(grammarAccess.getTERMAccess().getVariableAssignment_0(), "rule__TERM__VariableAssignment_0");
					put(grammarAccess.getTERMAccess().getConstantAssignment_1(), "rule__TERM__ConstantAssignment_1");
					put(grammarAccess.getEventActionAccess().getEventNameAssignment_1_0(), "rule__EventAction__EventNameAssignment_1_0");
					put(grammarAccess.getEventActionAccess().getParametersAssignment_1_1_1(), "rule__EventAction__ParametersAssignment_1_1_1");
					put(grammarAccess.getEventActionAccess().getEventExtensionAssignment_1_3(), "rule__EventAction__EventExtensionAssignment_1_3");
					put(grammarAccess.getParametersAccess().getParamAssignment_0(), "rule__Parameters__ParamAssignment_0");
					put(grammarAccess.getParametersAccess().getParamAssignment_1_0(), "rule__Parameters__ParamAssignment_1_0");
					put(grammarAccess.getParametersAccess().getParametersAssignment_1_2(), "rule__Parameters__ParametersAssignment_1_2");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.correttouml.grammars.ui.contentassist.antlr.internal.InternalStateMachineActionsParser typedParser = (org.correttouml.grammars.ui.contentassist.antlr.internal.InternalStateMachineActionsParser) parser;
			typedParser.entryRuleModel();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public StateMachineActionsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(StateMachineActionsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
