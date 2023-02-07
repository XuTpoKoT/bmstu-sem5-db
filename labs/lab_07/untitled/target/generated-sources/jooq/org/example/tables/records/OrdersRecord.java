/*
 * This file is generated by jOOQ.
 */
package org.example.tables.records;


import java.time.LocalDateTime;
import java.util.UUID;

import org.example.tables.Orders;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrdersRecord extends UpdatableRecordImpl<OrdersRecord> implements Record4<UUID, UUID, LocalDateTime, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.orders.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.orders.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.orders.customer_id</code>.
     */
    public void setCustomerId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.orders.customer_id</code>.
     */
    public UUID getCustomerId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.orders.date</code>.
     */
    public void setDate(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.orders.date</code>.
     */
    public LocalDateTime getDate() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>public.orders.status</code>.
     */
    public void setStatus(UUID value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.orders.status</code>.
     */
    public UUID getStatus() {
        return (UUID) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, UUID, LocalDateTime, UUID> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<UUID, UUID, LocalDateTime, UUID> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Orders.ORDERS.ID;
    }

    @Override
    public Field<UUID> field2() {
        return Orders.ORDERS.CUSTOMER_ID;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return Orders.ORDERS.DATE;
    }

    @Override
    public Field<UUID> field4() {
        return Orders.ORDERS.STATUS;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getCustomerId();
    }

    @Override
    public LocalDateTime component3() {
        return getDate();
    }

    @Override
    public UUID component4() {
        return getStatus();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getCustomerId();
    }

    @Override
    public LocalDateTime value3() {
        return getDate();
    }

    @Override
    public UUID value4() {
        return getStatus();
    }

    @Override
    public OrdersRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public OrdersRecord value2(UUID value) {
        setCustomerId(value);
        return this;
    }

    @Override
    public OrdersRecord value3(LocalDateTime value) {
        setDate(value);
        return this;
    }

    @Override
    public OrdersRecord value4(UUID value) {
        setStatus(value);
        return this;
    }

    @Override
    public OrdersRecord values(UUID value1, UUID value2, LocalDateTime value3, UUID value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrdersRecord
     */
    public OrdersRecord() {
        super(Orders.ORDERS);
    }

    /**
     * Create a detached, initialised OrdersRecord
     */
    public OrdersRecord(UUID id, UUID customerId, LocalDateTime date, UUID status) {
        super(Orders.ORDERS);

        setId(id);
        setCustomerId(customerId);
        setDate(date);
        setStatus(status);
    }
}