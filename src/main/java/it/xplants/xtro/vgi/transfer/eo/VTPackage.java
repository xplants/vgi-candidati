package it.xplants.xtro.vgi.transfer.eo;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.xplants.xtro.XTContainer;
import it.xplants.xtro.appserver.tools.AppSupport;
import it.xplants.xtro.helper.XTContainerHelper;

public class VTPackage extends _VTPackage {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(VTPackage.class);

	private Long theSize;

	public Long theSize() {
		if (theSize == null) {
			theSize = 0l;
			for (XTContainer c : theContainers()) {
				long cs = containerSize(c, AppSupport.MainLanguageCode());
				theSize = theSize + cs;
			}
		}
		return theSize;
	}

	public static long containerSize(XTContainer c, String languageCode) {
		if (c.isMultimedia() || c.isFile()) {
			return XTContainer.FileSize(c, languageCode);
		} else {
			String path = c.theFolderPath() + File.separator + c.primaryKey();

			File f = new File(path);
			return XTContainerHelper.folderSize(f);
		}
	}
}
