package com.demo.api.dao.impl;

import com.demo.api.dao.CustomerDao;
import com.demo.api.exception.CustomerException;
import com.demo.api.repository.CustomerRepository;
import com.demo.api.repository.TransactionRepository;
import com.demo.api.repository.entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import static com.demo.api.constant.AppConstants.CUSTOMER_DAO;
import static com.demo.api.constant.AppConstants.CUSTOMER_REPOSITORY;

/**
 * Implementation for @{@link CustomerDao} which is used to provide handling for customer operations to interact with database
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Repository(CUSTOMER_DAO)
public class CustomerDaoImpl implements CustomerDao {


    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(CustomerDaoImpl.class);
    public static final String UNABLE_TO_RETRIEVE_CUSTOMER_WITH_CUSTOMER_ID = "Customer does not exist with customerId = %s";

    /**
     * Autowired instance for {@link TransactionRepository}
     */
    @Autowired
    @Qualifier(CUSTOMER_REPOSITORY)
    private CustomerRepository customerRepository;

    /**
     * method which is used to retrieve at customer
     *
     * @param customerId - customerId
     * @return customer
     */
    @Override
    public Customer retrieveCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerException(String.format(UNABLE_TO_RETRIEVE_CUSTOMER_WITH_CUSTOMER_ID, customerId)));
    }
}
