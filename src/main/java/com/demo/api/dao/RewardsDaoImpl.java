package com.demo.api.dao;

import com.demo.api.exception.RewardsException;
import com.demo.api.repository.RewardsRepository;
import com.demo.api.repository.entity.Rewards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.demo.api.constant.RewardsConstants.REWARDS_DAO;
import static com.demo.api.constant.RewardsConstants.REWARDS_REPOSITORY;

/**
 * Implementation for @{@link RewardsDao} which is used to provide handling for methods for Retailer's Reward scheme
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Repository(REWARDS_DAO)
public class RewardsDaoImpl implements RewardsDao {


    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(RewardsDaoImpl.class);
    public static final String UNABLE_TO_FIND_CUSTOMER_WITH_CUSTOMER_ID = "Unable to find customer with customerId = %s";

    @Value("${rewards.app.last-months-points:3}")
    private int lastMonthPoints;

    /**
     * Autowired instance for {@link RewardsRepository}
     */
    @Autowired
    @Qualifier(REWARDS_REPOSITORY)
    private RewardsRepository rewardsRepository;
    /**
     * Method which is used to retrieve reward points for a customer in the period of last three months
     *
     * @param customerId - customerId
     * @return rewardPoints
     */
    @Override
    public List<Rewards> retrieveRewardsForCustomer(Long customerId) {
        LOGGER.debug("retrieving rewards for customerId = [{}] in RewardsDaoImpl.retrieveRewardsForCustomer", customerId);
        LocalDate.now().minusMonths(3);
        return rewardsRepository
                .findLastThreeMonths(customerId, LocalDate.now().minusMonths(3))
                .orElseThrow(() ->
                        new RewardsException(String.format(UNABLE_TO_FIND_CUSTOMER_WITH_CUSTOMER_ID, customerId)));
    }
}
