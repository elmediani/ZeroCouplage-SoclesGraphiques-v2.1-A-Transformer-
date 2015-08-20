package org.zc.transformer.dto.writer;

public class SimpleInstructionDTO extends InstructionDTO{
	
	protected String instructionContent;

	public String getInstructionContent() {
		return instructionContent;
	}

	public void setInstructionContent(String instructionContent) {
		this.instructionContent = instructionContent;
	}
	/**
	 * this method generate the code of a simple instruction : it's a set of instructions excluding a loop or a method
	 * @param tab : initial tabulation
	 * @return : a string that contains the structure of the instruction
	 */
	public String generateCode(String tab) {
		
		StringBuilder sbuild = new StringBuilder();

		String numtab = tab + "\t";
		sbuild.append(numtab);
		
		sbuild.append(instructionContent);
		sbuild.append(";\n");
		
		return sbuild.toString();
	}

}
