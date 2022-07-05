package resources;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cxf.orders.*;

@HandlerChain(file = "handlers.xml")
@WebService(serviceName = "CustomerOrdersResource", endpointInterface = "cxf.orders.CustomerOrdersPortType")
public class CustomerOrdersResource implements CustomerOrdersPortType {

    private Map<BigInteger, List<Order>> customerOrders = new HashMap<>();
    private int currentId;

    public CustomerOrdersResource() {
        List<Order> orders = new ArrayList<>();
        Product product = new Product();
        Order order = new Order();

        product.setQuantity(BigInteger.valueOf(3));
        product.setDescription("Samsung");
        product.setId("1");

        order.setId(BigInteger.valueOf(1));
        order.getProduct().add(product);
        orders.add(order);

        customerOrders.put(BigInteger.valueOf(++currentId), orders);
    }

    @Override
    public GetOrdersResponse getOrders(GetOrdersRequest request) {
        BigInteger customerId = request.getCustomerId();
        List<Order> orders = customerOrders.get(customerId);

        GetOrdersResponse response = new GetOrdersResponse();
        response.getOrder().addAll(orders);

        return response;
    }

    @Override
    public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
        BigInteger customerId = request.getCustomerId();
        Order order = request.getOrder();
        List<Order> orders = customerOrders.get(customerId);
        orders.add(order);

        CreateOrdersResponse response = new CreateOrdersResponse();
        response.setResult(true);

        return response;
    }
}