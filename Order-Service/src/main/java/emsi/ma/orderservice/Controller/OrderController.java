package emsi.ma.orderservice.Controller;

import emsi.ma.orderservice.Entity.Order;
import emsi.ma.orderservice.OrderServiceApplication;
import emsi.ma.orderservice.Service.OrderService;
import emsi.ma.orderservice.Service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;
    @GetMapping
    public Page<Order> getOrders(@RequestParam(defaultValue="0")int page, @RequestParam(defaultValue="10")int size){
        Pageable p= PageRequest.of(page,size);
        return  orderService.getOrders(p);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id){
        return orderService.getOrderById(id);    }

    @PostMapping("/add")
    public void addOrder(Order order){
        orderService.createOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id){
        return orderService.deleteOrder(id);
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable Integer id,@RequestBody Order order){
        return orderService.updateOrder(id,order);
    }

    @GetMapping("/Date")
    public List<Order> getOrderByDate(@RequestBody Date date){

        return orderService.getOrderByDate(date);
    }
}
