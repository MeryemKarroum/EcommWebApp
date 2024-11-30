package emsi.ma.orderservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class product {
    @Id
    public Integer id;
    public String name;
    public Double price;
    public String category;
}
