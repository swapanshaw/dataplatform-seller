/**
 * 
 */
package com.hypergrid.dataplatform.seller.rest.controller;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Sample Ticket seller rest api.
 * 
 * @author swapanshaw
 */

@RestController
@RequestMapping("/v1/seller")
public class SellerController {
	
  private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

  @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Sample rest api to check service availibility")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad Request")})
  @ResponseBody
  public ResponseEntity<String> ping() throws InterruptedException {
    logger.info("Service is up and running.");
    return new ResponseEntity<String>(JSONObject.quote("Service is up and running"), HttpStatus.OK);
  }
}
