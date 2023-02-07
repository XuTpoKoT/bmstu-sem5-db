package org.example.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Lombok;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Builder()
@Jacksonized
@JsonPropertyOrder({ "id", "email", "phone", "lastName", "birthDate", "firstName" })
public class Customer2 {
    private UUID id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String email;
    private String phone;

}
