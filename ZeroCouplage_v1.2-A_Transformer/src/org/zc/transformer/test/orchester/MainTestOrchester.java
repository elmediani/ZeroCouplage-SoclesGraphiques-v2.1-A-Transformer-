package org.zc.transformer.test.orchester;



import java.io.File;

import org.zc.transformer.impl.orchester.ZcOrchester;


public class MainTestOrchester {

	public static void main(String[] args) {
		
		
		ZcOrchester zcOrchester = new ZcOrchester();

		File pathview = new File("D:/ENSAO/GI4_S2/PFA/ZC_Projects/ZeroCouplageTransformer/ressourcesViews/ma/ensao/");

		zcOrchester.ochester(pathview , "D://ENSAO//GI4_S2//PFA//ZC_Projects//ZCAppsDemo//src");
				
	}

}
