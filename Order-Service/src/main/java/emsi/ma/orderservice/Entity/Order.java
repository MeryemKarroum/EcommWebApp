package emsi.ma.orderservice.Entity;

import emsi.ma.orderservice.models.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderId;
    Date orderDate;
    Integer clientId;
    @Transient
    List<Product> product;
    @ElementCollection
    List<Integer> ProductIds;
}
