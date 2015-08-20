package org.zc.transformer.dto.reader;

import java.util.HashMap;
import java.util.Map;

/**
 * classDTO contains as attributes : 
 * pathConfigXML : path of the file configuration
 * nameConfigXml : the name of the file configuration
 * config : contains a map that provides as a key & name : the name of component & the value of this compononent 
 */

public class ConfigDTO {

	private String pathConfigXML;
	private String nameConfigXml;
	private Map<String, ConfigComponentDTO> config;

	public ConfigDTO() {
		config = new HashMap<String, ConfigComponentDTO>();
	}

	public String getPathConfigXML() {
		return pathConfigXML;
	}

	public void setPathConfigXML(String pathConfigXML) {
		this.pathConfigXML = pathConfigXML;
	}

	public Map<String, ConfigComponentDTO> getConfig() {
		return config;
	}

	public void setConfig(Map<String, ConfigComponentDTO> config) {
		this.config = config;
	}

	public String getNameConfigXml() {
		return nameConfigXml;
	}

	public void setNameConfigXml(String nameConfigXml) {
		this.nameConfigXml = nameConfigXml;
	}

}
