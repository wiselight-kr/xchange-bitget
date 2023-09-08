package org.knowm.xchange.bitget.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitget.BitgetAuthenticated;
import org.knowm.xchange.bitget.BitgetDigest;
import org.knowm.xchange.bitget.BitgetException;
import org.knowm.xchange.bitget.BitgetExchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseResilientExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;

public class BitgetBaseService extends BaseResilientExchangeService<BitgetExchange> implements BaseService {

  protected final BitgetAuthenticated bitgetAuthenticated;
  protected final String apiKey;
  protected final String apiSecret;
  protected final String passphrase;
  protected final String url;
  protected ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitgetBaseService(BitgetExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
    this.bitgetAuthenticated =
        ExchangeRestProxyBuilder.forInterface(
                BitgetAuthenticated.class, exchange.getExchangeSpecification())
            .build();
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.apiSecret = exchange.getExchangeSpecification().getSecretKey();
    this.url = exchange.getExchangeSpecification().getSslUri();
    this.signatureCreator = BitgetDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    this.passphrase =
            (String)
                    exchange.getExchangeSpecification().getExchangeSpecificParametersItem("passphrase");
  }

  protected ExchangeException handleError(BitgetException exception) {
    return new ExchangeException(exception);
  }
}
