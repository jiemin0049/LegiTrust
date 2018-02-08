package de.legitrust.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.legitrust.dao.LegiTrustDao;
import de.legitrust.model.CryptoCurrency;

@Service
public class LegiTrustService {

  @Autowired
  private LegiTrustDao dao;

  public void save(List<CryptoCurrency> list) {
    dao.save(list);
  }

  public List<JSONObject> getDataHistory(String startTime, String endTime) {
    List<CryptoCurrency> list = dao.getDataHistory(startTime, endTime);
    return convertToJsonList(list);
  }

  public List<JSONObject> getDataByCurrency(String currency) {
    List<CryptoCurrency> list = dao.getDataByCurrency(currency);
    List<JSONObject> jsonList = new ArrayList<>();
    JSONObject json = new JSONObject();
    json.put("name", "n1");
    json.put("rank", "r1");
    json.put("price_usd", "12.1");
    json.put("price_btc", "1");
    jsonList.add(json);
    return jsonList;
    //return convertToJsonList(list);
  }

  private List<JSONObject> convertToJsonList(List<CryptoCurrency> list) {
    List<JSONObject> jsonList = new ArrayList<>();
    for (CryptoCurrency cc : list) {
      JSONObject json = new JSONObject();
      json.put("name", cc.getName());
      json.put("rank", cc.getRank());
      json.put("price_usd", cc.getUsdPrice());
      json.put("price_btc", cc.getBtcPrice());
      jsonList.add(json);
    }
    return jsonList;
  }
}
