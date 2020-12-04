import aggregator.Sum;
import aggregator.SumImpl;
import controller.SumController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Application {

  private static final SumController sumController = new SumController();
  private static final Sum summator = new SumImpl();

  public static void main(String[] args) {

    summator.input(sumController.sumMidValue());

    summator.input(sumController.sumRandomValueFromFile());

    summator.input(sumController.sumRandomDecimalValue());

    // RESULT
    log.info("RESULT | Sum is equal: [{}]", summator.getSumValue());

  }
}
