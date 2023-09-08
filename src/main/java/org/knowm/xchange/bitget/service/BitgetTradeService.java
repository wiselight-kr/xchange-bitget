package org.knowm.xchange.bitget.service;

import org.knowm.xchange.bitget.BitgetException;
import org.knowm.xchange.bitget.BitgetExchange;
import org.knowm.xchange.bitget.dto.BitgetResponse;
import org.knowm.xchange.bitget.dto.trade.BitgetOrderData;
import org.knowm.xchange.bitget.dto.trade.BitgetOrderRequestPayload;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.service.trade.TradeService;

import java.io.IOException;

public class BitgetTradeService extends BitgetTradeServiceRaw implements TradeService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitgetTradeService(BitgetExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    BitgetOrderRequestPayload payload = new BitgetOrderRequestPayload(
            marketOrder.getInstrument().toString().replace("/", "") + "_SPBL",
            marketOrder.getType().equals(Order.OrderType.ASK) ? "sell" : "buy",
            "market",
            "normal",
            null,
            marketOrder.getOriginalAmount().toPlainString(),
            null
    );

    BitgetResponse<BitgetOrderData> bitgetResponse =
            placeBitgetOrder(payload);

    if (bitgetResponse.getMsg().equals("success")) return bitgetResponse.getData().getOrderId();
    else
      throw new BitgetException(
              bitgetResponse.getMsg(),
              bitgetResponse.getCode());
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException, FundsExceededException {
    BitgetOrderRequestPayload payload = new BitgetOrderRequestPayload(
            limitOrder.getInstrument().toString().replace("/", "") + "_SPBL",
            limitOrder.getType().equals(Order.OrderType.ASK) ? "sell" : "buy",
            "limit",
            "normal",
            limitOrder.getLimitPrice().toPlainString(),
            limitOrder.getOriginalAmount().toPlainString(),
            null
    );

    BitgetResponse<BitgetOrderData> bitgetResponse =
            placeBitgetOrder(payload);

    if (bitgetResponse.getMsg().equals("success")) return bitgetResponse.getData().getOrderId();
    else
      throw new BitgetException(
              bitgetResponse.getMsg(),
              bitgetResponse.getCode());
  }

}
