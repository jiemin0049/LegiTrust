package de.legitrust;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.legitrust.model.CryptoCurrency;
import de.legitrust.service.LegiTrustService;

@Component
public class ScheduledTask {

  @Autowired
  private LegiTrustService service;

  RestTemplate restTemplate = new RestTemplate();

  @Scheduled(fixedRateString = "${fixedRate}")
  public void getCryptoCurrency() {
    String json = sendCryptoCurrencyRequest();
    List<CryptoCurrency> list = jsonToCryptoCurrencyEntity(json);
    service.save(list);
    System.out.println("CryptoCurrencies are saved in database: " + list.size() );
  }

  public List<CryptoCurrency> jsonToCryptoCurrencyEntity(String json) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, CryptoCurrency.class));
    } catch (IOException e) {
      // TODO: here should handle exception and write in log.
      e.printStackTrace();
    }
    return new ArrayList<CryptoCurrency>();
  }

  private String sendCryptoCurrencyRequest() {
    String answer = "";
    try {
      answer = restTemplate.getForObject(Constants.COIN_MARKET_CAP_URL, String.class);
      return answer;
    } catch (RestClientException e) {
      // TODO: here should handle exception and write in log.
      e.printStackTrace();
    }
    return answer;
  }

}
