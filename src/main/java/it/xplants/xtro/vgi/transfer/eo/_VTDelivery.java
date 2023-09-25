// DO NOT EDIT.  Make changes to VTDelivery.java instead.
package it.xplants.xtro.vgi.transfer.eo;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
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
public abstract class _VTDelivery extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "VTDelivery";

  // Attribute Keys
  public static final ERXKey<NSTimestamp> DATE_CREATION = new ERXKey<NSTimestamp>("dateCreation", Type.Attribute);
  public static final ERXKey<NSTimestamp> DATE_DELETION = new ERXKey<NSTimestamp>("dateDeletion", Type.Attribute);
  public static final ERXKey<NSTimestamp> DATE_EXPIRATION = new ERXKey<NSTimestamp>("dateExpiration", Type.Attribute);
  public static final ERXKey<String> ID = new ERXKey<String>("id", Type.Attribute);
  public static final ERXKey<String> THE_EMAILS = new ERXKey<String>("theEmails", Type.Attribute);

  // Relationship Keys
  public static final ERXKey<it.xplants.xtro.vgi.transfer.eo.VTAddressee> THE_ADDRESSEES = new ERXKey<it.xplants.xtro.vgi.transfer.eo.VTAddressee>("theAddressees", Type.ToManyRelationship);
  public static final ERXKey<it.xplants.xtro.vgi.transfer.eo.VTPackage> THE_PACKAGE = new ERXKey<it.xplants.xtro.vgi.transfer.eo.VTPackage>("thePackage", Type.ToOneRelationship);
  public static final ERXKey<it.xplants.xtro.XTUser> THE_USER_CREATOR = new ERXKey<it.xplants.xtro.XTUser>("theUserCreator", Type.ToOneRelationship);
  public static final ERXKey<it.xplants.xtro.XTUser> THE_USER_DELETER = new ERXKey<it.xplants.xtro.XTUser>("theUserDeleter", Type.ToOneRelationship);

  // Attributes
  public static final String DATE_CREATION_KEY = DATE_CREATION.key();
  public static final String DATE_DELETION_KEY = DATE_DELETION.key();
  public static final String DATE_EXPIRATION_KEY = DATE_EXPIRATION.key();
  public static final String ID_KEY = ID.key();
  public static final String THE_EMAILS_KEY = THE_EMAILS.key();

  // Relationships
  public static final String THE_ADDRESSEES_KEY = THE_ADDRESSEES.key();
  public static final String THE_PACKAGE_KEY = THE_PACKAGE.key();
  public static final String THE_USER_CREATOR_KEY = THE_USER_CREATOR.key();
  public static final String THE_USER_DELETER_KEY = THE_USER_DELETER.key();

  private static final Logger log = LoggerFactory.getLogger(_VTDelivery.class);

  public VTDelivery localInstanceIn(EOEditingContext editingContext) {
    VTDelivery localInstance = (VTDelivery)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp dateCreation() {
    return (NSTimestamp) storedValueForKey(_VTDelivery.DATE_CREATION_KEY);
  }

  public void setDateCreation(NSTimestamp value) {
    log.debug( "updating dateCreation from {} to {}", dateCreation(), value);
    takeStoredValueForKey(value, _VTDelivery.DATE_CREATION_KEY);
  }

  public NSTimestamp dateDeletion() {
    return (NSTimestamp) storedValueForKey(_VTDelivery.DATE_DELETION_KEY);
  }

  public void setDateDeletion(NSTimestamp value) {
    log.debug( "updating dateDeletion from {} to {}", dateDeletion(), value);
    takeStoredValueForKey(value, _VTDelivery.DATE_DELETION_KEY);
  }

  public NSTimestamp dateExpiration() {
    return (NSTimestamp) storedValueForKey(_VTDelivery.DATE_EXPIRATION_KEY);
  }

  public void setDateExpiration(NSTimestamp value) {
    log.debug( "updating dateExpiration from {} to {}", dateExpiration(), value);
    takeStoredValueForKey(value, _VTDelivery.DATE_EXPIRATION_KEY);
  }

  public String id() {
    return (String) storedValueForKey(_VTDelivery.ID_KEY);
  }

  public void setId(String value) {
    log.debug( "updating id from {} to {}", id(), value);
    takeStoredValueForKey(value, _VTDelivery.ID_KEY);
  }

  public String theEmails() {
    return (String) storedValueForKey(_VTDelivery.THE_EMAILS_KEY);
  }

  public void setTheEmails(String value) {
    log.debug( "updating theEmails from {} to {}", theEmails(), value);
    takeStoredValueForKey(value, _VTDelivery.THE_EMAILS_KEY);
  }

  public it.xplants.xtro.vgi.transfer.eo.VTPackage thePackage() {
    return (it.xplants.xtro.vgi.transfer.eo.VTPackage)storedValueForKey(_VTDelivery.THE_PACKAGE_KEY);
  }

  public void setThePackage(it.xplants.xtro.vgi.transfer.eo.VTPackage value) {
    takeStoredValueForKey(value, _VTDelivery.THE_PACKAGE_KEY);
  }

  public void setThePackageRelationship(it.xplants.xtro.vgi.transfer.eo.VTPackage value) {
    log.debug("updating thePackage from {} to {}", thePackage(), value);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      setThePackage(value);
    }
    else if (value == null) {
      it.xplants.xtro.vgi.transfer.eo.VTPackage oldValue = thePackage();
      if (oldValue != null) {
        removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _VTDelivery.THE_PACKAGE_KEY);
      }
    } else {
      addObjectToBothSidesOfRelationshipWithKey(value, _VTDelivery.THE_PACKAGE_KEY);
    }
  }

  public it.xplants.xtro.XTUser theUserCreator() {
    return (it.xplants.xtro.XTUser)storedValueForKey(_VTDelivery.THE_USER_CREATOR_KEY);
  }

  public void setTheUserCreator(it.xplants.xtro.XTUser value) {
    takeStoredValueForKey(value, _VTDelivery.THE_USER_CREATOR_KEY);
  }

  public void setTheUserCreatorRelationship(it.xplants.xtro.XTUser value) {
    log.debug("updating theUserCreator from {} to {}", theUserCreator(), value);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      setTheUserCreator(value);
    }
    else if (value == null) {
      it.xplants.xtro.XTUser oldValue = theUserCreator();
      if (oldValue != null) {
        removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _VTDelivery.THE_USER_CREATOR_KEY);
      }
    } else {
      addObjectToBothSidesOfRelationshipWithKey(value, _VTDelivery.THE_USER_CREATOR_KEY);
    }
  }

  public it.xplants.xtro.XTUser theUserDeleter() {
    return (it.xplants.xtro.XTUser)storedValueForKey(_VTDelivery.THE_USER_DELETER_KEY);
  }

  public void setTheUserDeleter(it.xplants.xtro.XTUser value) {
    takeStoredValueForKey(value, _VTDelivery.THE_USER_DELETER_KEY);
  }

  public void setTheUserDeleterRelationship(it.xplants.xtro.XTUser value) {
    log.debug("updating theUserDeleter from {} to {}", theUserDeleter(), value);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      setTheUserDeleter(value);
    }
    else if (value == null) {
      it.xplants.xtro.XTUser oldValue = theUserDeleter();
      if (oldValue != null) {
        removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _VTDelivery.THE_USER_DELETER_KEY);
      }
    } else {
      addObjectToBothSidesOfRelationshipWithKey(value, _VTDelivery.THE_USER_DELETER_KEY);
    }
  }

  public NSArray<it.xplants.xtro.vgi.transfer.eo.VTAddressee> theAddressees() {
    return (NSArray<it.xplants.xtro.vgi.transfer.eo.VTAddressee>)storedValueForKey(_VTDelivery.THE_ADDRESSEES_KEY);
  }

  public NSArray<it.xplants.xtro.vgi.transfer.eo.VTAddressee> theAddressees(EOQualifier qualifier) {
    return theAddressees(qualifier, null);
  }

  public NSArray<it.xplants.xtro.vgi.transfer.eo.VTAddressee> theAddressees(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<it.xplants.xtro.vgi.transfer.eo.VTAddressee> results;
      results = theAddressees();
      if (qualifier != null) {
        results = (NSArray<it.xplants.xtro.vgi.transfer.eo.VTAddressee>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<it.xplants.xtro.vgi.transfer.eo.VTAddressee>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }

  public void addToTheAddressees(it.xplants.xtro.vgi.transfer.eo.VTAddressee object) {
    includeObjectIntoPropertyWithKey(object, _VTDelivery.THE_ADDRESSEES_KEY);
  }

  public void removeFromTheAddressees(it.xplants.xtro.vgi.transfer.eo.VTAddressee object) {
    excludeObjectFromPropertyWithKey(object, _VTDelivery.THE_ADDRESSEES_KEY);
  }

  public void addToTheAddresseesRelationship(it.xplants.xtro.vgi.transfer.eo.VTAddressee object) {
    log.debug("adding {} to theAddressees relationship", object);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      addToTheAddressees(object);
    }
    else {
      addObjectToBothSidesOfRelationshipWithKey(object, _VTDelivery.THE_ADDRESSEES_KEY);
    }
  }

  public void removeFromTheAddresseesRelationship(it.xplants.xtro.vgi.transfer.eo.VTAddressee object) {
    log.debug("removing {} from theAddressees relationship", object);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      removeFromTheAddressees(object);
    }
    else {
      removeObjectFromBothSidesOfRelationshipWithKey(object, _VTDelivery.THE_ADDRESSEES_KEY);
    }
  }

  public it.xplants.xtro.vgi.transfer.eo.VTAddressee createTheAddresseesRelationship() {
    EOEnterpriseObject eo = EOUtilities.createAndInsertInstance(editingContext(),  it.xplants.xtro.vgi.transfer.eo.VTAddressee.ENTITY_NAME );
    addObjectToBothSidesOfRelationshipWithKey(eo, _VTDelivery.THE_ADDRESSEES_KEY);
    return (it.xplants.xtro.vgi.transfer.eo.VTAddressee) eo;
  }

  public void deleteTheAddresseesRelationship(it.xplants.xtro.vgi.transfer.eo.VTAddressee object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _VTDelivery.THE_ADDRESSEES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllTheAddresseesRelationships() {
    Enumeration<it.xplants.xtro.vgi.transfer.eo.VTAddressee> objects = theAddressees().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTheAddresseesRelationship(objects.nextElement());
    }
  }


  public static VTDelivery createVTDelivery(EOEditingContext editingContext, NSTimestamp dateCreation
, NSTimestamp dateExpiration
, String id
, it.xplants.xtro.vgi.transfer.eo.VTPackage thePackage, it.xplants.xtro.XTUser theUserCreator) {
    VTDelivery eo = (VTDelivery) EOUtilities.createAndInsertInstance(editingContext, _VTDelivery.ENTITY_NAME);
    eo.setDateCreation(dateCreation);
    eo.setDateExpiration(dateExpiration);
    eo.setId(id);
    eo.setThePackageRelationship(thePackage);
    eo.setTheUserCreatorRelationship(theUserCreator);
    return eo;
  }

  public static ERXFetchSpecification<VTDelivery> fetchSpec() {
    return new ERXFetchSpecification<VTDelivery>(_VTDelivery.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<VTDelivery> fetchAllVTDeliveries(EOEditingContext editingContext) {
    return _VTDelivery.fetchAllVTDeliveries(editingContext, null);
  }

  public static NSArray<VTDelivery> fetchAllVTDeliveries(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _VTDelivery.fetchVTDeliveries(editingContext, null, sortOrderings);
  }

  public static NSArray<VTDelivery> fetchVTDeliveries(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<VTDelivery> fetchSpec = new ERXFetchSpecification<VTDelivery>(_VTDelivery.ENTITY_NAME, qualifier, sortOrderings);
    NSArray<VTDelivery> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static VTDelivery fetchVTDelivery(EOEditingContext editingContext, String keyName, Object value) {
    return _VTDelivery.fetchVTDelivery(editingContext, ERXQ.equals(keyName, value));
  }

  public static VTDelivery fetchVTDelivery(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<VTDelivery> eoObjects = _VTDelivery.fetchVTDeliveries(editingContext, qualifier, null);
    VTDelivery eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VTDelivery that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VTDelivery fetchRequiredVTDelivery(EOEditingContext editingContext, String keyName, Object value) {
    return _VTDelivery.fetchRequiredVTDelivery(editingContext, ERXQ.equals(keyName, value));
  }

  public static VTDelivery fetchRequiredVTDelivery(EOEditingContext editingContext, EOQualifier qualifier) {
    VTDelivery eoObject = _VTDelivery.fetchVTDelivery(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VTDelivery that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VTDelivery localInstanceIn(EOEditingContext editingContext, VTDelivery eo) {
    VTDelivery localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
