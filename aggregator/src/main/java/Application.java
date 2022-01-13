import connector.Connector;
import connector.ExternalApiConnector;
import connector.FileConnector;
import connector.RandomConnector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import static java.util.Arrays.asList;

@Slf4j
@RequiredArgsConstructor
public class Application {

  public static void main(String[] args) {

    Connector.connectorList.addAll(asList(new FileConnector(), new RandomConnector(), new ExternalApiConnector()));
    double value = Connector.connectorList.stream()
            .map(Connector::getValue)
            .peek(valueToPrint -> log.info(valueToPrint.toPlainString()))
            .mapToDouble(BigDecimal::doubleValue)
            .sum();

    // RESULT
    log.info("RESULT | Value is equal: [{}]", value);

  }
}
