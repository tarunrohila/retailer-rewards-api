package com.demo.api.repository;

import com.demo.api.repository.entity.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.demo.api.constant.RewardsConstants.REWARDS_REPOSITORY;

/**
 * Interface which is used to create repository to intract with DB
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@Repository(REWARDS_REPOSITORY)
public interface RewardsRepository extends JpaRepository<Rewards, Long> {
    @Query(value = "select * from REWARDS where CUSTOMER_ID = :customerId and CREATE_DATE >= :createDate", nativeQuery = true)
    Optional<List<Rewards>> findLastThreeMonths(@Param("customerId") Long customerId, @Param("createDate") LocalDate createDate);
}
