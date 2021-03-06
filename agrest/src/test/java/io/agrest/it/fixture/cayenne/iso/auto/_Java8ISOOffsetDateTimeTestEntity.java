package io.agrest.it.fixture.cayenne.iso.auto;

import java.time.OffsetDateTime;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _Java8ISOTimeTestEntity was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Java8ISOOffsetDateTimeTestEntity extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<OffsetDateTime> TIMESTAMP = Property.create("timestamp", OffsetDateTime.class);

    public void setTimestamp(OffsetDateTime time) {
        writeProperty("timestamp", time);
    }
    public OffsetDateTime getTimestamp() {
        return (OffsetDateTime)readProperty("timestamp");
    }

}
