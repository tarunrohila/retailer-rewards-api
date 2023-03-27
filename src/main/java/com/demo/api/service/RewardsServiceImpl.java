package com.demo.api.service;

import com.demo.api.dao.RewardsDao;
import com.demo.api.dto.RewardsStatementDto;
import com.demo.api.repository.entity.Rewards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.api.constant.RewardsConstants.REWARDS_DAO;
import static com.demo.api.constant.RewardsConstants.REWARDS_SERVICE;

/**
 * Implementation for @{@link RewardsService} which is used to provide handling for methods for Retailer's Reward scheme
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Service(REWARDS_SERVICE)
public class RewardsServiceImpl implements RewardsService {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(RewardsServiceImpl.class);

    /**
     * Autowired instance for {@link com.demo.api.dao.RewardsDao}
     */
    @Autowired
    @Qualifier(REWARDS_DAO)
    private RewardsDao rewardsDao;


    /**
     * Method which is used to retrieve reward points for a customer in the period of last three months
     *
     * @param customerId - customerId
     * @return rewardPoints
     */
    @Override
    public RewardsStatementDto retrieveRewardsForCustomer(Long customerId) {
        LOGGER.debug("retrieving rewards for customerId = [{}] in RewardsServiceImpl.retrieveRewardsForCustomer", customerId);
        List<Rewards> purchaseList = rewardsDao.retrieveRewardsForCustomer(customerId);
        final RewardsStatementDto rewardsStatementDto = new RewardsStatementDto();
        purchaseList.stream().forEach( rewards -> {
            rewardsStatementDto.setCustomerId(rewards.getCustomerId());
            rewardsStatementDto.setCustomerName(rewards.getCustomerName());
            rewardsStatementDto.setTotalPoints(rewardsStatementDto.getTotalPoints() + rewards.getPointsEarned());
            rewardsStatementDto.setTotalPurchase(rewardsStatementDto.getTotalPurchase() + rewards.getPurchaseAmount());
            if(rewardsStatementDto.getPointsByMonth().containsKey(rewards.getCreateDate().getMonth().name())) {
                rewardsStatementDto.getPointsByMonth().put(rewards.getCreateDate().getMonth().name(), rewardsStatementDto.getPointsByMonth().get(rewards.getCreateDate().getMonth().name()) + rewards.getPointsEarned());
            } else {
                rewardsStatementDto.getPointsByMonth().put(rewards.getCreateDate().getMonth().name(), rewards.getPointsEarned());
            }
        });
        return rewardsStatementDto;
    }
}
