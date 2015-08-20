package org.zc.transformer.impl.orchester;

import java.io.File;

import org.zc.transformer.dto.reader.ConfigDTO;
import org.zc.transformer.dto.reader.ReaderDTO;
import org.zc.transformer.dto.writer.JavaDTO;

import org.zc.transformer.impl.reader.ZCReader;
import org.zc.transformer.impl.reader.ZCReaderConfig;
import org.zc.transformer.impl.transformer.ZCTransformer;
import org.zc.transformer.impl.writer.ZcWriter;

public class ZcOrchester {

	private String[] listpathViewXML;
	//File [] listpathViewXML;
	private ReaderDTO readerDTO;
	private ConfigDTO configDTO;
	private JavaDTO javaDTO;
	private ZCReader zcreader;
	private ZCTransformer zctransformer;
	private ZcWriter zcwriter;
	private ZCReaderConfig zcReaderConfig;

	public void ochester(File pathViewFolder, String outputFolder) {

		listpathViewXML = pathViewFolder.list();
		for(int i=0;i<listpathViewXML.length;i++){
			if(listpathViewXML[i].endsWith(".xml")==true){ 
				String pathView2 = listpathViewXML[i];
				String path =pathViewFolder+"/"+pathView2;
				String pathZCReader = path.replace("\\", "/");
				
				zcreader = new ZCReader();
			    readerDTO =  zcreader.ReadView(pathZCReader,"D:/ENSAO/GI4_S2/PFA/ZC_Projects/ZeroCouplageTransformer/ressourcesViews/");//cette fonction fait remplir la balise view du readerDTO
		        zcReaderConfig = new ZCReaderConfig();
		        configDTO =  zcReaderConfig.read("D:/ENSAO/GI4_S2/PFA/ZC_Projects/ZeroCouplageTransformer/ressources/configXML.xml");
		            
		        zctransformer=new org.zc.transformer.impl.transformer.ZCTransformer();
		        javaDTO = new JavaDTO();
		        javaDTO=zctransformer.transformer(readerDTO, configDTO);
		        zcwriter = new ZcWriter();
		               
		 		zcwriter.write(javaDTO, outputFolder);
			
		}
	}
}
}
