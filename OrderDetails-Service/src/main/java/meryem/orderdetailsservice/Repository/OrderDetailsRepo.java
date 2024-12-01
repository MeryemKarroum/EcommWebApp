package meryem.orderdetailsservice.Repository;

import meryem.orderdetailsservice.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {
   List<OrderDetails> findAllByOrderId(Integer OrderId);

   List<OrderDetails> findAllByProductId(Integer ProductId);
}
