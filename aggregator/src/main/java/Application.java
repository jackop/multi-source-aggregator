import connector.ExternalApiConnector;
import connector.FileConnector;
import connector.RandomConnector;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import service.AggregationService;

@Slf4j
public class Application {

  // Check for: USD, CAD, EUR, GBP
  private static final String CURRENCY = "USD";
  private static final String MARGE_FILE = "marge/currency-marge.txt";

  // Service
  private static final AggregationService service = new AggregationService();

  // Connectors - data producers
  private static final ExternalApiConnector externalApiConnector = new ExternalApiConnector();
  private static final FileConnector fileConnector = new FileConnector();
  private static final RandomConnector randomConnector = new RandomConnector();

  public static void main(String[] args) {
    List<BigDecimal> numbersToAggregate = new ArrayList<>();

    // Source 1: Get mid price of selected currency from the last working day
    BigDecimal currencyPrice = externalApiConnector.getLastMidRateForCurrency(CURRENCY)
      .orElse(BigDecimal.ZERO);
    numbersToAggregate.add(currencyPrice);

    // Source 2: Marge price which exchange is giving for transaction
    BigDecimal margeOfCurrency = fileConnector.getRandomBigDecimalFromFile(MARGE_FILE);
    numbersToAggregate.add(margeOfCurrency);

    // Source 3: Random tax for currency price
    BigDecimal tax = randomConnector.createRandomBigDecimalForRange(3);
    numbersToAggregate.add(tax);

    // RESULT
    BigDecimal result = service.sum(numbersToAggregate);

    log.info("RESULT | Sum of {} equal: [{}]", numbersToAggregate, result);
  }
}
