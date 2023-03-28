package com.demo.api.controller;


import com.demo.api.service.RewardsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.demo.api.constant.AppConstants.*;

/**
 * Class which is used to handle requests for Rewards app
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@RestController
@RequestMapping(REWARDS_ENDPOINT)
public class RewardsController {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(RewardsController.class);

    /**
     * Autowired instance for {@link RewardsService}
     */
    @Autowired
    @Qualifier(REWARDS_SERVICE)
    private RewardsService rewardsService;

    /**
     * Endpoint which is used to retrieve reward points for a customer in the period of last three months
     * @param customerId - customerId
     * @return rewardPoints
     */
    @GetMapping(CUSTOMER_ID_PARAM)
    public ResponseEntity<?> retrieveRewardsForCustomer(@PathVariable(value = CUSTOMER_ID) Long customerId) {
        LOGGER.info("Retrieving reward points for customerId = [{}]", customerId);
        LOGGER.debug("retrieving rewards for customerId = [{}] in RewardsController.retrieveRewardsForCustomer", customerId);
        return ResponseEntity.ok(rewardsService.retrieveRewardsForCustomer(customerId));
    }
}
