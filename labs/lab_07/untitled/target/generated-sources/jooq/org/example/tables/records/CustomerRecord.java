/*
 * This file is generated by jOOQ.
 */
package org.example.tables.records;


import java.time.LocalDate;
import java.util.UUID;

import org.example.tables.Customer;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerRecord extends UpdatableRecordImpl<CustomerRecord> implements Record6<UUID, String, String, LocalDate, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.customer.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.customer.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.customer.first_name</code>.
     */
    public void setFirstName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.customer.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.customer.last_name</code>.
     */
    public void setLastName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.customer.last_name</code>.
     */
    public String getLastName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.customer.birth_date</code>.
     */
    public void setBirthDate(LocalDate value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.customer.birth_date</code>.
     */
    public LocalDate getBirthDate() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>public.customer.email</code>.
     */
    public void setEmail(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.customer.email</code>.
     */
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.customer.phone</code>.
     */
    public void setPhone(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.customer.phone</code>.
     */
    public String getPhone() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<UUID, String, String, LocalDate, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<UUID, String, String, LocalDate, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Customer.CUSTOMER.ID;
    }

    @Override
    public Field<String> field2() {
        return Customer.CUSTOMER.FIRST_NAME;
    }

    @Override
    public Field<String> field3() {
        return Customer.CUSTOMER.LAST_NAME;
    }

    @Override
    public Field<LocalDate> field4() {
        return Customer.CUSTOMER.BIRTH_DATE;
    }

    @Override
    public Field<String> field5() {
        return Customer.CUSTOMER.EMAIL;
    }

    @Override
    public Field<String> field6() {
        return Customer.CUSTOMER.PHONE;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFirstName();
    }

    @Override
    public String component3() {
        return getLastName();
    }

    @Override
    public LocalDate component4() {
        return getBirthDate();
    }

    @Override
    public String component5() {
        return getEmail();
    }

    @Override
    public String component6() {
        return getPhone();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFirstName();
    }

    @Override
    public String value3() {
        return getLastName();
    }

    @Override
    public LocalDate value4() {
        return getBirthDate();
    }

    @Override
    public String value5() {
        return getEmail();
    }

    @Override
    public String value6() {
        return getPhone();
    }

    @Override
    public CustomerRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public CustomerRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public CustomerRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public CustomerRecord value4(LocalDate value) {
        setBirthDate(value);
        return this;
    }

    @Override
    public CustomerRecord value5(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public CustomerRecord value6(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public CustomerRecord values(UUID value1, String value2, String value3, LocalDate value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomerRecord
     */
    public CustomerRecord() {
        super(Customer.CUSTOMER);
    }

    /**
     * Create a detached, initialised CustomerRecord
     */
    public CustomerRecord(UUID id, String firstName, String lastName, LocalDate birthDate, String email, String phone) {
        super(Customer.CUSTOMER);

        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setEmail(email);
        setPhone(phone);
    }
}
