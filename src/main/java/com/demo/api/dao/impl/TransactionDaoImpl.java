package com.demo.api.dao.impl;

import static com.demo.api.constant.AppConstants.TRANSACTIONS_DAO;
import static com.demo.api.constant.AppConstants.TRANSACTIONS_REPOSITORY;

import com.demo.api.dao.TransactionDao;
import com.demo.api.exception.TransactionException;
import com.demo.api.repository.TransactionRepository;
import com.demo.api.repository.entity.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Implementation for @{@link TransactionDao} which is used to provide handling for customer
 * transactions to interact with database
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Repository(TRANSACTIONS_DAO)
public class TransactionDaoImpl implements TransactionDao {

    /** Logger declaration. */
    private static final Logger LOGGER = LogManager.getLogger(TransactionDaoImpl.class);

    public static final String UNABLE_TO_CREATE_TRANSACTION_WITH_CUSTOMER_ID =
            "Unable to create a transaction = %s for customer with customerId = %s";

    /** Autowired instance for {@link TransactionRepository} */
    @Autowired
    @Qualifier(TRANSACTIONS_REPOSITORY)
    private TransactionRepository transactionRepository;

    /**
     * method which is used to add at customer transaction
     *
     * @param transaction - transaction
     * @return transaction
     */
    @Override
    public Transaction addTransaction(Transaction transaction) {
        try {
            return transactionRepository.save(transaction);
        } catch (Exception ex) {
            throw new TransactionException(
                    String.format(
                            UNABLE_TO_CREATE_TRANSACTION_WITH_CUSTOMER_ID,
                            transaction,
                            transaction.getCustomer().getId()));
        }
    }
}
