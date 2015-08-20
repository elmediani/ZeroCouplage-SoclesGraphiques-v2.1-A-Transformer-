package org.zc.transformer.dto.writer;

import org.zc.transformer.api.writer.ICodeJavaGenerator;

/**
 * this class is reserved to the attributes: which means it create an object DTO for attributes 
 */

public class AttributsDTO extends VariableDTO implements ICodeJavaGenerator {

	protected String access;

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	/**
	 * this method generate the code concerning the attributes only
	 * @param tab : initial tabulation
	 * @return : a string that contains the declaration of an attribute
	 */
	@Override
	public String generateCode(String tab) {
		
		StringBuilder sbuild = new StringBuilder();
		
		String numtab = tab + "\t";
		sbuild.append(numtab);

		sbuild.append(access);
		sbuild.append(" ");
		sbuild.append(type);
		sbuild.append(" ");
		sbuild.append(VariableName);
		sbuild.append(";\n");
		
		String codeGenere = sbuild.toString();
		return codeGenere;
	}

}
