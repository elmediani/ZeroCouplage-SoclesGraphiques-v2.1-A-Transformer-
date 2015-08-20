package org.zc.transformer.dto.writer;

import java.util.ArrayList;
import java.util.List;

import org.zc.transformer.api.writer.ICodeJavaGenerator;
/**
 * this class provides the global structure of the java class that we want to generate
 * its attributes:
 * @attribute package: contains the java class's package
 * @attribute listImports: a list of all imports that the java class needs
 * @attribute name: the java class's name
 * @attribute extends: the parent class 
 * @attribute listImplement: list of the implement classes
 * @attribute listConst: list of constants
 * @attribute listAttributs: list of attributes
 * @attribute listConstructors: list of constructors
 * @attribute listMethode: list of methods
 */
public class JavaDTO implements ICodeJavaGenerator {

	protected String Pakage;
	protected List<String> listImports;
	protected String name;
	protected String extands;
	protected List<String> implement;
	protected List<ConstanteDTO> listConst;
	protected List<AttributsDTO> listAttr;
	protected List<MethodesDTO> listConstructors;
	protected List<MethodesDTO> listMethodes;
	
	public JavaDTO(){
		listImports = new ArrayList<String>();
		implement = new ArrayList<String>();
		listConst = new ArrayList<ConstanteDTO>();
		listAttr = new ArrayList<AttributsDTO>();
		listConstructors = new ArrayList<MethodesDTO>();
		listMethodes = new ArrayList<MethodesDTO>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtands() {
		return extands;
	}

	public void setExtands(String extands) {
		this.extands = extands;
	}

	public List<String> getListImports() {
		return listImports;
	}

	public void setListImports(List<String> listImports) {
		this.listImports = listImports;
	}

	public String getPakage() {
		return Pakage;
	}

	public void setPakage(String pakage) {
		Pakage = pakage;
	}

	public List<MethodesDTO> getListMethodes() {
		return listMethodes;
	}

	public void setListMethodes(List<MethodesDTO> listMethodes) {
		this.listMethodes = listMethodes;
	}

	public List<ConstanteDTO> getListConst() {
		return listConst;
	}

	public void setListConst(List<ConstanteDTO> listConst) {
		this.listConst = listConst;
	}

	public List<AttributsDTO> getListAttr() {
		return listAttr;
	}

	public void setListAttr(List<AttributsDTO> listAttr) {
		this.listAttr = listAttr;
	}

	public List<String> getImplement() {
		return implement;
	}

	public void setImplement(List<String> implement) {
		this.implement = implement;
	}

	public List<MethodesDTO> getListConstructors() {
		return listConstructors;
	}

	public void setListConstructors(List<MethodesDTO> listConstructors) {
		this.listConstructors = listConstructors;
	}
	/**
	 * this method generate the code of the java class
	 * @param tab : initial tabulation
	 * @return : a string that contains the structure of java class
	 */
	public String generateCode(String tab) {
		
		StringBuilder sbuild = new StringBuilder();
		String codeGenere = "";
		
		// packages
		sbuild.append("package ");
		sbuild.append(getPakage());
		sbuild.append(";\n\n");

		// les imports
		for (String imp : getListImports()) {
			if(imp!=""){
			sbuild.append("import ");
			sbuild.append(imp);
			sbuild.append(";\n");
			}
		}
		sbuild.append("\n");

		// declaration de la class
		sbuild.append("public class ");
		sbuild.append(getName());
		if (getExtands() != null) {
			sbuild.append(" extends ");
			sbuild.append(getExtands());
		}
		if (getImplement() != null) {
			sbuild.append(" implements ");
			StringBuilder s = new StringBuilder();
			for (String impl : getImplement()) {
				s.append(impl + ", ");
			}
			sbuild.append(s.substring(0, (s.length() - 2)));
		}
		sbuild.append(" { \n");

		// les constantes
		if (getListConst() != null) {
			for (ConstanteDTO constante : getListConst()) {
				sbuild.append(constante.generateCode(tab));
			}
		}

		// les attributs
		if (getListAttr() != null) {
			for (AttributsDTO attribut : getListAttr()) {
				sbuild.append(attribut.generateCode(tab));
			}
		}

		// les constructeurs
		if (getListConstructors() != null) {
			for (MethodesDTO i : getListConstructors()) {
				sbuild.append(i.generateCode(tab));
			}
		}

		// les methodes
		if (getListMethodes() != null) {
			for (MethodesDTO i : getListMethodes()) {
				sbuild.append(i.generateCode(tab));
			}
		}

		sbuild.append("\n}");
		codeGenere = sbuild.toString();

		return codeGenere;
	}

}
