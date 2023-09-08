package org.knowm.xchange.bitget;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import java.util.Base64;

public class BitgetDigest extends BaseParamsDigest {
    private BitgetDigest(String secretKey) {
        super(secretKey, HMAC_SHA_256);
    }

    public static BitgetDigest createInstance(String secretKey) {
        return secretKey == null ? null : new BitgetDigest(secretKey);
    }

    @Override
    public String digestParams(RestInvocation restInvocation) {
        StringBuilder sb = new StringBuilder();
        sb.append(restInvocation.getHttpHeadersFromParams().getOrDefault("ACCESS-TIMESTAMP", null));
        sb.append(restInvocation.getHttpMethod());
        sb.append(restInvocation.getPath());
        if ("GET".equals(restInvocation.getHttpMethod())
                && !restInvocation.getQueryString().isEmpty()) {
            sb.append("?" + restInvocation.getQueryString());
        }
        sb.append(restInvocation.getRequestBody());

        Mac mac = getMac();
        mac.update(sb.toString().getBytes());

        return Base64.getEncoder().encodeToString(mac.doFinal());
    }
}

