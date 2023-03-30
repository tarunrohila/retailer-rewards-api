package com.demo.api.service.impl;

import static com.demo.api.constant.AppConstants.*;

import com.demo.api.dao.CustomerDao;
import com.demo.api.dao.TransactionDao;
import com.demo.api.dto.TransactionRequest;
import com.demo.api.dto.TransactionResponse;
import com.demo.api.repository.entity.Transaction;
import com.demo.api.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation for @{@link TransactionService} which is used to handle requests for Customer
 * Transactions
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Service(TRANSACTIONS_SERVICE)
public class TransactionServiceImpl implements TransactionService {

    /** Logger declaration. */
    private static final Logger LOGGER = LogManager.getLogger(TransactionServiceImpl.class);

    /** Autowired instance for {@link com.demo.api.dao.TransactionDao} */
    @Autowired
    @Qualifier(TRANSACTIONS_DAO)
    private TransactionDao transactionDao;

    /** Autowired instance for {@link com.demo.api.dao.CustomerDao} */
    @Autowired
    @Qualifier(CUSTOMER_DAO)
    private CustomerDao customerDao;

    @Value("${rewards.app.first-reward-limit:50}")
    private Double firstRewardLimit;

    @Value("${rewards.app.second-reward-limit:100}")
    private Double secondRewardLimit;

    /**
     * method which is used to add at customer transaction
     *
     * @param transactionDto - transactionDto
     * @return transaction
     */
    @Override
    public TransactionResponse addTransaction(TransactionRequest transactionDto) {
        LOGGER.debug(
                "Adding a transaction = [{}] for customerId = [{}] in TransactionServiceImpl.addTransaction",
                transactionDto,
                transactionDto.getCustomerId());
        Transaction transaction =
                Transaction.builder()
                        .purchaseAmount(transactionDto.getPurchaseAmount())
                        .customer(customerDao.retrieveCustomer(transactionDto.getCustomerId()))
                        .build();
        LOGGER.debug(
                "Copying transaction properties from TransactionDto = [{}] to TransactionEntity = [{}] Object",
                transactionDto,
                transaction);
        transaction.setPointsEarned(
                calculatePointsForTransaction(transactionDto.getPurchaseAmount()));
        Transaction createdTransaction = transactionDao.addTransaction(transaction);
        LOGGER.info(
                "New Customer Transaction = [{}] is added for customerId = [{}]",
                createdTransaction,
                transaction.getCustomer().getId());
        LOGGER.debug(
                "Calculated Earned Point for the transaction are {} for customerId = [{}]",
                createdTransaction.getPointsEarned(),
                transaction.getCustomer().getId());
        return mapTransactionEntityToObj(createdTransaction);
    }

    /**
     * method which is used to return all customer transactions for a customer
     *
     * @param customerId
     * @return transactions
     */
    @Override
    public List<TransactionResponse> getAllCustomerTransactions(Long customerId) {
        LOGGER.debug("Retrieving all transactions for a customerId = [{}] in TransactionServiceImpl.getAllCustomerTransactions", customerId);
        List<Transaction> retrievedTransactions = transactionDao.getAllCustomerTransactions(customerId);
        return retrievedTransactions.stream().map(transaction -> mapTransactionEntityToObj(transaction)).collect(Collectors.toList());
    }

    /**
     * method which is used to return a  transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    @Override
    public TransactionResponse getTransaction(Long transactionId) {
        LOGGER.debug("Retrieving transaction for a transactionId = [{}] in TransactionServiceImpl.getTransaction", transactionId);
        Transaction retrievedTransaction = transactionDao.getTransaction(transactionId);
        LOGGER.debug("Retrieved transaction for a transactionId = [{}] is transaction = [{}]", transactionId, retrievedTransaction);
        return mapTransactionEntityToObj(retrievedTransaction);
    }

    private TransactionResponse mapTransactionEntityToObj(Transaction retrievedTransaction) {
        return TransactionResponse.builder()
                .pointEarned(calculatePointsForTransaction(retrievedTransaction.getPurchaseAmount()))
                .purchaseAmount(retrievedTransaction.getPurchaseAmount())
                .customerName(retrievedTransaction.getCustomer().getCustomerName())
                .customerId(retrievedTransaction.getCustomer().getId())
                .createDate(retrievedTransaction.getCreateDate())
                .id(retrievedTransaction.getId())
                .build();
    }

    /**
     * method which is used to delete a transaction
     *
     * @param transactionId - transactionId
     * @return transactions
     */
    @Override
    public TransactionResponse deleteTransaction(Long transactionId) {
        LOGGER.debug("Deleting transaction for a transactionId = [{}] in TransactionServiceImpl.deleteTransaction", transactionId);

        Transaction deletedTransaction = transactionDao.deleteTransaction(transactionId);
        LOGGER.debug("Deleted transaction for a transactionId = [{}] is transaction = [{}]", transactionId, deletedTransaction);
        return mapTransactionEntityToObj(deletedTransaction);
    }

    /**
     * method to calculate points based on first and second reward limits
     *
     * @param purchaseAmount
     * @return points earned for the transaction
     */
    private Long calculatePointsForTransaction(Double purchaseAmount) {
        if (purchaseAmount > firstRewardLimit && purchaseAmount <= secondRewardLimit) {
            return Math.round(purchaseAmount - firstRewardLimit);
        } else if (purchaseAmount > secondRewardLimit) {
            return Math.round(purchaseAmount - secondRewardLimit) * 2
                    + Math.round(secondRewardLimit - firstRewardLimit);
        } else return 0l;
    }
}
