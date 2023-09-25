package it.xplants.xtro.vgi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.appserver.ERXRedirect;
import er.extensions.appserver.ERXResponse;
import er.extensions.appserver.ERXWOContext;
import er.extensions.eof.ERXEC;
import it.xplants.xtro.XTContainer;
import it.xplants.xtro.XTContainerAccessDeniedException;
import it.xplants.xtro.XTContainerNotFoundException;
import it.xplants.xtro.appserver.XTDirectAction;
import it.xplants.xtro.appserver.admin.tools.ContainerData;
import it.xplants.xtro.appserver.admin.tools.ImageProcessor;
import it.xplants.xtro.appserver.tools.AppSupport;
import it.xplants.xtro.extensions.XTClass;
import it.xplants.xtro.helper.XTContainerHelper;
import it.xplants.xtro.utilities.XTWOUtilities;
import it.xplants.xtro.utilities.ZipUtils;
import it.xplants.xtro.vgi.transfer.components.VTAddresseeEditor;
import it.xplants.xtro.vgi.transfer.components.VTAddresseesList;
import it.xplants.xtro.vgi.transfer.components.VTCol;
import it.xplants.xtro.vgi.transfer.components.VTDeliveriesList;
import it.xplants.xtro.vgi.transfer.components.VTDeliveryEditor;
import it.xplants.xtro.vgi.transfer.components.VTEditor;
import it.xplants.xtro.vgi.transfer.components.VTFoldersManagement;
import it.xplants.xtro.vgi.transfer.components.VTLogsList;
import it.xplants.xtro.vgi.transfer.components.VTMain;
import it.xplants.xtro.vgi.transfer.components.VTPackageEditor;
import it.xplants.xtro.vgi.transfer.components.VTPackagesList;
import it.xplants.xtro.vgi.transfer.components.VTUsersList;
import it.xplants.xtro.vgi.transfer.components.VTViewDelivery;
import it.xplants.xtro.vgi.transfer.eo.VTAddressee;
import it.xplants.xtro.vgi.transfer.eo.VTDelivery;
import it.xplants.xtro.vgi.transfer.eo.VTLog;
import it.xplants.xtro.vgi.transfer.eo.VTPackage;

public class VTAction extends XTDirectAction {

	public VTAction(WORequest aRequest) {
		super(aRequest);
	}

	@Override
	public WOActionResults performActionNamed(String actionName) {
		if ("view".equals(actionName) || "download".equals(actionName)) {

			setContainer(repository());
			VTViewDelivery page = (VTViewDelivery) getPage(VTViewDelivery.class);
			boolean view = "view".equals(actionName);
			if (view) {
				useSession();
				session();
			} else {
				if (existingSession() == null) {
					return page;
				}
			}
			String userString = XTWOUtilities.GetClientAddress(request());
			String code = request().stringFormValueForKey("code");
			if (code == null) {
				return page;
			}
			VTDelivery d = (VTDelivery) EOUtilities.objectWithPrimaryKeyValue(session().defaultEditingContext(), VTDelivery.ENTITY_NAME, code);
			if (d == null) {
				return page;
			}
			if (view) {
				VTLog log = VTLog.createVTLog(session().defaultEditingContext(), new NSTimestamp(), "Accesso", userString);
				log.setTheDeliveryRelationship(d);
				log.setTheDescription("accesso pacchetto " + d.thePackage().theName());
				session().defaultEditingContext().saveChanges();
				page.selectedRecord = d;
				return page;
			} else {
				try {
					File tmpDir = Files.createTempDirectory("dl").toFile();
					String tmpDirPath = tmpDir.getAbsolutePath();
					String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "-" + d.thePackage().theName();

					long fs = Files.getFileStore(Paths.get(tmpDir.getAbsolutePath())).getUsableSpace();

//					long fs = FileSystemUtils.freeSpaceKb(tmpDir.getAbsolutePath());
					long ps = (d.thePackage().theSize() / 1024) * 2;

					System.out.println(" fs: " + fs + " " + tmpDir.getAbsolutePath());
					System.out.println("pkg: " + d.thePackage().theSize());
					System.out.println(" ps: " + ps);

					if (fs > ps) {
						tmpDir = new File(tmpDir.getAbsolutePath() + File.separator + fileName);
						tmpDir.mkdirs();

						NSArray<XTContainer> containers = d.thePackage().theContainers();
						for (XTContainer c : containers) {
							XTContainerHelper.toFile(c, null, tmpDir, AppSupport.MAIN_LANGUAGE_CODE);
						}
						File tmp = new File(tmpDirPath + File.separator + fileName + ".zip");

						VTLog log = VTLog.createVTLog(session().defaultEditingContext(), new NSTimestamp(), "Download", userString);
						log.setTheDeliveryRelationship(d);
						log.setTheDescription("download pacchetto " + d.thePackage().theName());
						session().defaultEditingContext().saveChanges();
						ZipUtils.zip(tmpDir, tmp);
						String mimeType = "multipart/x-zip", disp = "attachment;";
						ERXResponse resp = new ERXResponse();
						resp.setStatus(200);
						FileInputStream inStream = new FileInputStream(tmp);
						// resp.setStorePageInBacktrackCache(false);
						resp.disableClientCaching();
						resp.removeHeadersForKey("Cache-Control");
						resp.removeHeadersForKey("cache-control");
						resp.removeHeadersForKey("pragma");
						resp.setHeader(mimeType, "Content-Type");
						resp.setHeader(disp + " filename=\"" + tmp.getName() + "\"", "content-disposition");
						resp.setContentStream(inStream, 1024, tmp.length());
						return resp;
					} else {
						return new ERXResponse("no space available", 500);

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return super.performActionNamed(actionName);
	}

	public WOActionResults addPackageAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTPackageEditor page = (VTPackageEditor) getPage(VTPackageEditor.class);
			EOEditingContext ec = page.editorEC();
			page.setEditingContext(ec);
			page.setUser(page.user().localInstanceIn(ec));
			page.setContainer(page.container().localInstanceIn(ec));
			page.setSelectedRecord(VTPackage.createVTPackage(ec, new NSTimestamp(), 15, "Nuovo pacchetto", page.user()));
			return page;
		}
		return defaultAction();
	}

	public WOActionResults editDeliveryAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTDeliveryEditor page = (VTDeliveryEditor) getPage(VTDeliveryEditor.class);
			EOEditingContext ec = page.editorEC();
			page.setEditingContext(ec);
			page.setUser(page.user().localInstanceIn(ec));
			page.setContainer(page.container().localInstanceIn(ec));

			VTDelivery delivery = (VTDelivery) EOUtilities.objectWithPrimaryKeyValue(ec, VTDelivery.ENTITY_NAME, request().stringFormValueForKey("id"));
			page.setSelectedRecord(delivery);

			return page;
		}
		return defaultAction();
	}

	public WOActionResults addDeliveryAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			EOEditingContext ec = ERXEC.newEditingContext();
			VTDelivery delivery = VTDelivery.createVTDelivery(ec, user().theUsername());
			VTPackage _package = (VTPackage) EOUtilities.objectWithPrimaryKeyValue(ec, VTPackage.ENTITY_NAME, Integer.parseInt(request().stringFormValueForKey("id-package")));
			delivery.setThePackageRelationship(_package);
			Date dateExpiration = DateUtils.addDays(delivery.dateCreation(), _package.theMaxDaysToExpiration());
			delivery.setDateExpiration(new NSTimestamp(dateExpiration));
			NSArray<Object> addr = request().formValuesForKey("id-addressee");
			for (Object object : addr) {
				VTAddressee _addressee = (VTAddressee) EOUtilities.objectWithPrimaryKeyValue(ec, VTAddressee.ENTITY_NAME, Integer.parseInt(object.toString()));
				delivery.addToTheAddresseesRelationship(_addressee);
			}
			ec.saveChanges();
			NSMutableDictionary<String, Object> queryParameters = new NSMutableDictionary<>();
			queryParameters.setObjectForKey(session().sessionID(), "wosid");
			queryParameters.setObjectForKey(delivery.primaryKey(), "id");
			String link = ERXWOContext.directActionUrl(context(), "VTAction/editDelivery", queryParameters, XTWOUtilities.secure(request()), true);
			ERXResponse r = new ERXResponse(302);
			r.setHeader(link, "Location");
			return r;
		}
		return defaultAction();
	}

	public WOActionResults addAddresseeAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTAddresseeEditor page = (VTAddresseeEditor) getPage(VTAddresseeEditor.class);
			EOEditingContext ec = page.editorEC();
			page.setUser(page.user().localInstanceIn(ec));
			page.setSelectedRecord(VTAddressee.createVTAddressee(ec, new NSTimestamp(), page.user()));
			return page;
		}
		return defaultAction();
	}

	public WOActionResults editPackageAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTPackageEditor page = (VTPackageEditor) getPage(VTPackageEditor.class);
			EOEditingContext ec = page.editorEC();
			page.setEditingContext(ec);
			page.setUser(page.user().localInstanceIn(ec));
			page.setContainer(page.container().localInstanceIn(ec));
			page.setSelectedRecord((VTPackage) EOUtilities.objectWithPrimaryKeyValue(ec, VTPackage.ENTITY_NAME, Integer.parseInt(request().stringFormValueForKey("id"))));
			return page;
		}
		return defaultAction();
	}

	public WOActionResults editAddresseeAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTAddresseeEditor page = (VTAddresseeEditor) getPage(VTAddresseeEditor.class);
			EOEditingContext ec = page.editorEC();
			page.setEditingContext(ec);
			page.setUser(page.user().localInstanceIn(ec));
			page.setContainer(page.container().localInstanceIn(ec));
			page.setSelectedRecord((VTAddressee) EOUtilities.objectWithPrimaryKeyValue(ec, VTAddressee.ENTITY_NAME, Integer.parseInt(request().stringFormValueForKey("id"))));
			return page;
		}
		return defaultAction();
	}

	public WOActionResults deleteAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			XTContainer container = null;
			try {
				if (request().stringFormValueForKey("id") != null) {
					container = container();
				}
				try {
					EOQualifier qualifier = VTPackage.THE_CONTAINERS.is(container);
					NSArray<VTPackage> packages = VTPackage.fetchVTPackages(container.editingContext(), qualifier, null);
					for (VTPackage vtPackage : packages) {
						vtPackage.removeFromTheContainersRelationship(container);
					}

					if (container.isSection()) {
						NSArray<XTContainer> containers = container.containers();
						for (XTContainer c : containers) {
							for (VTPackage vtPackage : packages) {
								vtPackage.removeFromTheContainersRelationship(c);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				EOEditingContext ec = container.editingContext();
				String pk = container.container().primaryKey();
				ec.deleteObject(container);
				ec.saveChanges();

				NSMutableDictionary<String, Object> queryParameters = new NSMutableDictionary<>();
				queryParameters.setObjectForKey(repository().idXTContainer(), "id");
				if (existingSession() != null) {
					queryParameters.setObjectForKey(session().sessionID(), "wosid");
				}
				queryParameters.setObjectForKey(pk, "id-selected");
				ERXRedirect r = pageWithName(ERXRedirect.class);
				r.setDirectActionClass("VTAction");
				r.setDirectActionName("fileManager");
				r.setQueryParameters(queryParameters);
				r.setIncludeSessionID(existingSession() != null);
				r.setSecure(context().secureMode());
				return r;

			} catch (XTContainerNotFoundException e) {
				e.printStackTrace();
			} catch (XTContainerAccessDeniedException e) {
				e.printStackTrace();
			}
		}
		return defaultAction();
	}

	@Override
	public WOActionResults editAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			XTContainer container = null;
			try {
				if (request().stringFormValueForKey("id") != null) {
					container = container();
				}

				ContainerData d = new ContainerData(container, languageCode());
				d.takeValueFromRequest(request());
				d.updateContainer(true);
				d.container.setTheModificationDate(new NSTimestamp());
				try {
					d.container.thePath().objectAtIndex(2).setTheModificationDate(new NSTimestamp());
				} catch (Exception e) {
					// TODO: handle exception
				}
				container.editingContext().saveChanges();

				NSMutableDictionary<String, Object> queryParameters = new NSMutableDictionary<>();
				queryParameters.setObjectForKey(repository().idXTContainer(), "id");
				if (existingSession() != null) {
					queryParameters.setObjectForKey(session().sessionID(), "wosid");
				}
				queryParameters.setObjectForKey(container.primaryKey(), "id-selected");
				ERXRedirect r = pageWithName(ERXRedirect.class);
				r.setDirectActionClass("VTAction");
				r.setDirectActionName("fileManager");
				r.setQueryParameters(queryParameters);
				r.setIncludeSessionID(existingSession() != null);
				r.setSecure(context().secureMode());
				return r;

			} catch (XTContainerNotFoundException e) {
				e.printStackTrace();
			} catch (XTContainerAccessDeniedException e) {
				e.printStackTrace();
			}
		}
		return defaultAction();
	}

	public WOActionResults addSectionAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			XTContainer parent;
			try {
				if (request().stringFormValueForKey("id") != null) {
					parent = container();
				} else {
					parent = repository();
				}
				String newTitle = request().stringFormValueForKey("new-title");
				XTContainer subsection = parent.createContainer(XTClass.SECTION, "Center", true, user());
				subsection.mainLanguageTitle().setTheContent(newTitle);
				subsection.editingContext().saveChanges();

				NSMutableDictionary<String, Object> queryParameters = new NSMutableDictionary<>();
				queryParameters.setObjectForKey(repository().idXTContainer(), "id");
				if (existingSession() != null) {
					queryParameters.setObjectForKey(session().sessionID(), "wosid");

				}
				queryParameters.setObjectForKey(subsection.primaryKey(), "id-selected");
				ERXRedirect r = pageWithName(ERXRedirect.class);
				r.setDirectActionClass("VTAction");
				r.setDirectActionName("fileManager");
				r.setQueryParameters(queryParameters);
				r.setIncludeSessionID(existingSession() != null);
				r.setSecure(context().secureMode());
				return r;

			} catch (XTContainerNotFoundException e) {
				e.printStackTrace();
			} catch (XTContainerAccessDeniedException e) {
				e.printStackTrace();
			}
		}
		return defaultAction();
	}

	public WOActionResults listPackagesAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTPackagesList page = (VTPackagesList) getPage(VTPackagesList.class);
			page.searchAction();
			return page;
		}
		return defaultAction();
	}

	public WOActionResults listLogsAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTLogsList page = (VTLogsList) getPage(VTLogsList.class);
			return page;
		}
		return defaultAction();
	}

	public WOActionResults listAddresseesAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTAddresseesList page = (VTAddresseesList) getPage(VTAddresseesList.class);
			page.searchAction();
			return page;
		}
		return defaultAction();
	}

	public WOActionResults listDeliveriesAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTDeliveriesList page = (VTDeliveriesList) getPage(VTDeliveriesList.class);
			page.searchAction();
			return page;
		}
		return defaultAction();
	}

	public WOActionResults listUsersAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			setContainer(repository());
			VTUsersList page = (VTUsersList) getPage(VTUsersList.class);
			page.searchAction();
			return page;
		}
		return defaultAction();
	}

	public WOActionResults addFileAction() {
		request().setStorePageInBacktrackCache(false);
		if (existingSession() != null) {
			XTContainer parent;
			try {
				if (request().stringFormValueForKey("id") != null) {
					parent = container();
				} else {
					parent = repository();
				}
				NSMutableDictionary<String, Object> queryParameters = new NSMutableDictionary<>();
				queryParameters.setObjectForKey(repository().idXTContainer(), "id");
				if (existingSession() != null) {
					queryParameters.setObjectForKey(session().sessionID(), "wosid");

				}
				if (parent.isSection()) {
					String filepath = request().stringFormValueForKey("pl-filepath-new-file");
					if (filepath != null) {
						String filename = request().stringFormValueForKey("pl-filename-new-file");
						XTContainer record = parent.createContainer(XTClass.FILE, "Center", true, user());
						record.mainLanguageTitle().setTheContent(filename);
						record.editingContext().saveChanges();

						ImageProcessor pr = new ImageProcessor(record);
						pr.process(filepath, filename, languageCode());
						record.editingContext().saveChanges();
						queryParameters.setObjectForKey(record.primaryKey(), "id-selected");
					}
				} else {
					queryParameters.setObjectForKey("non puoi caricare un file all'interno di un altro file. Devi caricarlo in una cartella.", "error-message");
				}
				ERXRedirect r = pageWithName(ERXRedirect.class);
				r.setDirectActionClass("VTAction");
				r.setDirectActionName("fileManager");
				r.setQueryParameters(queryParameters);
				r.setIncludeSessionID(existingSession() != null);
				r.setSecure(context().secureMode());
				return r;

			} catch (XTContainerNotFoundException e) {
				e.printStackTrace();
			} catch (XTContainerAccessDeniedException e) {
				e.printStackTrace();
			}
		}
		return defaultAction();
	}

	@Override
	public WOActionResults defaultAction() {
		setContainer(repository());
		return getPage(VTMain.class);
	}

	public WOActionResults fileManagerAction() {
		setContainer(repository());
		return getPage(VTFoldersManagement.class);
	}

	public WOActionResults colAction() {
		request().setStorePageInBacktrackCache(false);
		return getPage(VTCol.class);
	}

	public WOActionResults editorAction() {
		request().setStorePageInBacktrackCache(false);
		return getPage(VTEditor.class);
	}

	private XTContainer repository;

	public XTContainer repository() {
		if (repository == null) {
			repository = XTContainer.fetchXTContainerWithName(editingContext(), "vt-repository");
		}
		return repository;
	}

	@Override
	public WOActionResults logoutAction() {
		logout();
		String url = ERXWOContext.directActionUrl(context(), "VTAction/default", XTWOUtilities.secure(request()), false);
		ERXResponse r = new ERXResponse(302);
		r.setHeader(url, "Location");
		return r;
	}

	public WOActionResults loginAction() {
		NSMutableDictionary<String, Object> queryParameters = new NSMutableDictionary<>();
		queryParameters.setObjectForKey(repository().idXTContainer(), "id");
		if (existingSession() != null) {
			queryParameters.setObjectForKey(session().sessionID(), "wosid");

		}
		if (context().request().stringFormValueForKey("id-selected") != null) {
			queryParameters.setObjectForKey(context().request().stringFormValueForKey("id-selected"), "");
		}
		ERXRedirect r = pageWithName(ERXRedirect.class);
		r.setDirectActionClass("VTAction");
		r.setDirectActionName("default");
		r.setQueryParameters(queryParameters);
		r.setIncludeSessionID(existingSession() != null);
		r.setSecure(context().secureMode());
		return r;

	}

}
