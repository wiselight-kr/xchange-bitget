package org.knowm.xchange.bitget.service;

import org.knowm.xchange.bitget.BitgetException;
import org.knowm.xchange.bitget.BitgetExchange;
import org.knowm.xchange.bitget.dto.BitgetResponse;
import org.knowm.xchange.bitget.dto.trade.BitgetOrderData;
import org.knowm.xchange.bitget.dto.trade.BitgetOrderRequestPayload;
import org.knowm.xchange.client.ResilienceRegistries;

import java.io.IOException;

import static org.knowm.xchange.bitget.BitgetExchange.PARAM_PASSPHRASE;

public class BitgetTradeServiceRaw extends BitgetBaseService {
  /**
   * Constructor
   *
   * @param exchange
   */
  protected BitgetTradeServiceRaw(BitgetExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }

  public BitgetResponse<BitgetOrderData> placeBitgetOrder(BitgetOrderRequestPayload payload)
      throws IOException {
    try {
      return decorateApiCall(
              () ->
                      bitgetAuthenticated.placeOrder(
                              exchange.getExchangeSpecification().getApiKey(),
                              signatureCreator,
                              (String)
                                      exchange
                                              .getExchangeSpecification()
                                              .getExchangeSpecificParametersItem(PARAM_PASSPHRASE),
                              exchange.getNonceFactory(),
                              payload))
              .call();
    } catch (BitgetException e) {
      throw handleError(e);
    }
  }
}
