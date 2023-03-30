package com.demo.api.service;

import com.demo.api.dto.TransactionRequest;
import com.demo.api.dto.TransactionResponse;

import java.util.List;

/**
 * Interface which is used to provide handling for customer transactions
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
public interface TransactionService {

    /**
     * method which is used to add at customer transaction
     *
     * @param transaction - transaction
     * @return transaction
     */
    TransactionResponse addTransaction(TransactionRequest transaction);

    /**
     * method which is used to return all customer transactions for a customer
     *
     * @return transactions
     */
    List<TransactionResponse> getAllCustomerTransactions(Long customerId);

    /**
     * method which is used to return a  transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    TransactionResponse getTransaction(Long transactionId);

    /**
     * method which is used to delete a transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    TransactionResponse deleteTransaction(Long transactionId);
}
