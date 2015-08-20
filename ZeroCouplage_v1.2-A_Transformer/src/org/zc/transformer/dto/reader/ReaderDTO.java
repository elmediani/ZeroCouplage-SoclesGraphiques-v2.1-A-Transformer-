package org.zc.transformer.dto.reader;


/**
 * the main class that will be instantiated in other services ( reader , transformer , writer )
 * pathViewXml : the path of View file
 * nameViewXml : name of View file
 * baliseDTOView : all information of each tag in the View File
 */

public class ReaderDTO  {

	
	private String pathViewXml ;
	private String nameViewXml ;
	
	private BaliseDTO baliseDTOView ;

	
	public ReaderDTO() {
		super();
	}


	public String getPathViewXml() {
		return pathViewXml;
	}


	public void setPathViewXml(String pathViewXml) {
		this.pathViewXml = pathViewXml;
	}


	public String getNameViewXml() {
		return nameViewXml;
	}


	public void setNameViewXml(String nameViewXml) {
		this.nameViewXml = nameViewXml;
	}


	public BaliseDTO getBaliseDTOView() {
		return baliseDTOView;
	}


	public void setBaliseDTOView(BaliseDTO baliseDTOView) {
		this.baliseDTOView = baliseDTOView;
	}
	

	
	
	}