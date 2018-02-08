package de.legitrust.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import de.legitrust.model.CryptoCurrency;

@Repository
public class LegiTrustDao {
  public void save(List<CryptoCurrency> list) {
    Date date = Calendar.getInstance().getTime();
    // Here you can use SQL or Hibernate to insert database.
    // name, rank, price in usd, price in btc, date should saved in database.
  }

  public List<CryptoCurrency> getDataHistory(String startTime, String endTime) {
    // Here get data from database.
    return new ArrayList<CryptoCurrency>();
  }

  public List<CryptoCurrency> getDataByCurrency(String currency) {
    // Herer get data from database
    return new ArrayList<CryptoCurrency>();
  }
}
