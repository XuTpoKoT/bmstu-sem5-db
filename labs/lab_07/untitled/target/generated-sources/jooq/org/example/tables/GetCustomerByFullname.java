/*
 * This file is generated by jOOQ.
 */
package org.example.tables;


import java.util.function.Function;

import org.example.Public;
import org.example.tables.records.GetCustomerByFullnameRecord;
import org.example.udt.records.FullnameRecord;
import org.jooq.Field;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Records;
import org.jooq.Row3;
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
public class GetCustomerByFullname extends TableImpl<GetCustomerByFullnameRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.get_customer_by_fullname</code>
     */
    public static final GetCustomerByFullname GET_CUSTOMER_BY_FULLNAME = new GetCustomerByFullname();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GetCustomerByFullnameRecord> getRecordType() {
        return GetCustomerByFullnameRecord.class;
    }

    /**
     * The column <code>public.get_customer_by_fullname.id</code>.
     */
    public final TableField<GetCustomerByFullnameRecord, String> ID = createField(DSL.name("id"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.get_customer_by_fullname.first_name</code>.
     */
    public final TableField<GetCustomerByFullnameRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.get_customer_by_fullname.last_name</code>.
     */
    public final TableField<GetCustomerByFullnameRecord, String> LAST_NAME = createField(DSL.name("last_name"), SQLDataType.CLOB, this, "");

    private GetCustomerByFullname(Name alias, Table<GetCustomerByFullnameRecord> aliased) {
        this(alias, aliased, new Field[] {
            DSL.val(null, org.example.udt.Fullname.FULLNAME.getDataType())
        });
    }

    private GetCustomerByFullname(Name alias, Table<GetCustomerByFullnameRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.function());
    }

    /**
     * Create an aliased <code>public.get_customer_by_fullname</code> table
     * reference
     */
    public GetCustomerByFullname(String alias) {
        this(DSL.name(alias), GET_CUSTOMER_BY_FULLNAME);
    }

    /**
     * Create an aliased <code>public.get_customer_by_fullname</code> table
     * reference
     */
    public GetCustomerByFullname(Name alias) {
        this(alias, GET_CUSTOMER_BY_FULLNAME);
    }

    /**
     * Create a <code>public.get_customer_by_fullname</code> table reference
     */
    public GetCustomerByFullname() {
        this(DSL.name("get_customer_by_fullname"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public GetCustomerByFullname as(String alias) {
        return new GetCustomerByFullname(DSL.name(alias), this, parameters);
    }

    @Override
    public GetCustomerByFullname as(Name alias) {
        return new GetCustomerByFullname(alias, this, parameters);
    }

    @Override
    public GetCustomerByFullname as(Table<?> alias) {
        return new GetCustomerByFullname(alias.getQualifiedName(), this, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public GetCustomerByFullname rename(String name) {
        return new GetCustomerByFullname(DSL.name(name), null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public GetCustomerByFullname rename(Name name) {
        return new GetCustomerByFullname(name, null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public GetCustomerByFullname rename(Table<?> name) {
        return new GetCustomerByFullname(name.getQualifiedName(), null, parameters);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Call this table-valued function
     */
    public GetCustomerByFullname call(
          FullnameRecord name
    ) {
        GetCustomerByFullname result = new GetCustomerByFullname(DSL.name("get_customer_by_fullname"), null, new Field[] {
            DSL.val(name, org.example.udt.Fullname.FULLNAME.getDataType())
        });

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }

    /**
     * Call this table-valued function
     */
    public GetCustomerByFullname call(
          Field<FullnameRecord> name
    ) {
        GetCustomerByFullname result = new GetCustomerByFullname(DSL.name("get_customer_by_fullname"), null, new Field[] {
            name
        });

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}