// DO NOT EDIT.  Make changes to VTLog.java instead.
package it.xplants.xtro.vgi.transfer.eo;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXFetchSpecification;
import er.extensions.eof.ERXGenericRecord;
import er.extensions.eof.ERXKey;
import er.extensions.eof.ERXKey.Type;
import er.extensions.eof.ERXQ;

@SuppressWarnings("all")
public abstract class _VTLog extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "VTLog";

  // Attribute Keys
  public static final ERXKey<NSTimestamp> DATE_CREATION = new ERXKey<NSTimestamp>("dateCreation", Type.Attribute);
  public static final ERXKey<String> ID_VT_DELIVERY = new ERXKey<String>("idVTDelivery", Type.Attribute);
  public static final ERXKey<String> THE_DESCRIPTION = new ERXKey<String>("theDescription", Type.Attribute);
  public static final ERXKey<String> THE_TYPE = new ERXKey<String>("theType", Type.Attribute);
  public static final ERXKey<String> THE_USERNAME = new ERXKey<String>("theUsername", Type.Attribute);

  // Relationship Keys
  public static final ERXKey<it.xplants.xtro.vgi.transfer.eo.VTDelivery> THE_DELIVERY = new ERXKey<it.xplants.xtro.vgi.transfer.eo.VTDelivery>("theDelivery", Type.ToOneRelationship);

  // Attributes
  public static final String DATE_CREATION_KEY = DATE_CREATION.key();
  public static final String ID_VT_DELIVERY_KEY = ID_VT_DELIVERY.key();
  public static final String THE_DESCRIPTION_KEY = THE_DESCRIPTION.key();
  public static final String THE_TYPE_KEY = THE_TYPE.key();
  public static final String THE_USERNAME_KEY = THE_USERNAME.key();

  // Relationships
  public static final String THE_DELIVERY_KEY = THE_DELIVERY.key();

  private static final Logger log = LoggerFactory.getLogger(_VTLog.class);

  public VTLog localInstanceIn(EOEditingContext editingContext) {
    VTLog localInstance = (VTLog)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp dateCreation() {
    return (NSTimestamp) storedValueForKey(_VTLog.DATE_CREATION_KEY);
  }

  public void setDateCreation(NSTimestamp value) {
    log.debug( "updating dateCreation from {} to {}", dateCreation(), value);
    takeStoredValueForKey(value, _VTLog.DATE_CREATION_KEY);
  }

  public String idVTDelivery() {
    return (String) storedValueForKey(_VTLog.ID_VT_DELIVERY_KEY);
  }

  public void setIdVTDelivery(String value) {
    log.debug( "updating idVTDelivery from {} to {}", idVTDelivery(), value);
    takeStoredValueForKey(value, _VTLog.ID_VT_DELIVERY_KEY);
  }

  public String theDescription() {
    return (String) storedValueForKey(_VTLog.THE_DESCRIPTION_KEY);
  }

  public void setTheDescription(String value) {
    log.debug( "updating theDescription from {} to {}", theDescription(), value);
    takeStoredValueForKey(value, _VTLog.THE_DESCRIPTION_KEY);
  }

  public String theType() {
    return (String) storedValueForKey(_VTLog.THE_TYPE_KEY);
  }

  public void setTheType(String value) {
    log.debug( "updating theType from {} to {}", theType(), value);
    takeStoredValueForKey(value, _VTLog.THE_TYPE_KEY);
  }

  public String theUsername() {
    return (String) storedValueForKey(_VTLog.THE_USERNAME_KEY);
  }

  public void setTheUsername(String value) {
    log.debug( "updating theUsername from {} to {}", theUsername(), value);
    takeStoredValueForKey(value, _VTLog.THE_USERNAME_KEY);
  }

  public it.xplants.xtro.vgi.transfer.eo.VTDelivery theDelivery() {
    return (it.xplants.xtro.vgi.transfer.eo.VTDelivery)storedValueForKey(_VTLog.THE_DELIVERY_KEY);
  }

  public void setTheDelivery(it.xplants.xtro.vgi.transfer.eo.VTDelivery value) {
    takeStoredValueForKey(value, _VTLog.THE_DELIVERY_KEY);
  }

  public void setTheDeliveryRelationship(it.xplants.xtro.vgi.transfer.eo.VTDelivery value) {
    log.debug("updating theDelivery from {} to {}", theDelivery(), value);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      setTheDelivery(value);
    }
    else if (value == null) {
      it.xplants.xtro.vgi.transfer.eo.VTDelivery oldValue = theDelivery();
      if (oldValue != null) {
        removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _VTLog.THE_DELIVERY_KEY);
      }
    } else {
      addObjectToBothSidesOfRelationshipWithKey(value, _VTLog.THE_DELIVERY_KEY);
    }
  }


  public static VTLog createVTLog(EOEditingContext editingContext, NSTimestamp dateCreation
, String theType
, String theUsername
) {
    VTLog eo = (VTLog) EOUtilities.createAndInsertInstance(editingContext, _VTLog.ENTITY_NAME);
    eo.setDateCreation(dateCreation);
    eo.setTheType(theType);
    eo.setTheUsername(theUsername);
    return eo;
  }

  public static ERXFetchSpecification<VTLog> fetchSpec() {
    return new ERXFetchSpecification<VTLog>(_VTLog.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<VTLog> fetchAllVTLogs(EOEditingContext editingContext) {
    return _VTLog.fetchAllVTLogs(editingContext, null);
  }

  public static NSArray<VTLog> fetchAllVTLogs(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _VTLog.fetchVTLogs(editingContext, null, sortOrderings);
  }

  public static NSArray<VTLog> fetchVTLogs(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<VTLog> fetchSpec = new ERXFetchSpecification<VTLog>(_VTLog.ENTITY_NAME, qualifier, sortOrderings);
    NSArray<VTLog> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static VTLog fetchVTLog(EOEditingContext editingContext, String keyName, Object value) {
    return _VTLog.fetchVTLog(editingContext, ERXQ.equals(keyName, value));
  }

  public static VTLog fetchVTLog(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<VTLog> eoObjects = _VTLog.fetchVTLogs(editingContext, qualifier, null);
    VTLog eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VTLog that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VTLog fetchRequiredVTLog(EOEditingContext editingContext, String keyName, Object value) {
    return _VTLog.fetchRequiredVTLog(editingContext, ERXQ.equals(keyName, value));
  }

  public static VTLog fetchRequiredVTLog(EOEditingContext editingContext, EOQualifier qualifier) {
    VTLog eoObject = _VTLog.fetchVTLog(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VTLog that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VTLog localInstanceIn(EOEditingContext editingContext, VTLog eo) {
    VTLog localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
