package org.zc.transformer.impl.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.zc.transformer.dto.reader.ConfigComponentDTO;
import org.zc.transformer.dto.reader.ConfigDTO;

public class ZCReaderConfig {

	public ConfigDTO read(String pathConfigXML) {

		try {

			ConfigDTO configDTO = new ConfigDTO();
			Map<String, ConfigComponentDTO> map = new HashMap<String, ConfigComponentDTO>();

			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new FileInputStream(pathConfigXML));
			Element root = doc.getRootElement();
			List<Element> zcComponenet = root.getChildren("ZcComponent");

			if (zcComponenet != null) {
				Iterator<Element> i = zcComponenet.iterator();
				while (i.hasNext()) {
					Element crt = (Element) i.next();
					ConfigComponentDTO baliseComponent = readConfigComponent(crt);
					map.put(crt.getAttributeValue("XMLName"), baliseComponent);
				}
			}

			configDTO.setPathConfigXML(pathConfigXML);
			configDTO.setConfig(map);

			return configDTO;

		} catch (JDOMException e) {
			System.err.println("Fichier XML mal forme !");

		} catch (IOException ee) {
			System.err.println("Erreur d’I/O...");
		}
		return null;

	}

	private ConfigComponentDTO readConfigComponent(Element component) {

		ConfigComponentDTO configComponent = new ConfigComponentDTO();
		String nameComponent = component.getAttributeValue("JAVAName");
		Map<String, String> listAttributs = attributComponent(component
				.getChildren());

		configComponent.setPackageClassJavaName(nameComponent);
		configComponent.setListAttribute(listAttributs);

		return configComponent;
	}

	private Map<String, String> attributComponent(
			List<Element> ElementProperties) {

		Map<String, String> map = new HashMap<String, String>();

		if (ElementProperties != null) {
			Iterator<Element> i = ElementProperties.iterator();
			while (i.hasNext()) {
				Element crt = (Element) i.next();

				map.put(crt.getAttributeValue("XMLName"),
						crt.getAttributeValue("MethodName"));

			}
		}

		return map;
	}

}
