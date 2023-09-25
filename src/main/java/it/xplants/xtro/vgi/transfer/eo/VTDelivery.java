package it.xplants.xtro.vgi.transfer.eo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSTimestamp;

import it.xplants.xtro.XTUser;

public class VTDelivery extends _VTDelivery {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(VTDelivery.class);

	public static VTDelivery createVTDelivery(EOEditingContext editingContext, String username) {
		VTDelivery eo = (VTDelivery) EOUtilities.createAndInsertInstance(editingContext, _VTDelivery.ENTITY_NAME);
		eo.setDateCreation(new NSTimestamp());
		Date dateExpiration = DateUtils.addWeeks(eo.dateCreation(), 2);
		eo.setDateExpiration(new NSTimestamp(dateExpiration));
		UUID id = UUID.randomUUID();
		eo.setId(id.toString());
		eo.setTheUserCreator(XTUser.fetchXTUser(editingContext, username));
		VTLog log = VTLog.createVTLog(editingContext, eo.dateCreation(), "Creato", username);
		log.setTheDeliveryRelationship(eo);
		log.setTheDescription("creazione pacchetto " + id);
		return eo;
	}

	public boolean isExpired() {
		GregorianCalendar g = (GregorianCalendar) GregorianCalendar.getInstance(Locale.ITALY);
		g.add(Calendar.DAY_OF_YEAR, -1);
		g.set(Calendar.HOUR_OF_DAY, 23);
		g.set(Calendar.MINUTE, 59);
		g.set(Calendar.SECOND, 59);

		return dateExpiration() != null && dateExpiration().before(g.getTime());
	}

}
