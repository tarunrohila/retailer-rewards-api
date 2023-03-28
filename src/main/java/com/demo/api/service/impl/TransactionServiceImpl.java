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
        return TransactionResponse.builder()
                .pointEarned(calculatePointsForTransaction(createdTransaction.getPurchaseAmount()))
                .purchaseAmount(createdTransaction.getPurchaseAmount())
                .customerName(createdTransaction.getCustomer().getCustomerName())
                .customerId(createdTransaction.getCustomer().getId())
                .createDate(createdTransaction.getCreateDate())
                .id(createdTransaction.getId())
                .build();
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
