package org.zc.transformer.impl.transformer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zc.transformer.api.transformer.IZCTransformer;
import org.zc.transformer.dto.reader.BaliseDTO;
import org.zc.transformer.dto.reader.ConfigComponentDTO;
import org.zc.transformer.dto.reader.ConfigDTO;
import org.zc.transformer.dto.reader.ReaderDTO;
import org.zc.transformer.dto.writer.AttributsDTO;
import org.zc.transformer.dto.writer.BlocInstructionDTO;
import org.zc.transformer.dto.writer.InstructionDTO;
import org.zc.transformer.dto.writer.JavaDTO;
import org.zc.transformer.dto.writer.MethodesDTO;
import org.zc.transformer.dto.writer.SimpleInstructionDTO;

public class ZCTransformer implements IZCTransformer {

	static int iComposantSansNom = 0;
	/**
	 * this method allows instantiating an attribute in the Java class in the section : Display()
	 * @param parentBaliseDTO : the parent DTO which is in the XML view File
	 * @param parentName : the name of the parent DTO "parentBaliseDTO" ( the tag in the XML view)
	 * @param currentBaliseDTO : the current DTO which is in the XML view File
	 * @param currentName : the name of the current DTO "currentBaliseDTO" ( the tag in the XML view)
	 * @param currentType : the type of the current DTO "currentBaliseDTO" ( the tag in the XML view)
	 * @param configDTO : a DTO containing the configuration of all tags "BaliseDTO"
	 * @param javaDTO : the DTO used to fill the java Class with the code matching with the XML view
	 * @param listInstructMethodDisplay : a list that we use to add the instructions of Instantiating 
	 * and adding the current ZCComponent to the Parent Component
	 */ 
	public void instancierAttribut(BaliseDTO parentBaliseDTO, String parentName,
			BaliseDTO currentBaliseDTO, String currentName, String currentType,
			ConfigDTO configDTO, JavaDTO javaDTO, List<InstructionDTO> listInstructMethodDisplay) {

		SimpleInstructionDTO instrucInitComp = new SimpleInstructionDTO();
		
		if(currentBaliseDTO.getName()=="style"){ instrucInitComp.setInstructionContent(currentName+"= new "+currentType+"()");}
		
		if(currentType!=null && currentType!="" && currentBaliseDTO.getName()!="style"){
		instrucInitComp.setInstructionContent(currentName + " =("+currentType+") ZCComponentFactory.newComponent("+currentType+".class)");
		}
		
		if(currentType=="" ){ instrucInitComp.setInstructionContent("");}
		
		
		listInstructMethodDisplay.add(instrucInitComp);
	}
	/**
	 * This method provides a map containing the type and the name of a tag  
	 * @param baliseDTO : a DTO matching with the tag in the XML view
	 * @param configDTO :  a DTO containing the configuration of all tags "BaliseDTO" 
	 * @return :a map containing the type and the name of a tag 
	 */
	public Map<String, String> typeNameBaliseDTO(BaliseDTO baliseDTO, ConfigDTO configDTO) {

		Map<String, String> mapTypeNameBaliseDTO = new HashMap<String, String>();
		Map<String, String> mapAttributsBaliseDTO = baliseDTO.getListAttribute();
		String nameBaliseDTO = null;
		if (mapAttributsBaliseDTO.get("name") != null) {
			nameBaliseDTO = baliseDTO.getListAttribute().get("name");
		}

		Map<String, ConfigComponentDTO> mapConfigDTO = configDTO.getConfig();
		if (mapConfigDTO.get(baliseDTO.getName()) != null) {

			String packageClassJavaName = mapConfigDTO.get(baliseDTO.getName())
					.getPackageClassJavaName();
			String typeBaliseDTO = packageClassJavaName.substring(packageClassJavaName
					.lastIndexOf('.') + 1);
			if (mapAttributsBaliseDTO.get("name") == null) {
				nameBaliseDTO = typeBaliseDTO + iComposantSansNom;
				iComposantSansNom++;
			}
			mapTypeNameBaliseDTO.put("type", typeBaliseDTO);
			mapTypeNameBaliseDTO.put("name", nameBaliseDTO);

		}

		return mapTypeNameBaliseDTO;

	}

	/**
	 * This methods provides the package to import to recognize the ZCcomponent in the tag of the XML view , in the Java Class 
	 * @param baliseDTO : a DTO matching with the tag in the XML view
	 * @param configDTO  : a DTO containing the configuration of all tags "BaliseDTO" 
	 * @param javaDTO : the DTO used to fill the java Class with the code matching with the XML view
	 */
	public void remplirImport(BaliseDTO baliseDTO, ConfigDTO configDTO,JavaDTO javaDTO) {
		Map<String, ConfigComponentDTO> mapConfigDTO = configDTO.getConfig();
		ConfigComponentDTO configComponentDTO = mapConfigDTO.get(baliseDTO.getName());
		String packClassJavaName = configComponentDTO.getPackageClassJavaName();
		if (!javaDTO.getListImports().contains(packClassJavaName)) {
			javaDTO.getListImports().add(packClassJavaName);
		}

	}

	/**
	 * This method allows providing the declaration of an attribute
	 * @param baliseDTO : a DTO which is in the XML view File
	 * @param baliseDTOName : the name of the DTO "BaliseDTO" ( the tag in the XML view)
	 * @param typeBaliseDTO : the type of the DTO "BaliseDTO" ( the tag in the XML view)
	 * @param configDTO : a DTO containing the configuration of a tag "BaliseDTO" 
	 * @param javaDTO : the DTO used to fill the java Class with the code matching with the XML view
	 */
	public void remplirAttributs(BaliseDTO baliseDTO, String baliseDTOName,String typeBaliseDTO, ConfigDTO configDTO, JavaDTO javaDTO) {
		if(baliseDTO.getName() !="center" && baliseDTO.getName() !="north"&& baliseDTO.getName() !="south"&& baliseDTO.getName() !="east"&& baliseDTO.getName() !="wests"){
		AttributsDTO attributTemporaire = new AttributsDTO();
		attributTemporaire.setAccess("private");
		attributTemporaire.setVariableName(baliseDTOName);
		attributTemporaire.setType(typeBaliseDTO);

		javaDTO.getListAttr().add(attributTemporaire);}
		
		if(baliseDTO.getName() !="style"){
			
			
		}
	}
/**
 * This method provides the code concerning the current BaliseDTO to add to
 *  the function Display() : Instantiating and adding the
 *  current ZCComponent to the Parent Component
 * @param parentBaliseDTO : the parent DTO which is in the XML view File
 * @param parentName : the name of the parent DTO "parentBaliseDTO" ( the tag in the XML view)
 * @param currentBaliseDTO : the current DTO which is in the XML view File
 * @param currentName : the name of the current DTO "currentBaliseDTO" ( the tag in the XML view)
 * @param currentType : the type of the current DTO "currentBaliseDTO" ( the tag in the XML view)
 * @param configDTO : a DTO containing the configuration of all tags "BaliseDTO"
 * @param javaDTO : the DTO used to fill the java Class with the code matching with the XML view
 * @param listInstructMethodDisplay : a list that we use to add the instructions of Instantiating 
 * and adding the current ZCComponent to the Parent Component
 */
	public void remplirDisplay(BaliseDTO parentBaliseDTO, String parentName,
			BaliseDTO currentBaliseDTO, String currentName, String currentType,
			ConfigDTO configDTO, JavaDTO javaDTO, List<InstructionDTO> listInstructMethodDisplay) {
/*
		SimpleInstructionDTO instrucInitComp = new SimpleInstructionDTO();
		if(currentType!=null &&currentType!="" ){
		instrucInitComp.setInstructionContent(currentName + " =("+currentType+") ZCComponentFactory.newComponent("+currentType+".class)");
		}
		else{ instrucInitComp.setInstructionContent("");}
		listInstructMethodDisplay.add(instrucInitComp);
*/
		
		
		//instancier la racine
		if (currentBaliseDTO.getName()=="Page") {
			instancierAttribut(parentBaliseDTO,  parentName,
					 currentBaliseDTO,  currentName,  currentType,
					 configDTO,  javaDTO,  listInstructMethodDisplay);}
		
// pour le BorderLayout Layout on a une exception de cas vu qu'on a définit dans le Config.XML des balises
		// intermediaires pour ajouter un composant au : center, north, south, east,wests
		Map<String, ConfigComponentDTO> mapConfigAllBaliseDTO = configDTO.getConfig();
		if (parentBaliseDTO != null  && parentBaliseDTO.getName() !="BorderLayout" && parentBaliseDTO.getName() !="center" && parentBaliseDTO.getName() !="north"&& parentBaliseDTO.getName() !="south"&& parentBaliseDTO.getName() !="east"&& parentBaliseDTO.getName() !="west") {
			instancierAttribut(parentBaliseDTO,  parentName,
					 currentBaliseDTO,  currentName,  currentType,
					 configDTO,  javaDTO,  listInstructMethodDisplay);
			// le fils courant s'auto-ajoute au père 
			SimpleInstructionDTO instrucAddToParent = new SimpleInstructionDTO();
			
			ConfigComponentDTO parentConfigDTO = mapConfigAllBaliseDTO.get(parentBaliseDTO.getName());
			//on récupère le "nom" de la méthode qui fait ajouter le composant fils au composant père
			String methodeAddChildName = parentConfigDTO.getListAttribute().get(currentBaliseDTO.getName());
			
			instrucAddToParent.setInstructionContent(parentName + "."+ methodeAddChildName + "(" + currentName + ")");
			
			listInstructMethodDisplay.add(instrucAddToParent);
		}
		// on traite cette condition à part,car sinon le composant fils sera ajouté au parent Border avant même d'être instancié !
		if (parentBaliseDTO != null  && parentBaliseDTO.getName() =="BorderLayout") {
			SimpleInstructionDTO instrucAddToBorderLayout = new SimpleInstructionDTO();
			ConfigComponentDTO parentConfigDTO = mapConfigAllBaliseDTO.get(parentBaliseDTO.getName());
			String methodeAddToBorderLayout = parentConfigDTO.getListAttribute().get(currentBaliseDTO.getName());
			for(BaliseDTO child:currentBaliseDTO.getListChildren()){
				String nameChild = typeNameBaliseDTO(child, configDTO).get("name");
				String typeChild = typeNameBaliseDTO(child, configDTO).get("type");
				remplirAttributs(child, nameChild, typeChild,configDTO, javaDTO);
				instancierAttribut(currentBaliseDTO,  currentName,
						 child, nameChild,  typeChild,
						 configDTO,  javaDTO,  listInstructMethodDisplay);
				
			instrucAddToBorderLayout.setInstructionContent(parentName + "."+ methodeAddToBorderLayout + "(" + nameChild + ")");
			listInstructMethodDisplay.add(instrucAddToBorderLayout);
			}
		}
		
		//**************************pour les autres paramètres d'un attribut de la classe java 
		
		Map<String, String> mapAttributValeur = currentBaliseDTO.getListAttribute();
		ConfigComponentDTO currentConfigDTO = mapConfigAllBaliseDTO.get(currentBaliseDTO.getName());
                   Map<String, String> mapXMLNameMethodName = currentConfigDTO.getListAttribute();
                   
		Set<String> clesMapAttributValeur = mapAttributValeur.keySet();
		Iterator<String> iterartClesMapAttributValeur=clesMapAttributValeur.iterator();
		
		while(iterartClesMapAttributValeur.hasNext()){
		Object attributBaliseDTO=iterartClesMapAttributValeur.next();
		String valeurAttributBaliseDTO=mapAttributValeur.get(attributBaliseDTO);
		if(attributBaliseDTO!="name"){
		SimpleInstructionDTO instruction = new SimpleInstructionDTO();
		//pour le cas du bouton , on doit ajouter à la valeur de l'attribut :,this
		String methodName= currentConfigDTO.getListAttribute().get(attributBaliseDTO);
		if(attributBaliseDTO=="action"){
		instruction.setInstructionContent(currentName+"."+methodName+"(\""+valeurAttributBaliseDTO+"\",this)");
		}
		
		else{
			instruction.setInstructionContent(currentName+"."+ methodName+"(\""+valeurAttributBaliseDTO+"\")");
		}
		//dans le cas d'un GridLayout , la fonction setRows et setCols doivent avoir en paramètres des Int donc on doit enlever
		if(attributBaliseDTO=="rows" || attributBaliseDTO=="cols"){
			instruction.setInstructionContent(currentName+"."+methodName+"("+valeurAttributBaliseDTO+")");
			}
		
		listInstructMethodDisplay.add(instruction);
		}

		}
	}
/**
 * This method provides all the code concerning the current BaliseDTO to add to the generated Java class
 * @param parentBaliseDTO :the parent DTO which is in the XML view File
 * @param parentName : the name of the parent DTO "parentBaliseDTO" ( the tag in the XML view)
 * @param currentBaliseDTO : the current DTO which is in the XML view File
 * @param currentName : the name of the current DTO "currentBaliseDTO" ( the tag in the XML view)
 * @param currentType : the type of the current DTO "currentBaliseDTO" ( the tag in the XML view)
 * @param currentChildren : the list of BaliseDTO representing the children of the current BaliseDTO
 * @param javaDTO : the DTO used to fill the java Class with the code matching with the XML view
 * @param configDTO : a DTO containing the configuration of all tags "BaliseDTO"
 * @param listInstructMethodDisplay : a list that we use to add the instructions of Instantiating 
 * and adding the current ZCComponent to the Parent Component
 */
	public void remplir(BaliseDTO parentBaliseDTO, String parentName,
			BaliseDTO currentBaliseDTO, String currentName, String currentType,
			List<BaliseDTO> currentChildren, JavaDTO javaDTO,
			ConfigDTO configDTO, List<InstructionDTO> listInstructMethodDisplay) {

		
		 

		remplirImport(currentBaliseDTO, configDTO, javaDTO);
		remplirAttributs(currentBaliseDTO, currentName, currentType, configDTO,
				javaDTO);

		remplirDisplay(parentBaliseDTO, parentName, currentBaliseDTO,
				currentName, currentType, configDTO, javaDTO,
				listInstructMethodDisplay);
		if (currentChildren != null) {
			for (BaliseDTO child : currentChildren) {
                    String childName = typeNameBaliseDTO(child, configDTO).get("name");
                    String childType = typeNameBaliseDTO(child, configDTO).get("type");
                    List<BaliseDTO> childChildren = child.getListChildren();
				remplir(currentBaliseDTO, currentName, child,childName,childType,childChildren, javaDTO, configDTO,listInstructMethodDisplay);
			}
		}
	}

	/**
	 * This method transforms a view.XML to a Java class
	 * @param configDTO : a DTO containing the configuration of all tags "BaliseDTO"
	 * * @param readerDTO : a DTO that allows reading a view.xml
	 */
	public JavaDTO transformer(ReaderDTO readerDTO, ConfigDTO configDTO) {
		
		JavaDTO javaDTO = new JavaDTO();
		
		String packName=readerDTO.getPathViewXml().replaceAll("/",".");
		
		if(packName != null && !packName.isEmpty()){
		javaDTO.setPakage(packName);}
		
		String className = readerDTO.getNameViewXml();
		javaDTO.setName(className);

		// implementation
		List<String> listImplements = javaDTO.getImplement();
		listImplements.add("ZCView");
		
		// Imports communs à toutes les classes
		  List<String> listImports = javaDTO.getListImports();
		   listImports.add("org.zerocouplage.component.api.view.ZCView");
		   listImports.add("org.zerocouplage.component.common.ZCComponentFactory");
		   listImports.add("java.io.IOException");
		   listImports.add("com.zerocouplage.common.exceptions.ZCExceptionConfig");
		   listImports.add("org.zerocouplage.component.api.exception.ZCCompNotFoundException");
		   listImports.add("java.io.IOException");
		   
		   //Annotations
		   
		   
		   
		   
		   
		BaliseDTO baliseDTORacine = readerDTO.getBaliseDTOView();
		List<BaliseDTO> racineChildren = baliseDTORacine.getListChildren();

		// ********************* Display
		

		MethodesDTO methodeDisplay = new MethodesDTO();
		methodeDisplay.setAccess("public");
		
		   //Annotations

		methodeDisplay.getAnnotations().add("@Override");

		//Exceptions
		methodeDisplay.getExceptions().add("IOException");
		methodeDisplay.getExceptions().add("ZCExceptionConfig");
		methodeDisplay.getExceptions().add("ZCCompNotFoundException");
		methodeDisplay.getExceptions().add("Exception");
		
		//Nom de la méthode
		methodeDisplay.setNom("display");
		
		//Retour de la méthode Display()
		methodeDisplay.setReturnType("ZCPage");
		
		BlocInstructionDTO blocInstruDTODisplay = new BlocInstructionDTO();
		methodeDisplay.setCorpsMethode(blocInstruDTODisplay);
		BlocInstructionDTO corpsDisplay = methodeDisplay.getCorpsMethode();
		List<InstructionDTO> ListInstruDisplay = corpsDisplay.getListInstructions();
		
		Map<String, String> mapTypeNameRacine = typeNameBaliseDTO(baliseDTORacine,configDTO);
		String racineName = mapTypeNameRacine.get("name");
		String racineType = mapTypeNameRacine.get("type");
		//baliseDTORacine.getName() => ceci est le nom de la balise XML!à ne pas confondre avec le nom du composant(attribut name de la balise XML)

		//appel de la méthode remplir pour la racine
		remplir(null,"", baliseDTORacine, racineName,racineType, racineChildren, javaDTO, configDTO, ListInstruDisplay);
		SimpleInstructionDTO instrucRetour = new SimpleInstructionDTO();
		instrucRetour.setInstructionContent("return " + racineName);
		ListInstruDisplay.add(instrucRetour);
		List<MethodesDTO> javaDTOlistMethodes = javaDTO.getListMethodes();
		javaDTOlistMethodes.add(methodeDisplay);

		return javaDTO;
	}

}
