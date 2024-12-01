package meryem.orderdetailsservice.Controller;

import meryem.orderdetailsservice.Entity.OrderDetails;
import meryem.orderdetailsservice.Models.Order;
import meryem.orderdetailsservice.Repository.OrderDetailsRepo;
import meryem.orderdetailsservice.clients.OrderRestClient;
import meryem.orderdetailsservice.clients.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailsController {
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    @Autowired
    OrderRestClient orderRestClient;
    @Autowired
    ProductRestClient productRestClient;
    @GetMapping("/OrderDetails")
    public List<OrderDetails> getOrderDetails() {
        List<OrderDetails> orderDetailsList = orderDetailsRepo.findAll();
        orderDetailsList.forEach(orderDetails -> {
            orderDetails.setOrderDesc(orderRestClient.getOrderById(orderDetails.getOrderId()));
            orderDetails.setProductDesc(productRestClient.getProductById(orderDetails.getProductId()));
        });
        return orderDetailsList;
    }
    @GetMapping("/OrderDetails/Order/{OrderId}")
    public List<OrderDetails> getOrderDetailsByOrderId(@PathVariable Integer OrderId){
        return orderDetailsRepo.findAllByOrderId(OrderId);
    }

    @GetMapping("/OrderDetails/{ProductId}")
    public List<OrderDetails> getProductOrderedInOrdersByProductId(@PathVariable Integer ProductId){
        return orderDetailsRepo.findAllByProductId(ProductId);
    }

}
