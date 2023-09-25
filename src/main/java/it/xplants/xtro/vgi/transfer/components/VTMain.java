package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXFetchSpecification;
import it.xplants.xtro.vgi.transfer.eo.VTAddressee;
import it.xplants.xtro.vgi.transfer.eo.VTDelivery;
import it.xplants.xtro.vgi.transfer.eo.VTLog;
import it.xplants.xtro.vgi.transfer.eo.VTPackage;

public class VTMain extends VTBaseComponent {
	public VTMain(WOContext context) {
		super(context);
	}

	private NSArray<VTPackage> theLatestPackages;
	public VTPackage currentPackage;
	private NSArray<VTDelivery> theLatestDeliveries;
	public VTDelivery currentDelivery;
	private NSArray<VTLog> theLatestLogs;
	public VTLog currentLog;
	private NSArray<VTAddressee> theLatestAddresees;
	public VTAddressee currentAddresee;

	public NSArray<VTDelivery> theLatestDeliveries() {
		if (theLatestDeliveries == null) {
			ERXFetchSpecification<VTDelivery> fs = new ERXFetchSpecification<>(VTDelivery.ENTITY_NAME, VTDelivery.DATE_DELETION.isNull(), VTDelivery.DATE_CREATION.descs());
			fs.setFetchLimit(5);
			theLatestDeliveries = fs.fetchObjects(session().defaultEditingContext());
		}
		return theLatestDeliveries;
	}

	public NSArray<VTLog> theLatestLogs() {
		if (theLatestLogs == null) {
			ERXFetchSpecification<VTLog> fs = new ERXFetchSpecification<>(VTLog.ENTITY_NAME, null, VTLog.DATE_CREATION.descs());
			fs.setFetchLimit(5);
			theLatestLogs = fs.fetchObjects(session().defaultEditingContext());
		}
		return theLatestLogs;
	}

	public NSArray<VTPackage> theLatestPackages() {
		if (theLatestPackages == null) {
			ERXFetchSpecification<VTPackage> fs = new ERXFetchSpecification<>(VTPackage.ENTITY_NAME, VTPackage.DATE_DELETION.isNull(), VTPackage.DATE_CREATION.descs());
			fs.setFetchLimit(5);
			theLatestPackages = fs.fetchObjects(session().defaultEditingContext());
		}
		return theLatestPackages;
	}

	public NSArray<VTAddressee> theLatestAddresees() {
		if (theLatestAddresees == null) {
			ERXFetchSpecification<VTAddressee> fs = new ERXFetchSpecification<>(VTAddressee.ENTITY_NAME, VTAddressee.DATE_DELETION.isNull(), VTAddressee.DATE_CREATION.descs());
			fs.setFetchLimit(5);
			theLatestAddresees = fs.fetchObjects(session().defaultEditingContext());
		}
		return theLatestAddresees;
	}
}