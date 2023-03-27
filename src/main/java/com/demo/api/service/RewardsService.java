package com.demo.api.service;

import com.demo.api.dto.RewardsStatementDto;

/**
 * Interface which is used to provide handling for methods for Retailer's Reward scheme
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
public interface RewardsService {

    /**
     * Method which is used to retrieve reward points for a customer in the period of last three months
     *
     * @param customerId - customerId
     * @return rewardPoints
     */
    RewardsStatementDto retrieveRewardsForCustomer(Long customerId);
}
