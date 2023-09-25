package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOContext;

import it.xplants.xtro.steam.appserver.SteamBaseWrapper;

public class VTWrapper extends SteamBaseWrapper {
	public String title;

	public VTWrapper(WOContext context) {
		super(context);
	}

	public String errorMessage() {
		return request().stringFormValueForKey("error-message");
	}

}