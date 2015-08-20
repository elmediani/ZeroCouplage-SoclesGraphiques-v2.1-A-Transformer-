package org.zc.transformer.impl.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.zc.transformer.api.reader.IZCReaderView;
import org.zc.transformer.dto.reader.BaliseDTO;



 public class ZCReaderView implements IZCReaderView {

	 
/**
 *  This method that reads a View.xml by passing as parameters a path of the view.xml
 *  it processes  using the composite design pattern.
 *  must be private because it is specific for this class
 *  @param pathViewXML : path of the file View.xml
 *  @return an object of baliseDTO ,
 */
	public BaliseDTO read(String pathViewXML) {

		BaliseDTO balise = null; 
		
		try {
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder
					.build(new FileInputStream(pathViewXML));
			Element root = doc.getRootElement();
			
			balise = baliseDomToJava(root);

		} catch (JDOMException e) {
			System.err.println("Fichier XML mal forme !");

		} catch (IOException ee) {
			System.err.println("Erreur d’I/O...");
		}
		return balise;
	}
	
	/**
	 * This method reads all information of a balise ( Name , list of attribute , list of children tag ,  content)
	 * @param baliseDom : current or specific tag that we need to know all its information  
	 * @return  an object of baliseDTO that contains all these information 
	 * must be private because it is specific for this class
	 */
	
	public BaliseDTO baliseDomToJava(Element baliseDom) {
		BaliseDTO baliseDto = new BaliseDTO();
		Map<String, String> listAttributs = attributDomToJava(baliseDom.getAttributes());
		List<BaliseDTO> listChildren = listChildrenDomToJava(baliseDom.getChildren());

		baliseDto.setName(baliseDom.getName());
		baliseDto.setListAttr(listAttributs);
		baliseDto.setListChildren(listChildren);
		baliseDto.setContent(BaliseContent(baliseDom));

		return baliseDto;
	}

	/**
	 * this method reads all children of a tag that have been passing in its parameters
	 * returns list of  objects of balise DTO 
	 * must be private because it is specific for this class
 	 * @param elt : current element  
	 * @return list of children of the current element or tag 
	 */
	
	private List<BaliseDTO> listChildrenDomToJava(List<Element> elt) {

		List<BaliseDTO> listBaliseChild = new ArrayList<BaliseDTO>();

		if (elt != null) {
			Iterator<Element> baliseChildCurrent = elt.iterator();
			while (baliseChildCurrent.hasNext()) {
				Element crt = (Element) baliseChildCurrent.next();
				BaliseDTO baliseChildDTO = baliseDomToJava(crt);
				listBaliseChild.add(baliseChildDTO);
			}

		}

		return listBaliseChild;
	}
	
	/**
	 * method that provides a list attribute of each tag 
	 * must be private because it is specific for this class
	 * @param listAttributs : list of attribute of a tag 
	 * @return map with key : name of attribute  _ value : value of this attribute
	 */

	private Map<String, String> attributDomToJava(List<Attribute> listAttributs) {

		Map<String, String> map = new HashMap<String, String>();
		for (Attribute currentAttribute : listAttributs)

		{
			map.put(currentAttribute.getName(), currentAttribute.getValue());

		}

		return map;
	}


	/**
	 * method that provides the content of each tag 
	 * must be private because it is specific for this class
	 * @param baliseDom : current tag 
	 * @return content of the tag 
	 */
	
	private String BaliseContent(Element baliseDom){
		
		String content = baliseDom.getText();
		
			 return content;
		
	}
}
