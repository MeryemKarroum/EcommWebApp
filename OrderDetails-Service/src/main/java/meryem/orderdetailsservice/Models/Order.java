package meryem.orderdetailsservice.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Order {
    public Date orderDate;
    public Integer clientId;

}
