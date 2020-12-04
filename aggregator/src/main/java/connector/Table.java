package connector;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class Table {

  private String table;
  private String no;
  private String tradingDate;
  private String effectiveDate;
  private List<Rate> rates;
}
