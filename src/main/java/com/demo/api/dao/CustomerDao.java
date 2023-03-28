package com.demo.api.dao;

import com.demo.api.repository.entity.Customer;

/**
 * Interface which is used to provide handling for customer related functions to interact with database
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
public interface CustomerDao {

    /**
     * method which is used to retrieve at customer
     *
     * @param customerId - customerId
     * @return customer
     */
    Customer retrieveCustomer(Long customerId);
}
