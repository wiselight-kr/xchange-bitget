package org.knowm.xchange.bitget.dto;

public class BitgetResponse<T> {

    private String code;
    private String msg;

    private Long requestTime;
    private T data;

    public BitgetResponse() {

    }

    public BitgetResponse(String code, String msg, Long requestTime, T data) {
        this.code = code;
        this.msg = msg;
        this.requestTime = requestTime;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public T getData() {
        return data;
    }
}
