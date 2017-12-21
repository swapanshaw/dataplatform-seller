package com.hypergrid.dataplatform.seller.service.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hypergrid.common.domain.Customer;
import com.hypergrid.common.domain.Receipt;
import com.hypergrid.common.service.BuyerTicketService;
import com.hypergrid.common.service.SellerTicketService;

@Service
public class SellerTicketServiceImpl implements SellerTicketService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ApplicationContext context;

  @Override
  public Receipt buyTicket(final String tickeType, final Double price, final Customer customer) {
    logger.info("Buy ticket call is initiated for ticket type {} for customer {} ", tickeType,
        customer.getCustomerName());
    final Receipt receipt = new Receipt();
    receipt.setCustomer(customer);
    receipt.setPrice(price);
    receipt.setReceiptDate(new Date());
    receipt.setReceiptNumber(UUID.randomUUID().toString());
    WaitAndSendStatusOfBooking(receipt.getReceiptNumber());
    return receipt;
  }

  private void WaitAndSendStatusOfBooking(final String receiptNumber) {
    // Todo should be replaced with executor with thread pull.
    Runnable statusSendingTask = () -> waitAndsendStatus(receiptNumber);
    new Thread(statusSendingTask).start();
  }

  private void waitAndsendStatus(final String receiptNumber) {
    logger.info("Statring sending status in another thread");
    try {
      Thread.sleep(30000);
      final String status = "OK";
      logger.info("Sending status for receipt {} with status as {}", receiptNumber, status);
      context.getBean(BuyerTicketService.class).setTicketStatus(receiptNumber, status);
    } catch (InterruptedException e) {
      logger.error("Eror while spawing thread to send status of ticket booking", e);
    }
  }
}
