package connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import org.junit.Test;

public class RandomConnectorTest {

  private final RandomConnector randomConnector = new RandomConnector();

  @Test
  public void range_randomConnector_test() {
    // Given
    int range = 3;

    // When
    BigDecimal result = randomConnector.createRandomBigDecimalForRange(range);

    // Then
    assertNotNull(result);
    assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
    assertTrue(result.compareTo(BigDecimal.ZERO) < 3);
  }

  @Test
  public void rangeNull_randomConnector_test() {
    // Given
    int range = 0;

    // When
    BigDecimal result = randomConnector.createRandomBigDecimalForRange(range);

    // Then
    assertNotNull(result);
    assertEquals(0, result.compareTo(BigDecimal.ZERO));
  }

}
