package org.example.dao;

import org.example.entity.Customer;
import org.example.entity.cmp.CustomerBirthDateComparator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerDAOImpl implements CustomerDAO {
    private Map<UUID, Customer> customers = new HashMap<>();

    {
        Customer c = Customer.builder().firstName("Tom").lastName("Oliver").birthDate(LocalDate.of(1999, 1, 1))
                .email("a1@example.com").phone("+7-206-986-76-7").id(UUID.fromString("ae42a131-b67d-4737-80ac-ece650baf040")).build();
        customers.put(c.getId(), c);
        c = Customer.builder().firstName("Tom").lastName("Wang").birthDate(LocalDate.of(2002, 1, 1))
                .email("a1@example.com").phone("+7-206-986-76-7").id(UUID.fromString("ae42a131-b67d-4737-80ac-ece650baf041")).build();
        customers.put(c.getId(), c);
        c = Customer.builder().firstName("Bob").lastName("Harris").birthDate(LocalDate.of(2004, 1, 1))
                .email("a1@example.com").phone("+7-206-986-76-7").id(UUID.fromString("ae42a131-b67d-4737-80ac-ece650baf042")).build();
        customers.put(c.getId(), c);
        c = Customer.builder().firstName("Bob").lastName("Kent").birthDate(LocalDate.of(1987, 1, 1))
                .email("a1@example.com").phone("+7-206-986-76-7").id(UUID.fromString("ae42a131-b67d-4737-80ac-ece650baf043")).build();
        customers.put(c.getId(), c);
        c = Customer.builder().firstName("Mike").lastName("Oliver").birthDate(LocalDate.of(1999, 1, 1))
                .email("a1@example.com").phone("+7-206-986-76-7").id(UUID.fromString("ae42a131-b67d-4737-80ac-ece650baf044")).build();
        customers.put(c.getId(), c);
    }

    @Override
    public void init() {
        customers = new HashMap<>();
//        Customer c = Customer.builder("Tom", "Oliver", LocalDate.of(1999, 1, 1))
//                .email("a1@example.com").phone("+7-206-986-76-7").build();
//        customers.put(c.getId(), c);
//        c = Customer.builder("Tom", "Wang", LocalDate.of(2002, 1, 1))
//                .email("a1@example.com").phone("+7-206-986-76-7").build();
//        customers.put(c.getId(), c);
//        c = Customer.builder("Bob", "Harris", LocalDate.of(2004, 1, 1))
//                .email("a1@example.com").phone("+7-206-986-76-7").build();
//        customers.put(c.getId(), c);
//        c = Customer.builder("Bob", "Kent", LocalDate.of(1987, 1, 1))
//                .email("a1@example.com").phone("+7-206-986-76-7").build();
//        customers.put(c.getId(), c);
//        c = Customer.builder("Mike", "Oliver", LocalDate.of(1999, 3, 1))
//                .email("a1@example.com").phone("+7-206-986-76-7").build();
//        customers.put(c.getId(), c);
    }

    public List<Customer> findByFirstName(String firstName) {
        return  customers.values().stream().filter(x -> firstName.equals(x.getFirstName())).toList();
    }

    public List<Customer> sortedByBirthDate() {
        return customers.values().stream().sorted(new CustomerBirthDateComparator()).toList();
    }

    public Map<String, Optional<Customer>> minBirthDateGroupByName() {
        return customers.values().stream().collect(Collectors.groupingBy(Customer::getFirstName,
                                          Collectors.minBy(new CustomerBirthDateComparator())));
    }

    public void insert(Customer c) {
        customers.put(c.getId(), c);
    }

    public void update(Customer c) {
        customers.put(c.getId(), c);
    }
}
