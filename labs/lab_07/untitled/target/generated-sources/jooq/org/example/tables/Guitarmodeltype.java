/*
 * This file is generated by jOOQ.
 */
package org.example.tables;


import java.util.UUID;
import java.util.function.Function;

import org.example.Keys;
import org.example.Public;
import org.example.tables.records.GuitarmodeltypeRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Guitarmodeltype extends TableImpl<GuitarmodeltypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.guitarmodeltype</code>
     */
    public static final Guitarmodeltype GUITARMODELTYPE = new Guitarmodeltype();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GuitarmodeltypeRecord> getRecordType() {
        return GuitarmodeltypeRecord.class;
    }

    /**
     * The column <code>public.guitarmodeltype.id</code>.
     */
    public final TableField<GuitarmodeltypeRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("gen_random_uuid()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.guitarmodeltype.name</code>.
     */
    public final TableField<GuitarmodeltypeRecord, String> NAME = createField(DSL.name("name"), SQLDataType.CLOB.nullable(false), this, "");

    private Guitarmodeltype(Name alias, Table<GuitarmodeltypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private Guitarmodeltype(Name alias, Table<GuitarmodeltypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.guitarmodeltype</code> table reference
     */
    public Guitarmodeltype(String alias) {
        this(DSL.name(alias), GUITARMODELTYPE);
    }

    /**
     * Create an aliased <code>public.guitarmodeltype</code> table reference
     */
    public Guitarmodeltype(Name alias) {
        this(alias, GUITARMODELTYPE);
    }

    /**
     * Create a <code>public.guitarmodeltype</code> table reference
     */
    public Guitarmodeltype() {
        this(DSL.name("guitarmodeltype"), null);
    }

    public <O extends Record> Guitarmodeltype(Table<O> child, ForeignKey<O, GuitarmodeltypeRecord> key) {
        super(child, key, GUITARMODELTYPE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<GuitarmodeltypeRecord> getPrimaryKey() {
        return Keys.GUITARMODELTYPE_PKEY;
    }

    @Override
    public Guitarmodeltype as(String alias) {
        return new Guitarmodeltype(DSL.name(alias), this);
    }

    @Override
    public Guitarmodeltype as(Name alias) {
        return new Guitarmodeltype(alias, this);
    }

    @Override
    public Guitarmodeltype as(Table<?> alias) {
        return new Guitarmodeltype(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Guitarmodeltype rename(String name) {
        return new Guitarmodeltype(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Guitarmodeltype rename(Name name) {
        return new Guitarmodeltype(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Guitarmodeltype rename(Table<?> name) {
        return new Guitarmodeltype(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<UUID, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super UUID, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super UUID, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
