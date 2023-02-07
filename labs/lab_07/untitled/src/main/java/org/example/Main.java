package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.entity.Customer;
import org.example.entity.Customer2;
import org.example.ui.Command;
import org.example.ui.UI;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.codegen.GenerationTool;
import org.jooq.impl.DSL;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import static org.example.ui.Command.*;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class Main {
    public static void main(String[] args) throws Exception {
        String userName = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/music_shop_db";

//        ObjectMapper objectMapper = JsonMapper.builder()
//                .addModule(new JavaTimeModule())
//                .build();
//        File customerDump = new File("src/main/resources/customer.json");
//
//        Customer2 c = objectMapper.readValue(customerDump, Customer2.class);
//        System.out.println(c);
//        Customer2 c2 = Customer2.builder().firstName("Tom")
//                                            .lastName("Oliver")
//                                            .birthDate(LocalDate.of(1999, 1, 1))
//                                            .email("a1@example.com")
//                                            .phone("+7-206-986-76-7")
//                                            .id(UUID.fromString("ae42a131-b67d-4737-80ac-ece650baf040"))
//                                            .build();
//        objectMapper.writeValue(customerDump, c2);

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {

            DSLContext ctx = DSL.using(conn, SQLDialect.POSTGRES);

            System.out.println("Hello world!");
            Command cmd = UNKNOWN_CMD;
            while (cmd != EXIT) {
                cmd = UI.getCommand();
                UI.handle(cmd, ctx);
            }
        }
    }
}