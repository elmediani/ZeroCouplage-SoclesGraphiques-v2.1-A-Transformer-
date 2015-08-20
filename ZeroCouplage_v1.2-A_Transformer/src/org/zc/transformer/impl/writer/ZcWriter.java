package org.zc.transformer.impl.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.zc.transformer.api.writer.IZcWriter;
import org.zc.transformer.dto.writer.JavaDTO;


public class ZcWriter implements IZcWriter {
/**
 * this method allows to write the final java class
 * it needs two objects as input: the object JavaDTO and the path to the outputFolder
 */
	public void write(JavaDTO javaDto, String pathTargetFolder) {

		try {

			String pathPackage = creatFolderPackage(javaDto.getPakage(), pathTargetFolder);
			// name the file
			FileWriter fw = new FileWriter(pathPackage + File.separator	+ javaDto.getName() + ".java", false);

			PrintWriter output = new PrintWriter(new BufferedWriter(fw));

			output.println(javaDto.generateCode(""));

			output.flush();
			output.close();
			System.out.println("File Created ! ");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}

	}
	/**
	 * this method allows to create the folder package for the java class
	 * @param pakage
	 * @param pathTargetFolder
	 * @return
	 */

	private String creatFolderPackage(String pakage, String pathTargetFolder) {
		
		String fullPath = pathTargetFolder;
		if (pakage != null) {
			fullPath = fullPath + File.separator + (pakage.replaceAll("\\.", "/"));
		}

		File fileFullPath = new File(fullPath);

		fileFullPath.mkdirs();

		return fullPath;
	}
}
