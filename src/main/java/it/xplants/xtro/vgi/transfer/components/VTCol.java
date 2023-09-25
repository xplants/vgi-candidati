package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXEOControlUtilities;
import it.xplants.xtro.XTContainer;
import it.xplants.xtro.utilities.XTS;

public class VTCol extends VTBaseComponent {

	public VTCol(WOContext context) {
		super(context);
	}

	public String liClass() {

		boolean hasChildren = currentContainer().containers().count() > 0;
		boolean isActive = ERXEOControlUtilities.eoEquals(selectedContainer(), currentContainer());
		boolean inPath = false;
		if (!isActive && selectedContainer() != null) {
			NSArray<XTContainer> path = selectedContainer().thePath();
			for (XTContainer c : path) {
				inPath = ERXEOControlUtilities.eoEquals(c, currentContainer());
				if (inPath)
					break;
			}
		}

		return ((hasChildren ? "has-children " : "") + (isActive ? "active " : "") + (inPath ? "in-path " : "")).trim();

	}

	public boolean hasChildren() {
		return container() != null && container().containers().count() > 0;
	}

	private NSArray<XTContainer> theSections;

	public NSArray<XTContainer> theSections() {
		if (theSections == null) {
			theSections = XTS.titleAscs().sorted((NSArray<XTContainer>) c().children().section());
		}
		return theSections;
	}

	private NSArray<XTContainer> theFiles;

	public NSArray<XTContainer> theFiles() {
		if (theFiles == null) {
			theFiles = XTS.titleAscs().sorted((NSArray<XTContainer>) c().children().file());
		}
		return theFiles;
	}
}