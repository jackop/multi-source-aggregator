package service;

import java.math.BigDecimal;
import java.util.List;

public class AggregationService {

  /**
   * Summary aggregator
   */
  public BigDecimal sum(List<BigDecimal> bigDecimalList) {
    return bigDecimalList.stream()
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
