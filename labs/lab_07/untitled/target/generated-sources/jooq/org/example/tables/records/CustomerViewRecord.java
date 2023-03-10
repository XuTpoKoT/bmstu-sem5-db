/*
 * This file is generated by jOOQ.
 */
package org.example.tables.records;


import java.time.LocalDate;
import java.util.UUID;

import org.example.tables.CustomerView;
import org.jooq.Field;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerViewRecord extends TableRecordImpl<CustomerViewRecord> implements Record6<UUID, String, String, LocalDate, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.customer_view.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.customer_view.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.customer_view.first_name</code>.
     */
    public void setFirstName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.customer_view.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.customer_view.last_name</code>.
     */
    public void setLastName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.customer_view.last_name</code>.
     */
    public String getLastName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.customer_view.birth_date</code>.
     */
    public void setBirthDate(LocalDate value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.customer_view.birth_date</code>.
     */
    public LocalDate getBirthDate() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>public.customer_view.email</code>.
     */
    public void setEmail(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.customer_view.email</code>.
     */
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.customer_view.phone</code>.
     */
    public void setPhone(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.customer_view.phone</code>.
     */
    public String getPhone() {
        return (String) get(5);
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
        return CustomerView.CUSTOMER_VIEW.ID;
    }

    @Override
    public Field<String> field2() {
        return CustomerView.CUSTOMER_VIEW.FIRST_NAME;
    }

    @Override
    public Field<String> field3() {
        return CustomerView.CUSTOMER_VIEW.LAST_NAME;
    }

    @Override
    public Field<LocalDate> field4() {
        return CustomerView.CUSTOMER_VIEW.BIRTH_DATE;
    }

    @Override
    public Field<String> field5() {
        return CustomerView.CUSTOMER_VIEW.EMAIL;
    }

    @Override
    public Field<String> field6() {
        return CustomerView.CUSTOMER_VIEW.PHONE;
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
    public CustomerViewRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public CustomerViewRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public CustomerViewRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public CustomerViewRecord value4(LocalDate value) {
        setBirthDate(value);
        return this;
    }

    @Override
    public CustomerViewRecord value5(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public CustomerViewRecord value6(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public CustomerViewRecord values(UUID value1, String value2, String value3, LocalDate value4, String value5, String value6) {
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
     * Create a detached CustomerViewRecord
     */
    public CustomerViewRecord() {
        super(CustomerView.CUSTOMER_VIEW);
    }

    /**
     * Create a detached, initialised CustomerViewRecord
     */
    public CustomerViewRecord(UUID id, String firstName, String lastName, LocalDate birthDate, String email, String phone) {
        super(CustomerView.CUSTOMER_VIEW);

        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setEmail(email);
        setPhone(phone);
    }
}
