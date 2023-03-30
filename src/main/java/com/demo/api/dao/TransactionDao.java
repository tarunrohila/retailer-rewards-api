package com.demo.api.dao;

import com.demo.api.repository.entity.Transaction;

import java.util.List;

/**
 * Interface which is used to provide handling for customer transactions to interact with database
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
public interface TransactionDao {

    /**
     * method which is used to add at customer transaction
     *
     * @param transaction - transaction
     * @return transaction
     */
    Transaction addTransaction(Transaction transaction);

    /**
     * method which is used to return all customer transactions for a customer
     *
     * @param customerId
     * @return transactions
     */
    List<Transaction> getAllCustomerTransactions(Long customerId);

    /**
     * method which is used to return a  transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    Transaction getTransaction(Long transactionId);

    /**
     * method which is used to delete a transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    Transaction deleteTransaction(Long transactionId);
}
