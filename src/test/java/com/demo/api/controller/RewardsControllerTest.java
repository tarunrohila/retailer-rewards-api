package com.demo.api.controller;

import static com.demo.api.constant.AppConstants.REWARDS_SERVICE;
import static org.junit.jupiter.api.Assertions.*;

import com.demo.api.dto.RewardsStatementDto;
import com.demo.api.helper.TestHelper;
import com.demo.api.service.impl.RewardsServiceImpl;
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

@ExtendWith(MockitoExtension.class)
class RewardsControllerTest {

    @InjectMocks private RewardsController rewardsController;

    @Mock
    @Qualifier(REWARDS_SERVICE)
    private RewardsServiceImpl rewardsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        rewardsController = null;
    }

    @Test
    void retrieveRewardsForCustomer() {
        RewardsStatementDto rewardsStatementDto = TestHelper.getRewardsStatementDto();
        Mockito.when(rewardsService.retrieveRewardsForCustomer(1L)).thenReturn(rewardsStatementDto);
        ResponseEntity<?> response = rewardsController.retrieveRewardsForCustomer(1L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void retrieveRewardsForCustomerWhenError() {
        RewardsStatementDto rewardsStatementDto = TestHelper.getRewardsStatementDto();
        Mockito.when(rewardsService.retrieveRewardsForCustomer(1L))
                .thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    ResponseEntity<?> response = rewardsController.retrieveRewardsForCustomer(1L);
                });
    }
}
