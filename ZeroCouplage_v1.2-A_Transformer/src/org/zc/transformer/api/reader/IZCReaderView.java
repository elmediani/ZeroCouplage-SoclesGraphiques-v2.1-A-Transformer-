package org.zc.transformer.api.reader;

import org.zc.transformer.dto.reader.BaliseDTO;


/**
 * Interface specific to View, contains just the one method that enables you to read tags of View file
 * 
 */
public interface  IZCReaderView {
	
	BaliseDTO read(String pathViwXML);

}

