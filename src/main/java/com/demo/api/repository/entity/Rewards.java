package com.demo.api.repository.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "REWARDS")
@NoArgsConstructor
@AllArgsConstructor
public class Rewards {

    /**
     * Variable declaration for id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Variable declaration for customerId
     */
    private Long customerId;

    /**
     * Variable declaration for customerName
     */
    private String customerName;

    /**
     * Variable declaration for total
     */
    private Double purchaseAmount;

    /**
     * Variable declaration for pointsEarned
     */
    private Long pointsEarned;

    /**
     * Variable declaration for createDate
     */
    @CreationTimestamp
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createDate;
}
