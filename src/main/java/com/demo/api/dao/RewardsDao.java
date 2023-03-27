package com.demo.api.dao;

import com.demo.api.repository.entity.Rewards;

import java.util.List;

/**
 * Interface which is used to handle gateway requests for Retailer's Reward scheme
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
public interface RewardsDao {

    /**
     * Method which is used to retrieve reward points for a customer in the period of last three months
     *
     * @param customerId - customerId
     * @return rewardPoints
     */
    List<Rewards> retrieveRewardsForCustomer(Long customerId);
}
