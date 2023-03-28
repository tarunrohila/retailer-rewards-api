package com.demo.api.repository.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Entity class to handle transactions
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Data
@Builder
@Entity
@Table(name = "TRANSACTIONS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "sequence", sequenceName = "transactionSequence", initialValue = 20)
public class Transaction {

    /** Variable declaration for id */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    /** Variable declaration for customerId */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    /** Variable declaration for total */
    private Double purchaseAmount;

    /** Variable declaration for pointsEarned */
    private Long pointsEarned;

    /** Variable declaration for createDate */
    @CreationTimestamp
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createDate;
}
