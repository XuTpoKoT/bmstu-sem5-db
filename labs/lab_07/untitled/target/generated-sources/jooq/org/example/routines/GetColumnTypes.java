/*
 * This file is generated by jOOQ.
 */
package org.example.routines;


import org.example.Public;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GetColumnTypes extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>public.get_column_types.table_name_</code>.
     */
    public static final Parameter<String> TABLE_NAME_ = Internal.createParameter("table_name_", SQLDataType.CLOB, false, false);

    /**
     * Create a new routine call instance
     */
    public GetColumnTypes() {
        super("get_column_types", Public.PUBLIC);

        addInParameter(TABLE_NAME_);
        setSQLUsable(false);
    }

    /**
     * Set the <code>table_name_</code> parameter IN value to the routine
     */
    public void setTableName_(String value) {
        setValue(TABLE_NAME_, value);
    }
}