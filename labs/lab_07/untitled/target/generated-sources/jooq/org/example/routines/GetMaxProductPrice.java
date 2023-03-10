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
public class GetMaxProductPrice extends AbstractRoutine<Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>public.get_max_product_price.RETURN_VALUE</code>.
     */
    public static final Parameter<Integer> RETURN_VALUE = Internal.createParameter("RETURN_VALUE", SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public GetMaxProductPrice() {
        super("get_max_product_price", Public.PUBLIC, SQLDataType.INTEGER);

        setReturnParameter(RETURN_VALUE);
    }
}
