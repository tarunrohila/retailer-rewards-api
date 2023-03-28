package com.demo.api.repository.entity;


import jakarta.persistence.*;
import lombok.*;


/**
 * Entity class to handle customers data
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Data
@Builder
@Entity
@Table(name = "CUSTOMERS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "sequence", sequenceName = "customerSequence", initialValue = 10)
public class Customer {

    /**
     * Variable declaration for id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    /**
     * Variable declaration for customerName
     */
    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;
}
