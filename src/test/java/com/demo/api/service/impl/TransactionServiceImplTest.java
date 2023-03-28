package com.demo.api.service.impl;

import static com.demo.api.constant.AppConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

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
}
