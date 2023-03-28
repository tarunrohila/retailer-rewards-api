package com.demo.api.controller;

import static com.demo.api.constant.AppConstants.TRANSACTIONS_ENDPOINT;
import static com.demo.api.constant.AppConstants.TRANSACTIONS_SERVICE;

import com.demo.api.dto.TransactionRequest;
import com.demo.api.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class which is used to handle requests for Customer Transactions
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@RestController
@RequestMapping(TRANSACTIONS_ENDPOINT)
public class TransactionController {

    /** Logger declaration. */
    private static final Logger LOGGER = LogManager.getLogger(TransactionController.class);

    /** Autowired instance for {@link TransactionService} */
    @Autowired
    @Qualifier(TRANSACTIONS_SERVICE)
    private TransactionService transactionService;

    /**
     * Endpoint which is used to add at customer transaction
     *
     * @param transaction - transaction
     * @return transaction
     */
    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest transaction) {
        LOGGER.info(
                "Adding a transaction = [{}] for customerId = [{}]",
                transaction,
                transaction.getCustomerId());
        LOGGER.debug(
                "Adding a transaction = [{}] for customerId = [{}] in RewardsController.retrieveRewardsForCustomer",
                transaction,
                transaction.getCustomerId());
        return ResponseEntity.status(201).body(transactionService.addTransaction(transaction));
    }
}
