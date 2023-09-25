package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOContext;

import it.xplants.xtro.appserver.admin.tools.ContainerData;
import it.xplants.xtro.vgi.transfer.eo.VTPackage;

public class VTEditor extends VTBaseComponent {
	public VTEditor(WOContext context) {
		super(context);
	}

	private ContainerData data;

	public ContainerData data() {
		if (data == null) {
			data = new ContainerData(container(), languageCode());
		}
		return data;
	}

	public String placeholderName() {
		return container().isFile() ? "Nome documento" : "Nome cartella";
	}

	public long containerSize() {
		return VTPackage.containerSize(container(), languageCode());
	}
}