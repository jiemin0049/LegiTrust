package de.legitrust.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import de.legitrust.dao.LegiTrustDao;
import de.legitrust.model.CryptoCurrency;
import de.legitrust.service.LegiTrustService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LegiTrustController.class, secure = false)
public class LegiTrustControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LegiTrustService legiTrustService;

  @MockBean
  private LegiTrustDao legiTrustDao;

  List<CryptoCurrency> cryptoList = new ArrayList<>();
  List<JSONObject> jsonList = new ArrayList<>();

  @Before
  public void before() {
    cryptoList.clear();
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

    JSONObject json = new JSONObject();
    json.put("name", "n1");
    json.put("rank", "r1");
    json.put("price_usd", "12.1");
    json.put("price_btc", "1");
    jsonList.add(json);
  }

  @Test
  public void getDataHistory() throws Exception {

    Mockito.when(legiTrustDao.getDataHistory(Mockito.anyString(), Mockito.anyString())).thenReturn(cryptoList);

    Mockito.when(legiTrustService.getDataHistory(Mockito.anyString(), Mockito.anyString())).thenReturn(jsonList);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cryptocurrency/20171206/20171207").accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    System.out.println(result.getResponse().getContentAsString());

    // JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

  }

  @Test
  public void getDataByCurrency() {
  }

}
