package emsi.ma.orderservice.Repository;

import emsi.ma.orderservice.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository

public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order> findByOrderDate(Date date);

}
