package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXDisplayGroup;
import er.extensions.eof.ERXQ;
import it.xplants.xtro.vgi.transfer.eo.VTAddressee;

public class VTAddresseesList extends VTBaseComponent {
	public ERXDisplayGroup displayGroup;
	public VTAddressee currentRecord;

	public VTAddresseesList(WOContext context) {
		super(context);
	}

	public String q;

	public WOActionResults searchAction() {
		EOQualifier textSearchQualifier = null;
		if (q != null) {
			textSearchQualifier = ERXQ.or(VTAddressee.THE_NAME.contains(q), VTAddressee.THE_SURNAME.contains(q), VTAddressee.THE_COMPANY_NAME.contains(q), VTAddressee.THE_EMAILS.contains(q));
		}

		EOQualifier qualifier = VTAddressee.DATE_DELETION.isNull();
		if (textSearchQualifier != null) {
			qualifier = ERXQ.and(qualifier, textSearchQualifier);
		}
		displayGroup.setObjectArray(VTAddressee.fetchVTAddressees(session().defaultEditingContext(), qualifier, displayGroup.sortOrderings()));
		return null;

	}

	public WOActionResults deleteAction() {
		currentRecord.setDateDeletion(new NSTimestamp());
		currentRecord.setTheUserDeleterRelationship(user().localInstanceIn(currentRecord.editingContext()));
		currentRecord.editingContext().saveChanges();

		return searchAction();
	}

}