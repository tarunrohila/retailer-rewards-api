package com.demo.api.dto;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import lombok.*;

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
@ToString
public class TransactionResponse {

    private Long id;

    /** Variable declaration for id */
    @NotEmpty private Long customerId;

    /** Variable declaration for customerName */
    private String customerName;

    /** Variable declaration for total */
    private Double purchaseAmount;

    /** Variable declaration for id */
    @NotEmpty private Long pointEarned;

    /** Variable declaration for createDate */
    private LocalDate createDate;
}
