package aggregator;

import java.math.BigDecimal;

public interface Sum {

  boolean input(BigDecimal num);

  BigDecimal getSumValue();
}
