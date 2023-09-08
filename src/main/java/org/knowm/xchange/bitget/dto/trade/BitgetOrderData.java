package org.knowm.xchange.bitget.dto.trade;

public class BitgetOrderData {

    private String orderId;
    private String clientOrderId;

    public BitgetOrderData() {

    }

    public BitgetOrderData(String orderId, String clientOrderId) {
        this.orderId = orderId;
        this.clientOrderId = clientOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }
}
