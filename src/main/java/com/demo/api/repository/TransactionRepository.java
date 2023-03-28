package com.demo.api.repository;

import com.demo.api.repository.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.demo.api.constant.AppConstants.TRANSACTIONS_REPOSITORY;

/**
 * Interface which is used to create repository to interact with DB
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Repository(TRANSACTIONS_REPOSITORY)
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "select * from TRANSACTIONS where CUSTOMER_ID = :customerId and CREATE_DATE >= :createDate", nativeQuery = true)
    Optional<List<Transaction>> findLastThreeMonths(@Param("customerId") Long customerId, @Param("createDate") LocalDate createDate);
}
