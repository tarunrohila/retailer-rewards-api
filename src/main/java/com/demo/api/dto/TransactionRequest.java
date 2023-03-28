package com.demo.api.dto;

import jakarta.validation.constraints.NotEmpty;
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
public class TransactionRequest {

    /** Variable declaration for id */
    @NotEmpty private Long customerId;

    /** Variable declaration for total */
    private Double purchaseAmount;
}
