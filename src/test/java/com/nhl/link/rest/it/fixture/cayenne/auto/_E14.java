package com.nhl.link.rest.it.fixture.cayenne.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _E14 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _E14 extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    @Deprecated
    public static final String NAME_PROPERTY = "name";

    public static final String LONG_ID_PK_COLUMN = "long_id";

    public static final Property<String> NAME = new Property<String>("name");

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

}
