package com.hypergrid.dataplatform.seller.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.hypergrid.common.domain.Customer;
import com.hypergrid.common.domain.Receipt;

public class sellerServiceImpl {

  @Spy
  private SellerTicketServiceImpl sellerTicketSevice;
  private Customer customer;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    customer = new Customer();
    customer.setCreditCardNumber("1234");
    customer.setCustomerName("Test Customer");
  }

  @After
  public void tearDown() throws Exception {
    customer = null;
  }

  @Test
  public void test_buyTicket_success() {
    Receipt receipt = sellerTicketSevice.buyTicket("testMovieType", 100.00d, customer);
    assertEquals(new Double(100.00), receipt.getPrice());
  }

}
