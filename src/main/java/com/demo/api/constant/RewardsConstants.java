package com.demo.api.constant;

import com.demo.api.service.RewardsService;

/**
 * Class file to keep all application constants used for rewards app
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
public final class RewardsConstants {

    /**
     * Private constructor
     */
    private RewardsConstants() { }

    public static final String CUSTOMER_ID = "customerId";

    public static final String CUSTOMER_ID_PARAM = "/{customerId}";

    public static final String REWARDS_ENDPOINT = "/rewards";

    public static final String REWARDS_SERVICE = "rewardsService";

    public static final String REWARDS_DAO = "rewardsDao";

    public static final String REWARDS_REPOSITORY = "rewardsRepository";

}
