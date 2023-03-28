package com.demo.api.repository;

import static com.demo.api.constant.AppConstants.CUSTOMER_REPOSITORY;

import com.demo.api.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface which is used to create customer repository to interact with DB
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Repository(CUSTOMER_REPOSITORY)
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
