/*
 * This file is generated by jOOQ.
 */
package org.example.udt.records;


import org.example.udt.Fullname;
import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UDTRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FullnameRecord extends UDTRecordImpl<FullnameRecord> implements Record2<String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.fullname.first_name</code>.
     */
    public void setFirstName(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.fullname.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.fullname.last_name</code>.
     */
    public void setLastName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.fullname.last_name</code>.
     */
    public String getLastName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Fullname.FIRST_NAME;
    }

    @Override
    public Field<String> field2() {
        return Fullname.LAST_NAME;
    }

    @Override
    public String component1() {
        return getFirstName();
    }

    @Override
    public String component2() {
        return getLastName();
    }

    @Override
    public String value1() {
        return getFirstName();
    }

    @Override
    public String value2() {
        return getLastName();
    }

    @Override
    public FullnameRecord value1(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public FullnameRecord value2(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public FullnameRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FullnameRecord
     */
    public FullnameRecord() {
        super(Fullname.FULLNAME);
    }

    /**
     * Create a detached, initialised FullnameRecord
     */
    public FullnameRecord(String firstName, String lastName) {
        super(Fullname.FULLNAME);

        setFirstName(firstName);
        setLastName(lastName);
    }
}