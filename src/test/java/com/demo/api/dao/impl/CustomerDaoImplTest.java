package com.demo.api.dao.impl;

import static com.demo.api.constant.AppConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

import com.demo.api.repository.CustomerRepository;
import com.demo.api.repository.entity.Customer;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

@ExtendWith(MockitoExtension.class)
class CustomerDaoImplTest {

    @InjectMocks private CustomerDaoImpl customerDao;

    @Mock
    @Qualifier(CUSTOMER_REPOSITORY)
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        customerDao = null;
    }

    @Test
    void retrieveCustomer() {
        Mockito.when(customerRepository.findById(anyLong()))
                .thenReturn(
                        Optional.of(Customer.builder().customerName("Customer1").id(1L).build()));
        Customer customer = customerDao.retrieveCustomer(1L);
        assertNotNull(customer);
    }

    @Test
    void retrieveCustomerWithError() {
        Mockito.when(customerRepository.findById(anyLong())).thenThrow(RuntimeException.class);
        assertThrows(
                RuntimeException.class,
                () -> {
                    Customer customer = customerDao.retrieveCustomer(1L);
                });
    }
}
