package org.zc.transformer.dto.writer;

import java.util.List;
/**
 * this class is reserved to generate the code of a constructor
 */
public class ConstructorDTO extends MethodesDTO {
	/**
	 * this method generate the code of a constructor; its declaration and body
	 * @param tab : initial tabulation
	 * @return : a string that contains the structure of the constructor
	 */
	@Override
	public String generateCode(String tab) {
		
		String nom = getNom();
		String access = getAccess();
		List<VariableDTO> arguments = getArguments();
		List<String> exceptions = getExceptions();
		StringBuilder sbuild = new StringBuilder();
		
		String numtab = tab + "\t";
		sbuild.append("\n");
		sbuild.append(numtab);
		
		sbuild.append(access);
		sbuild.append(" ");
		sbuild.append(nom);
		sbuild.append("(");
		String codeGenere = sbuild.toString();
		if (arguments != null) {
			StringBuilder sbuilder = new StringBuilder();
			for (VariableDTO arg : arguments) {
				sbuilder.append(arg.generateCode(""));
			}
			sbuild.append(sbuilder.substring(0, (sbuilder.length() - 2)));
			codeGenere = sbuild.toString();			
		}
		sbuild.append(")");
		codeGenere = sbuild.toString();
		if (exceptions != null) {
			sbuild.append(" throws ");
			StringBuilder sbuilder = new StringBuilder();
			for (String exception : exceptions) {
				sbuilder.append(exception);
				sbuilder.append(", ");
			}
			sbuild.append(sbuilder.substring(0, (sbuilder.length() - 2)));
		}
		
		if(corpsMethode != null){
		sbuild.append(corpsMethode.generateCode(tab));
		}
		else{
			sbuild.append("{ }\n");
		}
		codeGenere = sbuild.toString();

		return codeGenere;
	}

}