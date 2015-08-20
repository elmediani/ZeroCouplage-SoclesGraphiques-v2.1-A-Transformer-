package org.zc.transformer.api.transformer;

import org.zc.transformer.dto.reader.ConfigDTO;
import org.zc.transformer.dto.reader.ReaderDTO;
import org.zc.transformer.dto.writer.JavaDTO;

public interface IZCTransformer {

	JavaDTO transformer(ReaderDTO readerDTO, ConfigDTO configDTO);
	}

	
	
	