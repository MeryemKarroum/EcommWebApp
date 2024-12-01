package meryem.orderdetailsservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meryem.orderdetailsservice.Models.Order;
import meryem.orderdetailsservice.Models.Product;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Integer Id;
    Integer productId;
    Integer orderId;
    Integer quantity;
    @Transient
    Order orderDesc;
    @Transient
    Product ProductDesc;
}
