package com.demo.api.helper;

import com.demo.api.dto.RewardsStatementDto;
import com.demo.api.dto.TransactionRequest;
import com.demo.api.dto.TransactionResponse;
import com.demo.api.repository.entity.Customer;
import com.demo.api.repository.entity.Transaction;
import java.time.LocalDate;

public class TestHelper {
    public static TransactionRequest getTransactionRequest() {
        return TransactionRequest.builder().customerId(1L).purchaseAmount(200D).build();
    }

    public static TransactionResponse getTransactionResponse() {
        return TransactionResponse.builder()
                .customerId(1L)
                .purchaseAmount(200D)
                .pointEarned(500L)
                .customerName("Ahsan")
                .id(1L)
                .createDate(LocalDate.now())
                .build();
    }

    public static RewardsStatementDto getRewardsStatementDto() {
        return RewardsStatementDto.builder()
                .customerId(1L)
                .customerName("Customer1")
                .totalPoints(100L)
                .totalPurchase(1000D)
                .build();
    }

    public static Transaction getTransaction() {
        return Transaction.builder()
                .id(1L)
                .purchaseAmount(1D)
                .pointsEarned(100L)
                .createDate(LocalDate.now())
                .customer(Customer.builder().customerName("Customer 1").id(1L).build())
                .build();
    }
}
