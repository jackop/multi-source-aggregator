package controller;

import connector.Connector;
import connector.ExternalApiConnector;
import connector.FileConnector;
import connector.RandomConnector;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class SumController {

  private final Connector externalApiConnector = new ExternalApiConnector();
  private final Connector fileConnector = new FileConnector();
  private final Connector randomConnector = new RandomConnector();

  /**
   * Source 1: Get mid price of selected currency from the last working day
   */
  public BigDecimal sumMidValue() {
    BigDecimal currencyPrice = externalApiConnector.getDecimalsFromSource();

    log.info(".sumMidValue() | Adding to sum up: [{}]", currencyPrice);

    return currencyPrice;
  }

  /**
   * Source 2: Marge price which exchange is giving for transaction
   */
  public BigDecimal sumRandomValueFromFile() {
    BigDecimal margeOfCurrency = fileConnector.getDecimalsFromSource();

    log.info(".sumRandomValueFromFile() | Adding to sum up: [{}]", margeOfCurrency);

    return margeOfCurrency;
  }

  /**
   * Source 3: Random tax for currency price
   */
  public BigDecimal sumRandomDecimalValue() {
    BigDecimal tax = randomConnector.getDecimalsFromSource();

    log.info(".sumRandomDecimalValue() | Adding to sum up: [{}]", tax);

    return tax;
  }
}
