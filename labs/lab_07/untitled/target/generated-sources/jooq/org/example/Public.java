/*
 * This file is generated by jOOQ.
 */
package org.example;


import java.util.Arrays;
import java.util.List;

import org.example.tables.Category;
import org.example.tables.CategoryView;
import org.example.tables.Customer;
import org.example.tables.CustomerView;
import org.example.tables.CustomersJson;
import org.example.tables.Employee;
import org.example.tables.Fib;
import org.example.tables.GetCustomer;
import org.example.tables.GetCustomerByFullname;
import org.example.tables.GetCustomerByNames;
import org.example.tables.GetProductModelsByColor;
import org.example.tables.Guitarmodeltype;
import org.example.tables.Manufacturer;
import org.example.tables.Material;
import org.example.tables.OrderProductmodel;
import org.example.tables.Orders;
import org.example.tables.OrdersJson;
import org.example.tables.Orderstatus;
import org.example.tables.Productmodel;
import org.example.tables.Soundpickup;
import org.example.tables.Table2json;
import org.example.tables.ZeroOrdersView;
import org.example.tables.records.FibRecord;
import org.example.tables.records.GetCustomerByFullnameRecord;
import org.example.tables.records.GetCustomerByNamesRecord;
import org.example.tables.records.GetCustomerRecord;
import org.example.tables.records.GetProductModelsByColorRecord;
import org.example.tables.records.Table2jsonRecord;
import org.example.udt.Fullname;
import org.example.udt.records.FullnameRecord;
import org.jooq.Catalog;
import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.UDT;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.category</code>.
     */
    public final Category CATEGORY = Category.CATEGORY;

    /**
     * The table <code>public.category_view</code>.
     */
    public final CategoryView CATEGORY_VIEW = CategoryView.CATEGORY_VIEW;

    /**
     * The table <code>public.customer</code>.
     */
    public final Customer CUSTOMER = Customer.CUSTOMER;

    /**
     * The table <code>public.customer_view</code>.
     */
    public final CustomerView CUSTOMER_VIEW = CustomerView.CUSTOMER_VIEW;

    /**
     * The table <code>public.customers_json</code>.
     */
    public final CustomersJson CUSTOMERS_JSON = CustomersJson.CUSTOMERS_JSON;

    /**
     * The table <code>public.employee</code>.
     */
    public final Employee EMPLOYEE = Employee.EMPLOYEE;

    /**
     * The table <code>public.fib</code>.
     */
    public final Fib FIB = Fib.FIB;

    /**
     * Call <code>public.fib</code>.
     */
    public static Result<FibRecord> FIB(
          Configuration configuration
        , Integer first
        , Integer second
        , Integer max
    ) {
        return configuration.dsl().selectFrom(org.example.tables.Fib.FIB.call(
              first
            , second
            , max
        )).fetch();
    }

    /**
     * Get <code>public.fib</code> as a table.
     */
    public static Fib FIB(
          Integer first
        , Integer second
        , Integer max
    ) {
        return org.example.tables.Fib.FIB.call(
            first,
            second,
            max
        );
    }

    /**
     * Get <code>public.fib</code> as a table.
     */
    public static Fib FIB(
          Field<Integer> first
        , Field<Integer> second
        , Field<Integer> max
    ) {
        return org.example.tables.Fib.FIB.call(
            first,
            second,
            max
        );
    }

    /**
     * The table <code>public.get_customer</code>.
     */
    public final GetCustomer GET_CUSTOMER = GetCustomer.GET_CUSTOMER;

    /**
     * Call <code>public.get_customer</code>.
     */
    public static Result<GetCustomerRecord> GET_CUSTOMER(
          Configuration configuration
        , String firstName
    ) {
        return configuration.dsl().selectFrom(org.example.tables.GetCustomer.GET_CUSTOMER.call(
              firstName
        )).fetch();
    }

    /**
     * Get <code>public.get_customer</code> as a table.
     */
    public static GetCustomer GET_CUSTOMER(
          String firstName
    ) {
        return org.example.tables.GetCustomer.GET_CUSTOMER.call(
            firstName
        );
    }

    /**
     * Get <code>public.get_customer</code> as a table.
     */
    public static GetCustomer GET_CUSTOMER(
          Field<String> firstName
    ) {
        return org.example.tables.GetCustomer.GET_CUSTOMER.call(
            firstName
        );
    }

    /**
     * The table <code>public.get_customer_by_fullname</code>.
     */
    public final GetCustomerByFullname GET_CUSTOMER_BY_FULLNAME = GetCustomerByFullname.GET_CUSTOMER_BY_FULLNAME;

    /**
     * Call <code>public.get_customer_by_fullname</code>.
     */
    public static Result<GetCustomerByFullnameRecord> GET_CUSTOMER_BY_FULLNAME(
          Configuration configuration
        , FullnameRecord name
    ) {
        return configuration.dsl().selectFrom(org.example.tables.GetCustomerByFullname.GET_CUSTOMER_BY_FULLNAME.call(
              name
        )).fetch();
    }

    /**
     * Get <code>public.get_customer_by_fullname</code> as a table.
     */
    public static GetCustomerByFullname GET_CUSTOMER_BY_FULLNAME(
          FullnameRecord name
    ) {
        return org.example.tables.GetCustomerByFullname.GET_CUSTOMER_BY_FULLNAME.call(
            name
        );
    }

    /**
     * Get <code>public.get_customer_by_fullname</code> as a table.
     */
    public static GetCustomerByFullname GET_CUSTOMER_BY_FULLNAME(
          Field<FullnameRecord> name
    ) {
        return org.example.tables.GetCustomerByFullname.GET_CUSTOMER_BY_FULLNAME.call(
            name
        );
    }

    /**
     * The table <code>public.get_customer_by_names</code>.
     */
    public final GetCustomerByNames GET_CUSTOMER_BY_NAMES = GetCustomerByNames.GET_CUSTOMER_BY_NAMES;

    /**
     * Call <code>public.get_customer_by_names</code>.
     */
    public static Result<GetCustomerByNamesRecord> GET_CUSTOMER_BY_NAMES(
          Configuration configuration
        , String firstName
        , String lastName
    ) {
        return configuration.dsl().selectFrom(org.example.tables.GetCustomerByNames.GET_CUSTOMER_BY_NAMES.call(
              firstName
            , lastName
        )).fetch();
    }

    /**
     * Get <code>public.get_customer_by_names</code> as a table.
     */
    public static GetCustomerByNames GET_CUSTOMER_BY_NAMES(
          String firstName
        , String lastName
    ) {
        return org.example.tables.GetCustomerByNames.GET_CUSTOMER_BY_NAMES.call(
            firstName,
            lastName
        );
    }

    /**
     * Get <code>public.get_customer_by_names</code> as a table.
     */
    public static GetCustomerByNames GET_CUSTOMER_BY_NAMES(
          Field<String> firstName
        , Field<String> lastName
    ) {
        return org.example.tables.GetCustomerByNames.GET_CUSTOMER_BY_NAMES.call(
            firstName,
            lastName
        );
    }

    /**
     * The table <code>public.get_product_models_by_color</code>.
     */
    public final GetProductModelsByColor GET_PRODUCT_MODELS_BY_COLOR = GetProductModelsByColor.GET_PRODUCT_MODELS_BY_COLOR;

    /**
     * Call <code>public.get_product_models_by_color</code>.
     */
    public static Result<GetProductModelsByColorRecord> GET_PRODUCT_MODELS_BY_COLOR(
          Configuration configuration
        , String color1
        , String color2
    ) {
        return configuration.dsl().selectFrom(org.example.tables.GetProductModelsByColor.GET_PRODUCT_MODELS_BY_COLOR.call(
              color1
            , color2
        )).fetch();
    }

    /**
     * Get <code>public.get_product_models_by_color</code> as a table.
     */
    public static GetProductModelsByColor GET_PRODUCT_MODELS_BY_COLOR(
          String color1
        , String color2
    ) {
        return org.example.tables.GetProductModelsByColor.GET_PRODUCT_MODELS_BY_COLOR.call(
            color1,
            color2
        );
    }

    /**
     * Get <code>public.get_product_models_by_color</code> as a table.
     */
    public static GetProductModelsByColor GET_PRODUCT_MODELS_BY_COLOR(
          Field<String> color1
        , Field<String> color2
    ) {
        return org.example.tables.GetProductModelsByColor.GET_PRODUCT_MODELS_BY_COLOR.call(
            color1,
            color2
        );
    }

    /**
     * The table <code>public.guitarmodeltype</code>.
     */
    public final Guitarmodeltype GUITARMODELTYPE = Guitarmodeltype.GUITARMODELTYPE;

    /**
     * The table <code>public.manufacturer</code>.
     */
    public final Manufacturer MANUFACTURER = Manufacturer.MANUFACTURER;

    /**
     * The table <code>public.material</code>.
     */
    public final Material MATERIAL = Material.MATERIAL;

    /**
     * The table <code>public.order_productmodel</code>.
     */
    public final OrderProductmodel ORDER_PRODUCTMODEL = OrderProductmodel.ORDER_PRODUCTMODEL;

    /**
     * The table <code>public.orders</code>.
     */
    public final Orders ORDERS = Orders.ORDERS;

    /**
     * The table <code>public.orders_json</code>.
     */
    public final OrdersJson ORDERS_JSON = OrdersJson.ORDERS_JSON;

    /**
     * The table <code>public.orderstatus</code>.
     */
    public final Orderstatus ORDERSTATUS = Orderstatus.ORDERSTATUS;

    /**
     * The table <code>public.productmodel</code>.
     */
    public final Productmodel PRODUCTMODEL = Productmodel.PRODUCTMODEL;

    /**
     * The table <code>public.soundpickup</code>.
     */
    public final Soundpickup SOUNDPICKUP = Soundpickup.SOUNDPICKUP;

    /**
     * The table <code>public.table2json</code>.
     */
    public final Table2json TABLE2JSON = Table2json.TABLE2JSON;

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public static Result<Table2jsonRecord> TABLE2JSON(
          Configuration configuration
        , Object tableName
    ) {
        return configuration.dsl().selectFrom(org.example.tables.Table2json.TABLE2JSON.call(
              tableName
        )).fetch();
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public static Table2json TABLE2JSON(
          Object tableName
    ) {
        return org.example.tables.Table2json.TABLE2JSON.call(
            tableName
        );
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public static Table2json TABLE2JSON(
          Field<Object> tableName
    ) {
        return org.example.tables.Table2json.TABLE2JSON.call(
            tableName
        );
    }

    /**
     * The table <code>public.zero_orders_view</code>.
     */
    public final ZeroOrdersView ZERO_ORDERS_VIEW = ZeroOrdersView.ZERO_ORDERS_VIEW;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Category.CATEGORY,
            CategoryView.CATEGORY_VIEW,
            Customer.CUSTOMER,
            CustomerView.CUSTOMER_VIEW,
            CustomersJson.CUSTOMERS_JSON,
            Employee.EMPLOYEE,
            Fib.FIB,
            GetCustomer.GET_CUSTOMER,
            GetCustomerByFullname.GET_CUSTOMER_BY_FULLNAME,
            GetCustomerByNames.GET_CUSTOMER_BY_NAMES,
            GetProductModelsByColor.GET_PRODUCT_MODELS_BY_COLOR,
            Guitarmodeltype.GUITARMODELTYPE,
            Manufacturer.MANUFACTURER,
            Material.MATERIAL,
            OrderProductmodel.ORDER_PRODUCTMODEL,
            Orders.ORDERS,
            OrdersJson.ORDERS_JSON,
            Orderstatus.ORDERSTATUS,
            Productmodel.PRODUCTMODEL,
            Soundpickup.SOUNDPICKUP,
            Table2json.TABLE2JSON,
            ZeroOrdersView.ZERO_ORDERS_VIEW
        );
    }

    @Override
    public final List<UDT<?>> getUDTs() {
        return Arrays.asList(
            Fullname.FULLNAME
        );
    }
}
