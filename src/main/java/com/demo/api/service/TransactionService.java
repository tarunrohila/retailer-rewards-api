package com.demo.api.service;

import com.demo.api.dto.TransactionRequest;
import com.demo.api.dto.TransactionResponse;

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
}
