/*
 * This file is generated by jOOQ.
 */
package org.example.tables;


import java.util.UUID;
import java.util.function.Function;

import org.example.Public;
import org.example.tables.records.OrdersJsonRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.JSONB;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrdersJson extends TableImpl<OrdersJsonRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.orders_json</code>
     */
    public static final OrdersJson ORDERS_JSON = new OrdersJson();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrdersJsonRecord> getRecordType() {
        return OrdersJsonRecord.class;
    }

    /**
     * The column <code>public.orders_json.id</code>.
     */
    public final TableField<OrdersJsonRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.orders_json.doc</code>.
     */
    public final TableField<OrdersJsonRecord, JSONB> DOC = createField(DSL.name("doc"), SQLDataType.JSONB.nullable(false), this, "");

    private OrdersJson(Name alias, Table<OrdersJsonRecord> aliased) {
        this(alias, aliased, null);
    }

    private OrdersJson(Name alias, Table<OrdersJsonRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.orders_json</code> table reference
     */
    public OrdersJson(String alias) {
        this(DSL.name(alias), ORDERS_JSON);
    }

    /**
     * Create an aliased <code>public.orders_json</code> table reference
     */
    public OrdersJson(Name alias) {
        this(alias, ORDERS_JSON);
    }

    /**
     * Create a <code>public.orders_json</code> table reference
     */
    public OrdersJson() {
        this(DSL.name("orders_json"), null);
    }

    public <O extends Record> OrdersJson(Table<O> child, ForeignKey<O, OrdersJsonRecord> key) {
        super(child, key, ORDERS_JSON);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public OrdersJson as(String alias) {
        return new OrdersJson(DSL.name(alias), this);
    }

    @Override
    public OrdersJson as(Name alias) {
        return new OrdersJson(alias, this);
    }

    @Override
    public OrdersJson as(Table<?> alias) {
        return new OrdersJson(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OrdersJson rename(String name) {
        return new OrdersJson(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrdersJson rename(Name name) {
        return new OrdersJson(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrdersJson rename(Table<?> name) {
        return new OrdersJson(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<UUID, JSONB> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super UUID, ? super JSONB, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super UUID, ? super JSONB, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
