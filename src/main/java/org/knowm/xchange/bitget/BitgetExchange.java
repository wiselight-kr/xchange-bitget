package org.knowm.xchange.bitget;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitget.service.BitgetTradeService;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.utils.nonce.CurrentTimeIncrementalNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class BitgetExchange extends BaseExchange implements Exchange {

    public static final String PARAM_PASSPHRASE = "passphrase";
    private static ResilienceRegistries RESILIENCE_REGISTRIES;
    private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeIncrementalNonceFactory(MILLISECONDS);

    @Override
    protected void initServices() {
        this.tradeService = new BitgetTradeService(this, getResilienceRegistries());
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        return nonceFactory;
    }

    @Override
    public ResilienceRegistries getResilienceRegistries() {
        if (RESILIENCE_REGISTRIES == null) {
            RESILIENCE_REGISTRIES = BitgetResilience.createRegistries();
        }
        return RESILIENCE_REGISTRIES;
    }

    @Override
    public ExchangeSpecification getDefaultExchangeSpecification() {
        ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
        exchangeSpecification.setSslUri("https://api.bitget.com");
        exchangeSpecification.setHost("api.bitget.com");
        exchangeSpecification.setExchangeName("Bitget");

        return exchangeSpecification;
    }
}
