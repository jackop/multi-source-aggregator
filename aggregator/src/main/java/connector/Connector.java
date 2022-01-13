package connector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface Connector {
  List<Connector> connectorList = new ArrayList<>();
  BigDecimal getValue();
}
