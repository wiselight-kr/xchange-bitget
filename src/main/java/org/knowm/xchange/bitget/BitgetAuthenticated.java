package org.knowm.xchange.bitget;

import org.knowm.xchange.bitget.dto.BitgetResponse;
import org.knowm.xchange.bitget.dto.trade.BitgetOrderData;
import org.knowm.xchange.bitget.dto.trade.BitgetOrderRequestPayload;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public interface BitgetAuthenticated extends Bitget {

    @POST
    @Path("/spot/v1/trade/orders")
    @Consumes(MediaType.APPLICATION_JSON)
    BitgetResponse<BitgetOrderData> placeOrder(@HeaderParam("ACCESS-KEY") String apiKey,
                                               @HeaderParam("ACCESS-SIGN") ParamsDigest signature,
                                               @HeaderParam("ACCESS-PASSPHRASE") String passphrase,
                                               @HeaderParam("ACCESS-TIMESTAMP") SynchronizedValueFactory<Long> timestamp,
                                               BitgetOrderRequestPayload orderRequestPayload) throws IOException, BitgetException;
}
