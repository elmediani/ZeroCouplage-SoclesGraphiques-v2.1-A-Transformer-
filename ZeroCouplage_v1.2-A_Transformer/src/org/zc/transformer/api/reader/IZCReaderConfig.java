package org.zc.transformer.api.reader;

import org.zc.transformer.dto.reader.ConfigDTO;



/**
 * Interface specific to config, contains just method that enables you to read tags of configuration file 
 * 
 */

public interface IZCReaderConfig {


	public ConfigDTO read(String pathConfigXML) ;

}
