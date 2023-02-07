/*
 * This file is generated by jOOQ.
 */
package org.example.tables;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.example.Keys;
import org.example.Public;
import org.example.tables.records.OrderProductmodelRecord;
import org.jooq.Check;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrderProductmodel extends TableImpl<OrderProductmodelRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.order_productmodel</code>
     */
    public static final OrderProductmodel ORDER_PRODUCTMODEL = new OrderProductmodel();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrderProductmodelRecord> getRecordType() {
        return OrderProductmodelRecord.class;
    }

    /**
     * The column <code>public.order_productmodel.order_id</code>.
     */
    public final TableField<OrderProductmodelRecord, UUID> ORDER_ID = createField(DSL.name("order_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.order_productmodel.product_model_id</code>.
     */
    public final TableField<OrderProductmodelRecord, UUID> PRODUCT_MODEL_ID = createField(DSL.name("product_model_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.order_productmodel.cnt_products</code>.
     */
    public final TableField<OrderProductmodelRecord, Short> CNT_PRODUCTS = createField(DSL.name("cnt_products"), SQLDataType.SMALLINT.nullable(false), this, "");

    private OrderProductmodel(Name alias, Table<OrderProductmodelRecord> aliased) {
        this(alias, aliased, null);
    }

    private OrderProductmodel(Name alias, Table<OrderProductmodelRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.order_productmodel</code> table reference
     */
    public OrderProductmodel(String alias) {
        this(DSL.name(alias), ORDER_PRODUCTMODEL);
    }

    /**
     * Create an aliased <code>public.order_productmodel</code> table reference
     */
    public OrderProductmodel(Name alias) {
        this(alias, ORDER_PRODUCTMODEL);
    }

    /**
     * Create a <code>public.order_productmodel</code> table reference
     */
    public OrderProductmodel() {
        this(DSL.name("order_productmodel"), null);
    }

    public <O extends Record> OrderProductmodel(Table<O> child, ForeignKey<O, OrderProductmodelRecord> key) {
        super(child, key, ORDER_PRODUCTMODEL);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<ForeignKey<OrderProductmodelRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ORDER_PRODUCTMODEL__ORDER_PRODUCTMODEL_ORDER_ID_FKEY, Keys.ORDER_PRODUCTMODEL__ORDER_PRODUCTMODEL_PRODUCT_MODEL_ID_FKEY);
    }

    private transient Orders _orders;
    private transient Productmodel _productmodel;

    /**
     * Get the implicit join path to the <code>public.orders</code> table.
     */
    public Orders orders() {
        if (_orders == null)
            _orders = new Orders(this, Keys.ORDER_PRODUCTMODEL__ORDER_PRODUCTMODEL_ORDER_ID_FKEY);

        return _orders;
    }

    /**
     * Get the implicit join path to the <code>public.productmodel</code> table.
     */
    public Productmodel productmodel() {
        if (_productmodel == null)
            _productmodel = new Productmodel(this, Keys.ORDER_PRODUCTMODEL__ORDER_PRODUCTMODEL_PRODUCT_MODEL_ID_FKEY);

        return _productmodel;
    }

    @Override
    public List<Check<OrderProductmodelRecord>> getChecks() {
        return Arrays.asList(
            Internal.createCheck(this, DSL.name("order_productmodel_cnt_products_check"), "((cnt_products >= 0))", true)
        );
    }

    @Override
    public OrderProductmodel as(String alias) {
        return new OrderProductmodel(DSL.name(alias), this);
    }

    @Override
    public OrderProductmodel as(Name alias) {
        return new OrderProductmodel(alias, this);
    }

    @Override
    public OrderProductmodel as(Table<?> alias) {
        return new OrderProductmodel(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OrderProductmodel rename(String name) {
        return new OrderProductmodel(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrderProductmodel rename(Name name) {
        return new OrderProductmodel(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrderProductmodel rename(Table<?> name) {
        return new OrderProductmodel(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, Short> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super UUID, ? super UUID, ? super Short, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super UUID, ? super UUID, ? super Short, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}