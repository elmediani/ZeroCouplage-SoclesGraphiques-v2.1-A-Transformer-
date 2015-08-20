package org.zc.transformer.dto.writer;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is reserved to the bolc instructions : which means the body of a method and the body of a loop
 * it contains two attributes:
 *  entet : it's the method's declaration comprise the method signature
 *  listInstructions : a list that contains a list of instruction, and that can be another BlocInstruction or just a SimpleInstruction
 */

public class BlocInstructionDTO extends InstructionDTO {

	protected String entet;
	// Corps de du bloc :
	protected List<InstructionDTO> listInstructions;

	public BlocInstructionDTO() {
		listInstructions = new ArrayList<InstructionDTO>();
		entet = "";
	}

	public String getEntet() {
		return entet;
	}

	public void setEntet(String entet) {
		this.entet = entet;
	}

	public List<InstructionDTO> getListInstructions() {

		return listInstructions;
	}

	public void setListInstructions(List<InstructionDTO> listInstructions) {
		this.listInstructions = listInstructions;
	}
/**
 * this method generate the code of method's body or loop's body
 * @param tab : initial tabulation
 * @return : a string that contains the structure of a method or a loop
 */
	public String generateCode(String tab) {

		StringBuilder sbuild = new StringBuilder();
		
		String numtab = tab + "\t";
		sbuild.append(numtab);
		
		sbuild.append(entet);
		sbuild.append("{\n");

		String codeGenere = sbuild.toString();

		if (listInstructions != null) {
			for (InstructionDTO instruction : listInstructions) {
				sbuild.append(instruction.generateCode(numtab));
			}
		}

		sbuild.append(numtab);
		sbuild.append("}\n");
		
		codeGenere = sbuild.toString();
		return codeGenere;
	}

}
