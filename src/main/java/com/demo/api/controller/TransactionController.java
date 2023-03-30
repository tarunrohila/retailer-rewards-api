package com.demo.api.controller;

import com.demo.api.dto.TransactionRequest;
import com.demo.api.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.demo.api.constant.AppConstants.*;

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
     * Endpoint which is used to return all customer transactions for a customer
     *
     * @return transactions
     */
    @GetMapping(CUSTOMER_ENDPOINT + CUSTOMER_ID_PARAM)
    public ResponseEntity<?> getAllCustomerTransactions(@PathVariable(value = CUSTOMER_ID) Long customerId) {
        LOGGER.info(
                "Retrieving all transactions for customerId = [{}]",
                customerId);
        LOGGER.debug(
                "Retrieving all transactions = [{}] for customerId = [{}] in RewardsController.getAllCustomerTransactions",
                customerId);
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAllCustomerTransactions(customerId));
    }

    /**
     * Endpoint which is used to return a  transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    @GetMapping(TRANSACTION_ID_PARAM)
    public ResponseEntity<?> getTransaction(@PathVariable(value = TRANSACTION_ID) Long transactionId) {
        LOGGER.info(
                "Retrieving a transaction for transactionId = [{}]",
                transactionId);
        LOGGER.debug(
                "Adding a transaction for transactionId = [{}] in RewardsController.getTransaction",
                transactionId);
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransaction(transactionId));
    }


    /**
     * Endpoint which is used to delete a transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    @DeleteMapping(TRANSACTION_ID_PARAM)
    public ResponseEntity<?> deleteTransaction(@PathVariable(value = TRANSACTION_ID) Long transactionId) {
        LOGGER.info(
                "Deleting a transaction for transactionId = [{}]",
                transactionId);
        LOGGER.debug(
                "Deleting a transaction for transactionId = [{}] in RewardsController.deleteTransaction",
                transactionId);
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.deleteTransaction(transactionId));
    }

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
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.addTransaction(transaction));
    }
}
