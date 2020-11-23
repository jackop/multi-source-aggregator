package service;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;

public class AggregationServiceTest {

  private final AggregationService service = new AggregationService();

  @Test
  public void zeroValue_aggregationService_test() {
    // Given
    List<BigDecimal> bigDecimals = asList(BigDecimal.ZERO, BigDecimal.ZERO);

    // When
    BigDecimal result = service.sum(bigDecimals);

    // Then
    assertNotNull(result);
    assertEquals(BigDecimal.ZERO, result);
  }

  @Test
  public void emptyList_aggregationService_test() {
    // Given
    List<BigDecimal> bigDecimals = emptyList();

    // When
    BigDecimal result = service.sum(bigDecimals);

    // Then
    assertNotNull(result);
    assertEquals(0, result.compareTo(BigDecimal.ZERO) );
    assertEquals(BigDecimal.ZERO, result);
  }

  @Test
  public void oneValue_aggregationService_test() {
    // Given
    List<BigDecimal> bigDecimals = singletonList(BigDecimal.valueOf(2.890));

    // When
    BigDecimal result = service.sum(bigDecimals);

    // Then
    assertNotNull(result);
    assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
    assertEquals(result, BigDecimal.valueOf(2.890));
  }

  @Test
  public void moreValues_aggregationService_test() {
    // Given
    List<BigDecimal> bigDecimals = asList(BigDecimal.valueOf(2.890), BigDecimal.valueOf(4.2123));

    // When
    BigDecimal result = service.sum(bigDecimals);

    // Then
    assertNotNull(result);
    assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
    assertEquals(result, BigDecimal.valueOf(7.1023));
  }
}
