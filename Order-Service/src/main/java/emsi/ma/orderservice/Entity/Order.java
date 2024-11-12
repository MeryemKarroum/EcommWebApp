package emsi.ma.orderservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderId;
    Date orderDate;
    @ManyToMany
    List<productDTO> orderItemsDTOList;
}
