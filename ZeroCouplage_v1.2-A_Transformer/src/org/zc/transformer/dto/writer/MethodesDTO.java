package org.zc.transformer.dto.writer;

import java.util.ArrayList;
import java.util.List;

import org.zc.transformer.api.writer.ICodeJavaGenerator;
/**
 * this class provides the structure of methods
 * its attributes:
 * @attribute name: the method name
 * @attribute access: the accessibility of the method (public/private/protected)
 * @attribute listArguments: list of arguments
 * @attribute returnType: the type for the method 
 * @attribute listexceptions: list of the eceptions
 * @attribute corpsMethode: method's body
 * @attribute listAnnotations: list of annotations
 */
public class MethodesDTO implements ICodeJavaGenerator {

	protected String nom;
	protected String access;// l'accessibilité (public/private/protected)
	protected List<VariableDTO> arguments;
	protected String returnType;
	protected List<String> exceptions;
	// Corps de la methode :
	protected BlocInstructionDTO corpsMethode;
	protected List<String> annotations;
	
	public MethodesDTO(){
		arguments = new ArrayList<VariableDTO>();
		exceptions = new ArrayList<String>();
		annotations = new ArrayList<String>();
	}

	public List<String> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<String> annotations) {
		this.annotations = annotations;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public List<VariableDTO> getArguments() {
		return arguments;
	}

	public void setArguments(List<VariableDTO> arguments) {
		this.arguments = arguments;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<String> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<String> exceptions) {
		this.exceptions = exceptions;
	}

	public BlocInstructionDTO getCorpsMethode() {
		return corpsMethode;
	}

	public void setCorpsMethode(BlocInstructionDTO corpsMethode) {
		this.corpsMethode = corpsMethode;
	}
	/**
	 * this method generate the code of a method; its declaration and body
	 * @param tab : initial tabulation
	 * @return : a string that contains the structure of the method
	 */
	public String generateCode(String tab) {
		
		StringBuilder sbuild = new StringBuilder();
		sbuild.append("\n");
		String numtab = tab + "\t";
		
		
		if(annotations!=null){
			for(String annotation : annotations){
				sbuild.append(numtab);
				sbuild.append(annotation);
				sbuild.append("\n");
			}
		}
		sbuild.append(numtab);
		sbuild.append(getAccess());
		sbuild.append(" ");
		sbuild.append(getReturnType());
		sbuild.append(" ");
		sbuild.append(getNom());
		sbuild.append("(");
		String codeGenere = sbuild.toString();
		
		if (getArguments() != null) {
			StringBuilder concat = new StringBuilder();
			for (VariableDTO arg : getArguments()) {
				concat.append(arg.generateCode(""));
			}
		}
		sbuild.append(")");
		if (getExceptions() != null) {
			sbuild.append(" throws ");
			StringBuilder concat = new StringBuilder();
			for (String exception : getExceptions()) {
				concat.append(exception);
				concat.append(", ");
			}
			sbuild.append(concat.substring(0, (concat.length() - 2)));
		}

		sbuild.append(corpsMethode.generateCode(tab));

		sbuild.append("\n\n");

		codeGenere = sbuild.toString();

		return codeGenere;
	}

}
