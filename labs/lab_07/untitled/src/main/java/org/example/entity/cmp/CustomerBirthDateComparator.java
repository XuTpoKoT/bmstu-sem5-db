package org.example.entity.cmp;

import org.example.entity.Customer;

import java.util.Comparator;

public class CustomerBirthDateComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getBirthDate().compareTo(o2.getBirthDate());
        //return LocalDate.EPOCH.compareTo();
    }
}
