package resources;

import entities.PaymentResponse;
import entities.PaymentRequest;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebParam;

@WebService(name = "PaymentResource")
public class PaymentResource {

    //if we access this url http://localhost:8080/xml/PaymentResource?wsdl and save the wsdl file
    //then we could again use the cxf plugin

    private final static List<PaymentRequest> paymentRequests = new ArrayList<>();

    private Logger logger = Logger.getLogger(PaymentResource.class);

    @WebMethod
    public @WebResult(name = "response")
    PaymentResponse processPayment(@WebParam(name = "paymentProcessorRequest") PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();
        try {
            paymentRequests.add(paymentRequest);
            paymentResponse.setResult(true);
        } catch (Exception e) {
            logger.error(e);
            paymentResponse.setResult(false);
        }

        return paymentResponse;
    }
}
