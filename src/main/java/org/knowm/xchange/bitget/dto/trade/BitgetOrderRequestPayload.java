package org.knowm.xchange.bitget.dto.trade;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BitgetOrderRequestPayload {

  @JsonProperty("symbol")
  private String symbol;
  @JsonProperty("side")
  private String side;
  @JsonProperty("orderType")
  private String orderType;
  @JsonProperty("force")
  private String force;
  @JsonProperty("price")
  private String price;
  @JsonProperty("quantity")
  private String quantity;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("clientOrderId")
  private String clientOrderId;

  public BitgetOrderRequestPayload(String symbol,
                                   String side,
                                   String orderType,
                                   String force,
                                   String price,
                                   String quantity,
                                   String clientOrderId) {
    this.symbol = symbol;
    this.side = side;
    this.orderType = orderType;
    this.force = force;
    this.price = price;
    this.quantity = quantity;
    this.clientOrderId = clientOrderId;
  }

}
