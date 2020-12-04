package connector;

import static configuration.AppConstant.RANDOM_DECIMAL;
import static configuration.AppConstant.RANDOM_RANGE;
import static java.lang.Math.random;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class RandomConnector implements Connector {

  @Override
  public BigDecimal getDecimalsFromSource() {
    BigDecimal max = new BigDecimal(RANDOM_RANGE);
    BigDecimal randFromDouble = BigDecimal.valueOf(random());

    return randFromDouble
      .multiply(max)
      .setScale(RANDOM_DECIMAL, BigDecimal.ROUND_DOWN);
  }
}
