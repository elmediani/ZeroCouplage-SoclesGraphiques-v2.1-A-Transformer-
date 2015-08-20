package org.zc.transformer.dto.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassDTO of View , contains simple objects that should not contain any
 * business logic that would require testing
 * 
 */
public class BaliseDTO {

	private String Name;
	private Map<String, String> listAttribute;
	private List<BaliseDTO> listChildren;
	private String content;

	public BaliseDTO() {
		listAttribute = new HashMap<String, String>();
	}

	// Getters and Setters

	public Map<String, String> getListAttr() {
		return listAttribute;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Map<String, String> getListAttribute() {
		return listAttribute;
	}

	public void setListAttribute(Map<String, String> listAttribute) {
		this.listAttribute = listAttribute;
	}

	public List<BaliseDTO> getListChildren() {
		return listChildren;
	}

	public void setListChildren(List<BaliseDTO> listChildren) {
		this.listChildren = listChildren;
	}

	public void setListAttr(Map<String, String> listAttr) {
		listAttribute = listAttr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
