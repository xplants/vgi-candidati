package it.xplants.xtro.vgi.transfer.eo;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VTAddressee extends _VTAddressee {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(VTAddressee.class);

	public String theEmailsString() {
		String theEmailsStrings = "";
		String theEmails = theEmails();
		if (theEmails == null) {
			theEmails = "[]";
		}
		try {
			JSONArray array = new JSONArray(theEmails);
			theEmails = "";
			for (int i = 0; i < array.length(); i++) {
				String email = array.getString(i);
				theEmailsStrings = theEmailsStrings + (theEmailsStrings.length() > 0 ? ", " : "") + email;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theEmailsStrings;
	}
}
