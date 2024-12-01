package meryem.orderdetailsservice.clients;

import meryem.orderdetailsservice.Models.Order;
import meryem.orderdetailsservice.Models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="ORDER-SERVICE")
public interface OrderRestClient {
    @GetMapping("/orders/{id}")
    Order getOrderById(@PathVariable Integer id);
    @GetMapping
    List<Order> getAllOrderss();
}
