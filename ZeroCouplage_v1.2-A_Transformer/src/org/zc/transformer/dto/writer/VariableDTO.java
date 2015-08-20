package org.zc.transformer.dto.writer;

import org.zc.transformer.api.writer.ICodeJavaGenerator;


public class VariableDTO implements ICodeJavaGenerator {

	protected String type;
	protected String VariableName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVariableName() {
		return VariableName;
	}

	public void setVariableName(String variableName) {
		VariableName = variableName;
	}
	/**
	 * this method generate the code of a variable (and method's arguments)
	 * @param tab : initial tabulation
	 * @return : a string that contains the structure of the variable
	 */

	public String generateCode(String tab) {
		StringBuilder sbuild = new StringBuilder();
		
		String codeGenere;
		sbuild.append(getType());
		sbuild.append(" ");
		sbuild.append(getVariableName());
		sbuild.append(", ");
		
		codeGenere =  sbuild.toString();
		return codeGenere;
	}

}