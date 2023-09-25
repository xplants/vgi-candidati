package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.appserver.ERXRedirect;
import it.xplants.xtro.XTContainer;
import it.xplants.xtro.extensions.XTClass;

public class VTFoldersManagement extends VTBaseComponent {
	public VTFoldersManagement(WOContext context) {
		super(context);
	}

	public XTContainer selectedRecord = null;

	public XTContainer selectedRecord() {
		if (selectedRecord == null) {
			if (context().request().stringFormValueForKey("id-selected") != null) {
				selectedRecord = XTContainer.fetchXTContainer(editingContext(), context().request().stringFormValueForKey("id-selected"));
			}
		}
		return selectedRecord;
	}

	public String newTitle;
	public Integer idParent;
	private NSArray<XTContainer> theSelectedPath;

	public NSArray<XTContainer> theSelectedPath() {
		if (theSelectedPath == null) {
			if (selectedRecord() != null) {
				NSMutableArray<XTContainer> path = selectedRecord().thePath().mutableClone();
				path.remove(0);
				theSelectedPath = path.immutableClone();
			} else {
				theSelectedPath = new NSArray<>(container());
			}
		}
		return theSelectedPath;
	}

	public WOActionResults addSectionAction() {
		XTContainer parent = container();
		if (idParent != null) {
			parent = XTContainer.fetchXTContainer(container().editingContext(), idParent);
		}
		XTContainer subsection = parent.createContainer(XTClass.SECTION, "Center", true, user());
		subsection.mainLanguageTitle().setTheContent(newTitle);
		subsection.editingContext().saveChanges();
		return redirectDefaultAction();
	}

	public WOActionResults redirectDefaultAction() {
		NSMutableDictionary<String, Object> queryParameters = new NSMutableDictionary<>();
		queryParameters.setObjectForKey(container().idXTContainer(), "id");
		if (hasSession()) {
			queryParameters.setObjectForKey(session().sessionID(), "wosid");
		}
		if (selectedRecord() != null) {
			queryParameters.setObjectForKey(selectedRecord().primaryKey(), "id-selected");

		}
		ERXRedirect r = pageWithName(ERXRedirect.class);
		r.setDirectActionClass("VTAction");
		r.setDirectActionName("default");
		r.setQueryParameters(queryParameters);
		r.setIncludeSessionID(hasSession());
		r.setSecure(context().secureMode());
		return r;
	}
}