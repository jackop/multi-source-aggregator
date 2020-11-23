package connector;

import static java.util.Collections.emptyList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import model.Rate;
import model.Table;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ExternalApiConnector {

  private static final String API_URL = "http://api.nbp.pl/api/exchangerates/tables/A";
  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Get the last MID rate for selected currency by code.
   */
  public Optional<BigDecimal> getLastMidRateForCurrency(String currencyCode) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);
    List<Table> tables = mapperTableObject(response.getBody());

    log.info(".getLastMidRateForCurrency() | Trading rates from table: [{}], date of trade: {}",
      tables.get(0).getNo(), tables.get(0).getEffectiveDate());

    return tables.stream().map(Table::getRates)
      .flatMap(List::stream)
      .filter(rate -> rate.getCode().equalsIgnoreCase(currencyCode))
      .map(Rate::getMid)
      .findFirst();
  }

  /**
   * Map json as string for list of objects `Table`
   */
  private List<Table> mapperTableObject(String json) {
    try {
      return objectMapper.readValue(json, new TypeReference<List<Table>>() {});
    } catch (IOException exception) {
      log.info(".mapperTableObject() | Exception for json: {}", json, exception);
    }
    return emptyList();
  }
}
