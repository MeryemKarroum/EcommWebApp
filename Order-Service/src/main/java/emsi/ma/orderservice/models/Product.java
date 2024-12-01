package emsi.ma.orderservice.models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Product {
    public Integer id;
    public String name;
    public Double price;
    public String category;
}
