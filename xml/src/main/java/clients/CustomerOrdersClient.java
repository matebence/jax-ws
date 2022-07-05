package clients;

import org.apache.log4j.ConsoleAppender;
import java.net.MalformedURLException;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Logger;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import java.math.BigInteger;
import java.util.List;
import java.net.URL;

import cxf.orders.*;

public class CustomerOrdersClient {

    private final static String ENDPOINT = "http://localhost:8080/xml/CustomerOrdersResource?wsdl";

    private final static Logger logger = Logger.getLogger(CustomerOrdersClient.class);

    static {
        Logger.getRootLogger().setLevel(Level.ALL);
        Layout layout = new PatternLayout("%d [%t] %-5p %c %x - %m%n");
        Logger.getRootLogger().addAppender(new ConsoleAppender(layout));
    }

    public static void main(String[] args) throws MalformedURLException {
        CustomerOrdersResource service = new CustomerOrdersResource(new URL(ENDPOINT));
        CustomerOrdersPortType customerOrdersWsImplPort = service.getCustomerOrdersResourcePort();

        GetOrdersRequest request = new GetOrdersRequest();
        request.setCustomerId(BigInteger.valueOf(1));
        GetOrdersResponse response = customerOrdersWsImplPort.getOrders(request);
        List<Order> orders = response.getOrder();

        logger.info("Number of orders for the customer are: " + orders.size());
    }
}
