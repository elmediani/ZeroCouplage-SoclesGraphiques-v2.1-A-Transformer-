package org.zc.transformer.api.writer;

import org.zc.transformer.dto.writer.JavaDTO;

public interface IZcWriter {
	
	public  void write(JavaDTO javaDto, String pathTargetFolder);
}
