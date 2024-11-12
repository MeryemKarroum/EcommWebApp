package emsi.ma.orderservice.Service;

import emsi.ma.orderservice.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface OrderService {
public Page<Order> getOrders(Pageable pageable);
public Order getOrderById(Integer id);
public void createOrder(Order order);
public String deleteOrder(Integer id);
public String updateOrder(Integer id,Order order);

public List<Order> getOrderByDate(Date date);

}
