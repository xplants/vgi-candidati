package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.appserver.ERXWOContext;
import er.extensions.eof.ERXEC;
import it.xplants.xtro.utilities.XTWOUtilities;
import it.xplants.xtro.vgi.transfer.eo.VTAddressee;
import it.xplants.xtro.vgi.transfer.eo.VTDelivery;

public class VTDeliveryEditor extends VTBaseComponent {
	public VTDeliveryEditor(WOContext context) {
		super(context);
	}

	private VTDelivery selectedRecord;

	public VTDelivery selectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(VTDelivery selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	private EOEditingContext editorEC;
	private String link;
	public VTAddressee currentAddresee;

	public EOEditingContext editorEC() {
		if (editorEC == null) {
			editorEC = ERXEC.newEditingContext();
		}
		return editorEC;
	}

	public WOActionResults saveChangesAction() {
		editorEC().saveChanges();
		return null;
	}

	/**
	 * @return the link
	 */
	public String link() {
		if (link == null) {
			NSMutableDictionary<String, Object> dict = new NSMutableDictionary<>();
			dict.setObjectForKey(selectedRecord().primaryKey(), "code");
			link = XTWOUtilities.protocol(request()) + XTWOUtilities.host(request()) + ERXWOContext.directActionUrl(context(), "VTAction/view", dict, XTWOUtilities.secure(request()), false);
		}
		return link;
	}
}