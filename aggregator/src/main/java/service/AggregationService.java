package service;

import java.math.BigDecimal;
import java.util.List;

public class AggregationService {

  /**
   * Summary aggregator
   */
  public static BigDecimal sum(List<BigDecimal> bigDecimalList) {
    return bigDecimalList.stream()
      .filter(bd -> bd instanceof BigDecimal)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
