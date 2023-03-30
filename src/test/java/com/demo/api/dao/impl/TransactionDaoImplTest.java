package com.demo.api.dao.impl;

import static com.demo.api.constant.AppConstants.TRANSACTIONS_REPOSITORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.demo.api.exception.TransactionException;
import com.demo.api.helper.TestHelper;
import com.demo.api.repository.TransactionRepository;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TransactionDaoImplTest {

    @InjectMocks private TransactionDaoImpl transactionDao;

    @Mock
    @Qualifier(TRANSACTIONS_REPOSITORY)
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        transactionDao = null;
    }

    @Test
    void addTransaction() {
        Mockito.when(transactionRepository.save(any())).thenReturn(TestHelper.getTransaction());
        Transaction transaction = transactionDao.addTransaction(TestHelper.getTransaction());
        assertNotNull(transaction);
        assertEquals(1L, transaction.getId());
    }

    @Test
    void addTransactionWithError() {
        Mockito.when(transactionRepository.save(any())).thenReturn(RuntimeException.class);
        assertThrows(
                TransactionException.class,
                () -> {
                    Transaction transaction =
                            transactionDao.addTransaction(TestHelper.getTransaction());
                });
    }

    @Test
    void getAllCustomerTransactions() {
        Mockito.when(transactionRepository.findAllByCustomerId(any())).thenReturn(List.of(TestHelper.getTransaction()));
        List<Transaction> transaction = transactionDao.getAllCustomerTransactions(1L);
        assertNotNull(transaction);
        assertEquals(1L, transaction.get(0).getId());
    }

    @Test
    void getAllCustomerTransactionsWithError() {
        Mockito.when(transactionRepository.findAllByCustomerId(any())).thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    List<Transaction> transaction =
                            transactionDao.getAllCustomerTransactions(1L);
                });
    }

    @Test
    void getTransaction() {
        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.of(TestHelper.getTransaction()));
        Transaction transaction = transactionDao.getTransaction(1L);
        assertNotNull(transaction);
        assertEquals(1L, transaction.getId());
    }

    @Test
    void getTransactionWithError() {
        Mockito.when(transactionRepository.findById(any())).thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    Transaction transaction = transactionDao.getTransaction(1L);
                });
    }

    @Test
    void deleteTransaction() {
        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.of(TestHelper.getTransaction()));
        Mockito.doNothing().when(transactionRepository).deleteById(anyLong());
        Transaction transaction = transactionDao.deleteTransaction(1L);
        assertNotNull(transaction);
        assertEquals(1L, transaction.getId());
    }

    @Test
    void deleteTransactionWithError() {
        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.of(TestHelper.getTransaction()));
        Mockito.doThrow(RuntimeException.class).when(transactionRepository).deleteById(anyLong());
        assertThrows(
                RuntimeException.class,
                () -> {
                            Transaction transaction = transactionDao.deleteTransaction(1L);
                });
    }
}
