package emsi.ma.productservice.Repository;

import emsi.ma.productservice.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
public List<Product> findAllByCategoryId(Integer category);
   public  List<Product> findAllByName(String name);

   public List<Product> findAllByPriceLessThan(Double price);


}
