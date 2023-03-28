package com.demo.api.dao;

import com.demo.api.repository.entity.Transaction;

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
}
