package it.xplants.xtro.vgi.transfer.components;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXDisplayGroup;
import er.extensions.eof.ERXQ;
import it.xplants.xtro.vgi.transfer.eo.VTAddressee;
import it.xplants.xtro.vgi.transfer.eo.VTDelivery;

public class VTDeliveriesList extends VTBaseComponent {
	public ERXDisplayGroup displayGroup;
	public VTDelivery currentRecord;
	public VTAddressee currentAddresee;

	public VTDeliveriesList(WOContext context) {
		super(context);
	}

	public boolean qShowExpired = true;

	public WOActionResults searchAction() {
		EOQualifier qualifier = VTDelivery.DATE_DELETION.isNull();
		if (!qShowExpired) {
			GregorianCalendar g = (GregorianCalendar) GregorianCalendar.getInstance(Locale.ITALY);
			g.add(Calendar.DAY_OF_YEAR, -1);
			g.set(Calendar.HOUR_OF_DAY, 23);
			g.set(Calendar.MINUTE, 59);
			g.set(Calendar.SECOND, 59);
			qualifier = ERXQ.and(VTDelivery.DATE_EXPIRATION.after(new NSTimestamp(g.getTime())));
		}

		displayGroup.setObjectArray(VTDelivery.fetchVTDeliveries(session().defaultEditingContext(), qualifier, sortOrderings));

		return null;

	}

	public WOActionResults deleteAction() {
		currentRecord.setDateDeletion(new NSTimestamp());
		currentRecord.setTheUserDeleter(user().localInstanceIn(currentRecord.editingContext()));
		currentRecord.editingContext().saveChanges();
		return searchAction();
	}
}