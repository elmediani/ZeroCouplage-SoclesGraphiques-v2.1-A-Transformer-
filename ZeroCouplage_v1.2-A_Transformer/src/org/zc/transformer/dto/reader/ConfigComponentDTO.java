package org.zc.transformer.dto.reader;

import java.util.Map;

/**
 * ClassDTO with attributes :
 * packageClassJavaName : the package name where the class Java will be generated , 
 * listAttribute : list of attribute of the specific component 
 */

public class ConfigComponentDTO {

	private String packageClassJavaName;
	private Map<String, String> listAttribute;
	
	
	public String getPackageClassJavaName() {
		return packageClassJavaName;
	}
	public void setPackageClassJavaName(String packageClassJavaName) {
		this.packageClassJavaName = packageClassJavaName;
	}
	public Map<String, String> getListAttribute() {
		return listAttribute;
	}
	public void setListAttribute(Map<String, String> listAttribute) {
		this.listAttribute = listAttribute;
	}

	
}
