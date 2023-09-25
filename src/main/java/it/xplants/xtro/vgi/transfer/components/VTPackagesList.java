package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXDisplayGroup;
import er.extensions.eof.ERXQ;
import it.xplants.xtro.vgi.transfer.eo.VTPackage;

public class VTPackagesList extends VTBaseComponent {
	public ERXDisplayGroup displayGroup;
	public VTPackage currentRecord;

	public VTPackagesList(WOContext context) {
		super(context);
	}

	public String q;

	public WOActionResults searchAction() {
		EOQualifier textSearchQualifier = null;
		if (q != null) {
			textSearchQualifier = VTPackage.THE_NAME.contains(q);
		}

		EOQualifier qualifier = VTPackage.DATE_DELETION.isNull();
		if (textSearchQualifier != null) {
			qualifier = ERXQ.and(qualifier, textSearchQualifier);
		}
		displayGroup.setObjectArray(VTPackage.fetchVTPackages(session().defaultEditingContext(), qualifier, displayGroup.sortOrderings()));
		return null;

	}

	public WOActionResults deleteAction() {
		currentRecord.setDateDeletion(new NSTimestamp());
		currentRecord.setTheUserDeleterRelationship(user().localInstanceIn(currentRecord.editingContext()));
		currentRecord.editingContext().saveChanges();
		return searchAction();
	}
}