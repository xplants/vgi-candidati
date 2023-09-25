package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOContext;

import it.xplants.xtro.vgi.transfer.eo.VTDelivery;

public class VTViewDelivery extends VTBaseComponent {
	public VTDelivery selectedRecord;

	public VTViewDelivery(WOContext context) {
		super(context);
	}
}