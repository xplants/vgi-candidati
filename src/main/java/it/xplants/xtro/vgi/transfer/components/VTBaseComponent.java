package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOContext;

import it.xplants.xtro.steam.appserver.SteamBaseComponent;
import it.xplants.xtro.vgi.transfer.eo.VTPackage;

public class VTBaseComponent extends SteamBaseComponent {

	public VTBaseComponent(WOContext context) {
		super(context);
	}

	public Long cSize() {
		return VTPackage.containerSize(container(), languageCode());
	}

	public Long ccSize() {
		return VTPackage.containerSize(currentContainer(), languageCode());
	}

	public Long cscSize() {
		return VTPackage.containerSize(currentSubContainer(), languageCode());
	}

	public boolean hasUnsavedChanges;

}
