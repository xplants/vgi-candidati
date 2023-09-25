package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;

import er.extensions.eof.ERXEC;
import it.xplants.xtro.vgi.transfer.eo.VTAddressee;

public class VTAddresseeEditor extends VTBaseComponent {
	public VTAddresseeEditor(WOContext context) {
		super(context);
	}

	private EOEditingContext editorEC;

	public EOEditingContext editorEC() {
		if (editorEC == null) {
			editorEC = ERXEC.newEditingContext();
		}
		return editorEC;
	}

	private VTAddressee selectedRecord;

	public VTAddressee selectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(VTAddressee selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public WOActionResults saveChangesAction() {
		editorEC().saveChanges();
		return null;
	}

	public WOActionResults prepareDeliveryAction() {
		VTPrepareDelivery page = getPage(VTPrepareDelivery.class, container());
		page.selectedAddressee = selectedRecord();
		return page;
	}

	public String provincePattern() {
		return "[A-Z]{2}";
	}
}