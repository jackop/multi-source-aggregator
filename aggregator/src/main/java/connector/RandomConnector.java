package connector;

import static java.lang.Math.random;
import java.math.BigDecimal;

public class RandomConnector implements Connector {

  public static final int RANDOM_RANGE = 3;
  public static final int RANDOM_DECIMAL = 4;

  @Override
  public BigDecimal getValue() {
    final BigDecimal max = new BigDecimal(RANDOM_RANGE);
    final BigDecimal randFromDouble = BigDecimal.valueOf(random());

    return randFromDouble
      .multiply(max)
      .setScale(RANDOM_DECIMAL, BigDecimal.ROUND_DOWN);
  }
}
