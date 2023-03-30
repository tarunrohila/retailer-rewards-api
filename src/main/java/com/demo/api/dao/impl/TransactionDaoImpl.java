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

import java.util.List;

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

    /**
     * method which is used to return all customer transactions for a customer
     *
     * @param customerId
     * @return transactions
     */
    @Override
    public List<Transaction> getAllCustomerTransactions(Long customerId) {
        LOGGER.debug("Retrieving all transactions for customer id = [{}] in TransactionDaoImpl.getAllCustomerTransactions", customerId);
        return transactionRepository.findAllByCustomerId(customerId);
    }

    /**
     * method which is used to return a  transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    @Override
    public Transaction getTransaction(Long transactionId) {
        LOGGER.debug("Retrieving a transaction for TransactionId = [{}] in TransactionDaoImpl.getTransaction", transactionId);
        return transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionException(String.format("Failed to retrieve,  Transaction do not exit for transactionId = [%s]", transactionId)));
    }

    /**
     * method which is used to delete a transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    @Override
    public Transaction deleteTransaction(Long transactionId) {
        LOGGER.debug("Retrieving a transaction for TransactionId = [{}] in TransactionDaoImpl.getTransaction", transactionId);
        Transaction transactionToDelete = transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionException(String.format("Failed to delete, Transaction doesnot exit for transactionId = %s", transactionId)));
        transactionRepository.deleteById(transactionId);
        return transactionToDelete;
    }
}
