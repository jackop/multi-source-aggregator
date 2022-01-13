package connector;

import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static models.Currency.USD;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import models.Rate;
import models.Table;

@Slf4j
public class ExternalApiConnector implements Connector {

  public static final String API_URL = "https://api.nbp.pl/api/exchangerates/tables/A";

  /**
   * Get the last MID rate for selected currency by code.
   */
  @Override
  public BigDecimal getValue() {
    try (
      InputStream openStream = new URL(API_URL).openStream();
      Scanner scanner = new Scanner(openStream, valueOf(StandardCharsets.UTF_8))) {
      List<Table> tables = mapperTableObject(scanner.nextLine());

      return tables.stream().map(Table::getRates)
        .flatMap(List::stream)
        .filter(rate -> rate.getCode().equalsIgnoreCase(USD.name()))
        .map(Rate::getMid)
        .findFirst()
        .orElse(BigDecimal.ZERO);
    } catch (IOException exception) {
      log.debug(".mapperTableObject() | Exception during get data for currency: ", exception);
    }

    return BigDecimal.ZERO;
  }

  /**
   * Map json as string for list of objects `Table`
   */
  private List<Table> mapperTableObject(String json) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(json, new TypeReference<List<Table>>() {});
    } catch (IOException exception) {
      log.debug(".mapperTableObject() | Exception for json: {}", json, exception);
    }
    return emptyList();
  }
}
