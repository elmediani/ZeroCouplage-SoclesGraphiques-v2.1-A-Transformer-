package org.zc.transformer.dto.writer;

/**
 * this class is reserved to the declaration of a constant
 * it contains an attribute "valeur" that refers to the value of the constant
 */
public class ConstanteDTO extends AttributsDTO {

	protected Object valeur;

	public Object getValeur() {
		return valeur;
	}

	public void setValeur(Object valeur) {
		this.valeur = valeur;
	}
	/**
	 * this method generate the code of constant's declaration 
	 * @param tab : initial tabulation
	 * @return : a string that contains the structure of the constant's declaration
	 */
	@Override
	public String generateCode(String tab) {
		
		StringBuilder sbuild = new StringBuilder();
		
		sbuild.append(getAccess());
		sbuild.append(" static final ");
		sbuild.append(getType());
		sbuild.append(" ");
		sbuild.append(getVariableName());
		sbuild.append(" = ");
		sbuild.append(getValeur());
		sbuild.append(";\n");
		
		String codeGenere = sbuild.toString();
		
		return codeGenere;
	}

}
