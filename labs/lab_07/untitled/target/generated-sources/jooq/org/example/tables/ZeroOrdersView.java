/*
 * This file is generated by jOOQ.
 */
package org.example.tables;


import java.util.UUID;
import java.util.function.Function;

import org.example.Public;
import org.example.tables.records.ZeroOrdersViewRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function1;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row1;
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
public class ZeroOrdersView extends TableImpl<ZeroOrdersViewRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.zero_orders_view</code>
     */
    public static final ZeroOrdersView ZERO_ORDERS_VIEW = new ZeroOrdersView();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ZeroOrdersViewRecord> getRecordType() {
        return ZeroOrdersViewRecord.class;
    }

    /**
     * The column <code>public.zero_orders_view.order_id</code>.
     */
    public final TableField<ZeroOrdersViewRecord, UUID> ORDER_ID = createField(DSL.name("order_id"), SQLDataType.UUID, this, "");

    private ZeroOrdersView(Name alias, Table<ZeroOrdersViewRecord> aliased) {
        this(alias, aliased, null);
    }

    private ZeroOrdersView(Name alias, Table<ZeroOrdersViewRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.view("""
        create view "zero_orders_view" as  SELECT op.order_id
         FROM order_productmodel op
        WHERE (op.cnt_products = 0);
        """));
    }

    /**
     * Create an aliased <code>public.zero_orders_view</code> table reference
     */
    public ZeroOrdersView(String alias) {
        this(DSL.name(alias), ZERO_ORDERS_VIEW);
    }

    /**
     * Create an aliased <code>public.zero_orders_view</code> table reference
     */
    public ZeroOrdersView(Name alias) {
        this(alias, ZERO_ORDERS_VIEW);
    }

    /**
     * Create a <code>public.zero_orders_view</code> table reference
     */
    public ZeroOrdersView() {
        this(DSL.name("zero_orders_view"), null);
    }

    public <O extends Record> ZeroOrdersView(Table<O> child, ForeignKey<O, ZeroOrdersViewRecord> key) {
        super(child, key, ZERO_ORDERS_VIEW);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public ZeroOrdersView as(String alias) {
        return new ZeroOrdersView(DSL.name(alias), this);
    }

    @Override
    public ZeroOrdersView as(Name alias) {
        return new ZeroOrdersView(alias, this);
    }

    @Override
    public ZeroOrdersView as(Table<?> alias) {
        return new ZeroOrdersView(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ZeroOrdersView rename(String name) {
        return new ZeroOrdersView(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ZeroOrdersView rename(Name name) {
        return new ZeroOrdersView(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ZeroOrdersView rename(Table<?> name) {
        return new ZeroOrdersView(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row1<UUID> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function1<? super UUID, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function1<? super UUID, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
