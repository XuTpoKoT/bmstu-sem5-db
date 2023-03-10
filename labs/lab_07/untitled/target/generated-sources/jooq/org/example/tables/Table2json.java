/*
 * This file is generated by jOOQ.
 */
package org.example.tables;


import java.util.function.Function;

import org.example.Public;
import org.example.tables.records.Table2jsonRecord;
import org.jooq.Field;
import org.jooq.Function1;
import org.jooq.JSONB;
import org.jooq.Name;
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
public class Table2json extends TableImpl<Table2jsonRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.table2json</code>
     */
    public static final Table2json TABLE2JSON = new Table2json();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Table2jsonRecord> getRecordType() {
        return Table2jsonRecord.class;
    }

    /**
     * The column <code>public.table2json.table2json</code>.
     */
    public final TableField<Table2jsonRecord, JSONB> TABLE2JSON_ = createField(DSL.name("table2json"), SQLDataType.JSONB, this, "");

    private Table2json(Name alias, Table<Table2jsonRecord> aliased) {
        this(alias, aliased, new Field[] {
            DSL.val(null, org.jooq.impl.DefaultDataType.getDefaultDataType("\"pg_catalog\".\"regclass\""))
        });
    }

    private Table2json(Name alias, Table<Table2jsonRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.function());
    }

    /**
     * Create an aliased <code>public.table2json</code> table reference
     */
    public Table2json(String alias) {
        this(DSL.name(alias), TABLE2JSON);
    }

    /**
     * Create an aliased <code>public.table2json</code> table reference
     */
    public Table2json(Name alias) {
        this(alias, TABLE2JSON);
    }

    /**
     * Create a <code>public.table2json</code> table reference
     */
    public Table2json() {
        this(DSL.name("table2json"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Table2json as(String alias) {
        return new Table2json(DSL.name(alias), this, parameters);
    }

    @Override
    public Table2json as(Name alias) {
        return new Table2json(alias, this, parameters);
    }

    @Override
    public Table2json as(Table<?> alias) {
        return new Table2json(alias.getQualifiedName(), this, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public Table2json rename(String name) {
        return new Table2json(DSL.name(name), null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public Table2json rename(Name name) {
        return new Table2json(name, null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public Table2json rename(Table<?> name) {
        return new Table2json(name.getQualifiedName(), null, parameters);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row1<JSONB> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * Call this table-valued function
     */
    public Table2json call(
          Object tableName
    ) {
        Table2json result = new Table2json(DSL.name("table2json"), null, new Field[] {
            DSL.val(tableName, org.jooq.impl.DefaultDataType.getDefaultDataType("\"pg_catalog\".\"regclass\""))
        });

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }

    /**
     * Call this table-valued function
     */
    public Table2json call(
          Field<Object> tableName
    ) {
        Table2json result = new Table2json(DSL.name("table2json"), null, new Field[] {
            tableName
        });

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function1<? super JSONB, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function1<? super JSONB, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
