package aggregator;

import static service.AggregationService.sum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SumImpl implements Sum {

  List<BigDecimal> numbersToSum = new ArrayList<>();

  @Override
  public boolean input(BigDecimal num) {
    return numbersToSum.add(num);
  }

  @Override
  public BigDecimal getSumValue() {
    return sum(numbersToSum);
  }

}
