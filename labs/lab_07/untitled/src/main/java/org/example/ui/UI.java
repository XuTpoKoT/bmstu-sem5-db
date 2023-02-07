package org.example.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Routines;
import org.example.dao.CustomerDAOImpl;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.tools.json.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import static org.example.Tables.*;
import static org.jooq.impl.DSL.field;

public interface UI {
    Scanner scanner = new Scanner(System.in);
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    File customerDump = new File("src/main/resources/customer_dump2.json");

    static Command getCommand() {
        Command cmd = Command.UNKNOWN_CMD;
        while (cmd == Command.UNKNOWN_CMD) {
            UI.menu();
            try {
                System.out.print("Input command: ");
                cmd = Command.fromInt(Integer.parseInt(scanner.nextLine()));
                if (cmd == Command.UNKNOWN_CMD) {
                    System.out.println("Unknown command!");
                }
            } catch (NumberFormatException e) {
                scanner.nextLine();
                System.out.println("Unknown command!");
            }
        }

        return cmd;
    }

    static void menu() {
        System.out.println("\nMenu:");
        System.out.println("0. Exit");
        System.out.println("1. Find customers by firstname");
        System.out.println("2. Get customers sorted by birthdate");
        System.out.println("3. Get min birthdate among customers grouped by firstname");
        System.out.println("4. Insert new customer");
        System.out.println("5. Update customer");
        System.out.println("6. SQL Get all customers");
        System.out.println("7. SQL Get orders of customer");
        System.out.println("8. SQL Update");
        System.out.println("9. SQL Insert");
        System.out.println("10. SQL Delete");
        System.out.println("11. SQL Procedure");
        System.out.println("12. Json read");
        System.out.println("13. Json update");
        System.out.println("14. Json insert");
        System.out.println("14. Json del");
        System.out.println();
    }

    static void handle(Command cmd, DSLContext db) throws IOException {
        switch (cmd) {
            case EXIT -> {}
            case CMD1 -> {
                System.out.print("Input firstname: ");
                String firstName = scanner.nextLine();
                List<Customer> customers = customerDAO.findByFirstName(firstName);
                for (Customer customer : customers) {
                    System.out.println(customer);
                }
            }
            case CMD2 -> {
                System.out.println("Result:");
                List<Customer> customers = customerDAO.sortedByBirthDate();
                for (Customer customer : customers) {
                    System.out.println(customer);
                }
            }
            case CMD3 -> {
                System.out.println("Result:");
                Map<String, Optional<Customer>> customers = customerDAO.minBirthDateGroupByName();
                for (var entry : customers.entrySet()) {
                    if (entry.getValue().isPresent())
                        System.out.println(entry.getKey() + " - " + entry.getValue().get());
                }
            }
            case CMD4 -> {
                Customer customer = UI.inputCustomer();
                customerDAO.insert(customer);
                System.out.println("Successfully insert customer.");
            }
            case CMD5 -> {
                Customer c = Customer.builder().firstName("Bob").lastName("Marley")
                        .birthDate(LocalDate.of(1999, 1, 1))
                        .id(UUID.fromString("ae42a131-b67d-4737-80ac-ece650baf040")).build();
                System.out.print("Input firstname: ");
                String firstName = scanner.nextLine();
                c.setFirstName(firstName);
                customerDAO.update(c);
                System.out.println("Successfully update customer.");
            }
            case SQL_ONE_TABLE -> {
                Result<Record> result = db.select().from(CUSTOMER).fetch();

                for (Record r : result) {
                    UUID id = (UUID) r.getValue(field("id"));
                    String firstName = (String) r.getValue(field("first_name"));
                    String email = (String) r.getValue(field("email"));

                    System.out.println("ID: " + id + " firstName: " + firstName + " email: " + email);
                }
            }
            case SQL_MANY_TABLES -> {
                UUID id = UUID.fromString("3af43dc3-b687-4053-96b9-1119604d2789");
                Map<Customer, List<Order>> result = db
                        .select(CUSTOMER.fields())
                        .select(ORDERS.fields())
                        .from(CUSTOMER)
                        .join(ORDERS).on(CUSTOMER.ID.eq(ORDERS.CUSTOMER_ID))
                        .where(CUSTOMER.ID.eq(id))
                        .fetchGroups(
                            r -> r.into(CUSTOMER).into(Customer.class),
                            r -> r.into(ORDERS).into(Order.class));

                for (Customer c : result.keySet()) {
                    System.out.println("ID: " + id  + " firstName: " + c.getFirstName() + " email: " + c.getEmail());
                    for (Order o : result.get(c)) {
                        System.out.println("ID: " + o.getId() + " date: " + o.getDate() + " cId: " + o.getCustomerId());
                    }
                }
            }
            case SQL_UPDATE -> {
                UUID id = UUID.fromString("3af43dc3-b687-4053-96b9-1119604d2789");
                db.update(CUSTOMER)
                  .set(CUSTOMER.LAST_NAME, "Alex")
                  .where(CUSTOMER.ID.eq(id))
                  .execute();
                System.out.println("Successfully update customer.");
            }
            case SQL_INSERT -> {
                Customer c = UI.inputCustomer();
                db.insertInto(CUSTOMER)
                    .set(db.newRecord(CUSTOMER, c))
                    .returning()
                    .fetchOptional()
                    .orElseThrow(() -> new DataAccessException("Error inserting entity: " + c.getId()))
                    .into(Customer.class);

                System.out.println("Successfully insert customer.");
            }
            case SQL_DEL -> {
                UUID id = UUID.fromString("eb30af02-b835-4556-b8b4-54525442c197");
                boolean success = db.deleteFrom(CUSTOMER)
                        .where(CUSTOMER.ID.eq(id))
                        .execute() == 1;
                if (success) {
                    System.out.println("Successfully delete customer.");
                }
            }
            case SQL_PROC -> {
                Routines.changeStorageCnt(db.configuration(), "CRAFTER MLR-3464", 4);
                System.out.println("Successfully call proc.");
            }
            case JSON_READ -> {
                Customer[] customers = objectMapper.readValue(customerDump, Customer[].class);
                for (Customer c : customers) {
                    System.out.println(c.toString());
                }
            }
            case JSON_UPDATE -> {
                JSONObject[] customers = objectMapper.readValue(customerDump, JSONObject[].class);
                for (JSONObject c : customers) {
                    if (c.get("first_name").equals("Dimn")) {
                        c.put("first_name", "Diman");
                        System.out.println(c.toString());
                    }
                }
                objectMapper.writeValue(customerDump, customers);
                System.out.println("Successfully update.");
            }
            case JSON_INSERT -> {
                List<Customer> customers = Arrays.stream(objectMapper.readValue(customerDump, Customer[].class)).toList();
                Customer customer = UI.inputCustomer();
                customers.add(customer);
                objectMapper.writeValue(customerDump, customers);
                System.out.println("Successfully insert.");
            }
            case JSON_DEL -> {
                List<Customer> customers = new ArrayList<>(Arrays.stream(objectMapper.readValue(customerDump, Customer[].class)).toList());
//                Customer c = Customer.builder().firstName("Tom").lastName("Oliver").birthDate(LocalDate.of(1999, 1, 1))
//                        .email("a1@example.com").phone("+7-206-986-76-7").id(UUID.fromString("ae42a131-b67d-4737-80ac-ece650baf040")).build();
                customers.remove(customers.get(0));
                objectMapper.writeValue(customerDump, customers);
                System.out.println("Successfully remove.");
            }
            case ADD1 -> {
//                List<String> strCustomers = new ArrayList<>();
//                try (BufferedReader br = new BufferedReader(new FileReader(customerDump))) {
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        strCustomers.add(line);
//                    }
//                }
//                try (BufferedWriter bw = new BufferedWriter(new FileWriter(customerDump))) {
//                    String line;
//                    for (String str : strCustomers) {
//                        bw.write(str + ",");
//                        bw.newLine();
//                    }
//                }

                System.out.println("Successfully rewrite.");
            }
            default -> System.out.println("Cant handle command!");
        }
    }

    static Customer inputCustomer() {
        System.out.print("Input firstname: ");
        String firstName = scanner.nextLine();
        System.out.print("Input lastname: ");
        String lastName = scanner.nextLine();
        LocalDate birthdate = LocalDate.of(1985, 7, 24);
        System.out.print("Input birthdate (yyyy-mm-dd): ");
        try {
            birthdate = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.print("Cant parse birthdate!");
        }
        return Customer.builder().firstName(firstName).lastName(lastName).birthDate(birthdate).id(UUID.randomUUID()).build();
    }
}
