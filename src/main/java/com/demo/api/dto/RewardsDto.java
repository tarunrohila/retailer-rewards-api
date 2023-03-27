package com.demo.api.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

/**
 * Dto class for RewardsDto
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardsDto {
    /**
     * Variable declaration for id
     */
    @NotEmpty
    private Long customerId;

    /**
     * Variable declaration for customerName
     */
    @NotEmpty
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
    private LocalDate createDate;
}
