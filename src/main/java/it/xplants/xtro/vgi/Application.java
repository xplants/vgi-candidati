// Generated by the WOLips Templateengine Plug-in at 14-gen-2014 11.13.54
package it.xplants.xtro.vgi;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver.WOSession;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSNotification;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXApplication;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXQ;
import er.extensions.foundation.ERXPatcher;
import it.xplants.xtro.XTContainer;
import it.xplants.xtro.XTContainerAction;
import it.xplants.xtro.XTUser;
import it.xplants.xtro.appserver.XTApplication;
import it.xplants.xtro.extensions.XTClass;
import it.xplants.xtro.utilities.XTQ;
import it.xplants.xtro.vgi.components.Main;

public class Application extends XTApplication {
	public static void main(String[] argv) {
		ERXApplication.main(argv, Application.class);
	}

	public Application() {
		setAllowsConcurrentRequestHandling(true);
//		NSSelector<Object> selector = new NSSelector<Object>("unsubscribeNewsletter", new Class[] { NSNotification.class });
//		NSNotificationCenter.defaultCenter().addObserver(this, selector, XTUser.NewsletterUnsubscribeNotificationManager, null);
//		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance(Locale.ITALIAN);
//		gc.add(GregorianCalendar.MINUTE, 5);
//		Date firstTime = gc.getTime();
//		Timer r = new Timer(true);
//		r.scheduleAtFixedRate(new VWSendFormRequest(), firstTime, 5 * 60 * 1000);
		
//		Timer t = new Timer(true);
//		t.schedule(new VTDeliveryExpirationCheckTask(), 5 * 1000, 24 * 60 * 60 * 1000);

//		archiveEvents();
	}

	@Override
	protected Class<? extends WOSession> _sessionClass() {
		return Session.class;
	}

	@Override
	public void installPatches() {
		super.installPatches();
		ERXPatcher.setClassForName(Main.class, "Main");
	}

	public void unsubscribeNewsletter(NSNotification not) {
		((XTUser) not.object()).setExtraField020("Send");
	}

	@Override
	public Boolean enabledModuleNewsletter() {
		return true;
	}

	public static void archiveEvents() {

		EOEditingContext ec = ERXEC.newEditingContext();
		XTUser user = XTUser.fetchXTUser(ec, "martymac");
		XTContainer newsIta = XTContainer.containerWithBehaviour(ec, "news");
		XTContainer newsEng = XTContainer.containerWithBehaviour(ec, "news-en");
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
		gc.add(GregorianCalendar.YEAR, -1);
		gc.set(GregorianCalendar.DAY_OF_YEAR, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));

		NSTimestamp maxDate = new NSTimestamp(gc.getTime());

		EOQualifier qualifier = ERXQ.and(XTQ.ContentsQualifier(), XTQ.DefaultPositionQualifier(), XTContainer.THE_PUBLISHED_DATE.lessThanOrEqualTo(maxDate));
		NSArray<XTContainer> newsListIta = newsIta.containers(qualifier);
		NSArray<XTContainer> newsListEng = newsEng.containers(qualifier);

		NSMutableDictionary<Integer, XTContainer> itaYearsSections = new NSMutableDictionary<Integer, XTContainer>();

		for (XTContainer c : newsListIta) {
			GregorianCalendar dd = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
			dd.setTime(c.thePublishedDate());
			Integer year = dd.get(GregorianCalendar.YEAR);
			XTContainer yearSection = itaYearsSections.objectForKey(year);
			if (yearSection == null) {
				yearSection = newsIta.createContainer(XTClass.SECTION, "Archive", true, user);
				yearSection.setTheDirectAction("viewNewsList");
				yearSection.mainLanguageTitle().setTheContent(year.toString());
				itaYearsSections.setObjectForKey(yearSection, year);
			}
			NSArray<XTContainerAction> actions = c.theContainerActions(XTContainerAction.FLAG_AUTOGENERATED.isTrue());
			for (XTContainerAction a : actions) {
				a.setFlagAutogenerated(false);
			}
			c.setTheBehaviour("news-archived");
			c.moveTo(yearSection);
			ec.saveChanges();
		}

		NSMutableDictionary<Integer, XTContainer> engYearsSections = new NSMutableDictionary<Integer, XTContainer>();

		for (XTContainer c : newsListEng) {
			GregorianCalendar dd = (GregorianCalendar) Calendar.getInstance(Locale.ITALY);
			dd.setTime(c.thePublishedDate());
			Integer year = dd.get(GregorianCalendar.YEAR);
			XTContainer yearSection = engYearsSections.objectForKey(year);
			if (yearSection == null) {
				yearSection = newsEng.createContainer(XTClass.SECTION, "Archive", true, user);
				yearSection.setFlagLayoutPosition("Archive");
				yearSection.mainLanguageTitle().setTheContent(year.toString());
				engYearsSections.setObjectForKey(yearSection, year);
			}
			NSArray<XTContainerAction> actions = c.theContainerActions(XTContainerAction.FLAG_AUTOGENERATED.isTrue());
			for (XTContainerAction a : actions) {
				a.setFlagAutogenerated(false);
			}
			c.setTheBehaviour("news-archived-en");
			c.moveTo(yearSection);
			ec.saveChanges();
		}

		ec.dispose();
	}

	@Override
	public WOResponse handleException(Exception e, WOContext arg1) {
		e.printStackTrace();
		return super.handleException(e, arg1);
	}

}