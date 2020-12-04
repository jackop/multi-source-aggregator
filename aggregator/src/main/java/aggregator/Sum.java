package aggregator;

import java.math.BigDecimal;

public interface Sum {

  void input(BigDecimal num);

  BigDecimal getSumValue();
}
