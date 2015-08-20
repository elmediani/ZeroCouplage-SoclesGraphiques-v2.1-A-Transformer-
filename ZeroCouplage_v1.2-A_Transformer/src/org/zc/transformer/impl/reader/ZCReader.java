package org.zc.transformer.impl.reader;

import java.io.File;

import org.zc.transformer.api.reader.IZCReader;
import org.zc.transformer.dto.reader.ConfigDTO;
import org.zc.transformer.dto.reader.ReaderDTO;


public class ZCReader implements IZCReader {

	

    /**
     * the main method that reads the View File
     * @param pathView : the path where is created the View File 
     * @param pathReference : the path that where it created the packages 
     * @return object of ReaderDTO
     */
	public ReaderDTO ReadView(String pathView,String pathReference) {

		ReaderDTO readerDTO = new ReaderDTO();
		ZCReaderView zcReaderView = new ZCReaderView();
		
		// get the path where it will be placed the package of created java class
		String packagePathView = pathView.replace(pathReference, "");
		
		File fichier = new File(pathView);
		
		//Get the name of View file  
		String viewXMLName = (fichier.getName() != null) ? fichier.getName()
				.substring(0, fichier.getName().indexOf('.')) : "";

		readerDTO.setPathViewXml(packagePathView.substring(0, packagePathView.lastIndexOf("/")));
		readerDTO.setBaliseDTOView(zcReaderView.read(pathView));
		readerDTO.setNameViewXml(viewXMLName);

		return readerDTO;
	}

	/**
     * the main method that reads the configuration File
	 * @param pathConfig : where the configuration path is placed
	 * @return configuration file 
	 */
	public ConfigDTO ReadConfig(String pathConfig) {

		File fichier = new File(pathConfig);
		//get the name of configuration file
		String configXMLName = (fichier.getName() != null) ? fichier.getName()
				.substring(0, fichier.getName().indexOf('.')) : "";

		ZCReaderConfig zcReaderConfig = new ZCReaderConfig();

		ConfigDTO config = zcReaderConfig.read(pathConfig);
		config.setPathConfigXML(pathConfig);
		config.setNameConfigXml(configXMLName);
		config.setConfig(config.getConfig());

		return config;
	}




}
