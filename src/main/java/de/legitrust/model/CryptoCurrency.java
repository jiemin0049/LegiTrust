package de.legitrust.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CryptoCurrency {
  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("rank")
  private String rank;

  @JsonProperty("price_usd")
  private String usdPrice;

  @JsonProperty("price_btc")
  private String btcPrice;

  @JsonProperty("24h_volume_usd")
  private String volume;

  @JsonProperty("market_cap_usd")
  private String marketCap;

  @JsonProperty("available_supply")
  private String supply;

  @JsonProperty("total_supply")
  private String totalSupply;

  @JsonProperty("max_supply")
  private String maxSupply;

  @JsonProperty("percent_change_1h")
  private String percentOneHour;

  @JsonProperty("percent_change_24h")
  private String percentOneDay;

  @JsonProperty("percent_change_7d")
  private String percentSevenDays;

  @JsonProperty("last_updated")
  private String lastUpdated;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getUsdPrice() {
    return usdPrice;
  }

  public void setUsdPrice(String usdPrice) {
    this.usdPrice = usdPrice;
  }

  public String getBtcPrice() {
    return btcPrice;
  }

  public void setBtcPrice(String btcPrice) {
    this.btcPrice = btcPrice;
  }

}
