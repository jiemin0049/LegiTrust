package de.legitrust.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.legitrust.exception.CryptoCurrencyNotFoundExcption;
import de.legitrust.model.CryptoCurrency;
import de.legitrust.service.LegiTrustService;

@RestController
public class LegiTrustController {

  @Autowired
  private LegiTrustService service;

  RestTemplate restTemplate = new RestTemplate();

  @RequestMapping(value = "/cryptocurrency/{starttime}/{endtime}", method = RequestMethod.GET)
  public ResponseEntity<Object> getDataHistrory(@PathVariable("starttime") String startTime, @PathVariable("endtime") String endTime) {
    // Here should check date format of start time and end time.
    List<JSONObject> jsonList = service.getDataHistory(startTime, endTime);
    return new ResponseEntity<Object>(jsonList, HttpStatus.OK);
  }

  @RequestMapping(value = "/cryptocurrency/{currency}", method = RequestMethod.GET)
  public ResponseEntity<List<JSONObject>> getDataByCurrency(@PathVariable("currency") String currency) {
    List<JSONObject> jsonList = service.getDataByCurrency(currency);
    return new ResponseEntity<List<JSONObject>>(jsonList, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> createSomething(@RequestBody Map<String, String> attributeMap) {
    // TODO: Create something and return id.
    HttpHeaders headers = new HttpHeaders();
    try {
      headers.setLocation(new URI("/customers/" + "id0"));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/cryptocurrency/list/{currency}", method = RequestMethod.GET)
  public ResponseEntity<List<CryptoCurrency>> getDataList(@PathVariable("currency") String currency) {
    List<CryptoCurrency> cryptoList = new ArrayList<>();
    CryptoCurrency cc1 = new CryptoCurrency();
    cc1.setName("Bitcoin");
    cc1.setRank("1");
    cc1.setUsdPrice("13402.2");
    cc1.setBtcPrice("1.0");

    CryptoCurrency cc2 = new CryptoCurrency();
    cc2.setName("Ethereum");
    cc2.setRank("2");
    cc2.setUsdPrice("429.546");
    cc2.setBtcPrice("0.0329421");

    CryptoCurrency cc3 = new CryptoCurrency();
    cc3.setName("Bitcoin Cash");
    cc3.setRank("3");
    cc3.setUsdPrice("1459.01");
    cc3.setBtcPrice("0.111195");

    cryptoList.add(cc1);
    cryptoList.add(cc2);
    cryptoList.add(cc3);
    return new ResponseEntity<List<CryptoCurrency>>(cryptoList, HttpStatus.OK);
  }

  @ExceptionHandler(CryptoCurrencyNotFoundExcption.class)
  public ResponseEntity<String> handleCustomerNotFound(Exception e) {
    return new ResponseEntity<String>("{\"reason\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({ IllegalArgumentException.class })
  public ResponseEntity<String> handleBadRequest(Exception e) {
    return new ResponseEntity<String>("{\"reason\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
  }
}