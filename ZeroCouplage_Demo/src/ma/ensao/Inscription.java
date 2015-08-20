package ma.ensao;

import org.zerocouplage.component.api.view.ZCView;
import org.zerocouplage.component.common.ZCComponentFactory;
import java.io.IOException;
import com.zerocouplage.common.exceptions.ZCExceptionConfig;
import org.zerocouplage.component.api.exception.ZCCompNotFoundException;
import java.io.IOException;
import org.zerocouplage.component.api.page.ZCPage;
import org.zerocouplage.component.api.layout.ZCGridLayout;
import org.zerocouplage.component.api.component.ZCLabel;
import org.zerocouplage.component.api.component.ZCButton;

public class Inscription implements ZCView { 
	private ZCPage page;
	private ZCGridLayout layout;
	private ZCLabel labelOfLogin;
	private ZCButton valider;
	private ZCButton reset;

	@Override
	public ZCPage display() throws IOException, ZCExceptionConfig, ZCCompNotFoundException, Exception	{
		page =(ZCPage) ZCComponentFactory.newComponent(ZCPage.class);
		page.setName("inscription");
		page.setTitle("Inscription");
		layout =(ZCGridLayout) ZCComponentFactory.newComponent(ZCGridLayout.class);
		page.setBody(layout);
		layout.setName("sign in");
		layout.setRows(4);
		layout.setCols(1);
		labelOfLogin =(ZCLabel) ZCComponentFactory.newComponent(ZCLabel.class);
		layout.addComponent(labelOfLogin);
		labelOfLogin.setLabel("Entrer Login :");
		valider =(ZCButton) ZCComponentFactory.newComponent(ZCButton.class);
		layout.addComponent(valider);
		valider.setAction("goTraitementInscription",this);
		valider.setText("Valider");
		reset =(ZCButton) ZCComponentFactory.newComponent(ZCButton.class);
		layout.addComponent(reset);
		reset.setAction("goInscription",this);
		reset.setText("Valider");
		return page;
	}



}
