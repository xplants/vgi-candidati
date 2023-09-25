package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import it.xplants.xtro.vgi.transfer.eo.VTAddressee;
import it.xplants.xtro.vgi.transfer.eo.VTPackage;

public class VTPrepareDelivery extends VTPackageEditor {
	private NSArray<VTAddressee> theAddresees;
	private NSArray<VTPackage> thePackages;
	public VTAddressee currentAddressee;
	public VTPackage currentPackage;
	public VTAddressee selectedAddressee;

	public VTPrepareDelivery(WOContext context) {
		super(context);
	}

	public NSArray<VTAddressee> theAddresees() {
		if (theAddresees == null) {
			EOEditingContext ec = selectedRecord().editingContext();
			theAddresees = VTAddressee.fetchVTAddressees(ec, VTAddressee.DATE_DELETION.isNull(), VTAddressee.THE_COMPANY_NAME.ascInsensitives());
		}
		return theAddresees;
	}

	public NSArray<VTPackage> thePackages() {
		if (thePackages == null) {
			EOEditingContext ec = selectedAddressee.editingContext();
			thePackages = VTPackage.fetchVTPackages(ec, VTPackage.DATE_DELETION.isNull(), VTPackage.THE_NAME.ascInsensitives());
		}
		return thePackages;
	}
}