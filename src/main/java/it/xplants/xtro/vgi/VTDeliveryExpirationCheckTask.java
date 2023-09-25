package it.xplants.xtro.vgi;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.concurrency.ERXTimerTask;
import er.javamail.ERMailDeliveryPlainText;
import it.xplants.xtro.vgi.transfer.eo.VTDelivery;

public class VTDeliveryExpirationCheckTask extends ERXTimerTask {

	private static final Logger log = Logger.getLogger(VTDeliveryExpirationCheckTask.class);

	public VTDeliveryExpirationCheckTask() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void _run() {
		log.info("run");
		// TODO Auto-generated method stub
		EOEditingContext ec = newEditingContext();
		try {
			NSTimestamp adesso = new NSTimestamp();
			GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance(Locale.ITALY);
			gc.add(GregorianCalendar.DAY_OF_YEAR, 7);
			EOQualifier qualifier = VTDelivery.DATE_EXPIRATION.lte(new NSTimestamp(gc.getTime())).and(VTDelivery.DATE_DELETION.isNull(), VTDelivery.DATE_EXPIRATION.gte(adesso));
			NSArray<VTDelivery> deliveries = VTDelivery.fetchVTDeliveries(ec, qualifier, VTDelivery.DATE_CREATION.descs());
			log.info("deliveries " + deliveries.count());
			for (VTDelivery d : deliveries) {
				int dayDiff = DateTools.dayDiff(adesso, d.dateExpiration());
				if (d.theAddressees().count() > 0 && d.thePackage() != null) {
					String nomePacchetto = d.thePackage().theName();
					String ragioneSociale = d.theAddressees().objectAtIndex(0).theCompanyName();
					String subject = dayDiff + " giorn" + (dayDiff > 1 ? "i" : "o") + " scadenza invio " + ragioneSociale;
					String text = "Gentile utente, l'accesso al pacchetto " + nomePacchetto + " destinato a " + ragioneSociale + " scadrà il giorno " + new SimpleDateFormat("dd/MM/yyyy").format(d.dateExpiration());
					ERMailDeliveryPlainText mail = new ERMailDeliveryPlainText();
					try {
						mail.setSubject(subject);
						mail.setTextContent(text);
						mail.setFromAddress("noreply@xpl.io");
						mail.setToAddress("martino@xplants.it");
						mail.sendMail();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} finally {
			ec.dispose();
		}
	}

}
