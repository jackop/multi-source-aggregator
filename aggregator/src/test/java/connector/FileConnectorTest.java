package connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import org.junit.Test;

public class FileConnectorTest {

  FileConnector fileConnector = new FileConnector();

  @Test
  public void oneNumber_fileConnector_test() {
    // Given
    String fileName = "marge/one-number-file.txt";

    // When
    BigDecimal result = fileConnector.getRandomBigDecimalFromFile(fileName);

    // Then
    assertNotNull(result);
    assertTrue(result.compareTo(BigDecimal.ZERO) > 0);
    assertEquals(result, BigDecimal.valueOf(2.8765));
  }

  @Test(expected = NoSuchElementException.class)
  public void empty_fileConnector_test() {
    // Given
    String fileName = "marge/empty-file.txt";

    // When
    BigDecimal result = fileConnector.getRandomBigDecimalFromFile(fileName);

    // Then
    assertNull(result);
  }
}
