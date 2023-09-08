package org.knowm.xchange.bitget;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.mazi.rescu.HttpStatusExceptionSupport;

public class BitgetException extends HttpStatusExceptionSupport {

    private String msg;
    private String code;

    public BitgetException(@JsonProperty("msg") String msg,
                           @JsonProperty("code") String code) {
        super();
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
