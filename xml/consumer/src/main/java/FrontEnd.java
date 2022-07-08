import org.apache.cxf.ws.security.SecurityConstants;
import security.AuthenticationCallback;
import java.net.MalformedURLException;
import javax.xml.ws.BindingProvider;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.net.URL;

import resources.orders.*;
import org.apache.log4j.*;

public class FrontEnd {

    private final static String ENDPOINT = "https://localhost:8443/producer/CustomerOrdersResource?wsdl";

    private final static Logger logger = Logger.getLogger(FrontEnd.class);

    static {
        Logger.getRootLogger().setLevel(Level.ALL);
        Layout layout = new PatternLayout("%d [%t] %-5p %c %x - %m%n");
        Logger.getRootLogger().addAppender(new ConsoleAppender(layout));
    }

    public static void main(String[] args) throws MalformedURLException {
        CustomerOrdersResource service = new CustomerOrdersResource(new URL(ENDPOINT));
        CustomerOrdersPortType customerOrdersWsImplPort = service.getCustomerOrdersResourcePort();

        Map<String, Object> ctx = ((BindingProvider)customerOrdersWsImplPort).getRequestContext();
        ctx.put(SecurityConstants.CALLBACK_HANDLER, new AuthenticationCallback());

        //Username and password authentication
        //ctx.put(SecurityConstants.PASSWORD, AuthenticationCallback.database.get(AuthenticationCallback.PASSWORD_USERNAME));
        //ctx.put(SecurityConstants.USERNAME, AuthenticationCallback.SIGNED_ENCRYPTED_CONSUMER_USERNAME);

        ctx.put(SecurityConstants.SIGNATURE_PROPERTIES, Thread.currentThread().getContextClassLoader().getResource("application.properties"));
        ctx.put(SecurityConstants.ENCRYPT_PROPERTIES, Thread.currentThread().getContextClassLoader().getResource("application.properties"));
        ctx.put(SecurityConstants.SIGNATURE_USERNAME, AuthenticationCallback.SIGNED_ENCRYPTED_CONSUMER_USERNAME);
        ctx.put(SecurityConstants.ENCRYPT_USERNAME, AuthenticationCallback.SIGNED_ENCRYPTED_PRODUCER_USERNAME);

        GetOrdersRequest request = new GetOrdersRequest();
        request.setCustomerId(BigInteger.valueOf(1));
        GetOrdersResponse response = customerOrdersWsImplPort.getOrders(request);
        List<Order> orders = response.getOrder();

        logger.info("Number of orders for the customer are: " + orders.size());
    }
}
