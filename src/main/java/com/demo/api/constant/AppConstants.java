package com.demo.api.constant;

/**
 * Class file to keep all application constants used for rewards app
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
public final class AppConstants {

    /** Private constructor */
    private AppConstants() {}

    public static final String CUSTOMER_ID = "customerId";

    public static final String CUSTOMER_ID_PARAM = "/{customerId}";

    public static final String REWARDS_ENDPOINT = "/rewards";

    public static final String TRANSACTIONS_ENDPOINT = "/transactions";

    public static final String REWARDS_SERVICE = "rewardsService";

    public static final String REWARDS_DAO = "rewardsDao";

    public static final String TRANSACTIONS_SERVICE = "transactionsService";

    public static final String TRANSACTIONS_DAO = "transactionsDao";
    public static final String CUSTOMER_DAO = "customerDao";

    public static final String TRANSACTIONS_REPOSITORY = "transactionsRepository";
    public static final String CUSTOMER_REPOSITORY = "customerRepository";
}
