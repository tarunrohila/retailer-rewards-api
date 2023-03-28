package com.demo.api.dao.impl;

import static com.demo.api.constant.AppConstants.TRANSACTIONS_REPOSITORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.demo.api.helper.TestHelper;
import com.demo.api.repository.TransactionRepository;
import com.demo.api.repository.entity.Transaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

@ExtendWith(MockitoExtension.class)
class RewardsDaoImplTest {

    @InjectMocks private RewardsDaoImpl rewardsDao;

    @Mock
    @Qualifier(TRANSACTIONS_REPOSITORY)
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        rewardsDao = null;
    }

    @Test
    void retrieveTransactionsForCustomer() {
        Mockito.when(transactionRepository.findLastThreeMonths(anyLong(), any(LocalDate.class)))
                .thenReturn(Optional.of(List.of(TestHelper.getTransaction())));
        List<Transaction> transactions = rewardsDao.retrieveTransactionsForCustomer(1L);
        assertNotNull(transactions);
    }

    @Test
    void retrieveTransactionsForCustomerError() {
        Mockito.when(transactionRepository.findLastThreeMonths(anyLong(), any(LocalDate.class)))
                .thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    List<Transaction> transactions = rewardsDao.retrieveTransactionsForCustomer(1L);
                });
    }
}
