package emsi.ma.orderservice.Service;

import emsi.ma.orderservice.Clients.ProductRestClient;
import emsi.ma.orderservice.Entity.Order;
import emsi.ma.orderservice.Repository.OrderRepo;
import emsi.ma.orderservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRestClient productRestClient;
    @Override
    public Page<Order> getOrders(Pageable p) {

        Page<Order> orderList =orderRepo.findAll(p);
        orderList.forEach(order -> {
            List<Product> products = order.getProductIds().stream()
                    .map(productId -> productRestClient.getProductById(productId))
                    .collect(Collectors.toList());
            order.setProduct(products);
        });
          return orderList;
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepo.findById(id).orElseThrow(()->new NoSuchElementException("Commande n'existe pas"+id));
    }


    @Override
    public void createOrder(Order order) {
        orderRepo.save(order);
    }

    @Override
    public String deleteOrder(Integer id) {
        if(orderRepo.findById(id).isPresent()){
            orderRepo.deleteById(id);
            return String.format("la commande %d est supprimé",id);
        }
          return String.format("la commande %d n'existe pas",id);

    }

    @Override
    public String updateOrder(Integer id, Order order) {
        Optional<Order> order1=orderRepo.findById(id);
        if(order1.isPresent()){
            Order ord=order1.get();
            ord.setOrderId(order.getOrderId());
            ord.setOrderDate(order.getOrderDate());
            ord.setProductIds(order.getProductIds());
            ord.setClientId(order.getClientId());
            return String.format("la commande %d est modifié",id);
        }
        return String.format("la commande %d n'existe pas",id);
    }

    @Override
    public List<Order> getOrderByDate(Date date) {
        return orderRepo.findByOrderDate(date);

    }
}
