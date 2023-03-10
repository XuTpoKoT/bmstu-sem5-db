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
public class ChangeStorageCntClr extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter
     * <code>public.change_storage_cnt_clr.productmodel_name</code>.
     */
    public static final Parameter<String> PRODUCTMODEL_NAME = Internal.createParameter("productmodel_name", SQLDataType.CLOB, false, false);

    /**
     * The parameter <code>public.change_storage_cnt_clr.new_cnt</code>.
     */
    public static final Parameter<Integer> NEW_CNT = Internal.createParameter("new_cnt", SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public ChangeStorageCntClr() {
        super("change_storage_cnt_clr", Public.PUBLIC);

        addInParameter(PRODUCTMODEL_NAME);
        addInParameter(NEW_CNT);
        setSQLUsable(false);
    }

    /**
     * Set the <code>productmodel_name</code> parameter IN value to the routine
     */
    public void setProductmodelName(String value) {
        setValue(PRODUCTMODEL_NAME, value);
    }

    /**
     * Set the <code>new_cnt</code> parameter IN value to the routine
     */
    public void setNewCnt(Integer value) {
        setValue(NEW_CNT, value);
    }
}
