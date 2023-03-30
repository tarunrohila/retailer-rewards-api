package com.demo.api.service.impl;

import static com.demo.api.constant.AppConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.demo.api.dao.impl.CustomerDaoImpl;
import com.demo.api.dao.impl.TransactionDaoImpl;
import com.demo.api.dto.TransactionResponse;
import com.demo.api.exception.CustomerException;
import com.demo.api.exception.TransactionException;
import com.demo.api.helper.TestHelper;
import com.demo.api.repository.entity.Customer;
import com.demo.api.repository.entity.Transaction;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks private TransactionServiceImpl transactionService;

    @Mock
    @Qualifier(TRANSACTIONS_DAO)
    private TransactionDaoImpl transactionDao;

    @Mock
    @Qualifier(CUSTOMER_DAO)
    private CustomerDaoImpl customerDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(transactionService, "firstRewardLimit", 50D);
        ReflectionTestUtils.setField(transactionService, "secondRewardLimit", 100D);
    }

    @AfterEach
    void tearDown() {
        transactionService = null;
    }

    @Test
    void addTransaction() {
        Mockito.when(customerDao.retrieveCustomer(1L))
                .thenReturn(Customer.builder().id(1L).customerName("Customer1").build());
        Mockito.when(transactionDao.addTransaction(any(Transaction.class)))
                .thenReturn(TestHelper.getTransaction());
        TransactionResponse response =
                transactionService.addTransaction(TestHelper.getTransactionRequest());
        assertNotNull(response);
        assertEquals(response.getCustomerId(), 1L);
    }

    @Test
    void addTransactionWhenErrorWithCustomer() {
        Mockito.when(customerDao.retrieveCustomer(1L)).thenThrow(CustomerException.class);
        assertThrows(
                CustomerException.class,
                () -> {
                    TransactionResponse response =
                            transactionService.addTransaction(TestHelper.getTransactionRequest());
                });
    }

    @Test
    void addTransactionWhenErrorWithTransaction() {
        Mockito.when(customerDao.retrieveCustomer(1L))
                .thenReturn(Customer.builder().id(1L).customerName("Customer1").build());
        Mockito.when(transactionDao.addTransaction(any(Transaction.class)))
                .thenThrow(TransactionException.class);
        assertThrows(
                TransactionException.class,
                () -> {
                    TransactionResponse response =
                            transactionService.addTransaction(TestHelper.getTransactionRequest());
                });
    }


    @Test
    void getAllCustomerTransactions() {
        Mockito.when(transactionDao.getAllCustomerTransactions(anyLong()))
                .thenReturn(List.of(TestHelper.getTransaction()));
        List<TransactionResponse> response =
                transactionService.getAllCustomerTransactions(1L);
        assertNotNull(response);
        assertEquals(response.get(0).getCustomerId(), 1L);
    }

    @Test
    void getAllCustomerTransactionsWithError() {
        Mockito.when(transactionDao.getAllCustomerTransactions(anyLong()))
                .thenThrow(TransactionException.class);
               assertThrows( TransactionException.class,
                () -> {
                    List<TransactionResponse> response =
                            transactionService.getAllCustomerTransactions(1L);
                });
    }

    @Test
    void getTransaction() {
        Mockito.when(transactionDao.getTransaction(anyLong()))
                .thenReturn(TestHelper.getTransaction());
        TransactionResponse response =
                transactionService.getTransaction(1L);
        assertNotNull(response);
        assertEquals(response.getCustomerId(), 1L);
    }

    @Test
    void getTransactionWhenErrorWithTransaction() {
        Mockito.when(transactionDao.getTransaction(anyLong()))
                .thenThrow(TransactionException.class);
        assertThrows(
                TransactionException.class,
                () -> {
                    TransactionResponse response =
                            transactionService.getTransaction(1L);
                });
    }

    @Test
    void deleteTransaction() {
        Mockito.when(transactionDao.deleteTransaction(anyLong()))
                .thenReturn(TestHelper.getTransaction());
        TransactionResponse response =
                transactionService.deleteTransaction(1L);
        assertNotNull(response);
        assertEquals(response.getCustomerId(), 1L);
    }

    @Test
    void deleteTransactionWhenErrorWithTransaction() {
        Mockito.when(transactionDao.deleteTransaction(anyLong()))
                .thenThrow(TransactionException.class);
        assertThrows(
                TransactionException.class,
                () -> {
                    TransactionResponse response =
                            transactionService.deleteTransaction(1L);
                });
    }
}
