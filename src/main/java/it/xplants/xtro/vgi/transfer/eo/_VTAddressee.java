// DO NOT EDIT.  Make changes to VTAddressee.java instead.
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
public abstract class _VTAddressee extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "VTAddressee";

  // Attribute Keys
  public static final ERXKey<NSTimestamp> DATE_CREATION = new ERXKey<NSTimestamp>("dateCreation", Type.Attribute);
  public static final ERXKey<NSTimestamp> DATE_DELETION = new ERXKey<NSTimestamp>("dateDeletion", Type.Attribute);
  public static final ERXKey<String> THE_COMPANY_NAME = new ERXKey<String>("theCompanyName", Type.Attribute);
  public static final ERXKey<String> THE_EMAILS = new ERXKey<String>("theEmails", Type.Attribute);
  public static final ERXKey<String> THE_NAME = new ERXKey<String>("theName", Type.Attribute);
  public static final ERXKey<String> THE_NOTES = new ERXKey<String>("theNotes", Type.Attribute);
  public static final ERXKey<String> THE_POSTAL_CODE = new ERXKey<String>("thePostalCode", Type.Attribute);
  public static final ERXKey<String> THE_PROVINCE = new ERXKey<String>("theProvince", Type.Attribute);
  public static final ERXKey<String> THE_STREET = new ERXKey<String>("theStreet", Type.Attribute);
  public static final ERXKey<String> THE_SURNAME = new ERXKey<String>("theSurname", Type.Attribute);
  public static final ERXKey<String> THE_TOWN = new ERXKey<String>("theTown", Type.Attribute);

  // Relationship Keys
  public static final ERXKey<it.xplants.xtro.vgi.transfer.eo.VTDelivery> THE_DELIVERIES = new ERXKey<it.xplants.xtro.vgi.transfer.eo.VTDelivery>("theDeliveries", Type.ToManyRelationship);
  public static final ERXKey<it.xplants.xtro.XTUser> THE_USER_CREATOR = new ERXKey<it.xplants.xtro.XTUser>("theUserCreator", Type.ToOneRelationship);
  public static final ERXKey<it.xplants.xtro.XTUser> THE_USER_DELETER = new ERXKey<it.xplants.xtro.XTUser>("theUserDeleter", Type.ToOneRelationship);

  // Attributes
  public static final String DATE_CREATION_KEY = DATE_CREATION.key();
  public static final String DATE_DELETION_KEY = DATE_DELETION.key();
  public static final String THE_COMPANY_NAME_KEY = THE_COMPANY_NAME.key();
  public static final String THE_EMAILS_KEY = THE_EMAILS.key();
  public static final String THE_NAME_KEY = THE_NAME.key();
  public static final String THE_NOTES_KEY = THE_NOTES.key();
  public static final String THE_POSTAL_CODE_KEY = THE_POSTAL_CODE.key();
  public static final String THE_PROVINCE_KEY = THE_PROVINCE.key();
  public static final String THE_STREET_KEY = THE_STREET.key();
  public static final String THE_SURNAME_KEY = THE_SURNAME.key();
  public static final String THE_TOWN_KEY = THE_TOWN.key();

  // Relationships
  public static final String THE_DELIVERIES_KEY = THE_DELIVERIES.key();
  public static final String THE_USER_CREATOR_KEY = THE_USER_CREATOR.key();
  public static final String THE_USER_DELETER_KEY = THE_USER_DELETER.key();

  private static final Logger log = LoggerFactory.getLogger(_VTAddressee.class);

  public VTAddressee localInstanceIn(EOEditingContext editingContext) {
    VTAddressee localInstance = (VTAddressee)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp dateCreation() {
    return (NSTimestamp) storedValueForKey(_VTAddressee.DATE_CREATION_KEY);
  }

  public void setDateCreation(NSTimestamp value) {
    log.debug( "updating dateCreation from {} to {}", dateCreation(), value);
    takeStoredValueForKey(value, _VTAddressee.DATE_CREATION_KEY);
  }

  public NSTimestamp dateDeletion() {
    return (NSTimestamp) storedValueForKey(_VTAddressee.DATE_DELETION_KEY);
  }

  public void setDateDeletion(NSTimestamp value) {
    log.debug( "updating dateDeletion from {} to {}", dateDeletion(), value);
    takeStoredValueForKey(value, _VTAddressee.DATE_DELETION_KEY);
  }

  public String theCompanyName() {
    return (String) storedValueForKey(_VTAddressee.THE_COMPANY_NAME_KEY);
  }

  public void setTheCompanyName(String value) {
    log.debug( "updating theCompanyName from {} to {}", theCompanyName(), value);
    takeStoredValueForKey(value, _VTAddressee.THE_COMPANY_NAME_KEY);
  }

  public String theEmails() {
    return (String) storedValueForKey(_VTAddressee.THE_EMAILS_KEY);
  }

  public void setTheEmails(String value) {
    log.debug( "updating theEmails from {} to {}", theEmails(), value);
    takeStoredValueForKey(value, _VTAddressee.THE_EMAILS_KEY);
  }

  public String theName() {
    return (String) storedValueForKey(_VTAddressee.THE_NAME_KEY);
  }

  public void setTheName(String value) {
    log.debug( "updating theName from {} to {}", theName(), value);
    takeStoredValueForKey(value, _VTAddressee.THE_NAME_KEY);
  }

  public String theNotes() {
    return (String) storedValueForKey(_VTAddressee.THE_NOTES_KEY);
  }

  public void setTheNotes(String value) {
    log.debug( "updating theNotes from {} to {}", theNotes(), value);
    takeStoredValueForKey(value, _VTAddressee.THE_NOTES_KEY);
  }

  public String thePostalCode() {
    return (String) storedValueForKey(_VTAddressee.THE_POSTAL_CODE_KEY);
  }

  public void setThePostalCode(String value) {
    log.debug( "updating thePostalCode from {} to {}", thePostalCode(), value);
    takeStoredValueForKey(value, _VTAddressee.THE_POSTAL_CODE_KEY);
  }

  public String theProvince() {
    return (String) storedValueForKey(_VTAddressee.THE_PROVINCE_KEY);
  }

  public void setTheProvince(String value) {
    log.debug( "updating theProvince from {} to {}", theProvince(), value);
    takeStoredValueForKey(value, _VTAddressee.THE_PROVINCE_KEY);
  }

  public String theStreet() {
    return (String) storedValueForKey(_VTAddressee.THE_STREET_KEY);
  }

  public void setTheStreet(String value) {
    log.debug( "updating theStreet from {} to {}", theStreet(), value);
    takeStoredValueForKey(value, _VTAddressee.THE_STREET_KEY);
  }

  public String theSurname() {
    return (String) storedValueForKey(_VTAddressee.THE_SURNAME_KEY);
  }

  public void setTheSurname(String value) {
    log.debug( "updating theSurname from {} to {}", theSurname(), value);
    takeStoredValueForKey(value, _VTAddressee.THE_SURNAME_KEY);
  }

  public String theTown() {
    return (String) storedValueForKey(_VTAddressee.THE_TOWN_KEY);
  }

  public void setTheTown(String value) {
    log.debug( "updating theTown from {} to {}", theTown(), value);
    takeStoredValueForKey(value, _VTAddressee.THE_TOWN_KEY);
  }

  public it.xplants.xtro.XTUser theUserCreator() {
    return (it.xplants.xtro.XTUser)storedValueForKey(_VTAddressee.THE_USER_CREATOR_KEY);
  }

  public void setTheUserCreator(it.xplants.xtro.XTUser value) {
    takeStoredValueForKey(value, _VTAddressee.THE_USER_CREATOR_KEY);
  }

  public void setTheUserCreatorRelationship(it.xplants.xtro.XTUser value) {
    log.debug("updating theUserCreator from {} to {}", theUserCreator(), value);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      setTheUserCreator(value);
    }
    else if (value == null) {
      it.xplants.xtro.XTUser oldValue = theUserCreator();
      if (oldValue != null) {
        removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _VTAddressee.THE_USER_CREATOR_KEY);
      }
    } else {
      addObjectToBothSidesOfRelationshipWithKey(value, _VTAddressee.THE_USER_CREATOR_KEY);
    }
  }

  public it.xplants.xtro.XTUser theUserDeleter() {
    return (it.xplants.xtro.XTUser)storedValueForKey(_VTAddressee.THE_USER_DELETER_KEY);
  }

  public void setTheUserDeleter(it.xplants.xtro.XTUser value) {
    takeStoredValueForKey(value, _VTAddressee.THE_USER_DELETER_KEY);
  }

  public void setTheUserDeleterRelationship(it.xplants.xtro.XTUser value) {
    log.debug("updating theUserDeleter from {} to {}", theUserDeleter(), value);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      setTheUserDeleter(value);
    }
    else if (value == null) {
      it.xplants.xtro.XTUser oldValue = theUserDeleter();
      if (oldValue != null) {
        removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _VTAddressee.THE_USER_DELETER_KEY);
      }
    } else {
      addObjectToBothSidesOfRelationshipWithKey(value, _VTAddressee.THE_USER_DELETER_KEY);
    }
  }

  public NSArray<it.xplants.xtro.vgi.transfer.eo.VTDelivery> theDeliveries() {
    return (NSArray<it.xplants.xtro.vgi.transfer.eo.VTDelivery>)storedValueForKey(_VTAddressee.THE_DELIVERIES_KEY);
  }

  public NSArray<it.xplants.xtro.vgi.transfer.eo.VTDelivery> theDeliveries(EOQualifier qualifier) {
    return theDeliveries(qualifier, null);
  }

  public NSArray<it.xplants.xtro.vgi.transfer.eo.VTDelivery> theDeliveries(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<it.xplants.xtro.vgi.transfer.eo.VTDelivery> results;
      results = theDeliveries();
      if (qualifier != null) {
        results = (NSArray<it.xplants.xtro.vgi.transfer.eo.VTDelivery>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<it.xplants.xtro.vgi.transfer.eo.VTDelivery>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }

  public void addToTheDeliveries(it.xplants.xtro.vgi.transfer.eo.VTDelivery object) {
    includeObjectIntoPropertyWithKey(object, _VTAddressee.THE_DELIVERIES_KEY);
  }

  public void removeFromTheDeliveries(it.xplants.xtro.vgi.transfer.eo.VTDelivery object) {
    excludeObjectFromPropertyWithKey(object, _VTAddressee.THE_DELIVERIES_KEY);
  }

  public void addToTheDeliveriesRelationship(it.xplants.xtro.vgi.transfer.eo.VTDelivery object) {
    log.debug("adding {} to theDeliveries relationship", object);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      addToTheDeliveries(object);
    }
    else {
      addObjectToBothSidesOfRelationshipWithKey(object, _VTAddressee.THE_DELIVERIES_KEY);
    }
  }

  public void removeFromTheDeliveriesRelationship(it.xplants.xtro.vgi.transfer.eo.VTDelivery object) {
    log.debug("removing {} from theDeliveries relationship", object);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      removeFromTheDeliveries(object);
    }
    else {
      removeObjectFromBothSidesOfRelationshipWithKey(object, _VTAddressee.THE_DELIVERIES_KEY);
    }
  }

  public it.xplants.xtro.vgi.transfer.eo.VTDelivery createTheDeliveriesRelationship() {
    EOEnterpriseObject eo = EOUtilities.createAndInsertInstance(editingContext(),  it.xplants.xtro.vgi.transfer.eo.VTDelivery.ENTITY_NAME );
    addObjectToBothSidesOfRelationshipWithKey(eo, _VTAddressee.THE_DELIVERIES_KEY);
    return (it.xplants.xtro.vgi.transfer.eo.VTDelivery) eo;
  }

  public void deleteTheDeliveriesRelationship(it.xplants.xtro.vgi.transfer.eo.VTDelivery object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _VTAddressee.THE_DELIVERIES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllTheDeliveriesRelationships() {
    Enumeration<it.xplants.xtro.vgi.transfer.eo.VTDelivery> objects = theDeliveries().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTheDeliveriesRelationship(objects.nextElement());
    }
  }


  public static VTAddressee createVTAddressee(EOEditingContext editingContext, NSTimestamp dateCreation
, it.xplants.xtro.XTUser theUserCreator) {
    VTAddressee eo = (VTAddressee) EOUtilities.createAndInsertInstance(editingContext, _VTAddressee.ENTITY_NAME);
    eo.setDateCreation(dateCreation);
    eo.setTheUserCreatorRelationship(theUserCreator);
    return eo;
  }

  public static ERXFetchSpecification<VTAddressee> fetchSpec() {
    return new ERXFetchSpecification<VTAddressee>(_VTAddressee.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<VTAddressee> fetchAllVTAddressees(EOEditingContext editingContext) {
    return _VTAddressee.fetchAllVTAddressees(editingContext, null);
  }

  public static NSArray<VTAddressee> fetchAllVTAddressees(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _VTAddressee.fetchVTAddressees(editingContext, null, sortOrderings);
  }

  public static NSArray<VTAddressee> fetchVTAddressees(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<VTAddressee> fetchSpec = new ERXFetchSpecification<VTAddressee>(_VTAddressee.ENTITY_NAME, qualifier, sortOrderings);
    NSArray<VTAddressee> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static VTAddressee fetchVTAddressee(EOEditingContext editingContext, String keyName, Object value) {
    return _VTAddressee.fetchVTAddressee(editingContext, ERXQ.equals(keyName, value));
  }

  public static VTAddressee fetchVTAddressee(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<VTAddressee> eoObjects = _VTAddressee.fetchVTAddressees(editingContext, qualifier, null);
    VTAddressee eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VTAddressee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VTAddressee fetchRequiredVTAddressee(EOEditingContext editingContext, String keyName, Object value) {
    return _VTAddressee.fetchRequiredVTAddressee(editingContext, ERXQ.equals(keyName, value));
  }

  public static VTAddressee fetchRequiredVTAddressee(EOEditingContext editingContext, EOQualifier qualifier) {
    VTAddressee eoObject = _VTAddressee.fetchVTAddressee(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VTAddressee that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VTAddressee localInstanceIn(EOEditingContext editingContext, VTAddressee eo) {
    VTAddressee localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
