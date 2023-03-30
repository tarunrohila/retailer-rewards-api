package com.demo.api.controller;

import static com.demo.api.constant.AppConstants.TRANSACTIONS_SERVICE;
import static com.demo.api.helper.TestHelper.getTransactionRequest;
import static com.demo.api.helper.TestHelper.getTransactionResponse;
import static org.junit.jupiter.api.Assertions.*;

import com.demo.api.dto.TransactionRequest;
import com.demo.api.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @InjectMocks private TransactionController transactionController;

    @Mock
    @Qualifier(TRANSACTIONS_SERVICE)
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        transactionController = null;
    }

    @Test
    void addTransaction() {
        TransactionRequest transactionRequest = getTransactionRequest();
        Mockito.when(transactionService.addTransaction(transactionRequest))
                .thenReturn(getTransactionResponse());
        ResponseEntity<?> response = transactionController.addTransaction(transactionRequest);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void addTransactionWithError() {
        TransactionRequest transactionRequest = getTransactionRequest();
        Mockito.when(transactionService.addTransaction(transactionRequest))
                .thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    ResponseEntity<?> response =
                            transactionController.addTransaction(transactionRequest);
                });
    }

    @Test
    void getAllCustomerTransactions() {
        Mockito.when(transactionService.getAllCustomerTransactions(1L))
                .thenReturn(List.of(getTransactionResponse()));
        ResponseEntity<?> response = transactionController.getAllCustomerTransactions(1L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void getAllCustomerTransactionsError() {
        Mockito.when(transactionService.getAllCustomerTransactions(1L))
                .thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    ResponseEntity<?> response = transactionController.getAllCustomerTransactions(1L);
                });
    }

    @Test
    void getTransaction() {
        Mockito.when(transactionService.getTransaction(1L))
                .thenReturn(getTransactionResponse());
        ResponseEntity<?> response = transactionController.getTransaction(1L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void getTransactionWithError() {
        Mockito.when(transactionService.getTransaction(1L))
                .thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    ResponseEntity<?> response = transactionController.getTransaction(1L);
                });
    }

    @Test
    void deleteTransaction() {
        Mockito.when(transactionService.deleteTransaction(1L))
                .thenReturn(getTransactionResponse());
        ResponseEntity<?> response = transactionController.deleteTransaction(1L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void deleteTransactionError() {
        Mockito.when(transactionService.deleteTransaction(1L))
                .thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    ResponseEntity<?> response = transactionController.deleteTransaction(1L);
                });
    }
}
