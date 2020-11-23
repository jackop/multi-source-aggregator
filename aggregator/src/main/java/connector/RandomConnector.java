package connector;

import static java.lang.Math.random;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class RandomConnector {

  public BigDecimal createRandomBigDecimalForRange(int range) {
    BigDecimal max = new BigDecimal(range);
    BigDecimal randFromDouble = BigDecimal.valueOf(random());

    return randFromDouble
      .multiply(max)
      .setScale(4, BigDecimal.ROUND_DOWN);
  }
}
