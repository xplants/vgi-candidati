package it.xplants.xtro.vgi.transfer.components;

import org.json.JSONArray;
import org.json.JSONException;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEOControlUtilities;
import it.xplants.xtro.XTContainer;
import it.xplants.xtro.vgi.transfer.eo.VTPackage;

public class VTPackageEditor extends VTBaseComponent {
	public VTPackageEditor(WOContext context) {
		super(context);
	}

	private EOEditingContext editorEC;

	public EOEditingContext editorEC() {
		if (editorEC == null) {
			editorEC = ERXEC.newEditingContext();
		}
		return editorEC;
	}

	private VTPackage selectedRecord;

	public VTPackage selectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(VTPackage selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public WOActionResults saveChangesAction() {

		try {
			NSArray<XTContainer> containers = selectedRecord.theContainers().immutableClone();
			for (XTContainer xtContainer : containers) {
				selectedRecord.removeFromTheContainersRelationship(xtContainer);
			}
			JSONArray a = new JSONArray(theContainersJSONArray());
			for (int i = 0; i < a.length(); i++) {
				int id = -1;
				try {
					String _id = a.getString(i);
					_id = _id.split("-")[1];
					id = Integer.parseInt(_id);
				} catch (JSONException en) {
					en.printStackTrace();
				}
				if (id > 0) {
					try {
						XTContainer c = XTContainer.fetchXTContainer(editorEC(), id);
						if (c != null)
							selectedRecord.addToTheContainersRelationship(c);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		selectedRecord.editingContext().saveChanges();
		return null;
	}

	@Override
	public void flushCurrentContainerCaches() {
		draggableElementClasses = null;
		super.flushCurrentContainerCaches();
	}

	@Override
	public void flushCurrentSubContainerCaches() {
		draggableSubElementClasses = null;
		super.flushCurrentSubContainerCaches();
	}

	private String draggableElementClasses = null;

	public String draggableElementClasses() {
		if (draggableElementClasses == null) {
			draggableElementClasses = "draggable";
			NSArray<XTContainer> array = selectedRecord().theContainers();
			boolean presente = false;
			for (XTContainer c : array) {
				if (ERXEOControlUtilities.eoEquals(currentContainer(), c)) {
					presente = true;
					break;
				}
			}
			if (presente) {
				draggableElementClasses = draggableElementClasses + " in-set";
			} else {
				draggableElementClasses = draggableElementClasses + " out-set";
			}

		}
		return draggableElementClasses;
	}

	private String draggableSubElementClasses = null;

	public String draggableSubElementClasses() {
		if (draggableSubElementClasses == null) {
			draggableSubElementClasses = "draggable";
			NSArray<XTContainer> array = selectedRecord().theContainers();
			boolean presente = false;
			for (XTContainer c : array) {
				if (ERXEOControlUtilities.eoEquals(currentSubContainer(), c)) {
					presente = true;
					break;
				}
			}
			if (presente) {
				draggableSubElementClasses = draggableSubElementClasses + " in-set";
			} else {
				draggableSubElementClasses = draggableSubElementClasses + " out-set";
			}

		}
		return draggableSubElementClasses;
	}

	private String theContainersJSONArray;

	public String theContainersJSONArray() {
		if (theContainersJSONArray == null) {
			JSONArray _theContainersJSONArray = new JSONArray();
			NSArray<XTContainer> array = selectedRecord().theContainers();
			for (XTContainer c : array) {
				_theContainersJSONArray.put(c.thePathLevel() + "-" + c.idXTContainer());
			}
			theContainersJSONArray = _theContainersJSONArray.toString();
		}
		return theContainersJSONArray;
	}

	public void setTheContainersJSONArray(String theContainersJSONArray) {
		this.theContainersJSONArray = theContainersJSONArray;
	}

	public boolean ccHasSubContainers() {
		return cc().children().section() != null || cc().children().file() != null;
	}

	public WOActionResults prepareDeliveryAction() {
		VTPrepareDelivery page = getPage(VTPrepareDelivery.class, container());
		page.setSelectedRecord(selectedRecord());
		return page;
	}

}