package com.hypergrid.dataplatform.seller.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class SellerControllerTest {

  private MockMvc mockMvc;

  @InjectMocks
  private SellerController sellerController;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(sellerController).build();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void test_ping_success() throws Exception {
    mockMvc.perform(get("/v1/seller/ping")).andExpect(status().isOk());
  }

}
