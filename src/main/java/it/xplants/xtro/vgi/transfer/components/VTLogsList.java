package it.xplants.xtro.vgi.transfer.components;

import com.webobjects.appserver.WOContext;

import er.extensions.appserver.ERXDisplayGroup;
import it.xplants.appserver.dg.components.XPDGListSortManager;
import it.xplants.xtro.vgi.transfer.eo.VTLog;

public class VTLogsList extends VTBaseComponent {
    public ERXDisplayGroup displayGroup;
    public VTLog currentRecord;
    public XPDGListSortManager sortBy;
	public VTLogsList(WOContext context) {
        super(context);
        sortBy = new XPDGListSortManager(displayGroup);
        
    }
}