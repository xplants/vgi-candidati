package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXDisplayGroup;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXQ;
import it.xplants.xtro.XTUser;
import it.xplants.xtro.utilities.XTLoggerUtilities;
import it.xplants.xtro.vgi.Session;

public class VTUsersList extends VTBaseComponent {
	public ERXDisplayGroup displayGroup;
	public XTUser currentRecord, selectedRecord;
	private EOQualifier baseQualifier = XTUser.EXTRA_FIELD020.is("VTREPOSITORY").and(XTUser.FLAG_ENABLED.is(1), XTUser.FLAG_ACCESS_LEVEL.is(XTUser.LEVEL_EDITOR_USER), XTUser.FLAG_HIDDEN.isTrue());

	public VTUsersList(WOContext context) {
		super(context);
	}

	public String newUsername, newPassword, newName, newSurname, newEmail, newPasswordConfirmation;

	public WOActionResults createUserAction() {
		EOEditingContext ec = ERXEC.newEditingContext();
		XTUser newUser = XTUser.createActiveUser(ec, newUsername, newPassword);
		newUser.setFlagAccessLevel(XTUser.LEVEL_EDITOR_USER);
		newUser.setFlagAccessBackend(0);
		newUser.setFlagSubscribeNewsletter(false);
		newUser.setFlagHidden(true);
		newUser.setTheDateRegistration(new NSTimestamp());
		newUser.setExtraField020("VTREPOSITORY");
		newUser.setTheName(newName);
		newUser.setTheSurname(newSurname);
		ec.saveChanges();
		
		XTLoggerUtilities.getInstance().logOperation(null, "Creazione utente " + newUsername, ((Session) session()).xtro().endUser().theUsername() + " ha creato una nuova utenza", XTUser.class, newUser.idXTUser());
	
		ec.dispose();

		newUsername = null;
		newPassword = null;
		newName = null;
		newSurname = null;
		newEmail = null;
		newPasswordConfirmation = null;

		return searchAction();
	}

	public WOActionResults searchAction() {
		EOQualifier qualifier = ERXQ.and(baseQualifier, XTUser.THE_USERNAME.isNot(user().theUsername()));
		displayGroup.setObjectArray(XTUser.fetchXTUsers(session().defaultEditingContext(), qualifier, XTUser.THE_USERNAME.ascInsensitives()));
		return null;
	}

	public WOActionResults deleteAction() {
		currentRecord.setTheEmail(null);
		currentRecord.setTheUsername(currentRecord.theUsername() + "-disabled");
		currentRecord.setFlagEnabled(0);
		session().defaultEditingContext().saveChanges();
		
		
		XTLoggerUtilities.getInstance().logOperation(null, "Cancellazione utente " + currentRecord.theUsername(), ((Session) session()).xtro().endUser().theUsername() + " ha creato eliminato una utenza", XTUser.class, currentRecord.idXTUser());

		
		return searchAction();
	}
}