package org.zc.transformer.api.reader;

import org.zc.transformer.dto.reader.ConfigDTO;
import org.zc.transformer.dto.reader.ReaderDTO;

/**
 * Interface that gathers all methods enables you to read configuration file and View file
 * 
 */

public interface IZCReader {

	 ReaderDTO ReadView(String pathView,String pathReference);
	 ConfigDTO ReadConfig(String pathConfig);

}

