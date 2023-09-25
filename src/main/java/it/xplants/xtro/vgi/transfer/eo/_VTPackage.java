// DO NOT EDIT.  Make changes to VTPackage.java instead.
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
public abstract class _VTPackage extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "VTPackage";

  // Attribute Keys
  public static final ERXKey<NSTimestamp> DATE_CREATION = new ERXKey<NSTimestamp>("dateCreation", Type.Attribute);
  public static final ERXKey<NSTimestamp> DATE_DELETION = new ERXKey<NSTimestamp>("dateDeletion", Type.Attribute);
  public static final ERXKey<Integer> THE_MAX_DAYS_TO_EXPIRATION = new ERXKey<Integer>("theMaxDaysToExpiration", Type.Attribute);
  public static final ERXKey<String> THE_NAME = new ERXKey<String>("theName", Type.Attribute);

  // Relationship Keys
  public static final ERXKey<it.xplants.xtro.XTContainer> THE_CONTAINERS = new ERXKey<it.xplants.xtro.XTContainer>("theContainers", Type.ToManyRelationship);
  public static final ERXKey<it.xplants.xtro.XTUser> THE_USER_CREATOR = new ERXKey<it.xplants.xtro.XTUser>("theUserCreator", Type.ToOneRelationship);
  public static final ERXKey<it.xplants.xtro.XTUser> THE_USER_DELETER = new ERXKey<it.xplants.xtro.XTUser>("theUserDeleter", Type.ToOneRelationship);

  // Attributes
  public static final String DATE_CREATION_KEY = DATE_CREATION.key();
  public static final String DATE_DELETION_KEY = DATE_DELETION.key();
  public static final String THE_MAX_DAYS_TO_EXPIRATION_KEY = THE_MAX_DAYS_TO_EXPIRATION.key();
  public static final String THE_NAME_KEY = THE_NAME.key();

  // Relationships
  public static final String THE_CONTAINERS_KEY = THE_CONTAINERS.key();
  public static final String THE_USER_CREATOR_KEY = THE_USER_CREATOR.key();
  public static final String THE_USER_DELETER_KEY = THE_USER_DELETER.key();

  private static final Logger log = LoggerFactory.getLogger(_VTPackage.class);

  public VTPackage localInstanceIn(EOEditingContext editingContext) {
    VTPackage localInstance = (VTPackage)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp dateCreation() {
    return (NSTimestamp) storedValueForKey(_VTPackage.DATE_CREATION_KEY);
  }

  public void setDateCreation(NSTimestamp value) {
    log.debug( "updating dateCreation from {} to {}", dateCreation(), value);
    takeStoredValueForKey(value, _VTPackage.DATE_CREATION_KEY);
  }

  public NSTimestamp dateDeletion() {
    return (NSTimestamp) storedValueForKey(_VTPackage.DATE_DELETION_KEY);
  }

  public void setDateDeletion(NSTimestamp value) {
    log.debug( "updating dateDeletion from {} to {}", dateDeletion(), value);
    takeStoredValueForKey(value, _VTPackage.DATE_DELETION_KEY);
  }

  public Integer theMaxDaysToExpiration() {
    return (Integer) storedValueForKey(_VTPackage.THE_MAX_DAYS_TO_EXPIRATION_KEY);
  }

  public void setTheMaxDaysToExpiration(Integer value) {
    log.debug( "updating theMaxDaysToExpiration from {} to {}", theMaxDaysToExpiration(), value);
    takeStoredValueForKey(value, _VTPackage.THE_MAX_DAYS_TO_EXPIRATION_KEY);
  }

  public String theName() {
    return (String) storedValueForKey(_VTPackage.THE_NAME_KEY);
  }

  public void setTheName(String value) {
    log.debug( "updating theName from {} to {}", theName(), value);
    takeStoredValueForKey(value, _VTPackage.THE_NAME_KEY);
  }

  public it.xplants.xtro.XTUser theUserCreator() {
    return (it.xplants.xtro.XTUser)storedValueForKey(_VTPackage.THE_USER_CREATOR_KEY);
  }

  public void setTheUserCreator(it.xplants.xtro.XTUser value) {
    takeStoredValueForKey(value, _VTPackage.THE_USER_CREATOR_KEY);
  }

  public void setTheUserCreatorRelationship(it.xplants.xtro.XTUser value) {
    log.debug("updating theUserCreator from {} to {}", theUserCreator(), value);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      setTheUserCreator(value);
    }
    else if (value == null) {
      it.xplants.xtro.XTUser oldValue = theUserCreator();
      if (oldValue != null) {
        removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _VTPackage.THE_USER_CREATOR_KEY);
      }
    } else {
      addObjectToBothSidesOfRelationshipWithKey(value, _VTPackage.THE_USER_CREATOR_KEY);
    }
  }

  public it.xplants.xtro.XTUser theUserDeleter() {
    return (it.xplants.xtro.XTUser)storedValueForKey(_VTPackage.THE_USER_DELETER_KEY);
  }

  public void setTheUserDeleter(it.xplants.xtro.XTUser value) {
    takeStoredValueForKey(value, _VTPackage.THE_USER_DELETER_KEY);
  }

  public void setTheUserDeleterRelationship(it.xplants.xtro.XTUser value) {
    log.debug("updating theUserDeleter from {} to {}", theUserDeleter(), value);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      setTheUserDeleter(value);
    }
    else if (value == null) {
      it.xplants.xtro.XTUser oldValue = theUserDeleter();
      if (oldValue != null) {
        removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _VTPackage.THE_USER_DELETER_KEY);
      }
    } else {
      addObjectToBothSidesOfRelationshipWithKey(value, _VTPackage.THE_USER_DELETER_KEY);
    }
  }

  public NSArray<it.xplants.xtro.XTContainer> theContainers() {
    return (NSArray<it.xplants.xtro.XTContainer>)storedValueForKey(_VTPackage.THE_CONTAINERS_KEY);
  }

  public NSArray<it.xplants.xtro.XTContainer> theContainers(EOQualifier qualifier) {
    return theContainers(qualifier, null);
  }

  public NSArray<it.xplants.xtro.XTContainer> theContainers(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<it.xplants.xtro.XTContainer> results;
      results = theContainers();
      if (qualifier != null) {
        results = (NSArray<it.xplants.xtro.XTContainer>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<it.xplants.xtro.XTContainer>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }

  public void addToTheContainers(it.xplants.xtro.XTContainer object) {
    includeObjectIntoPropertyWithKey(object, _VTPackage.THE_CONTAINERS_KEY);
  }

  public void removeFromTheContainers(it.xplants.xtro.XTContainer object) {
    excludeObjectFromPropertyWithKey(object, _VTPackage.THE_CONTAINERS_KEY);
  }

  public void addToTheContainersRelationship(it.xplants.xtro.XTContainer object) {
    log.debug("adding {} to theContainers relationship", object);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      addToTheContainers(object);
    }
    else {
      addObjectToBothSidesOfRelationshipWithKey(object, _VTPackage.THE_CONTAINERS_KEY);
    }
  }

  public void removeFromTheContainersRelationship(it.xplants.xtro.XTContainer object) {
    log.debug("removing {} from theContainers relationship", object);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      removeFromTheContainers(object);
    }
    else {
      removeObjectFromBothSidesOfRelationshipWithKey(object, _VTPackage.THE_CONTAINERS_KEY);
    }
  }

  public it.xplants.xtro.XTContainer createTheContainersRelationship() {
    EOEnterpriseObject eo = EOUtilities.createAndInsertInstance(editingContext(),  it.xplants.xtro.XTContainer.ENTITY_NAME );
    addObjectToBothSidesOfRelationshipWithKey(eo, _VTPackage.THE_CONTAINERS_KEY);
    return (it.xplants.xtro.XTContainer) eo;
  }

  public void deleteTheContainersRelationship(it.xplants.xtro.XTContainer object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _VTPackage.THE_CONTAINERS_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllTheContainersRelationships() {
    Enumeration<it.xplants.xtro.XTContainer> objects = theContainers().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteTheContainersRelationship(objects.nextElement());
    }
  }


  public static VTPackage createVTPackage(EOEditingContext editingContext, NSTimestamp dateCreation
, Integer theMaxDaysToExpiration
, String theName
, it.xplants.xtro.XTUser theUserCreator) {
    VTPackage eo = (VTPackage) EOUtilities.createAndInsertInstance(editingContext, _VTPackage.ENTITY_NAME);
    eo.setDateCreation(dateCreation);
    eo.setTheMaxDaysToExpiration(theMaxDaysToExpiration);
    eo.setTheName(theName);
    eo.setTheUserCreatorRelationship(theUserCreator);
    return eo;
  }

  public static ERXFetchSpecification<VTPackage> fetchSpec() {
    return new ERXFetchSpecification<VTPackage>(_VTPackage.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<VTPackage> fetchAllVTPackages(EOEditingContext editingContext) {
    return _VTPackage.fetchAllVTPackages(editingContext, null);
  }

  public static NSArray<VTPackage> fetchAllVTPackages(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _VTPackage.fetchVTPackages(editingContext, null, sortOrderings);
  }

  public static NSArray<VTPackage> fetchVTPackages(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<VTPackage> fetchSpec = new ERXFetchSpecification<VTPackage>(_VTPackage.ENTITY_NAME, qualifier, sortOrderings);
    NSArray<VTPackage> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static VTPackage fetchVTPackage(EOEditingContext editingContext, String keyName, Object value) {
    return _VTPackage.fetchVTPackage(editingContext, ERXQ.equals(keyName, value));
  }

  public static VTPackage fetchVTPackage(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<VTPackage> eoObjects = _VTPackage.fetchVTPackages(editingContext, qualifier, null);
    VTPackage eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one VTPackage that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VTPackage fetchRequiredVTPackage(EOEditingContext editingContext, String keyName, Object value) {
    return _VTPackage.fetchRequiredVTPackage(editingContext, ERXQ.equals(keyName, value));
  }

  public static VTPackage fetchRequiredVTPackage(EOEditingContext editingContext, EOQualifier qualifier) {
    VTPackage eoObject = _VTPackage.fetchVTPackage(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no VTPackage that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static VTPackage localInstanceIn(EOEditingContext editingContext, VTPackage eo) {
    VTPackage localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
