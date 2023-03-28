package com.demo.api.service.impl;

import static com.demo.api.constant.AppConstants.REWARDS_DAO;
import static org.junit.jupiter.api.Assertions.*;

import com.demo.api.dao.impl.RewardsDaoImpl;
import com.demo.api.dto.RewardsStatementDto;
import com.demo.api.helper.TestHelper;
import java.util.Collections;
import java.util.List;
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
class RewardsServiceImplTest {

    @InjectMocks private RewardsServiceImpl rewardsService;

    @Mock
    @Qualifier(REWARDS_DAO)
    private RewardsDaoImpl rewardsDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        rewardsService = null;
    }

    @Test
    void retrieveRewardsForCustomer() {
        Mockito.when(rewardsDao.retrieveTransactionsForCustomer(1L))
                .thenReturn(List.of(TestHelper.getTransaction()));
        RewardsStatementDto rewardsStatementDto = rewardsService.retrieveRewardsForCustomer(1L);
        assertNotNull(rewardsStatementDto);
        assertEquals(rewardsStatementDto.getCustomerId(), 1L);
    }

    @Test
    void retrieveRewardsForCustomerEmptySale() {
        Mockito.when(rewardsDao.retrieveTransactionsForCustomer(1L))
                .thenReturn(Collections.emptyList());
        RewardsStatementDto rewardsStatementDto = rewardsService.retrieveRewardsForCustomer(1L);
        assertNotNull(rewardsStatementDto);
        assertEquals(rewardsStatementDto.getCustomerId(), 1L);
    }

    @Test
    void retrieveRewardsForCustomerWhenError() {
        Mockito.when(rewardsDao.retrieveTransactionsForCustomer(1L))
                .thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    RewardsStatementDto rewardsStatementDto =
                            rewardsService.retrieveRewardsForCustomer(1L);
                });
    }
}
