package models;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Rate {

  private String currency;
  private String code;
  private BigDecimal bid;
  private BigDecimal ask;
  private BigDecimal mid;
}
